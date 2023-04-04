package mccc.gamemode.hungergames.stages;


import com.connorlinfoot.titleapi.TitleAPI;
import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Countdown extends GamemodeStage {

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  public void tickSecond() {
    super.tickSecond();
    updateScreenCountdown(super.getSecondsLeft());
  }

  public void updateScreenCountdown(int secondsRemaining) {
    final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

    // determining the color of the title based on how much time is left

    if (secondsRemaining > 10 && secondsRemaining % 5 != 0)
      return;

    if (secondsRemaining == 0)
      return;

    ChatColor titleColor;
    if (secondsRemaining > 5) {
      titleColor = ChatColor.YELLOW;
    } else if (secondsRemaining > 3) {
      titleColor = ChatColor.GOLD;
    } else if (secondsRemaining > 1) {
      titleColor = ChatColor.RED;
    } else {
      titleColor = ChatColor.DARK_RED;
    }

    String currentTitle = titleColor + "" + secondsRemaining;
    String subTitle = "";

    if (secondsRemaining % 5 == 0 && secondsRemaining >= 10) {
      subTitle = "секунд до начала битвы";
    }

    for (Player player : onlinePlayers) {
      // displaying the countdown on the screen
      TitleAPI.sendTitle(player, 3, 14, 3, currentTitle, subTitle);
      // playing a note

      int tone = 10 - secondsRemaining;

      if (tone >= 0 && tone <= 24)
        player.playNote(player.getLocation(), Instrument.PIANO, new Note(tone));
      else
        player.playSound(player, Sound.UI_BUTTON_CLICK, 1, 1);
    }
  }

  @Override
  public void load() {
    super.load();

    plugin.storage.getSpawnLocations();
    buildCage(Material.BARRIER);
    spawnPlayers();

    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.ADVENTURE);
  }

  public void buildCage(Material fill) {
    for (Location spawnLocation : plugin.storage.spawnLocations) {
      Location anchor = spawnLocation.clone().add(0, 1, 0);

      int diameter = plugin.getConfig().getInt("cageDiameter");
      int radius = diameter / 2;

      for (int i = -radius - 1; i <= radius + 1; i++) {
        anchor.clone().add(i, 0, -radius - 1).getBlock().setType(fill);
        anchor.clone().add(i, 0, radius + 1).getBlock().setType(fill);
        anchor.clone().add(-radius - 1, 0, i).getBlock().setType(fill);
        anchor.clone().add(radius + 1, 0, i).getBlock().setType(fill);
      }
    }
  }

  public void spawnPlayers() {

    Location defaultLocation = plugin.getConfig().getLocation("border.location");

    if (defaultLocation != null)
      for (Player player : Bukkit.getOnlinePlayers())
        player.teleport(defaultLocation);


    int locationIndex = 0;
    for (Team team : plugin.core.apiManager.teamManager.getTeams().values()) {

      Location teamLocation = plugin.storage.spawnLocations.get(locationIndex).clone();

      for (int i = 0; i < team.players.size(); i++) {
        int offsetX = (int)Math.pow(-1, i / 2);
        int offsetZ = (int)Math.pow(-1, i % 2);

        Location playerLocation = teamLocation.clone().add(offsetX, 0, offsetZ);

        mccc.core.local.data.Player teamPlayer = team.players.get(i);
        Player bukkitPlayer = Bukkit.getPlayer(teamPlayer.nickname);

        if (bukkitPlayer == null)
          plugin.core.offlinePlayerScheduler.scheduledLocation.put(teamPlayer.nickname, playerLocation);

        else
          bukkitPlayer.teleport(playerLocation);
      }

      locationIndex++;
    }

  }

  @Override
  public void unload() {
    buildCage(Material.AIR);
    super.unload();
  }

  @Override
  public String getDisplayName() {
    return "Обратный отсчёт";
  }

  public Countdown(HungerGames plugin_) {
    super(plugin_);
    super.timeLimit = 400;
  }
}

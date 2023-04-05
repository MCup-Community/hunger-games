package mcup.gamemode.hungergames.stages;

import mcup.core.Core;
import mcup.core.local.data.Team;
import mcup.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Countdown extends mcup.core.stages.Countdown {

  @Override
  public void load() {
    super.load();
    plugin.storage.getSpawnLocations();
    buildCage(Material.BARRIER);
    spawnPlayers();
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

        mcup.core.local.data.Player teamPlayer = team.players.get(i);
        Player bukkitPlayer = Bukkit.getPlayer(teamPlayer.nickname);

        if (bukkitPlayer == null)
          core.offlinePlayerScheduler.scheduledLocation.put(teamPlayer.nickname, playerLocation);

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

  protected HungerGames plugin;

  public Countdown(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (HungerGames)plugin_;
  }
}

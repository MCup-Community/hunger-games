package mccc.gamemode.hungergames.stages;


import com.connorlinfoot.titleapi.TitleAPI;
import mccc.core.ApiManager;
import mccc.core.api.PlayerManager;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Collection;

public class Countdown extends GamemodeStage {

  @Override
  public void tick() {
    super.tick();
    // the number updates at the start of each second
    int updatedSecondsRemaining = (timeLimit - timeElapsed) / 20;
    if ((timeLimit - timeElapsed) % 20 > 0) {
      updatedSecondsRemaining++;
    }

    if (updatedSecondsRemaining != secondsRemaining && secondsRemaining > 0) {
      secondsRemaining = updatedSecondsRemaining;
      updateScreenCountdown();
    }

  }

  public void updateScreenCountdown() {
    final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

    // determining the color of the title based on how much time is left
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

    for (Player player : onlinePlayers) {
      // displaying the countdown on the screen
      TitleAPI.sendTitle(player, 7, 5, 7, currentTitle, "");
      player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1f, 1f);
    }
  }

  @Override
  public void load() {
    super.load();
    System.out.println("FFUUUCK");
    // insert player spawning
    competitorsToAdventure();
  }

  public int timeLimit = 200;

  // The number displayed on screen
  public int secondsRemaining = -1;

  public void competitorsToAdventure() {
    final ArrayList<mccc.core.local.data.Player> competitors = plugin.core.apiManager.playerManager.getPlayers();

    // set every competitor to Adventure mode
    for (mccc.core.local.data.Player competitor : competitors) {
      String competitorName = competitor.nickname;
      org.bukkit.entity.Player competitorObject = org.bukkit.Bukkit.getPlayer(competitorName);
      if (competitorObject == null) {
        plugin.getLogger().warning("Player " + competitorName + " is offline!");
      } else{
        competitorObject.setGameMode(GameMode.ADVENTURE);
      }
    }
  }


  public Countdown(HungerGames plugin_) {
    super(plugin_);
  }
}

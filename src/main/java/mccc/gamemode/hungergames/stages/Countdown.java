package mccc.gamemode.hungergames.stages;


import com.connorlinfoot.titleapi.TitleAPI;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

import java.util.Collection;

public class Countdown extends GamemodeStage {

  @Override
  public void tick() {
    super.tick();
    int updatedSecondsRemaining = (timeLimit - timeElapsed) / 20;

    if (updatedSecondsRemaining != secondsRemaining) {
      secondsRemaining = updatedSecondsRemaining;
      updateScreenCountdown();
    }

  }

  public void updateScreenCountdown() {
    final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

    String currentTitle = ChatColor.YELLOW + "" + secondsRemaining;

    for (Player player : onlinePlayers) {
      TitleAPI.sendTitle(player, 7, 5, 7, currentTitle, "");
      player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1f, 1f);
    }
  }

  @Override
  public void load() {
    super.load();
  }

  // The number displayed on screen
  public int secondsRemaining = -1;


  public Countdown(HungerGames plugin_) {
    super(plugin_);
    super.timeLimit = 200;
  }
}

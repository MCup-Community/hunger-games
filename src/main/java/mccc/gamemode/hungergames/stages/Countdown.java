package mccc.gamemode.hungergames.stages;


import com.connorlinfoot.titleapi.TitleAPI;
import mccc.core.ApiManager;
import mccc.core.api.PlayerManager;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Countdown extends GamemodeStage {

  @Override
  public void tick() {
    super.tick();
    if ((timeLimit - timeElapsed) % 20 == 0) {
      // the number updates at the start of each second
      int updatedSecondsRemaining = (timeLimit - timeElapsed) / 20;
      updateScreenCountdown(updatedSecondsRemaining);
    }
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

    for (Player player : onlinePlayers) {
      // displaying the countdown on the screen
      TitleAPI.sendTitle(player, 7, 5, 7, currentTitle, "");
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
    // TODO: player spawning
    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.ADVENTURE);
  }



  public Countdown(HungerGames plugin_) {
    super(plugin_);
    super.timeLimit = 400;
  }
}

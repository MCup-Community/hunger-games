package mccc.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Ending extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    displayFinalMessage();
    plugin.core.repository.write();
  }

  public void displayFinalMessage() {
    Team winnerTeam = null;
    int aliveTeamsCount = 0;

    for (String teamName : plugin.storage.alivePlayers.keySet()) {
      Team team = plugin.core.apiManager.teamManager.getTeam(teamName);

      if (team == null)
        continue;

      if (plugin.storage.alivePlayers.get(teamName) != 0) {
        winnerTeam = team;
        aliveTeamsCount++;
      }
    }

    String message, subMessage = "";
    if (winnerTeam == null || aliveTeamsCount > 1) {
      message = ChatColor.GOLD + "Ничья?..";
      subMessage = "Победила дружба?";
    }

    else {
      message =
        winnerTeam.color + winnerTeam.name +
          ChatColor.RESET + " побеждают!";

      int scoreAmount = plugin.getConfig().getInt("winScoreValue");
      plugin.core.apiManager.scoreManager.addScoreTeam(winnerTeam.name, scoreAmount, "Грандиозная победа в Голодных играх");
    }

    for (Player player : Bukkit.getOnlinePlayers()) {
      TitleAPI.sendTitle(player, 10, 70, 10, message, subMessage);
      player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 2.0f, 1.0f);
    }
  }

  public Ending(HungerGames plugin_) {
    super(plugin_);
  }
}

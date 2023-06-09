package mcup.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mcup.core.Core;
import mcup.core.local.data.Team;
import mcup.core.stages.GamemodeStage;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class Ending extends GamemodeStage {


  @Override
  public void load() {
    super.load();

    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
    worldBorder.setSize(1e9);

    displayFinalMessage();
    core.repository.write();
  }

  public void displayFinalMessage() {

    Team winnerTeam = null;
    HashSet<String> aliveTeams = new HashSet<>();

    for (Player player : Bukkit.getOnlinePlayers()) {

      if (player.getGameMode() != GameMode.SURVIVAL)
        continue;

      Team playerTeam = core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam != null) {
        aliveTeams.add(playerTeam.name);
        winnerTeam = playerTeam;
      }
    }

    String message, subMessage = "";
    if (winnerTeam == null || aliveTeams.size() > 1) {
      message = ChatColor.GOLD + "Ничья?..";
      subMessage = "Победила дружба?";
    }

    else {
      message =
        winnerTeam.color + winnerTeam.name +
          ChatColor.RESET + " побеждают!";

      int scoreAmount = plugin.getConfig().getInt("winScoreValue");
      core.apiManager.scoreManager.addScoreTeam(winnerTeam.name, scoreAmount, "Грандиозная победа в Голодных играх");
    }

    for (Player player : Bukkit.getOnlinePlayers()) {
      TitleAPI.sendTitle(player, 10, 70, 10, message, subMessage);
      player.playSound(player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 2.0f, 0.7f);
    }
  }

  public Ending(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
  }

}

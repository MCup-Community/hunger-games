package mccc.gamemode.hungergames.stages;

import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class Fight extends GamemodeStage {

  public void initFight() {
    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.SURVIVAL);
  }

  @Override
  public void unload() {
    plugin.storage.fightEnd = true;
    super.unload();
  }

  @Override
  public boolean endCondition() {

    HashSet<String> aliveTeams = new HashSet<>();

    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getGameMode() != GameMode.SURVIVAL)
        continue;

      Team playerTeam = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam != null)
        aliveTeams.add(playerTeam.name);
    }

    return (aliveTeams.size() <= 1);
//    return false;  // for sake of testing
  }

  @Override
  public boolean skipCondition() {
    return plugin.storage.fightEnd;
  }

  @Override
  public String getDisplayName() {
    return "Битва";
  }


  public Fight(HungerGames plugin_) {
    super(plugin_);
    super.timeLimit = 5 * 20 * 60;
  }
}

package mccc.gamemode.hungergames.stages;

import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class Fight extends GamemodeStage {

  public void initFight() {
    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.SURVIVAL);

    for (Player player : Bukkit.getOnlinePlayers()) {
      Team playerTeam = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam == null)
        continue;

      plugin.storage.alivePlayers.put(playerTeam.name, plugin.storage.alivePlayers.getOrDefault(playerTeam.name, 0) + 1);
    }
  }


  public void decrementAlivePlayers(String teamName) {
    if (!plugin.storage.alivePlayers.containsKey(teamName))
      return;

    plugin.storage.alivePlayers.put(teamName, plugin.storage.alivePlayers.getOrDefault(teamName, 0) - 1);
  }

  @Override
  public boolean endCondition() {

    int aliveTeamsCount = 0;
    for (Integer alivePlayersCount : plugin.storage.alivePlayers.values())
      if (alivePlayersCount > 0)
        aliveTeamsCount++;

    //return (aliveTeamsCount <= 1);
    return false;  // for sake of testing
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

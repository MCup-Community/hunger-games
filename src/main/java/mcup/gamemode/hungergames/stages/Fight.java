package mcup.gamemode.hungergames.stages;

import mcup.core.Core;
import mcup.core.local.data.Team;
import mcup.core.stages.GamemodeStage;
import mcup.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class Fight extends GamemodeStage {

  public void initFight() {
    core.apiManager.playerManager.setGlobalGamemode(GameMode.SURVIVAL);
  }

  @Override
  public void unload() {
    super.unload();
  }

  @Override
  public boolean endCondition() {

    HashSet<String> aliveTeams = new HashSet<>();

    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getGameMode() != GameMode.SURVIVAL)
        continue;

      Team playerTeam = core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam != null)
        aliveTeams.add(playerTeam.name);
    }

    if (aliveTeams.size() <= 1)
      plugin.storage.fightEnd = true;


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

  protected HungerGames plugin;


  public Fight(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (HungerGames)plugin_;
    super.timeLimit = 5 * 20 * 60;
  }
}

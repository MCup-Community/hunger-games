package mccc.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class Fight extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    // TODO: Chest loot randomization

    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.SURVIVAL);
    playIntro();

    for (Player player : Bukkit.getOnlinePlayers()) {
      Team playerTeam = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam == null)
        continue;

      alivePlayers.put(playerTeam.name, alivePlayers.getOrDefault(playerTeam.name, 0) + 1);
    }
  }

  public void playIntro() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      // display the stage title and play the sound
      String fightTitle = ChatColor.GOLD + "ДА НАЧНУТСЯ ГОЛОДНЫЕ ИГРЫ!";
      TitleAPI.sendTitle(player, 30, 10, 10, fightTitle, "");
      player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3.0f, 0.8f);
    }
  }


  public void decrementAlivePlayers(String teamName) {
    if (!alivePlayers.containsKey(teamName))
      return;

    alivePlayers.put(teamName, alivePlayers.getOrDefault(teamName, 0) - 1);
  }

  @Override
  public boolean endCondition() {

    int aliveTeamsCount = 0;
    for (Integer alivePlayersCount : alivePlayers.values())
      if (alivePlayersCount > 0)
        aliveTeamsCount++;

    return (aliveTeamsCount <= 1);
  }

  private LinkedHashMap<String, Integer> alivePlayers = new LinkedHashMap<>();

  public Fight(HungerGames plugin_) {
    super(plugin_);
  }
}

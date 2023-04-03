package mccc.gamemode.hungergames.listeners;

import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.HungerGames;
import mccc.gamemode.hungergames.stages.Fight;
import mccc.gamemode.hungergames.stages.Cutscene;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {

    if (plugin.stageManager.getCurrentStage() instanceof Fight) {
      Player player = event.getEntity().getPlayer();

      if (player == null)
        return;

      player.getWorld().strikeLightningEffect(player.getLocation());

      Team playerTeam = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam == null)
        return;

      ((Fight)plugin.stageManager.getCurrentStage()).decrementAlivePlayers(playerTeam.name);

      Player killer = player.getKiller();

      if (killer == null) {
        return;
      }

      int killScoreValue = plugin.getConfig().getInt("killScoreValue");

      plugin.core.apiManager.scoreManager.addScorePlayer(killer.getName(), killScoreValue, "Kill");

    }

  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    if (plugin.stageManager.getCurrentStage() instanceof Cutscene) {
      event.setCancelled(true);
    }
  }
  private final HungerGames plugin;

  public PlayerListener(HungerGames plugin_) {
    plugin = plugin_;
  }
}

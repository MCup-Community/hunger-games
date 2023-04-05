package mcup.gamemode.hungergames.listeners;

import mcup.core.local.data.Team;
import mcup.gamemode.hungergames.HungerGames;
import mcup.gamemode.hungergames.stages.Fight;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener {

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {

    if (plugin.core.stageManager.getCurrentStage() instanceof Fight) {
      Player player = event.getEntity().getPlayer();

      if (player == null)
        return;

      player.getWorld().strikeLightningEffect(player.getLocation());

      Team playerTeam = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

      if (playerTeam == null)
        return;

      player.setGameMode(GameMode.SPECTATOR);

      Player killer = player.getKiller();

      if (killer == null)
        return;


      int killScoreValue = plugin.getConfig().getInt("killScoreValue");

      plugin.core.apiManager.scoreManager.addScorePlayer(killer.getName(), killScoreValue, "Kill");

    }
  }

  private final HungerGames plugin;

  public PlayerListener(HungerGames plugin_) {
    plugin = plugin_;
  }
}

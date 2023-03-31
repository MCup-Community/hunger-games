package mccc.gamemode.hungergames.listeners;

import mccc.core.local.data.Team;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerListener implements Listener {

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getEntity().getPlayer();

    Team player_team = plugin.core.apiManager.teamManager.getTeamByPlayer(player.getName());

    plugin.core.apiManager.scoreManager.addScore(player_team.name, 666, "Thanks for dying!", null);
  }

  private final HungerGames plugin;

  public PlayerListener(HungerGames plugin_) {
    plugin = plugin_;
  }
}

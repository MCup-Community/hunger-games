package mccc.gamemode.hungergames.listeners;

import mccc.gamemode.hungergames.HungerGames;
import mccc.gamemode.hungergames.events.GamemodeStageEndEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GamemodeListener implements Listener {

  @EventHandler
  public void onStageEnd(GamemodeStageEndEvent e) {
    plugin.stageManager.switchToNextStage();
  }


  private final HungerGames plugin;

  public GamemodeListener(HungerGames plugin_) {
    plugin = plugin_;
  }
}

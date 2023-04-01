package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.events.GamemodeStageEndEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GamemodeStage {
  public void load() {
    GamemodeStage instance = this;
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (endCondition() || (timeLimit != -1 && timeElapsed >= timeLimit)) {
          plugin.getServer().getPluginManager().callEvent(new GamemodeStageEndEvent(instance));
        }

        timeElapsed++;
      }
    }, 0, 50);
  }

  public void unload() {
    timer.cancel();
  }

  public int timeLimit = -1;

  public int timeElapsed = 0;

  public boolean endCondition() {
    return false;
  }
  private final HungerGames plugin;

  private Timer timer = new Timer();

  public GamemodeStage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

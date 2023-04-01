package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.events.GamemodeStageEndEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GamemodeStage {

  public void load() {
    GamemodeStage instance = this;
    tickTimer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        tick();
      }
    }, 0, 50);
  }

  public void unload() {
    tickTimer.cancel();
  }

  public void tick() {
    if (endCondition() || (timeLimit != -1 && timeElapsed >= timeLimit)) {
      plugin.getServer().getPluginManager().callEvent(new GamemodeStageEndEvent(this));
    }

    timeElapsed++;
  }


  public int timeLimit = -1;

  public int timeElapsed = 0;

  public boolean endCondition() {
    return false;
  }
  protected final HungerGames plugin;

  private final Timer tickTimer = new Timer();

  public GamemodeStage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.events.GamemodeStageEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Timer;
import java.util.TimerTask;

public class GamemodeStage {

  public void load() {
    tickTimer = new BukkitRunnable() {
      public void run() {
        tick();
      }
    }.runTaskTimer(plugin, 0, 1);
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

  public BukkitTask tickTimer;

  public boolean endCondition() {
    return false;
  }
  protected final HungerGames plugin;


  public GamemodeStage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

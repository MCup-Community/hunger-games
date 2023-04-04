package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.events.GamemodeStageEndEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class GamemodeStage {

  public void load() {
    tickTimer = new BukkitRunnable() {
      public void run() {
        tick();
      }
    }.runTaskTimer(plugin, 0, 1);
  }

  public void unload() {
    if (bossBarEnabled)
      bossBarAudience.hideBossBar(bossBarInstance);

    tickTimer.cancel();
  }

  public void tick() {
    if (endCondition() || (timeLimit != -1 && (timeElapsed + 1) >= timeLimit)) {
      plugin.getServer().getPluginManager().callEvent(new GamemodeStageEndEvent(this));
    }

    if (timeElapsed % 20 == 0) {
      tickSecond();
    }

    timeElapsed++;
  }

  public void tickSecond() {
    if (bossBarEnabled) {
      if (timeLimit != -1)
        bossBarInstance.progress((float)getSecondsLeft() / (float)getSecondsTotal());
      bossBarInstance.name(Component.text(getBossBarCountdownLabelPrefix()));
      bossBarAudience.showBossBar(bossBarInstance);
    }
  }


  public int timeLimit = -1;

  public int timeElapsed = 0;

  public BukkitTask tickTimer;

  public int getSecondsLeft() {
    return (timeLimit - timeElapsed) / 20;
  }

  public int getSecondsElapsed() {
    return timeElapsed / 20;
  }

  public int getSecondsTotal() {
    return timeLimit / 20;
  }

  public boolean endCondition() {
    return false;
  }
  protected final HungerGames plugin;

  protected boolean bossBarEnabled = false;
  protected Audience bossBarAudience;
  protected BossBar bossBarInstance;

  protected String bossBarCountdownLabelPrefix = "До конца фазы: ";
  protected String bossBarCountdownLabelSuffix = "";

  protected String getBossBarCountdownLabelPrefix() {
    return bossBarCountdownLabelPrefix + getSecondsLeft() + bossBarCountdownLabelSuffix;
  }

  protected void initBossBarCountdown() {

    bossBarAudience = plugin.adventure.players();
    bossBarInstance = BossBar.bossBar(Component.text(getBossBarCountdownLabelPrefix()), 1, BossBar.Color.RED, BossBar.Overlay.NOTCHED_20);

    bossBarAudience.showBossBar(bossBarInstance);

    bossBarEnabled = true;
  }

  public String getDisplayName() {
    return "None";
  }


  public GamemodeStage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

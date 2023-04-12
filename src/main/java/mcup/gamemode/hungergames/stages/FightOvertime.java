package mcup.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mcup.core.Core;
import mcup.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FightOvertime extends Fight {

  @Override
  public void load() {
    super.load();

    initBossBarCountdown();
    showTitle();
    initBorder();
  }


  public void initBorder() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    int worldBorderShrunkD = plugin.getConfig().getInt("border.shrunkDiameter");
    worldBorder.setSize(worldBorderShrunkD);
  }

  public void showTitle() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      String title = ChatColor.RED + "" + ChatColor.BOLD + "OVERTIME";
      TitleAPI.sendTitle(player, 10, 30, 10, title, "");
      player.playSound(player, Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);
    }
  }

  @Override
  protected String getBossBarCountdownLabel() {
    String title = ChatColor.BOLD + "OVERTIME";

    if (getSecondsElapsed() % 2 == 0)
      title = ChatColor.RED + title;
    else
      title = ChatColor.DARK_RED + title;

    return title;
  }

  protected HungerGames plugin;

  public FightOvertime(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (HungerGames) plugin_;
    timeLimit = 1 * 60 * 20;
  }
}

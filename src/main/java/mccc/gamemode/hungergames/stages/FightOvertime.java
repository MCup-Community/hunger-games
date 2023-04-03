package mccc.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class FightOvertime extends Fight {

  @Override
  public void load() {
    super.load();

    super.initBossBarCountdown();
    showTitle();
    initBorder();
  }

  @Override
  public void unload() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
    worldBorder.setSize(1e9);
    super.unload();
  }

  public void initBorder() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    int worldBorderShrunkD = plugin.getConfig().getInt("border.shrunkDiameter");
    worldBorder.setSize(worldBorderShrunkD);
  }

  public void showTitle() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      String title = ChatColor.RED + "" + ChatColor.BOLD + "OVERTIME";
      TitleAPI.sendTitle(player, 10, 30, 10, title);
      player.playSound(player, Sound.ENTITY_WITHER_DEATH, 1.0f, 1.0f);
    }
  }

  @Override
  protected String getBossBarCountdownLabelPrefix() {
    String title = ChatColor.BOLD + "OVERTIME";

    if (getSecondsElapsed() % 2 == 0)
      title = ChatColor.RED + title;
    else
      title = ChatColor.DARK_RED + title;

    return title;
  }

  public FightOvertime(HungerGames plugin_) {
    super(plugin_);
    timeLimit = 1 * 60 * 20;
  }
}

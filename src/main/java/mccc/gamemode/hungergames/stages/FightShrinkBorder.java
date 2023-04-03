package mccc.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class FightShrinkBorder extends Fight {

  @Override
  public void load() {
    super.load();

    super.initBossBarCountdown();
    showTitle();
    shrinkBorder();
  }

  public void showTitle() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      String title = ChatColor.GOLD +  "Граница сужается!";
      TitleAPI.sendTitle(player, 10, 30, 10, title);
      player.playSound(player, Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
    }
  }

  public void shrinkBorder() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    int worldBorderShrunkD = plugin.getConfig().getInt("border.shrunkDiameter");
    worldBorder.setSize(worldBorderShrunkD, getSecondsTotal());
  }

  public FightShrinkBorder(HungerGames plugin_) {
    super(plugin_);
    timeLimit = 1 * 60 * 20;
    bossBarCountdownLabelPrefix = "До конца битвы ";
  }
}

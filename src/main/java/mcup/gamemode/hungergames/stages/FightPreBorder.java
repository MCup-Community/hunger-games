package mcup.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mcup.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FightPreBorder extends Fight {

  @Override
  public void load() {
    initFight();
    super.load();

    // TODO: Chest loot randomization
    initBossBarCountdown();
    initBorder();
    playIntro();
  }


  public void initBorder() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    Location worldBorderLocation = plugin.getConfig().getLocation("border.location");
    int worldBorderD = plugin.getConfig().getInt("border.diameter");

    worldBorder.setCenter(worldBorderLocation);
    worldBorder.setSize(worldBorderD);
  }
  public void playIntro() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      String fightTitle = plugin.getConfig().getString("fightBeginTitle");
      TitleAPI.sendTitle(player, 30, 10, 10, fightTitle, "");
      player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3.0f, 0.9f);
    }
  }


  public FightPreBorder(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    bossBarCountdownLabelPrefix = "Сужение границы через: ";
    timeLimit = 2 * 60 * 20;
  }
}

package mccc.gamemode.hungergames.stages;

import com.connorlinfoot.titleapi.TitleAPI;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class FightPreBorder extends Fight{

  @Override
  public void load() {
    super.load();

    // TODO: Chest loot randomization
    super.initBossBarCountdown();
    initFight();
    initBorder();
    playIntro();
  }


  public void initBorder() {
    WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();

    int worldBorderX = plugin.getConfig().getInt("border.x");
    int worldBorderY = plugin.getConfig().getInt("border.y");
    int worldBorderD = plugin.getConfig().getInt("border.diameter");

    worldBorder.setCenter(worldBorderX, worldBorderY);
    worldBorder.setSize(worldBorderD);
  }
  public void playIntro() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      String fightTitle = plugin.getConfig().getString("fightBeginTitle");
      TitleAPI.sendTitle(player, 30, 10, 10, fightTitle, "");
      player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3.0f, 0.9f);
    }
  }


  public FightPreBorder(HungerGames plugin_) {
    super(plugin_);
    bossBarCountdownLabelPrefix = "Сужение границы через ";
    timeLimit = 2 * 60 * 20;
  }
}

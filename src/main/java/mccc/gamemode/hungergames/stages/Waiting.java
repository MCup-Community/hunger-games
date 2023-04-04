package mccc.gamemode.hungergames.stages;

import mccc.core.local.data.Player;
import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;

public class Waiting extends GamemodeStage {

  @Override
  public String getDisplayName() {
    return "Ожидание";
  }

  @Override
  public void load() {
    super.load();

    initBossBarCountdown();
    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.ADVENTURE);
  }

  @Override
  protected String getBossBarCountdownLabelPrefix() {
    StringBuilder label = new StringBuilder("Ожидание игроков");
    for (int j = 0; j < getSecondsElapsed() % 3 + 1; j++)
      label.append(" .");

    return label.toString();
  }

  @Override
  public boolean endCondition() {
    for (Player player : plugin.core.apiManager.playerManager.getPlayers()) {
      org.bukkit.entity.Player bukkitPlayer = Bukkit.getPlayer(player.nickname);

      if (bukkitPlayer == null || !bukkitPlayer.isOnline())
        return false;
    }

    return true;
  }


  public Waiting(HungerGames plugin_) {
    super(plugin_);
  }
}

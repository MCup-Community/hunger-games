package mcup.gamemode.hungergames.stages;

import mcup.core.Core;
import mcup.gamemode.hungergames.HungerGames;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;


public class Countdown extends mcup.core.stages.Countdown {

  @Override
  public void load() {
    super.load();
    getSpawnLocations();
    buildCage(Material.BARRIER);
    core.apiManager.playerManager.clearPlayersInventory();
    spawnPlayers();
  }

  @Override
  public void unload() {
    buildCage(Material.AIR);
    super.unload();
  }

  protected HungerGames plugin;

  public Countdown(Core core_, JavaPlugin plugin_) {
    super(core_, plugin_);
    plugin = (HungerGames)plugin_;
  }
}

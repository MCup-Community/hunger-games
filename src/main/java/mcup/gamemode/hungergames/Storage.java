package mcup.gamemode.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.util.*;

public class Storage {

  public ArrayList<Location> spawnLocations = new ArrayList<>();

  public boolean fightEnd = false;

  public void resetStorage() {
    spawnLocations.clear();
    fightEnd = false;
  }

  public void getSpawnLocations() {
    spawnLocations.clear();

    for (int i = 0; i < plugin.core.apiManager.teamManager.getTeams().size(); i++) {
      Location location = plugin.getConfig().getLocation("spawn." + i + ".location");
      spawnLocations.add(location);
    }

    Collections.shuffle(spawnLocations);
  }


  private final HungerGames plugin;
  public Storage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

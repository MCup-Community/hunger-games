package mcup.gamemode.hungergames;

import org.bukkit.Location;
import java.util.*;

public class Storage {

  public boolean fightEnd = false;

  public void resetStorage() {
    fightEnd = false;
  }

  private final HungerGames plugin;
  public Storage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

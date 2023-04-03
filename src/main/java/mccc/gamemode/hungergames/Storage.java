package mccc.gamemode.hungergames;

import java.util.LinkedHashMap;

public class Storage {


  public LinkedHashMap<String, Integer> alivePlayers = new LinkedHashMap<>();
  private HungerGames plugin;
  public Storage(HungerGames plugin_) {
    plugin = plugin_;
  }
}

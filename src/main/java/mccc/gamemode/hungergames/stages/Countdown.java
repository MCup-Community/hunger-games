package mccc.gamemode.hungergames.stages;


import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;

public class Countdown extends GamemodeStage {

  @Override
  public void load() {
    super.load();
    System.out.println("FFUUUCK");
  }

  public Countdown(HungerGames plugin_) {
    super(plugin_);
  }
}

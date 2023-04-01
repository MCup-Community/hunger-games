package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.stages.Countdown;

import java.util.ArrayList;

public class StageManager {

  public ArrayList<GamemodeStage> stages = new ArrayList<>();
  public int currentStageIndex = 0;

  public void switchToNextStage() {
    stages.get(currentStageIndex).unload();



    currentStageIndex++;

    stages.get(currentStageIndex).load();
  }


  private final HungerGames plugin;
  public StageManager(HungerGames plugin_) {
    plugin = plugin_;

    stages.add(new Countdown(plugin));
  }
}

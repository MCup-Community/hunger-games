package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.stages.Countdown;
import mccc.gamemode.hungergames.stages.Fight;

import java.util.ArrayList;

public class StageManager {

  public ArrayList<GamemodeStage> stages = new ArrayList<>();
  public int currentStageIndex = 0;

  public void startSequence() {
    currentStageIndex = 0;
    stages.get(currentStageIndex).load();
  }

  public void switchToNextStage() {
    stages.get(currentStageIndex).unload();

    currentStageIndex++;

    stages.get(currentStageIndex).load();
  }

  public GamemodeStage getCurrentStage() {
    return stages.get(currentStageIndex);
  }


  private final HungerGames plugin;
  public StageManager(HungerGames plugin_) {
    plugin = plugin_;

    stages.add(new Countdown(plugin));
    stages.add(new Fight(plugin));
  }
}

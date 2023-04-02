package mccc.gamemode.hungergames;

import mccc.gamemode.hungergames.stages.Countdown;
import mccc.gamemode.hungergames.stages.Fight;
import mccc.gamemode.hungergames.stages.Waiting;

import java.util.ArrayList;

public class StageManager {

  public ArrayList<GamemodeStage> stages = new ArrayList<>();
  public int currentStageIndex = 0;

  public void startSequence() {
    currentStageIndex = 0;
    fillSequence();

    stages.get(currentStageIndex).load();
  }

  public void switchToNextStage() {
    stages.get(currentStageIndex).unload();

    currentStageIndex++;

    if (currentStageIndex < stages.size())
      stages.get(currentStageIndex).load();
  }

  public void switchToPreviousStage() {
    stages.get(currentStageIndex).unload();

    currentStageIndex--;

    if (currentStageIndex >= 0)
      stages.get(currentStageIndex).load();
  }

  public void terminateSequence() {
    if (currentStageIndex < 0 || currentStageIndex >= stages.size())
      return;

    stages.get(currentStageIndex).unload();
  }

  public void restartSequence() {
    terminateSequence();
    startSequence();
  }

  public void fillSequence() {
    stages.clear();

    stages.add(new Waiting(plugin));
    stages.add(new Countdown(plugin));
    stages.add(new Fight(plugin));
  }

  public GamemodeStage getCurrentStage() {
    return stages.get(currentStageIndex);
  }


  private final HungerGames plugin;
  public StageManager(HungerGames plugin_) {
    plugin = plugin_;
    fillSequence();
  }
}

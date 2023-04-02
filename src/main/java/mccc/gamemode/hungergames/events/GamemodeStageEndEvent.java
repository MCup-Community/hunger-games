package mccc.gamemode.hungergames.events;

import mccc.gamemode.hungergames.GamemodeStage;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GamemodeStageEndEvent extends Event {
  private static final HandlerList handlers = new HandlerList();

  GamemodeStage stage;

  public GamemodeStage getStage() {
    return stage;
  }

  public GamemodeStageEndEvent(GamemodeStage stage_) {
    stage = stage_;
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

}

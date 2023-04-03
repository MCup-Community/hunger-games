package mccc.gamemode.hungergames.stages;

import mccc.gamemode.hungergames.GamemodeStage;
import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class Cutscene extends GamemodeStage {

  @Override
  public void load() {
    int maxSceneCount = 50; // ??? IDK sorry for this shit

    for (int i = 0; i < maxSceneCount; i++) {
      Location location = plugin.getConfig().getLocation("cutscene." + i + ".location");
      int duration = plugin.getConfig().getInt("cutscene." + i + ".duration");
      String message = plugin.getConfig().getString("cutscene." + i + ".message");

      if (location == null || message == null)
        break;

      cutsceneStages.add(new CutsceneStage(location, duration, message));
    }

    for (Player player : Bukkit.getOnlinePlayers())
      initialPlayerLocations.put(player, player.getLocation());

    super.load();

    plugin.core.apiManager.playerManager.setGlobalGamemode(GameMode.SPECTATOR);
    nextCutsceneStage();
  }

  @Override
  public void unload() {
    super.unload();

    for (Player player : initialPlayerLocations.keySet())
      player.teleport(initialPlayerLocations.get(player));
  }

  public void nextCutsceneStage() {
    cutsceneStageIndex++;

    if (cutsceneStageIndex >= cutsceneStages.size())
      return;

    String prefix = plugin.getConfig().getString("cutscenePrefix");

    for (Player player : Bukkit.getOnlinePlayers()) {
      player.teleport(cutsceneStages.get(cutsceneStageIndex).location);
      player.sendMessage(prefix + ChatColor.GOLD + cutsceneStages.get(cutsceneStageIndex).chatMessage);
      player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 3.0f);
    }

    new BukkitRunnable() {
      public void run() {
        nextCutsceneStage();
      }
    }.runTaskLater(plugin, cutsceneStages.get(cutsceneStageIndex).tickDuration);
  }

  @Override
  public boolean endCondition() {
    return (cutsceneStageIndex >= cutsceneStages.size());
  }

  private HashMap<Player, Location> initialPlayerLocations = new HashMap<>();
  private ArrayList<CutsceneStage> cutsceneStages = new ArrayList<>();

  private int cutsceneStageIndex = -1;

  public Cutscene(HungerGames plugin_) {
    super(plugin_);
  }
}


class CutsceneStage {

  Location location;
  int tickDuration;
  String chatMessage;

  CutsceneStage(Location location_, int tickDuration_, String chatMessage_) {
    location = location_;
    tickDuration = tickDuration_;
    chatMessage = chatMessage_;
  }

}
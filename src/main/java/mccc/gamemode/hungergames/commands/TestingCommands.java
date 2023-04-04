package mccc.gamemode.hungergames.commands;

import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TestingCommands implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, String[] args) {

    if (args.length == 0) {
      // ...
    }

    if (Objects.equals(args[0], "sequence")) {

      if (args.length == 1) {
        // ...
      }

      if (Objects.equals(args[1], "start"))
        plugin.stageManager.startSequence();

      if (Objects.equals(args[1], "terminate"))
        plugin.stageManager.terminateSequence();

      if (Objects.equals(args[1], "restart"))
        plugin.stageManager.restartSequence();

      if (Objects.equals(args[1], "next"))
        plugin.stageManager.switchToNextStage();

      if (Objects.equals(args[1], "prev"))
        plugin.stageManager.switchToPreviousStage();

      if (Objects.equals(args[1], "current"))
        commandSender.sendMessage(plugin.stageManager.getCurrentStage().toString());
    }

    return true;
  }

  private final HungerGames plugin;
  public TestingCommands(HungerGames plugin_) {
    plugin = plugin_;
  }
}

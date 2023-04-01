package mccc.gamemode.hungergames.commands;

import mccc.gamemode.hungergames.HungerGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class TestingCommands implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

    if (Objects.equals(args[0], "start")) {
      plugin.stageManager.startSequence();
    }

    return true;
  }

  private final HungerGames plugin;
  public TestingCommands(HungerGames plugin_) {
    plugin = plugin_;
  }
}

package mccc.gamemode.hungergames;

import mccc.core.Core;
import mccc.gamemode.hungergames.commands.TestingCommands;
import mccc.gamemode.hungergames.listeners.GamemodeListener;
import mccc.gamemode.hungergames.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungerGames extends JavaPlugin {

    public Core core;
    public StageManager stageManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hunger Games plugin started!");

        // Core API initialization
        core = (Core)Bukkit.getPluginManager().getPlugin("Core");

        stageManager = new StageManager(this);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);
        getCommand("game").setExecutor(new TestingCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

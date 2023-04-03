package mccc.gamemode.hungergames;

import mccc.core.Core;
import mccc.gamemode.hungergames.commands.TestingCommands;
import mccc.gamemode.hungergames.listeners.GamemodeListener;
import mccc.gamemode.hungergames.listeners.PlayerListener;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungerGames extends JavaPlugin {

    public Core core;
    public StageManager stageManager;

    public BukkitAudiences adventure;

    public Storage storage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hunger Games plugin started!");

        // Core API initialization
        core = (Core)Bukkit.getPluginManager().getPlugin("Core");
        adventure = BukkitAudiences.create(this);

        storage = new Storage(this);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new GamemodeListener(this), this);
        getCommand("game").setExecutor(new TestingCommands(this));

        stageManager = new StageManager(this);
        stageManager.startSequence();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if (adventure != null) {
            adventure.close();
            adventure = null;
        }
    }
}

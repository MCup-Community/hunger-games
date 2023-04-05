package mcup.gamemode.hungergames;

import mcup.core.Core;
import mcup.core.StageManager;
import mcup.core.stages.Cutscene;
import mcup.core.stages.Waiting;
import mcup.gamemode.hungergames.listeners.PlayerListener;
import mcup.gamemode.hungergames.stages.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public final class HungerGames extends JavaPlugin {

    public Core core;
    public Storage storage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hunger Games plugin started!");

        // Core API initialization
        core = (Core)Bukkit.getPluginManager().getPlugin("Core");

        if (core == null)
            this.getLogger().warning("This plugin require Core API plugin to function!");

        core.registerStageManager(
          new StageManager(core, this, new ArrayList<>(Arrays.asList(
            Waiting.class,
            Cutscene.class,
            Countdown.class,
            FightPreBorder.class,
            FightShrinkBorder.class,
            FightOvertime.class,
            Ending.class
          )))
        );

        storage = new Storage(this);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        core.stageManager.startSequence();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

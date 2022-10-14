package fr.papergx.maintenance;

import fr.papergx.maintenance.cmd.CommandManager;
import fr.papergx.maintenance.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("maintenance").setExecutor(new CommandManager(this));
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

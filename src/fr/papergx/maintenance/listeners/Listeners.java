package fr.papergx.maintenance.listeners;

import fr.papergx.maintenance.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class Listeners implements Listener {
    private Main main;
    public Listeners(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        File file = new File("plugins/Maintenance/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if(!(configuration.contains("maintenance.statut"))) {return;}

        String prefix = "§2§lIrilia§a§lMC §f§l➜ §f";
        Player player = event.getPlayer();
        if(configuration.getString("maintenance.statut").equalsIgnoreCase("on")) {
            if(player.hasPermission("maintenance.bypass")) {return;}
            player.kickPlayer(prefix + "§cLe serveur est actuellement en maintenance !" + "\n" + "\n" + "§f§lPour plus d'information : §2§lhttps://discord.gg/TcjdpqScW9");
        }
    }
}

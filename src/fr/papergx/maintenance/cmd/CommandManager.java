package fr.papergx.maintenance.cmd;

import fr.papergx.maintenance.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CommandManager implements CommandExecutor {
    private Main main;
    public CommandManager(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        File file = new File("plugins/Maintenance/config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if(!(commandSender instanceof Player)) {return false;}
        Player player = (Player) commandSender;
        String prefix = "§2§lIrilia§a§lMC §f§l➜ §f";
        switch (strings.length) {
            case 0:
                player.sendMessage("§a§m-----------------------------");
                player.sendMessage(" ");
                player.sendMessage("§f§lPlugin §7§l➜ §2§lMaintenance");
                player.sendMessage(" ");
                player.sendMessage("§7§l➥ §a/maintenance on §7§l➢ §fPermet de passer le serveur en maintenance");
                player.sendMessage("§7§l➥ §a/maintenance off §7§l➢ §fPermet d'enlever la maintenance du serveur");
                player.sendMessage(" ");
                player.sendMessage("§a§m-----------------------------");
                break;
            case 1:
                if (strings[0].equalsIgnoreCase("on")) {
                    if(!player.hasPermission("maintenance.on")) {player.sendMessage(prefix + "§cTu n'as pas la permission de faire cela !");}
                    player.sendMessage(prefix + "Tu as mis le serveur en maintenance");
                    Bukkit.broadcastMessage(prefix + "§cLe serveur va rentrer en maintenance");
                    configuration.set("maintenance.statut", "on");
                    try {
                        configuration.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (strings[0].equalsIgnoreCase("off")) {
                    if(!player.hasPermission("maintenance.on")) {player.sendMessage(prefix + "§cTu n'as pas la permission de faire cela !");}
                    player.sendMessage(prefix + "Tu as enlevé le serveur en maintenance");
                    Bukkit.broadcastMessage(prefix + "Le serveur est §a§louvert");
                    configuration.set("maintenance.statut", "off");
                    try {
                        configuration.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }
        return false;
    }
}

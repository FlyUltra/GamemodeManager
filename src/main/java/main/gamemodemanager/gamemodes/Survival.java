package main.gamemodemanager.gamemodes;

import main.gamemodemanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival implements CommandExecutor {

    private Main plugin;


    public Survival(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gms").setExecutor((CommandExecutor) this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("gmmanager.gms")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            } else {
                if (args.length == 0) {
                    String GamemodeSurvival = plugin.getConfig().getString("GamemodeSurvival");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSurvival));
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }
            if (args.length == 1) {
                if (!p.hasPermission("gmanager.gms.other")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
                } else {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        String GamemodeSurvivalToOther = plugin.getConfig().getString("GamemodeSurvivalToOther").replace("%TARGET%", t.getName());

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSurvivalToOther));
                        String GamemodeSurvivalTarget = plugin.getConfig().getString("GamemodeSurvivalTarget");

                        t.setGameMode(GameMode.SURVIVAL);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSurvivalTarget));
                    } else {
                        String TargetNull = plugin.getConfig().getString("TargetNull").replace("%TARGET%", args[0]);

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', TargetNull));
                    }
                }
            }
        }

        return false;
    }
}



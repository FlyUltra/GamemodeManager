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

public class Spectator implements CommandExecutor {
    private Main plugin;


    public Spectator(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gmsp").setExecutor((CommandExecutor) this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("gmmanager.gmsp")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            } else {
                if (args.length == 0) {
                    String GamemodeSpectator = plugin.getConfig().getString("GamemodeSpectator");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSpectator));
                    p.setGameMode(GameMode.SPECTATOR);
                }
            }
            if (args.length == 1) {
                if (!p.hasPermission("gmanager.gmsp.other")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
                } else {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        String GamemodeSpectatorToOther = plugin.getConfig().getString("GamemodeSpectatorToOther").replace("%TARGET%", t.getName());

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSpectatorToOther));


                        String GamemodeSpectatorTarget = plugin.getConfig().getString("GamemodeSpectatorTarget");

                        t.setGameMode(GameMode.SPECTATOR);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeSpectatorTarget));
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

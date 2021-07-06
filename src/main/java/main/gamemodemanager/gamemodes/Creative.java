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

public class Creative implements CommandExecutor {

    private Main plugin;


    public Creative(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gmc").setExecutor((CommandExecutor) this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("gmmanager.gmc")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            } else {
                if (args.length == 0) {
                    String GamemodeCreative = plugin.getConfig().getString("GamemodeCreative");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeCreative));
                    p.setGameMode(GameMode.CREATIVE);
                }
            }
            if (args.length == 1) {
                if (!p.hasPermission("gmmanager.gmc.other")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
                } else {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        String GamemodeCreativeToOther = plugin.getConfig().getString("GamemodeCreativeToOther").replace("%TARGET%", t.getName());

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeCreativeToOther));
                        String GamemodeCreativeTarget = plugin.getConfig().getString("GamemodeCreativeTarget");

                        t.setGameMode(GameMode.CREATIVE);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeCreativeTarget));
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



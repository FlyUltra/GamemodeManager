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

public class Adventure implements CommandExecutor {


    private Main plugin;


    public Adventure(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gma").setExecutor((CommandExecutor) this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("gmmanager.gma")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            } else {
                if (args.length == 0) {
                    String GamemodeAdventure = plugin.getConfig().getString("GamemodeAdventure");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeAdventure));
                    p.setGameMode(GameMode.ADVENTURE);
                }
            }
            if (args.length == 1) {
                if (!p.hasPermission("gmmanager.gma.other")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
                } else {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        String GamemodeAdventureToOther = plugin.getConfig().getString("GamemodeAdventureToOther").replace("%TARGET%", t.getName());

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeAdventureToOther));
                        String GamemodeAdventureTarget = plugin.getConfig().getString("GamemodeAdventureTarget");

                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', GamemodeAdventureTarget));
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

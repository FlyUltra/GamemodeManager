package main.gamemodemanager.gamemodes;

import main.gamemodemanager.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Manager implements CommandExecutor {
    private Main plugin;

    public Manager(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gmmanager").setExecutor((CommandExecutor) this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("gm.manager")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.noperm));
            } else {
                try {
                        if (args[0].equalsIgnoreCase("help")) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e#######################"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bSecurity (Place, Destroy, Move)"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aIf you want to turn off look at config.yml"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/GmManager Plugin disable"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDisable Plugin"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/GmManager Plugin Config Reload"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReload Plugin"));
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e#######################"));

                        }
                        if (args[0].equalsIgnoreCase("plugin") && (args[1].equalsIgnoreCase("disable"))) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin has been Disabled"));
                            plugin.getPluginLoader().disablePlugin((Plugin) this);
                        }
                        if (args[0].equalsIgnoreCase("plugin") && (args[1].equalsIgnoreCase("config")) || (args[2].equalsIgnoreCase("reload"))) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig has been Reloaded"));
                            plugin.reloadConfig();
                        }
                } catch (ArrayIndexOutOfBoundsException exc) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e#######################"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bUse /GmManager Help"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e#######################"));
                }
            }
        }
        return false;
    }
}

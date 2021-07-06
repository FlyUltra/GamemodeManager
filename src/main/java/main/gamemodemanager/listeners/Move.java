package main.gamemodemanager.listeners;

import main.gamemodemanager.Main;
import main.gamemodemanager.gamemodes.Creative;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class Move implements Listener {
    private Main plugin;

    public Move(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        if (plugin.getConfig().getBoolean("security")) {
            if (!p.hasPermission("gamemode.creative.move")) {
                if (p.getGameMode().equals(GameMode.CREATIVE)) {
                    e.setCancelled(true);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(this.plugin.getConfig().getString("MoveCreativeBar").replace("&", "§")));
                }
                if (!p.hasPermission("gamemode.spectator.move")) {
                    if (p.getGameMode().equals(GameMode.SPECTATOR)) {
                        e.setCancelled(true);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(this.plugin.getConfig().getString("MoveSpectatorBar").replace("&", "§")));
                    }
                }
                if (!p.hasPermission("gamemode.adventure.move")) {
                    if (p.getGameMode().equals(GameMode.ADVENTURE)) {
                        e.setCancelled(true);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(this.plugin.getConfig().getString("MoveAdventureBar").replace("&", "§")));

                    }
                }
            } else {

            }
        }
    }
}
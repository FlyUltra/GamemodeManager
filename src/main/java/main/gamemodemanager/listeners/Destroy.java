package main.gamemodemanager.listeners;

import main.gamemodemanager.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class Destroy implements Listener {
    private Main plugin;

    public Destroy(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, (Plugin) plugin);
    }

    @EventHandler
    public void onDestroy(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("security")) {

            if (!p.hasPermission("gamemode.creative.destroy")) {
                if (p.getGameMode().equals(GameMode.CREATIVE)) {
                    e.setCancelled(true);
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent) new TextComponent(this.plugin.getConfig().getString("DestroyCreativeBar").replace("&", "§")));
                }
            }
        }

    }
}

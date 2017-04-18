package com.fusenetworks.fuse.listener;
 
import static com.fusenetworks.fuse.Fuse.plugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Developer implements Listener {
    @EventHandler
    public boolean onPlayerJoin(PlayerJoinEvent event) {
    String dev = plugin.getConfig().getString("server.dev");
    Player player = event.getPlayer();
    if (dev.equals("true"))
    {
        player.sendMessage(ChatColor.DARK_AQUA + "Warning: Fuse Networks is currently in development mode. "
        + "This means there may be unstable plugin builds on this server, and the server could crash more than normal!");
        return true;
    }
    return true;
    }
 
}
package com.fusenetworks.fuse.commands;

import java.util.Random;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Location;

@CommandPermissions(source = SourceType.BOTH)
@CommandParameters(description = "Teleports a player to a random location", usage = "/<command>", aliases = "tpr")
public class Command_tprandom extends BaseCommand {
    @Override
    public boolean run(final CommandSender sender, final Player sender_p, final Command cmd, final String commandLabel, final String[] args, final boolean senderIsConsole) 
    {
        if (!sender.hasPermission("fuse.tprandom"))
        {
            sender.sendMessage(Messages.MSG_NO_PERMS);
            return true;
        }
        String location = plugin.getConfig().getString("server.location");
        if (location.equalsIgnoreCase("factions"))
        {
        if (sender instanceof Player)
        {
        Random r = new Random();
        int x = sender_p.getLocation().getBlockX() + r.nextInt(1000);
        int z = sender_p.getLocation().getBlockZ() + r.nextInt(1000);
        Location l = new Location(sender_p.getLocation().getWorld(), x, sender_p.getLocation().getBlockY(), z);
        sender_p.teleport(l);
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "Poof!");
        } else {
            sender.sendMessage("That command is for players only!");
        }
        } else {
            sender.sendMessage(Messages.INVALID_SERVER);
        }
        return true;
    }
}

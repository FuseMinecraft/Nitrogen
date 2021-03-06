package me.telesphoreo.commands;

import me.telesphoreo.utils.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Credit to TFM devs

@CommandPermissions(source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Spy on other players commands", usage = "/<command>", aliases = "cmdspy")
public class Command_commandspy extends BaseCommand
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (senderIsConsole)
        {
            sender.sendMessage(Messages.PLAYER_ONLY);
            return true;
        }
        if (!sender.hasPermission("nitrogen.commandspy"))
        {
            sender.sendMessage(Messages.NO_PERMISSION);
            return true;
        }
        else
        {
            PlayerData playerdata = PlayerData.getPlayerData(sender_p);
            playerdata.setCommandSpy(!playerdata.cmdspyEnabled());
            sender.sendMessage(ChatColor.GRAY + "CommandSpy " + (playerdata.cmdspyEnabled() ? "enabled" : "disabled"));
            return true;
        }
    }
}

package de.ancozockt.lobby.commands;

import de.ancozockt.lobby.commands.handler.WarpCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD_Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return new WarpCommandHandler().handle(sender, command.getName(), args);
    }

}

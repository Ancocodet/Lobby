package de.ancozockt.lobby.commands;

import de.ancozockt.lobby.inventorys.setup.Setup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Admin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(command.getName().equalsIgnoreCase("loadchanges")){
                if(player.hasPermission("lobby.loadchanges")){

                }
            }else if(command.getName().equalsIgnoreCase("setup")){
                if(player.hasPermission("lobby.setup")){
                    Setup.openSetup(player);
                }
            }
        }
        return false;
    }
}

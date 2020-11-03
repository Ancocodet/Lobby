package de.ancozockt.lobby.commands;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.setup.Setup;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Admin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("loadchanges")){
                if(player.hasPermission("lobby.loadchanges")){
                    Main.getInstance().loadConfigs();
                    String message = Main.getInstance().getConfigurationManager().getLanguage().getString("Messages.Changes.Loaded");
                    player.sendMessage(AEStringBuilder.buildMessage(message));
                }
            }else if(command.getName().equalsIgnoreCase("setup")){
                if(player.hasPermission("lobby.setup")){
                    Setup.openSetup(player);
                }
            }
        }else{
            if(!Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("Commands.ConsoleCommands")){
                sender.sendMessage("[Lobby] ConsoleCommands are disabled in your config");
                return false;
            }
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("loadchanges")){
                Main.getInstance().loadConfigs();
                player.sendMessage("[Lobby] Changes loaded successfully");
            }
        }
        return false;
    }
}

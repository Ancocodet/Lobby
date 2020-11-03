package de.ancozockt.lobby.commands;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.commands.handler.WarpCommandHandler;
import de.ancozockt.lobby.inventorys.setup.Setup;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Local implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0) {
                if (args[0].toLowerCase().contains("warp")) {
                    return new WarpCommandHandler().handle(sender, args[0].toLowerCase(), (String[]) ArrayUtils.remove(args, 0));
                }else if(args[0].equalsIgnoreCase("loadchanges")){
                    if(player.hasPermission("lobby.loadchanges")){
                        Main.getInstance().loadConfigs();
                        String message = Main.getInstance().getConfigurationManager().getLanguage().getString("Messages.Changes.Loaded");
                        player.sendMessage(AEStringBuilder.buildMessage(message));
                    }
                }else if(args[0].equalsIgnoreCase("setup")){
                    if(player.hasPermission("lobby.setup")){
                        Setup.openSetup(player);
                    }
                }
            }else{
                if(!Main.getInstance().getPlayerManager().getInLobby().contains(player)){
                    Main.getInstance().getLobbyManager().joinLobby(player);
                }else{
                    Main.getInstance().getLobbyManager().quitLobby(player);
                }
            }
        }else{
            if(!Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("Commands.ConsoleCommands")){
                sender.sendMessage("[Lobby] ConsoleCommands are disabled in your config");
                return false;
            }
            Player player = (Player) sender;
            if(args.length > 0) {
                if (args[0].equalsIgnoreCase("loadchanges")) {
                    Main.getInstance().loadConfigs();
                    player.sendMessage("[Lobby] Changes loaded successfully");
                }
            }
        }
        return false;
    }

}

package de.ancozockt.lobby.commands.handler;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.configuration.StringManager;
import de.ancozockt.lobby.utilitys.interfaces.CommandHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommandHandler implements CommandHandler {

    public boolean handle(CommandSender sender, String command, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            StringManager stringManager = Main.getInstance().getStringManager();

            if(command.equalsIgnoreCase("setwarp")){
                if(player.hasPermission("lobby.setwarp")){
                    if(args.length == 0){
                        player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "/setwarp <name>");
                    }else{
                        String name = args[0].toLowerCase();
                        Main.getInstance().getWarpManager().createWarp(player, name);
                    }
                }
            }
            if(command.equalsIgnoreCase("delwarp")){
                if(player.hasPermission("lobby.delwarp")){
                    if(args.length == 0){
                        player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "/delwarp <name>");
                    }else{
                        String name = args[0].toLowerCase();
                        Main.getInstance().getWarpManager().deleteWarp(player, name);
                    }
                }
            }
            if(command.equalsIgnoreCase("warp")){
                if(args.length == 1){
                    String name = args[0].toLowerCase();
                    Main.getInstance().getWarpManager().teleportWarp(player, name);
                }
            }
        }
        return false;
    }

}

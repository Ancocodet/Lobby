package de.ancozockt.lobby.commands;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.configuration.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            StringManager stringManager = Main.getInstance().getStringManager();
            if(command.getName().equalsIgnoreCase("setwarp")){
                if(player.hasPermission("lobby.setwarp")){
                    if(args.length == 0){
                        player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "/setwarp <name>");
                    }else{
                        String name = args[0].toLowerCase();
                        Main.getInstance().getWarpManager().createWarp(player, name);
                    }
                }
            }
            if(command.getName().equalsIgnoreCase("delwarp")){
                if(player.hasPermission("lobby.delwarp")){
                    if(args.length == 0){
                        player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "/delwarp <name>");
                    }else{
                        String name = args[0].toLowerCase();
                        Main.getInstance().getWarpManager().deleteWarp(player, name);
                    }
                }
            }
            if(command.getName().equalsIgnoreCase("warp")){
                if(args.length == 1){
                    String name = args[0].toLowerCase();
                    Main.getInstance().getWarpManager().teleportWarp(player, name);
                }
            }
        }
        return false;
    }

}

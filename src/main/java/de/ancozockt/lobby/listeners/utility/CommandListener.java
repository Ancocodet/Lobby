package de.ancozockt.lobby.listeners.utility;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.enums.CommandBlock;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CommandListener implements Listener {

    @EventHandler
    public void onFalseCommand(PlayerCommandPreprocessEvent event){
        if(!event.isCancelled()){
            Player player = event.getPlayer();
            if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                    || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
                String msg = event.getMessage().split(" ")[0];

                Configuration configuration = Main.getInstance().getConfigurationManager().getConfiguration();
                Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

                CommandBlock blockState = CommandBlock.getBlockByID(configuration.getInteger("Commands.FalseCommands"));
                if(blockState.equals(CommandBlock.NO_BLOCK))
                    return;
                if(blockState.equals(CommandBlock.ONLY_TEAM) && (player.hasPermission("lobby.team") || player.hasPermission("lobby.admin")))
                    return;
                if(blockState.equals(CommandBlock.ONLY_ADMIN) && player.hasPermission("lobby.admin"))
                    return;

                HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
                if (topic == null) {
                    event.setCancelled(true);
                    if (language.getString("Messages.Commands.Unknown") != null) {
                        player.sendMessage(Main.getInstance().getStringManager().getPrefix() + language.getString("Messages.Commands.Unknown").replace("%command", event.getMessage().toLowerCase()).replace("%n", Main.getInstance().getStringManager().getNormal()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlocked(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){

            Configuration configuration = Main.getInstance().getConfigurationManager().getConfiguration();
            Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

            CommandBlock blockState = CommandBlock.getBlockByID(configuration.getInteger("Commands.DisabledCommands"));
            if(blockState.equals(CommandBlock.NO_BLOCK))
                return;
            if(blockState.equals(CommandBlock.ONLY_TEAM) && (player.hasPermission("lobby.team") || player.hasPermission("lobby.admin")))
                return;
            if(blockState.equals(CommandBlock.ONLY_ADMIN) && player.hasPermission("lobby.admin"))
                return;

            String command = event.getMessage().toLowerCase();
            if(command.startsWith("/ver")
                    || command.startsWith("/pl")
                        || command.startsWith("/plugin")) {
                event.setCancelled(true);
                if(language.getString("Messages.Commands.Unknown") != null){
                    player.sendMessage(Main.getInstance().getStringManager().getPrefix() + language.getString("Messages.Commands.Unknown").replace("%command", command).replace("%n", Main.getInstance().getStringManager().getNormal()));
                }
            }
            if(command.startsWith("/bukkit")
                    || command.startsWith("/help")
                        || command.startsWith("/?")) {
                event.setCancelled(true);
                if(language.getString("Messages.Commands.Unknown") != null){
                    player.sendMessage(Main.getInstance().getStringManager().getPrefix() + language.getString("Messages.Commands.Unknown").replace("%command", command).replace("%n", Main.getInstance().getStringManager().getNormal()));
                }
            }
            if(command.startsWith("/me")
                    || command.startsWith("/tell")) {
                event.setCancelled(true);
                if(language.getString("Messages.Commands.Unknown") != null){
                    player.sendMessage(Main.getInstance().getStringManager().getPrefix() + language.getString("Messages.Commands.Unknown").replace("%command", command).replace("%n", Main.getInstance().getStringManager().getNormal()));
                }
            }
        }
    }

}

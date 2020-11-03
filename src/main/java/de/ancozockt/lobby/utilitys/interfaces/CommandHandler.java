package de.ancozockt.lobby.utilitys.interfaces;

import org.bukkit.command.CommandSender;

public interface CommandHandler {

    boolean handle(CommandSender sender, String command, String[] args);

}

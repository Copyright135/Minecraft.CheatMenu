package io.github.copyright135.cheatmenu.commands;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    boolean onCommand(CommandSender commandSender, String[] args);

    String getPermission();

}

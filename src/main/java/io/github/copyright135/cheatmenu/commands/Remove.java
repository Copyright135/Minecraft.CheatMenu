package io.github.copyright135.cheatmenu.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Remove implements SubCommand {

    private FileConfiguration config;

    public Remove(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        // To be implemented
        return false;
    }

    @Override
    public String getPermission() {
        return null;
    }
}

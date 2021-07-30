package io.github.copyright135.cheatmenu.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Add implements SubCommand {


    private FileConfiguration config;

    public Add(FileConfiguration config) {
        this.config = config;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {
        // To be implemented
        return false;
    }

    @Override
    public String getPermission() {
        return "cheatmenu.add";
    }
}

package io.github.copyright135.cheatmenu.commands;

import io.github.copyright135.cheatmenu.CheatMenu;

public class CommandHandler {

    private CheatMenu plugin;

    public CommandHandler(CheatMenu plugin) {
        this.plugin = plugin;
        loadCommands();
    }

    private void loadCommands() {
        plugin.getCommand("cheatmenu").setExecutor(new CheatMenuCommand(plugin));
    }
}

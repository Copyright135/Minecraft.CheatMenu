package io.github.copyright135.cheatmenu;

import io.github.copyright135.cheatmenu.ui.CheatMenuUI;
import org.bukkit.plugin.java.JavaPlugin;

public class CheatMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadCommands();
        loadListeners();
        CheatMenuUI.init();
    }

    @Override
    public void onDisable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void loadCommands() {
        getCommand("cheatmenu").setExecutor(new CommandHandler(this));
    }

    private void loadListeners() {
        new ListenerHandler(this);
    }

}

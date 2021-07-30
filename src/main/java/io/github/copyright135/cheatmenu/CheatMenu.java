package io.github.copyright135.cheatmenu;

import io.github.copyright135.cheatmenu.commands.CommandHandler;
import io.github.copyright135.cheatmenu.listeners.ListenerHandler;
import io.github.copyright135.cheatmenu.ui.CheatMenuUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CheatMenu extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new CommandHandler(this);
        new ListenerHandler(this);
        CheatMenuUI.init();
    }

    public CheatMenu getInstance() {
        return this;
    }

}

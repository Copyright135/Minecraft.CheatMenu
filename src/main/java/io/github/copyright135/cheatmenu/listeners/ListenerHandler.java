package io.github.copyright135.cheatmenu.listeners;

import io.github.copyright135.cheatmenu.CheatMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenerHandler {

    private CheatMenu plugin;

    public ListenerHandler(CheatMenu plugin) {
        this.plugin = plugin;
        PluginManager plm = Bukkit.getPluginManager();

        plm.registerEvents(new InventoryClickListener(), plugin);
    }
}

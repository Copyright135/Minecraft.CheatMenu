package io.github.copyright135.cheatmenu.listeners;

import io.github.copyright135.cheatmenu.CheatMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenerHandler {

    public ListenerHandler(CheatMenu plugin) {
        PluginManager plm = Bukkit.getPluginManager();

        plm.registerEvents(new InventoryClickListener(), plugin);
    }
}

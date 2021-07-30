package io.github.copyright135.cheatmenu.listeners;

import io.github.copyright135.cheatmenu.ui.CheatMenuUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(CheatMenuUI.getInventoryName())) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null) {
                return;
            }

            if (e.getWhoClicked() instanceof Player) {
                CheatMenuUI.click((Player) e.getWhoClicked(), e.getCurrentItem());
            }
        }
    }
}

package io.github.copyright135.cheatmenu.ui;

import io.github.copyright135.cheatmenu.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CheatMenuUI {

    private static Inventory inv;
    private static String invName;
    private static int invSize = 6 * 9;

    public static void init() {
        invName = Utils.chat("&cCheat Menu");
        inv = Bukkit.createInventory(null, invSize, invName);

        loadPossibleItems();
    }

    private static void loadPossibleItems() {
        ItemStack potion = Utils.createItem(Material.POTION, 1, Utils.chat("&cHeal"), null);
        ItemMeta meta = potion.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        potion.setItemMeta(meta);

        inv.addItem(potion);
        inv.addItem(Utils.createItem(Material.PORKCHOP, 1, Utils.chat("&6Feed"), null));
        inv.addItem(Utils.createItem(Material.FEATHER, 1, Utils.chat("&bFly"), null));
        inv.addItem(Utils.createItem(Material.EXPERIENCE_BOTTLE, 1, Utils.chat("&aLevel Up"), null));
        inv.addItem(Utils.createItem(Material.NETHER_STAR, 1, Utils.chat("&eCreative"), null));
    }

    public static Inventory getInventory(Player p) {
        Inventory newInv = getAllowedCheats(p);
        return newInv;
    }

    public static Inventory getAllowedCheats(Player p) {
        Inventory tempInventory = Bukkit.createInventory(null, invSize, invName);

        for (ItemStack item : inv.getContents()) {
            if (item != null) {
                if (p.hasPermission(getPermFromItem(item)) || p.isOp()) {
                    tempInventory.addItem(item);
                }
            }
        }

        Bukkit.broadcastMessage(String.valueOf(9 * (int) Math.ceil((double) tempInventory.firstEmpty() / 9)));
        Inventory newInv = Bukkit.createInventory(null, 9 * (int) Math.ceil((double) tempInventory.firstEmpty() / 9), invName);

        for (ItemStack item : tempInventory) {
            if (item != null) {
                newInv.addItem(item);
            }
        }

        return newInv;
    }

    // Pass to Utils to handle command
    public static void click(Player p, ItemStack item) {
        Utils.handleCommand(p, p, getItemDescription(item));
    }

    public static String getInventoryName() {
        return invName;
    }

    //
    private static String getPermFromItem(ItemStack item) {
        String perm = "cheatmenu." + getItemDescription(item);
        return perm;
    }

    // Extract command from item name
    private static String getItemDescription(ItemStack item) {
        return item.getItemMeta().getDisplayName().substring(2);
    }

}

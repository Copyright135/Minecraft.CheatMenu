package io.github.copyright135.cheatmenu.ui;

import io.github.copyright135.cheatmenu.CheatMenu;
import io.github.copyright135.cheatmenu.utils.ItemBuilder;
import io.github.copyright135.cheatmenu.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CheatMenuUI {

    private static Inventory inv;
    private static String invName;
    private static final int invSize = 6 * 9;
    private static HashMap<String, HashMap<String, String>> itemCommands;

    // Set up master cheat menu
    public static void init() {
        invName = Utils.chat("&cCheat Menu");
        inv = Bukkit.createInventory(null, invSize, invName);
        itemCommands = new HashMap<>();

        loadPossibleItems();
    }

    /*
     * Iterates over each item listed in "Cheat Items" in config.yml
     * Creates an ItemStack for each item listed, provided the config is correctly set with all values
     *
     */
    private static void loadPossibleItems() {
        FileConfiguration config = CheatMenu.getPlugin(CheatMenu.class).getConfig();

        for (Object obj : config.getList("Cheat Items")) {

            HashMap<String, Object> cheatItem = (HashMap) obj;

            // Get list of "Cheat Items" with values and iterate over them
            for (Map.Entry entry : cheatItem.entrySet()) {

                // Each "Cheat Item" has their own specific attributes, assign as map and pass to helper
                HashMap<String, Object> map = (HashMap) entry.getValue();
                ItemStack item = ItemBuilder.createItemFromObject(map);

                // Check that a valid command is assigned to the item, store connection
                String command = (String) map.get("command");
                String perm = (String) map.get("perm");
                if (item != null && command != null && perm != null) {

                    // Store info related to the item
                    HashMap<String, String> commandInfo = new HashMap<>();
                    commandInfo.put("command", command);
                    commandInfo.put("perm", perm);

                    // Check that a named item is not a duplicate, so that items aren't overwritten
                    if (itemCommands.put(item.getItemMeta().getDisplayName(), commandInfo) != null) {
                        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aCheat Menu: &c Item " + entry.getKey()
                                + " has a duplicate named item. Skipping...."));
                    } else {
                        inv.addItem(item);
                    }
                } else {
                    // Do not add commandless items to cheat menu
                    Bukkit.getConsoleSender().sendMessage(Utils.chat("&aCheatMenu: &cItem " + entry.getKey()
                            + " not set up correctly in config.yml, skipping...."));
                }
            }
        }
    }

    public static Inventory getInventory(Player p) {
        return getAllowedCheats(p);
    }

    /*
     * Create a dynamic cheat menu based on user's perms
     */
    public static Inventory getAllowedCheats(Player p) {
        // Temporary inventory will gather all of the items so that we can change the size later
        Inventory tempInventory = Bukkit.createInventory(null, invSize, invName);

        // Iterate over contents of master cheat menu and add to personalized cheat menu if user has perms
        for (ItemStack item : inv.getContents()) {
            if (item != null) {

                // Get command info per item
                String itemName = item.getItemMeta().getDisplayName();
                if (p.hasPermission(itemCommands.get(itemName).get("perm")) || p.isOp()) {
                    tempInventory.addItem(item);
                }
            }
        }

        // Dynamically count how many rows are required to fit all cheat items
        int totalSlots = 9 * (((tempInventory.firstEmpty() - 1) / 9) + 1);

        // Create and populate a dynamically sized cheat menu
        Inventory newInv = Bukkit.createInventory(null, totalSlots, invName);

        for (ItemStack item : tempInventory) {
            if (item != null) {
                newInv.addItem(item);
            }
        }

        return newInv;
    }

    // Perform command on behalf of player
    public static void click(Player p, ItemStack item) {
        String command = itemCommands.get(item.getItemMeta().getDisplayName()).get("command");
        p.performCommand(command);
    }

    public static String getInventoryName() {
        return invName;
    }

    //
    private static String getPermFromItem(ItemStack item) {
        return "cheatmenu." + getItemDescription(item);
    }

    // Extract command from item name
    private static String getItemDescription(ItemStack item) {
        return item.getItemMeta().getDisplayName().substring(2);
    }

}

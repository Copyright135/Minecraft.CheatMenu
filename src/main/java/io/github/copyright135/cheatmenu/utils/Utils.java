package io.github.copyright135.cheatmenu.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

    // Format colored text
    public static String chat(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    // Allow passing of block names instead of material enums
    public static ItemStack createItem(String material, int amount, String itemName, String... itemLore) {
      return createItem(Material.matchMaterial(material), amount, itemName, itemLore);
    }

    // Create an item based on specified parameters
    public static ItemStack createItem(Material material, int amount, String itemName, String... itemLore) {

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.chat(itemName));

        // Create lore if applicable
        if (itemLore != null && itemLore.length >= 1) {
            ArrayList<String> lore = new ArrayList<>(Arrays.asList(itemLore));

            for (int i = 0; i < lore.size(); i++) {
                lore.set(i, Utils.chat(lore.get(i)));
            }
            meta.setLore(lore);
        }

        item.setItemMeta(meta);

        return item;
    }
}

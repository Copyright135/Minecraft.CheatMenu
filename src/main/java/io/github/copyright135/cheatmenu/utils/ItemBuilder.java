package io.github.copyright135.cheatmenu.utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class ItemBuilder {


    public static ItemStack createItemFromObject(HashMap<String, Object> obj) {

        ItemStack item;

        try {
            String itemType = (String) obj.get("item");
            int amount = (int) obj.get("amount");
            String name = (String) obj.get("item_name");

            List loreList = (List) obj.get("item_lore");
            String[] lore = loreList == null ? null : (String[]) loreList.toArray(new String[0]);

            List flagsList = (List) obj.get("flags");
            String[] flags = flagsList == null ? null : (String[]) flagsList.toArray(new String[0]);

            item = Utils.createItem(itemType, amount, name, lore);

            if (flags != null && flags.length != 0) {
                ItemMeta meta = item.getItemMeta();
                for (String flag : flags) {
                    meta.addItemFlags(ItemFlag.valueOf(flag));
                }
                item.setItemMeta(meta);
            }
        } catch (Exception e) {
            item = null;
        }

        return item;
    }

}

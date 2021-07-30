package io.github.copyright135.cheatmenu.utils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
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

            meta.setLore(lore);
        }

        item.setItemMeta(meta);

        return item;
    }


    /*
     * Utility Commands
     */

    private static final String INSUFFICIENT_PERMISSION = "&cYou do not have permission to use this command.";

    public static void handleCommand(Player sender, Player receiver, String command) {
        if (checkPerm(sender, receiver, command)) {
            switch (command.toLowerCase()) {
                case "heal":
                    heal(receiver);
                    break;
                case "feed":
                    feed(receiver);
                    break;
                case "fly":
                    toggleFly(receiver);
                    break;
                case "toggle downfall":
                    toggleDownfall(receiver);
                    break;
                case "creative":
                    toggleCreative(receiver);
                    break;
                case "+1 level":
                    levelUp(receiver, 1);
                    break;
                case "+5 levels":
                    levelUp(receiver, 5);
                    break;
                case "+10 levels":
                    levelUp(receiver, 10);
                    break;
                case "repair":
                    repair(receiver);
                    break;
            }
        }
    }

    private static void heal(Player p) {
        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
        p.sendMessage(Utils.chat("&cYou have been healed!"));
    }

    private static void feed(Player p) {
        p.setFoodLevel(20);
        p.sendMessage(Utils.chat("&6You have been fed!"));
    }

    private static void toggleFly(Player p) {
        if (p.isFlying()) {
            p.setFlying(false);
            p.setAllowFlight(false );
        } else {
            p.setAllowFlight(true);
            p.setFlying(true);
        }
        p.sendMessage(Utils.chat("&bFlight has been toggled!"));
    }

    private static void toggleDownfall(Player p) {
        p.getWorld().setStorm(!p.getWorld().hasStorm());
    }

    private static void toggleCreative(Player p) {
        if (p.getGameMode() == GameMode.CREATIVE) {
            p.setGameMode(GameMode.SURVIVAL);
        } else {
            p.setGameMode(GameMode.CREATIVE);
        }
        p.sendMessage(Utils.chat("&eCreative mode toggled!"));
    }

    private static void levelUp(Player p, int levels) {
        p.giveExpLevels(levels);

        p.sendMessage(Utils.chat("&aYou have been granted " + levels + (levels > 1 ? " levels!" : " level.")));
    }

    private static void repair(Player p) {
        for (ItemStack item : p.getInventory()) {
            if (item != null) {
                Damageable meta = (Damageable) item.getItemMeta();
                meta.setDamage(0);
                item.setItemMeta((ItemMeta) meta);
            }
        }
    }


    // Check various perm possibilities
    private static boolean checkPerm(Player sender, Player receiver, String permRoot) {
        // Skip if op
        if (!sender.isOp()) {
            if (!sender.equals(receiver) && !sender.hasPermission(permRoot + ".other")) {
                // Check player has perm to use command on other
                sender.sendMessage(Utils.chat(INSUFFICIENT_PERMISSION));
                return false;
            } else if (sender.equals(receiver) && !sender.hasPermission(permRoot + ".self")) {
                // Check player has per mto use command on self
                sender.sendMessage(Utils.chat(INSUFFICIENT_PERMISSION));
                return false;
            }
        }
        return true;
    }
}

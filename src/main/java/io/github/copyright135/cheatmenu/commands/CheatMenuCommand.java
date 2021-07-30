package io.github.copyright135.cheatmenu.commands;

import io.github.copyright135.cheatmenu.CheatMenu;
import io.github.copyright135.cheatmenu.ui.CheatMenuUI;
import io.github.copyright135.cheatmenu.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CheatMenuCommand implements CommandExecutor {

    private final CheatMenu plugin;

    public CheatMenuCommand(CheatMenu plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = plugin.getConfig();

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Utils.chat(config.getString("sender_not_player")));
            return true;
        }

        Player p = (Player) commandSender;

        if (!p.hasPermission("cheatmenu.use") && !p.isOp()) {
            p.sendMessage(Utils.chat(config.getString("insufficient_permission")));
            return true;
        }

        p.openInventory(CheatMenuUI.getInventory(p));

        return true;
    }
}
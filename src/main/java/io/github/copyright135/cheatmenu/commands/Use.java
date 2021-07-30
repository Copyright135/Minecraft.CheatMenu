package io.github.copyright135.cheatmenu.commands;

import io.github.copyright135.cheatmenu.ui.CheatMenuUI;
import io.github.copyright135.cheatmenu.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Use implements SubCommand {

    private FileConfiguration config;

    public Use(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Utils.chat(config.getString("sender_not_player")));
            return false;
        }

        Player p = (Player) commandSender;

        if (!p.hasPermission(getPermission()) && !p.isOp()) {
            p.sendMessage(Utils.chat(config.getString("insufficient_permission")));
            return false;
        }

        p.openInventory(CheatMenuUI.getInventory(p));

        return true;
    }

    @Override
    public String getPermission() {
        return "cheatmenu.use";
    }
}

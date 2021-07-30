package io.github.copyright135.cheatmenu;

import io.github.copyright135.cheatmenu.commands.SubCommand;
import io.github.copyright135.cheatmenu.commands.Use;
import io.github.copyright135.cheatmenu.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    FileConfiguration config;
    private HashMap<String, SubCommand> commands;

    public CommandHandler(JavaPlugin plugin) {
        config = plugin.getConfig();
        commands = new HashMap<>();
        loadCommands();
    }

    private void loadCommands() {
        commands.put("use", new Use(config));

        // To be added
        /*commands.put("add", new Add(config));
        commands.put("remove", new Remove(config));*/
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String cmd;

        if (args.length <= 0) {
            cmd = "use";
        } else {
            cmd = args[0];
        }

        if (!commands.containsKey(cmd)) {
            commandSender.sendMessage(Utils.chat(config.getString("invalid_command")));
            return false;
        }

        commands.get(cmd).onCommand(commandSender, args);

        return false;
    }
}

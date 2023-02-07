// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice.core.commands;

import me.chickensaysbak.dice.core.Dice;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class DicePluginCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Dice plugin = Dice.getInstance();

        if (args.length < 1 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(plugin.formatUIMessage("diceplugin_usage"));
            return true;
        }

        plugin.getSettings().reload();
        sender.sendMessage(plugin.formatUIMessage("reload_config"));
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> result = new ArrayList<>();
        if (args.length == 1) result.add("reload");
        return result;
    }

}

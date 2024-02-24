// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice.core.commands;

import me.chickensaysbak.dice.Roll;
import me.chickensaysbak.dice.core.Dice;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class RollCommand implements CommandExecutor {

    private HashMap<String, Long> lastRoll = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Dice plugin = Dice.getInstance();
        int cooldown = plugin.getSettings().getCooldown();
        int defaultAmount = plugin.getSettings().getDefaultAmount();
        int maximum = plugin.getSettings().getMaximum();
        int minimum = plugin.getSettings().getMinimum();

        long lastRollSeconds = (System.currentTimeMillis() - lastRoll.getOrDefault(sender.getName(), 0L))/1000;

        if (lastRollSeconds < cooldown) {
            int remaining = (int) (cooldown-lastRollSeconds);
            sender.sendMessage(plugin.formatUIMessage("error_cooldown", String.valueOf(remaining), remaining == 1 ? "" : "s"));
            return true;
        }

        int amount;

        try {
            amount = args.length >= 1 ? Integer.parseInt(args[0]) : defaultAmount;
        } catch (NumberFormatException e) {
            sender.sendMessage(plugin.formatUIMessage("roll_usage"));
            return true;
        }

        if (amount > maximum) {
            sender.sendMessage(plugin.formatUIMessage("error_maximum", String.valueOf(maximum)));
            return true;
        }

        if (amount < minimum) {
            sender.sendMessage(plugin.formatUIMessage("error_minimum", String.valueOf(minimum)));
            return true;
        }

        Roll roll = new Roll(amount);
        Bukkit.broadcastMessage(plugin.formatUIMessage("roll", sender.getName(), roll.getDice(), String.valueOf(roll.getTotal())));
        lastRoll.put(sender.getName(), System.currentTimeMillis());
        return true;

    }

}

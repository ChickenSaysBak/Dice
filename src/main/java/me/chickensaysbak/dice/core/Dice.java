// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice.core;

import me.chickensaysbak.dice.core.commands.DicePluginCommand;
import me.chickensaysbak.dice.core.commands.RollCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Dice extends JavaPlugin {

    private static Dice instance;
    private Settings settings;

    @Override
    public void onEnable() {

        instance = this;
        settings = new Settings();

        getCommand("diceplugin").setExecutor(new DicePluginCommand());
        getCommand("roll").setExecutor(new RollCommand());

    }

    /**
     * Formats a UI message if it exists in the settings.
     * @param key the name of the message in messages.yml
     * @param vars replacement variables
     * @return a formatted message
     */
    public String formatUIMessage(String key, String... vars) {

        String msg = settings.getMessage(key);
        if (msg == null) return null;

        switch (key) {

            case "roll":
                msg = msg.replace("%player%", vars[0]).replace("%dice%", vars[1]).replace("%total%", vars[2]);
                break;

            case "error_maximum":
                msg = msg.replace("%maximum%", vars[0]);
                break;

            case "error_minimum":
                msg = msg.replace("%minimum%", vars[0]);
                break;

            case "error_cooldown":
                msg = msg.replace("%remaining%", vars[0]).replace("%s%", vars[1]);
                break;

        }

        return ChatColor.translateAlternateColorCodes('&', msg);

    }

    public static Dice getInstance() {
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }

}

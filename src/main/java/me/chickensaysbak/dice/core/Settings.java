// Dice © 2023 ChickenSaysBak
// This code is licensed under MIT license (see LICENSE file for details).
package me.chickensaysbak.dice.core;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Settings {

    // Config fields
    private File configFile;
    private int cooldown;
    private int defaultAmount;
    private int maximum;
    private int minimum;

    // Message fields
    private File messagesFile;
    private HashMap<String, String> msgs = new HashMap<>();

    Settings() {

        File dataFolder = Dice.getInstance().getDataFolder();
        configFile = new File(dataFolder, "config.yml");
        messagesFile = new File(dataFolder, "messages.yml");

        reload();

    }

    /**
     * Loads or reloads all settings from their files and creates the files if they do not exist.
     * If any setting is not found, a default value is used.
     */
    public void reload() {

        createFile(configFile);
        createFile(messagesFile);

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        cooldown = config.getInt("cooldown", 10);
        defaultAmount = config.getInt("default_amount", 2);
        maximum = config.getInt("maximum", 12);
        minimum = config.getInt("minimum", 1);

        YamlConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
        msgs.clear();
        for (String key : messages.getKeys(false)) msgs.put(key, messages.getString(key));

    }

    public int getCooldown() {return cooldown;}
    public int getDefaultAmount() {return defaultAmount;}
    public int getMaximum() {return maximum;}
    public int getMinimum() {return minimum;}

    public String getMessage(String name) {return msgs.getOrDefault(name, null);}

    /**
     * If the file does not exist, it is created and written based on the corresponding resource file.
     * @param file the file to create
     */
    private void createFile(File file) {

        if (file.exists()) return;

        try {

            file.getParentFile().mkdirs();
            file.createNewFile();

            String content = convertStreamToString(Dice.getInstance().getResource(file.getName()));
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Converts InputStream to String
     * @param in the InputStream to convert
     * @return a String containing the content of the InputStream
     */
    private String convertStreamToString(InputStream in) {

        String result = null;

        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int length; (length = in.read(buffer)) != -1;) output.write(buffer, 0, length);
            result = output.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}

package fr.syrql.hypingbees.utils.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigManager {

    private JavaPlugin javaPlugin;
    private FileConfiguration config;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.config = javaPlugin.getConfig();
    }

    public Component getComponent(String path) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        String message = javaPlugin.getConfig().getString(path);
        if (message == null) return null;

        return miniMessage.deserialize(message);
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.config.getString(path)));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = javaPlugin.getConfig().getStringList(path);
        ArrayList<String> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(ChatColor.translateAlternateColorCodes('&', line)));

        return toReturn;
    }

    public List<Integer> getIntList(String path) {

        List<String> stringList = javaPlugin.getConfig().getStringList(path);
        ArrayList<Integer> toReturn = new ArrayList<>();

        try {
            stringList.forEach(line -> {
                int number = Integer.parseInt(line);
                toReturn.add(number);
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    public List<Component> getComponentList(String path) {

        List<String> stringList = javaPlugin.getConfig().getStringList(path);
        ArrayList<Component> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(MiniMessage.miniMessage().deserialize(line)));

        return toReturn;
    }

    public void setDouble(String path, double value) {
        config.set(path, value);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    private double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getFloat(String path) {
        return config.getDouble(path);
    }

    public long getLong(String path) {
        return config.getLong(path);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void updateConfig() {
        javaPlugin.saveConfig();
        javaPlugin.reloadConfig();
    }
}

package me.yochran.invadedsoup.data;

import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class UDat {

    private InvadedSoup plugin;
    public FileConfiguration config;
    public File file;

    public UDat() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    public void setupData() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(), "data.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Soup]: ERROR CREATING DATA FILE!");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getData() {
        return config;
    }

    public void saveData() {
        try {
            config.save(file);
        }
        catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Soup]: ERROR SAVING DATA FILE!");
        }
    }

    public void reloadData() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}

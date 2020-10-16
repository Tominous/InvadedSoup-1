package me.yochran.invadedsoup;

import me.yochran.invadedsoup.commands.HelpCommand;
import me.yochran.invadedsoup.commands.InfoCommand;
import me.yochran.invadedsoup.commands.SetSpawnCommand;
import me.yochran.invadedsoup.data.UDat;
import me.yochran.invadedsoup.listeners.HelpItemListener;
import me.yochran.invadedsoup.listeners.PlayerLogListeners;
import me.yochran.invadedsoup.listeners.guis.GuiClickListener;
import me.yochran.invadedsoup.listeners.guis.KitSelector;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class InvadedSoup extends JavaPlugin {

    public UDat data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        startupAnnouncements();
        registerCommands();
        registerEvents();
        runRunnables();
        saveDefaultConfig();
        registerData();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        shutdownAnnouncements();
    }

    public ArrayList<Location> spawn = new ArrayList<>();

    private void startupAnnouncements() {
        System.out.println(ChatColor.GREEN + "[Soup]: InvadedLands soup core v1.0 by Yochran is loading...");
        System.out.println(ChatColor.GREEN + "[Soup]: InvadedLands soup core v1.0 by Yochran has successfully loaded.");
    }

    private void shutdownAnnouncements() {
        System.out.println(ChatColor.GREEN + "[Soup]: InvadedLands soup core v1.0 by Yochran is unloading...");
        System.out.println(ChatColor.GREEN + "[Soup]: InvadedLands soup core v1.0 by Yochran has successfully unloaded.");
    }

    private void registerCommands() {
        getCommand("soup").setExecutor(new InfoCommand());
        getCommand("setsoupspawn").setExecutor(new SetSpawnCommand());
        getCommand("help").setExecutor(new HelpCommand());
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerLogListeners(), this);
        manager.registerEvents(new GuiClickListener(), this);
        manager.registerEvents(new KitSelector(), this);
        manager.registerEvents(new HelpItemListener(), this);
    }

    private void runRunnables() {

    }

    public void loadFile(String name){
        File file = new File(getDataFolder(), name);
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            Utils.loadData(this, name); }

        try { fileConfig.load(file); }
        catch (Exception e3) { e3.printStackTrace(); }

        for(String priceString : fileConfig.getKeys(false)) {
            fileConfig.set(priceString, fileConfig.getString(priceString));
        }
    }


    private void registerData(){
        data = new UDat();
        data.setupData();
        data.saveData();
        data.reloadData();
    }
}

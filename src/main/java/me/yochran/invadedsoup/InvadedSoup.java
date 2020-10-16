package me.yochran.invadedsoup;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.yochran.invadedsoup.commands.HelpCommand;
import me.yochran.invadedsoup.commands.InfoCommand;
import me.yochran.invadedsoup.commands.SetSpawnCommand;
import me.yochran.invadedsoup.data.UDat;
import me.yochran.invadedsoup.kits.*;
import me.yochran.invadedsoup.listeners.HealthFoodChangeEvents;
import me.yochran.invadedsoup.listeners.HelpItemListener;
import me.yochran.invadedsoup.listeners.PlayerLogListeners;
import me.yochran.invadedsoup.listeners.SpawnLeaveEvent;
import me.yochran.invadedsoup.listeners.guis.KitSelector;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class InvadedSoup extends JavaPlugin {

    public UDat data;
    public WorldGuardPlugin worldGuardPlugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        startupAnnouncements();
        registerCommands();
        registerEvents();
        runRunnables();
        saveDefaultConfig();
        registerData();
        getKits();
        worldGuardPlugin = getWorldGuard();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        shutdownAnnouncements();
    }

    public WorldGuardPlugin getWorldGuard() {
        Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || ! (plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }

    public ArrayList<Location> spawn = new ArrayList<>();
    public HashMap<UUID, String> kit = new HashMap<>();
    public ArrayList<UUID> entered = new ArrayList<>();
    public ArrayList<UUID> left = new ArrayList<>();

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
        manager.registerEvents(new KitSelector(), this);
        manager.registerEvents(new HelpItemListener(), this);
        manager.registerEvents(new HealthFoodChangeEvents(), this);
        manager.registerEvents(new SpawnLeaveEvent(), this);
    }

    private void getKits() {
        PluginManager kits = getServer().getPluginManager();
        kits.registerEvents(new PvPKit(), this);
        kits.registerEvents(new PotionKit(), this);
        kits.registerEvents(new ArcherKit(), this);
        kits.registerEvents(new SwitcherKit(), this);
        kits.registerEvents(new UrgalKit(), this);
        kits.registerEvents(new SonicKit(), this);
        kits.registerEvents(new VikingKit(), this);
        kits.registerEvents(new StomperKit(), this);
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

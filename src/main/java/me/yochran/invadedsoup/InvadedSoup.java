package me.yochran.invadedsoup;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.yochran.invadedsoup.commands.*;
import me.yochran.invadedsoup.kits.*;
import me.yochran.invadedsoup.listeners.*;
import me.yochran.invadedsoup.listeners.guis.KitSelector;
import me.yochran.invadedsoup.listeners.kitlisteners.*;
import me.yochran.invadedsoup.runnables.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class InvadedSoup extends JavaPlugin {

    public WorldGuardPlugin worldGuardPlugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerEvents();
        runRunnables();
        saveDefaultConfig();
        getKits();
        worldGuardPlugin = getWorldGuard();
        startupAnnouncements();
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

    public HashMap<UUID, String> kit = new HashMap<>();
    public HashMap<UUID, Double> spawn = new HashMap<>();
    public HashMap<UUID, Integer> X = new HashMap<>();
    public HashMap<UUID, Integer> Z = new HashMap<>();
    public HashMap<UUID, Double> kang_cooldown = new HashMap<>();
    public HashMap<UUID, Double> ninja_cooldown = new HashMap<>();
    public HashMap<Player, Player> ninja_last_hit = new HashMap<>();
    public ArrayList<UUID> kangaroo = new ArrayList<>();
    public ArrayList<UUID> dropsOn = new ArrayList<>();
    public ArrayList<UUID> potion = new ArrayList<>();
    public ArrayList<UUID> urgal = new ArrayList<>();
    public ArrayList<UUID> switcher = new ArrayList<>();
    public ArrayList<UUID> stomper = new ArrayList<>();
    public ArrayList<UUID> ninja = new ArrayList<>();

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
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("toggledrops").setExecutor(new ToggleDropsCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerLogListeners(), this);
        manager.registerEvents(new KitSelector(), this);
        manager.registerEvents(new HelpItemListener(), this);
        manager.registerEvents(new HealthFoodChangeEvents(), this);
        manager.registerEvents(new SpawnLeaveEvent(), this);
        manager.registerEvents(new SoupListener(), this);
        manager.registerEvents(new UrgalAbility(), this);
        manager.registerEvents(new SwitcherAbility(), this);
        manager.registerEvents(new RespawnEvent(), this);
        manager.registerEvents(new StomperAbility(), this);
        manager.registerEvents(new FishermanAbility(), this);
        manager.registerEvents(new KangarooAbility(), this);
        manager.registerEvents(new NinjaAbility(), this);
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
        kits.registerEvents(new FishermanKit(), this);
        kits.registerEvents(new KangarooKit(), this);
        kits.registerEvents(new NinjaKit(), this);
    }

    private void runRunnables() {
        new PotionRunnable().runTaskTimer(this, 0, 1);
        new SpawnRunnable().runTaskTimer(this, 0, 20);
        new KangarooCooldown().runTaskTimer(this, 0, 5);
        new NinjaCooldown().runTaskTimer(this, 0, 20);
    }
}

package me.yochran.invadedsoup;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.yochran.invadedsoup.commands.*;
import me.yochran.invadedsoup.kits.*;
import me.yochran.invadedsoup.listeners.*;
import me.yochran.invadedsoup.listeners.guis.KitSelector;
import me.yochran.invadedsoup.listeners.guis.SettingsGUI;
import me.yochran.invadedsoup.listeners.guis.SettingsOptions;
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

    // HashMaps (Misc)
    public HashMap<UUID, Double> spawn = new HashMap<>();
    public HashMap<UUID, Integer> X = new HashMap<>();
    public HashMap<UUID, Integer> Z = new HashMap<>();
    public HashMap<String, String> reply = new HashMap<>();
    public HashMap<UUID, Double> refill = new HashMap<>();

    // HashMaps (Kits)
    public HashMap<UUID, String> kit = new HashMap<>();
    public HashMap<UUID, Double> kang_cooldown = new HashMap<>();
    public HashMap<UUID, Double> ninja_cooldown = new HashMap<>();
    public HashMap<UUID, Double> thor_cooldown = new HashMap<>();
    public HashMap<Player, Player> ninja_last_hit = new HashMap<>();
    public HashMap<UUID, Double> viper_hit_count = new HashMap<>();

    // ArrayLists (Settings)
    public ArrayList<UUID> tpm = new ArrayList<>();
    public ArrayList<UUID> tgc = new ArrayList<>();
    public ArrayList<UUID> tms = new ArrayList<>();

    // ArrayLists (Kits)
    public ArrayList<UUID> kangaroo = new ArrayList<>();
    public ArrayList<UUID> potion = new ArrayList<>();
    public ArrayList<UUID> urgal = new ArrayList<>();
    public ArrayList<UUID> switcher = new ArrayList<>();
    public ArrayList<UUID> stomper = new ArrayList<>();
    public ArrayList<UUID> ninja = new ArrayList<>();
    public ArrayList<UUID> snowman = new ArrayList<>();
    public ArrayList<UUID> thor = new ArrayList<>();
    public ArrayList<UUID> viper = new ArrayList<>();

    // ArrayLists (Misc)
    public ArrayList<UUID> dropsOn = new ArrayList<>();

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
        getCommand("message").setExecutor(new MsgCommand());
        getCommand("reply").setExecutor(new MsgCommand());
        getCommand("togglemessages").setExecutor(new ToggleMessages());
        getCommand("toggleglobalchat").setExecutor(new ToggleGlobalChat());
        getCommand("togglemessagesounds").setExecutor(new ToggleMessageSounds());
        getCommand("refill").setExecutor(new RefillCommand());
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
        manager.registerEvents(new SnowmanAbility(), this);
        manager.registerEvents(new ThorAbility(), this);
        manager.registerEvents(new ViperAbility(), this);
        manager.registerEvents(new SettingsGUI(), this);
        manager.registerEvents(new ChatListener(), this);
        manager.registerEvents(new SettingsOptions(), this);
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
        kits.registerEvents(new SnowmanKit(), this);
        kits.registerEvents(new ThorKit(), this);
        kits.registerEvents(new ViperKit(), this);
    }

    private void runRunnables() {
        new PotionRunnable().runTaskTimer(this, 0, 1);
        new SpawnRunnable().runTaskTimer(this, 0, 20);
        new KangarooCooldown().runTaskTimer(this, 0, 5);
        new NinjaCooldown().runTaskTimer(this, 0, 20);
        new ThorCooldown().runTaskTimer(this, 0, 20);
        new RefillRunnable().runTaskTimer(this, 0, 20);
    }
}

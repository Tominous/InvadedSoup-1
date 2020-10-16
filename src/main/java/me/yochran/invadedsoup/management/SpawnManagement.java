package me.yochran.invadedsoup.management;

import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SpawnManagement {

    private Player player;
    private InvadedSoup plugin;
    public SpawnManagement(Player player){
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
        this.player = player;
    }

    public void send(){
        File file = new File(plugin.getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        String worldname = config.getString("Spawn.world");
        double x = config.getDouble("Spawn.x");
        double y = config.getDouble("Spawn.y");
        double z = config.getDouble("Spawn.z");
        double yaw = config.getDouble("Spawn.yaw");
        double pitch = config.getDouble("Spawn.pitch");

        World world = Bukkit.getWorld(worldname);

        Location loc = new Location(world, x, y, z, (float) pitch, (float) yaw);

        player.teleport(loc);
    }
}

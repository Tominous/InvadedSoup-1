package me.yochran.invadedsoup.utils;

import com.google.common.io.ByteStreams;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Utils {

    private static InvadedSoup plugin;

    public Utils() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void liner(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------"));
    }

    public static void liner(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------------------------"));
    }

    public static void spacer(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7 "));
    }

    public static void spacer(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7 "));
    }

    public static String guiSpacer() {
        ChatColor.translateAlternateColorCodes('&', "&7 ");
        return null;
    }

    public static boolean regionLeave(Player player){
        for (ProtectedRegion wgregion : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
            ArrayList<String> tr = new ArrayList<>();
            ArrayList<String> regions = new ArrayList<>();
            regions.add("spawn");
            for(String region : regions){
                tr.add(region);
            }
            if (tr.contains(wgregion.getId().toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}

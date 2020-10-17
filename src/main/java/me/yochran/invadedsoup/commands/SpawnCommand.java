package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    private InvadedSoup plugin;

    public SpawnCommand() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (!Utils.regionLeave(player)) {
                    SpawnManagement spwn = new SpawnManagement(player);
                    spwn.tp();
                    player.getInventory().clear();
                    player.getInventory().setArmorContents(null);
                    Inventory playerInv = player.getInventory();
                    ItemStack kitSelector = XMaterial.NETHER_STAR.parseItem();
                    ItemStack help = XMaterial.PAPER.parseItem();
                    ItemStack settings = XMaterial.BOOK.parseItem();

                    ItemMeta kitSelectorName = kitSelector.getItemMeta();
                    ItemMeta settingsName = settings.getItemMeta();
                    ItemMeta helpName = help.getItemMeta();

                    kitSelectorName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"));
                    settingsName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Settings"));
                    helpName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Help &7(Right Click&7)"));
                    ArrayList<String> helpLore = new ArrayList<>();
                    helpLore.add(ChatColor.translateAlternateColorCodes('&', "&7Right click this item for information about the Soup server!"));
                    helpName.setLore(helpLore);

                    kitSelector.setItemMeta(kitSelectorName);
                    settings.setItemMeta(settingsName);
                    help.setItemMeta(helpName);

                    playerInv.setItem(0, help);
                    playerInv.setItem(4, kitSelector);
                    playerInv.setItem(8, settings);
                    player.removePotionEffect(PotionEffectType.SPEED);
                    plugin.kit.remove(player.getUniqueId());
                    plugin.potion.remove(player.getUniqueId());
                    plugin.urgal.remove(player.getUniqueId());
                    plugin.switcher.remove(player.getUniqueId());
                } else {
                    Utils.sendMessage(player, "&7You will be teleported to spawn in &b5 seconds&7.");
                    Location location = player.getLocation();
                    double X = location.getBlockX();
                    double Z = location.getBlockZ();
                    plugin.X.put(player.getUniqueId(), X);
                    plugin.Z.put(player.getUniqueId(), Z);
                    plugin.spawn.put(player.getUniqueId(), 5);
                }
            }
        }
        return true;
    }
}

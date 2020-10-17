package me.yochran.invadedsoup.kits;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class VikingKit implements Listener {

    private InvadedSoup plugin;

    public VikingKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.IRON_AXE.parseMaterial()) {
                    event.setCancelled(true);
                    player.getInventory().clear();
                    plugin.kit.remove(player.getUniqueId());
                    ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                    ItemStack axe = XMaterial.IRON_AXE.parseItem();
                    player.getInventory().setItem(0, axe);
                    for (int i = 1; i < 36; i++) {
                        player.getInventory().setItem(i, soup);
                    }
                    player.closeInventory();
                    player.getInventory().setHelmet(XMaterial.GOLDEN_HELMET.parseItem());
                    player.getInventory().setChestplate(XMaterial.DIAMOND_CHESTPLATE.parseItem());
                    player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                    player.getInventory().setBoots(XMaterial.DIAMOND_BOOTS.parseItem());

                    Utils.sendMessage(player, "&7You have recieved the &6Viking &7Kit.");
                    plugin.kit.put(player.getUniqueId(), "Viking");
                    plugin.potion.remove(player.getUniqueId());
                    plugin.urgal.remove(player.getUniqueId());
                    plugin.switcher.remove(player.getUniqueId());
                }
            }
        }
    }
}

package me.yochran.invadedsoup.kits;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class SwitcherKit implements Listener {

    private InvadedSoup plugin;

    public SwitcherKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem().getType() == XMaterial.SNOWBALL.parseMaterial()) {
                event.setCancelled(true);
                player.getInventory().clear();
                plugin.kit.remove(player.getUniqueId());
                ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                ItemStack sword = XMaterial.IRON_SWORD.parseItem();
                ItemStack snowballs = XMaterial.SNOWBALL.parseItem();
                ItemStack helmet = XMaterial.LEATHER_HELMET.parseItem();
                LeatherArmorMeta helmMeta = (LeatherArmorMeta) helmet.getItemMeta();
                helmMeta.setColor(Color.RED);
                helmet.setItemMeta(helmMeta);
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                player.getInventory().setItem(0, sword);
                for (int s = 0; s < 16; s++) {
                    player.getInventory().addItem(snowballs);
                }
                for (int i = 2; i < 36; i++) {
                    player.getInventory().addItem(soup);
                }
                player.closeInventory();
                player.getInventory().setHelmet(helmet);
                player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());

                Utils.sendMessage(player, "&7You have recieved the &4Switcher &7Kit.");
                plugin.kit.put(player.getUniqueId(), "Switcher");
            }
        }
    }
}

package me.yochran.invadedsoup.kits;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class NinjaKit implements Listener {

    private InvadedSoup plugin;

    public NinjaKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.GOLDEN_CARROT.parseMaterial()) {
                    if (!player.hasPermission("soup.kits.ninja")) {
                        player.closeInventory();
                        Utils.sendMessage(player, "&cYou can't use that kit!");
                    } else {
                        event.setCancelled(true);
                        player.getInventory().clear();
                        plugin.kit.remove(player.getUniqueId());
                        ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                        ItemStack sword = XMaterial.IRON_SWORD.parseItem();
                        ItemStack boots = XMaterial.LEATHER_BOOTS.parseItem();
                        LeatherArmorMeta bootMeta = (LeatherArmorMeta) boots.getItemMeta();
                        bootMeta.setColor(Color.BLACK);
                        boots.setItemMeta(bootMeta);
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                        boots.addEnchantment(Enchantment.DURABILITY, 1);
                        player.getInventory().setItem(0, sword);
                        for (int i = 1; i < 36; i++) {
                            player.getInventory().setItem(i, soup);
                        }
                        player.closeInventory();
                        player.getInventory().setHelmet(XMaterial.IRON_HELMET.parseItem());
                        player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                        player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                        player.getInventory().setBoots(boots);

                        Utils.sendMessage(player, "&7You have recieved the &cNinja &7Kit.");
                        plugin.kit.put(player.getUniqueId(), "Ninja");
                        plugin.potion.remove(player.getUniqueId());
                        plugin.urgal.remove(player.getUniqueId());
                        plugin.switcher.remove(player.getUniqueId());
                        plugin.stomper.remove(player.getUniqueId());
                        plugin.kangaroo.remove(player.getUniqueId());
                        plugin.ninja.add(player.getUniqueId());
                    }
                }
            }
        }
    }
}

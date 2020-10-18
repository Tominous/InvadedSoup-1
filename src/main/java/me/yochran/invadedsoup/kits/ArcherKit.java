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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArcherKit implements Listener {

    private InvadedSoup plugin;

    public ArcherKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.BOW.parseMaterial()) {
                    event.setCancelled(true);
                    player.getInventory().clear();
                    plugin.kit.remove(player.getUniqueId());
                    ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                    ItemStack sword = XMaterial.STONE_SWORD.parseItem();
                    ItemStack bow = XMaterial.BOW.parseItem();
                    ItemStack arrow = XMaterial.ARROW.parseItem();
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    player.getInventory().setItem(0, sword);
                    player.getInventory().setItem(1, bow);
                    player.getInventory().setItem(2, arrow);
                    for (int i = 3; i < 36; i++) {
                        player.getInventory().setItem(i, soup);
                    }
                    player.closeInventory();
                    player.getInventory().setHelmet(XMaterial.CHAINMAIL_HELMET.parseItem());
                    player.getInventory().setChestplate(XMaterial.GOLDEN_CHESTPLATE.parseItem());
                    player.getInventory().setLeggings(XMaterial.CHAINMAIL_LEGGINGS.parseItem());
                    player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());

                    Utils.sendMessage(player, "&7You have recieved the &bArcher &7Kit.");
                    plugin.kit.put(player.getUniqueId(), "Archer");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
                    plugin.potion.remove(player.getUniqueId());
                    plugin.urgal.remove(player.getUniqueId());
                    plugin.switcher.remove(player.getUniqueId());
                    plugin.stomper.remove(player.getUniqueId());
                    plugin.kangaroo.remove(player.getUniqueId());
                    plugin.ninja.remove(player.getUniqueId());
                }
            }
        }
    }
}

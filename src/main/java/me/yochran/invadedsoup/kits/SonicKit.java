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

public class SonicKit implements Listener {

    private InvadedSoup plugin;

    public SonicKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.SUGAR.parseMaterial()) {
                    event.setCancelled(true);
                    player.getInventory().clear();
                    plugin.kit.remove(player.getUniqueId());
                    ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                    ItemStack sword = XMaterial.DIAMOND_SWORD.parseItem();
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    player.getInventory().setItem(0, sword);
                    for (int i = 1; i < 36; i++) {
                        player.getInventory().setItem(i, soup);
                    }
                    player.closeInventory();
                    player.getInventory().setHelmet(XMaterial.CHAINMAIL_HELMET.parseItem());
                    player.getInventory().setChestplate(XMaterial.CHAINMAIL_CHESTPLATE.parseItem());
                    player.getInventory().setLeggings(XMaterial.CHAINMAIL_LEGGINGS.parseItem());
                    player.getInventory().setBoots(XMaterial.CHAINMAIL_BOOTS.parseItem());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));

                    Utils.sendMessage(player, "&7You have recieved the &8Sonic &7Kit.");
                    plugin.kit.put(player.getUniqueId(), "Sonic");
                }
            }
        }
    }
}

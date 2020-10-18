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

public class ThorKit implements Listener {

    private InvadedSoup plugin;

    public ThorKit() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void selectKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"))) {
            if (event.getCurrentItem()== null) {
                return;
            } else {
                if (event.getCurrentItem().getType() == XMaterial.WOODEN_AXE.parseMaterial()) {
                    if (!player.hasPermission("soup.kits.thor")) {
                        player.closeInventory();
                        Utils.sendMessage(player, "&cYou can't use that kit!");
                    } else {
                        event.setCancelled(true);
                        player.getInventory().clear();
                        plugin.kit.remove(player.getUniqueId());
                        ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                        ItemStack axe = XMaterial.DIAMOND_AXE.parseItem();
                        axe.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                        player.getInventory().setItem(0, axe);
                        for (int i = 1; i < 36; i++) {
                            player.getInventory().setItem(i, soup);
                        }
                        player.closeInventory();
                        player.getInventory().setChestplate(XMaterial.IRON_CHESTPLATE.parseItem());
                        player.getInventory().setLeggings(XMaterial.IRON_LEGGINGS.parseItem());
                        player.getInventory().setBoots(XMaterial.IRON_BOOTS.parseItem());

                        Utils.sendMessage(player, "&7You have recieved the &9Thor &7Kit.");
                        plugin.kit.put(player.getUniqueId(), "Thor");
                        plugin.potion.remove(player.getUniqueId());
                        plugin.urgal.remove(player.getUniqueId());
                        plugin.switcher.remove(player.getUniqueId());
                        plugin.stomper.remove(player.getUniqueId());
                        plugin.kangaroo.remove(player.getUniqueId());
                        plugin.ninja.remove(player.getUniqueId());
                        plugin.snowman.remove(player.getUniqueId());
                        plugin.thor.add(player.getUniqueId());
                        plugin.viper.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
}

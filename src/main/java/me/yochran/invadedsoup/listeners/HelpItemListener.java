package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HelpItemListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == XMaterial.PAPER.parseMaterial()) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                Utils.sendMessage(player, "&7This is a &6Soup &7server, all kits are temporary and can he selected again after you die.");
                Utils.sendMessage(player, "&7Right click the &bKit Selector &7to get started.");
                Utils.sendMessage(player, "&7Each kit has a unique ability, everyone starts out with the kits:");
                Utils.sendMessage(player, "&6PVP&7, &6Potion&7, &6Archer&7, &6Grandpa&7, &6Switcher&7, &6Urgal&7, &6Sonic&7, &6Viking &7and &6Stomper");
                Utils.sendMessage(player, "&7You can also purchase &6enchantments&7, &6items &7and &6kits &7using money by typing &6/shop&7.");
                Utils.liner(player);
                Utils.sendMessage(player, "&7The &6/refill &7command refills your inventory with soups&7.");
                Utils.liner(player);
                Utils.sendMessage(player, "&7Every kit gets an inventory full of &6mushroom soup&7.");
                Utils.sendMessage(player, "&7Right clicking the &6mushroom soup &7heals your health by");
                Utils.sendMessage(player, "&63.5 hearts &7or &67 health points&7.");
            }
        }
    }
}

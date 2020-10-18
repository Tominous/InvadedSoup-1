package me.yochran.invadedsoup.listeners.kitlisteners;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Snow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class SnowmanAbility implements Listener {

    private InvadedSoup plugin;

    public SnowmanAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    public ArrayList<UUID> in_air = new ArrayList<>();

    @EventHandler
    public void onSnowball(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null || event.getItem().getType() == Material.AIR)return;

            if (event.getItem().getType() == XMaterial.SNOWBALL.parseMaterial()) {
                if (plugin.snowman.contains(player.getUniqueId())) {
                    player.updateInventory();
                    if (!Utils.regionLeave(event.getPlayer())) {
                        return;
                    }
                    player.updateInventory();
                    in_air.add(player.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Snowball) {
            Player thrower = (Player) projectile.getShooter();
            if (plugin.snowman.contains(thrower.getUniqueId())) {
                if (!plugin.switcher.contains(thrower.getUniqueId())) {
                    int slot = thrower.getInventory().getHeldItemSlot();
                    ItemStack snowball = XMaterial.SNOWBALL.parseItem();
                    ItemMeta snowballName = snowball.getItemMeta();
                    snowballName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7&lSacred Snow"));
                    snowball.setItemMeta(snowballName);
                    thrower.getInventory().addItem(snowball);
                }
            }
        }
    }
}

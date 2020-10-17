package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class SwitcherAbility implements Listener {

    private InvadedSoup plugin;

    public SwitcherAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    public ArrayList<UUID> in_air = new ArrayList<>();

    @EventHandler
    public void onSnowball(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getItem() == null || event.getItem().getType() == Material.AIR)return;

            if (event.getItem().getType() == XMaterial.SNOWBALL.parseMaterial()) {
                if (plugin.switcher.contains(player.getUniqueId())) {
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
    public void land(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        if (projectile instanceof Snowball){
            Player thrower = (Player) projectile.getShooter();

            if (plugin.switcher.contains(thrower.getUniqueId())) {
                if (in_air.contains(thrower.getUniqueId())){
                    in_air.remove(thrower.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void snowballHit(EntityDamageByEntityEvent event){
        new BukkitRunnable(){

            @Override
            public void run() {
                if (event.isCancelled()) return;
                if (event.getEntity() instanceof Player) {
                    if (event.getDamager() instanceof Snowball) {
                        Player thrower = (Player) ((Snowball) event.getDamager()).getShooter();
                        Player hit = (Player) event.getEntity();
                        if (plugin.switcher.contains(thrower.getUniqueId())) {
                            Location hitLocation = hit.getLocation();
                            Location throwerLocation = thrower.getLocation();
                            thrower.teleport(hitLocation);
                            hit.teleport(throwerLocation);
                            Utils.sendMessage(hit, "&aYou have been switched!");
                            Utils.sendMessage(thrower, "&aYou have switched with " + hit.getName() + "&a!");
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player attacker = event.getEntity().getKiller();
        if (attacker != null) {
            if (plugin.switcher.contains(attacker.getUniqueId())) {
                ItemStack newAmount = XMaterial.SNOWBALL.parseItem();
                for (int i = 0; i < 5; i++) {
                    attacker.getInventory().addItem(newAmount);
                }
                attacker.updateInventory();
            }
        }
    }
}

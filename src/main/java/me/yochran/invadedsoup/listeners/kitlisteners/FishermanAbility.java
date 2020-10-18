package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class FishermanAbility implements Listener {

    private InvadedSoup plugin;

    public FishermanAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    public ArrayList<UUID> rod_shot = new ArrayList<>();

    @EventHandler
    public void onRodThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null || event.getItem().getType() == Material.AIR)return;

            if (event.getItem().getType() == XMaterial.FISHING_ROD.parseMaterial()) {
                player.updateInventory();
                if (!Utils.regionLeave(event.getPlayer())) {
                    return;
                }
                player.updateInventory();
                rod_shot.add(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void rodLand(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        if (projectile instanceof Snowball){
            Player thrower = (Player) projectile.getShooter();

            if (plugin.switcher.contains(thrower.getUniqueId())) {
                if (rod_shot.contains(thrower.getUniqueId())){
                    rod_shot.remove(thrower.getUniqueId());
                }
            }
        }
    }

    @EventHandler
    public void rodHit(PlayerFishEvent event){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.isCancelled()) return;
                Player shooter = event.getPlayer();
                Entity caught = event.getCaught();
                if (caught instanceof Player) {
                    Player caughtNew = (Player) event.getCaught();
                    if (!Utils.regionLeave(caughtNew)) {
                        event.setCancelled(true);
                    } else {
                        if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
                            Location shooterLocation = shooter.getLocation();
                            caughtNew.teleport(shooterLocation);
                            Utils.sendMessage(caughtNew, "&aYou have been fished!");
                            Utils.sendMessage(shooter, "&aYou have fished " + caughtNew.getName() + "&a!");
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }

    @EventHandler
    public void onRodDamage(PlayerItemDamageEvent event) {
        ItemStack rod = event.getItem();
        if (rod.getType() == XMaterial.FISHING_ROD.parseMaterial()) {
            event.setCancelled(true);
        }
    }
}

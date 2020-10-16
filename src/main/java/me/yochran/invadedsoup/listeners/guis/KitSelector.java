package me.yochran.invadedsoup.listeners.guis;

import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KitSelector implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType() == XMaterial.NETHER_STAR.parseMaterial()) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                Inventory kitInv = Bukkit.createInventory(player, 18, ChatColor.AQUA + "Kit Selector");
                // Kits
                ItemStack pvp = XMaterial.MUSHROOM_STEW.parseItem();
                ItemStack potion = new ItemStack(Material.POTION, 1, (short) 16421);
                ItemStack archer = XMaterial.BOW.parseItem();
                ItemStack switcher = XMaterial.SNOWBALL.parseItem();
                ItemStack urgal = XMaterial.BLAZE_POWDER.parseItem();
                ItemStack sonic = XMaterial.SUGAR.parseItem();
                ItemStack viking = XMaterial.IRON_AXE.parseItem();
                ItemStack stomper = XMaterial.IRON_BOOTS.parseItem();
                ItemStack fisherman = XMaterial.FISHING_ROD.parseItem();
                ItemStack kangaroo = XMaterial.FIREWORK_ROCKET.parseItem();
                ItemStack snowman = XMaterial.JACK_O_LANTERN.parseItem();
                ItemStack thor = XMaterial.WOODEN_AXE.parseItem();
                ItemStack viper = XMaterial.SPIDER_EYE.parseItem();
                ItemStack ninja = XMaterial.GOLDEN_CARROT.parseItem();

                // Item Meta
                ItemMeta pvpMeta = pvp.getItemMeta();
                ItemMeta potionMeta = potion.getItemMeta();
                ItemMeta archerMeta = archer.getItemMeta();
                ItemMeta switcherMeta = switcher.getItemMeta();
                ItemMeta urgalMeta = urgal.getItemMeta();
                ItemMeta sonicMeta = sonic.getItemMeta();
                ItemMeta vikingMeta = viking.getItemMeta();
                ItemMeta stomperMeta = stomper.getItemMeta();
                ItemMeta fishermanMeta = fisherman.getItemMeta();
                ItemMeta kangarooMeta = kangaroo.getItemMeta();
                ItemMeta snowmanMeta = snowman.getItemMeta();
                ItemMeta thorMeta = thor.getItemMeta();
                ItemMeta viperMeta = viper.getItemMeta();
                ItemMeta ninjaMeta = ninja.getItemMeta();

                // Item Names
                pvpMeta.setDisplayName(ChatColor.GREEN + "PvP");
                potionMeta.setDisplayName(ChatColor.DARK_GREEN + "Potion");
                archerMeta.setDisplayName(ChatColor.AQUA + "Archer");
                switcherMeta.setDisplayName(ChatColor.RED + "Switcher");
                urgalMeta.setDisplayName(ChatColor.AQUA + "Urgal");
                sonicMeta.setDisplayName(ChatColor.DARK_GRAY + "Sonic");
                vikingMeta.setDisplayName(ChatColor.GOLD + "Viking");
                stomperMeta.setDisplayName(ChatColor.DARK_AQUA + "Stomper");
                fishermanMeta.setDisplayName(ChatColor.BLUE + "Fisherman");
                kangarooMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Kangaroo");
                snowmanMeta.setDisplayName(ChatColor.GRAY + "Snowman");
                thorMeta.setDisplayName(ChatColor.BLUE + "Thor");
                viperMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Viper");
                ninjaMeta.setDisplayName(ChatColor.RED + "Ninja");

                // Lores
                ArrayList<String> pvpLore = new ArrayList<>();
                pvpLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                pvpLore.add(Utils.guiSpacer());
                pvpLore.add(ChatColor.translateAlternateColorCodes('&', "&7The standard kit, for standard people."));

                ArrayList<String> potionLore = new ArrayList<>();
                potionLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                potionLore.add(Utils.guiSpacer());
                potionLore.add(ChatColor.translateAlternateColorCodes('&', "&7Use potions instead of soups."));

                ArrayList<String> archerLore = new ArrayList<>();
                archerLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                archerLore.add(Utils.guiSpacer());
                archerLore.add(ChatColor.translateAlternateColorCodes('&', "&7The further your target, the more damage you deal."));

                ArrayList<String> switcherLore = new ArrayList<>();
                switcherLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                switcherLore.add(Utils.guiSpacer());
                switcherLore.add(ChatColor.translateAlternateColorCodes('&', "&7Allows you to shoot snowballs at your enemies"));
                switcherLore.add(ChatColor.translateAlternateColorCodes('&', "&7to switch places with them."));

                ArrayList<String> urgalLore = new ArrayList<>();
                urgalLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                urgalLore.add(Utils.guiSpacer());
                urgalLore.add(ChatColor.translateAlternateColorCodes('&', "&7You receive strength after every kill with this kit."));

                ArrayList<String> sonicLore = new ArrayList<>();
                sonicLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                sonicLore.add(Utils.guiSpacer());
                sonicLore.add(ChatColor.translateAlternateColorCodes('&', "&7Gives you speed so you can"));
                sonicLore.add(ChatColor.translateAlternateColorCodes('&', "&run circles around your enemies"));

                ArrayList<String> vikingLore = new ArrayList<>();
                vikingLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                vikingLore.add(Utils.guiSpacer());
                vikingLore.add(ChatColor.translateAlternateColorCodes('&', "&7Strong armor, less powerful weapons."));

                ArrayList<String> stomperLore = new ArrayList<>();
                stomperLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                stomperLore.add(Utils.guiSpacer());
                stomperLore.add(ChatColor.translateAlternateColorCodes('&', "&7Jump off a high structure onto enemies to stomp"));
                stomperLore.add(ChatColor.translateAlternateColorCodes('&', "&7and kill them, if they shift, they will"));
                stomperLore.add(ChatColor.translateAlternateColorCodes('&', "&7not be affected as much."));

                ArrayList<String> fishermanLore = new ArrayList<>();
                fishermanLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                fishermanLore.add(Utils.guiSpacer());
                fishermanLore.add(ChatColor.translateAlternateColorCodes('&', "&7Pull players to you after hooking them."));

                ArrayList<String> kangarooLore = new ArrayList<>();
                kangarooLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                kangarooLore.add(Utils.guiSpacer());
                kangarooLore.add(ChatColor.translateAlternateColorCodes('&', "&7Leap higher by clicking your firework rocket,"));
                kangarooLore.add(ChatColor.translateAlternateColorCodes('&', "&7leap further while shifting."));

                ArrayList<String> snowmanLore = new ArrayList<>();
                snowmanLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                snowmanLore.add(Utils.guiSpacer());
                snowmanLore.add(ChatColor.translateAlternateColorCodes('&', "&7Horrify your enemies by shooting snowballs at them."));

                ArrayList<String> thorLore = new ArrayList<>();
                thorLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                thorLore.add(Utils.guiSpacer());
                thorLore.add(ChatColor.translateAlternateColorCodes('&', "&7Smite your enemies."));

                ArrayList<String> viperLore = new ArrayList<>();
                viperLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                viperLore.add(Utils.guiSpacer());
                viperLore.add(ChatColor.translateAlternateColorCodes('&', "&7Your enemies have a chance to"));
                viperLore.add(ChatColor.translateAlternateColorCodes('&', "&7recieve poison after you attack them."));

                ArrayList<String> ninjaLore = new ArrayList<>();
                ninjaLore.add(ChatColor.translateAlternateColorCodes('&', "&7Availability: &aOwned"));
                ninjaLore.add(Utils.guiSpacer());
                ninjaLore.add(ChatColor.translateAlternateColorCodes('&', "&7Hit your enemies and shift in front"));
                ninjaLore.add(ChatColor.translateAlternateColorCodes('&', "&7of them to teleport behind them."));

                // Setting Up
                pvpMeta.setLore(pvpLore);
                potionMeta.setLore(potionLore);
                archerMeta.setLore(archerLore);
                switcherMeta.setLore(switcherLore);
                urgalMeta.setLore(urgalLore);
                sonicMeta.setLore(sonicLore);
                vikingMeta.setLore(vikingLore);
                stomperMeta.setLore(stomperLore);
                fishermanMeta.setLore(fishermanLore);
                kangarooMeta.setLore(kangarooLore);
                snowmanMeta.setLore(snowmanLore);
                thorMeta.setLore(thorLore);
                viperMeta.setLore(viperLore);
                ninjaMeta.setLore(ninjaLore);

                pvp.setItemMeta(pvpMeta);
                potion.setItemMeta(potionMeta);
                archer.setItemMeta(archerMeta);
                switcher.setItemMeta(switcherMeta);
                urgal.setItemMeta(urgalMeta);
                sonic.setItemMeta(sonicMeta);
                viking.setItemMeta(vikingMeta);
                stomper.setItemMeta(stomperMeta);
                fisherman.setItemMeta(fishermanMeta);
                kangaroo.setItemMeta(kangarooMeta);
                snowman.setItemMeta(snowmanMeta);
                thor.setItemMeta(thorMeta);
                viper.setItemMeta(viperMeta);
                ninja.setItemMeta(ninjaMeta);
                kitInv.addItem(pvp);
                kitInv.addItem(potion);
                kitInv.addItem(archer);
                kitInv.addItem(switcher);
                kitInv.addItem(urgal);
                kitInv.addItem(sonic);
                kitInv.addItem(viking);
                kitInv.addItem(stomper);
                kitInv.addItem(fisherman);
                kitInv.addItem(kangaroo);
                kitInv.addItem(snowman);
                kitInv.addItem(thor);
                kitInv.addItem(viper);
                kitInv.addItem(ninja);

                // Open Inventory
                player.openInventory(kitInv);
            }
        }
    }
}

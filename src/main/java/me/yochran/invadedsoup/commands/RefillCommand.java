package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefillCommand implements CommandExecutor {

    private InvadedSoup plugin;

    public RefillCommand() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("refill")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (!plugin.kit.containsKey(player.getUniqueId())) {
                    Utils.sendMessage(player, "&cYou cannot use that command without a kit selected.");
                } else {
                    Utils.sendMessage(player, "&7You will have the refill box opened in &b5 seconds.");
                    Location location = player.getLocation();
                    int X = location.getBlockX();
                    int Z = location.getBlockZ();
                    plugin.X.put(player.getUniqueId(), X);
                    plugin.Z.put(player.getUniqueId(), Z);
                    plugin.refill.put(player.getUniqueId(), 5.0);
                }
            }
        }
        return true;
    }
}

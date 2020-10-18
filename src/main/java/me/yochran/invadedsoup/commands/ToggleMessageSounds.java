package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleMessageSounds implements CommandExecutor {

    private InvadedSoup plugin;

    public ToggleMessageSounds() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("togglemessagesounds")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (plugin.tms.contains(player.getUniqueId())) {
                    plugin.tms.remove(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOnSounds"));
                } else {
                    plugin.tms.add(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOffSounds"));
                }
            }
        }
        return true;
    }
}

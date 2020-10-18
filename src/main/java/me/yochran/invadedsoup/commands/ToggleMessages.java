package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleMessages implements CommandExecutor {

    private InvadedSoup plugin;

    public ToggleMessages() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("togglemessages")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (plugin.tpm.contains(player.getUniqueId())) {
                    plugin.tpm.remove(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOnMsg"));
                } else {
                    plugin.tpm.add(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOffMsg"));
                }
            }
        }
        return true;
    }
}

package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("soup")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (args.length == 0) {
                    Utils.liner(player);
                    Utils.sendMessage(player, "&a&lInvadedLands Soup:");
                    Utils.spacer(player);
                    Utils.sendMessage(player, "&2Plugin By: &b&oYochran");
                    Utils.sendMessage(player, "&2Plugin Version: &a1.0");
                    Utils.liner(player);
                } else if (args.length == 1) {
                    Utils.liner(player);
                    Utils.sendMessage(player, "&a&lSoup Core Commands:");
                    Utils.spacer(player);
                    Utils.sendMessage(player, "&2 - &7/soup - Get the plugin information.");
                    Utils.sendMessage(player, "&2 - &7/soup help - Display this page.");
                    Utils.liner(player);
                }
            }
        }
        return true;
    }
}

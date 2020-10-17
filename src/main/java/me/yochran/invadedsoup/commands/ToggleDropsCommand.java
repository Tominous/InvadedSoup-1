package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleDropsCommand implements CommandExecutor {

    private InvadedSoup plugin;

    public ToggleDropsCommand() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("toggledrops")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    plugin.dropsOn.add(player.getUniqueId());
                    Utils.sendMessage(player, "&6You have set item dropping to &7TRUE");
                } else {
                    plugin.dropsOn.remove(player.getUniqueId());
                    Utils.sendMessage(player, "&6You have set item dropping to &7FALSE");
                }
            }
        }
        return true;
    }
}

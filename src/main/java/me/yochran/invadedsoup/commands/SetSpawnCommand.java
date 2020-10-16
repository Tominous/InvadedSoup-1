package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private InvadedSoup plugin;

    public SetSpawnCommand() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setsoupspawn")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                if (!sender.hasPermission("invadedsoup.setspawn")) {
                    Utils.sendMessage(sender, "&cYou do not have permission to use that command.");
                } else {
                    Player player = (Player) sender;
                    Double X = player.getLocation().getX();
                    Double Y = player.getLocation().getY();
                    Double Z = player.getLocation().getZ();
                    Utils.sendMessage(player, "&aYou have set the spawnpoint.");
                }
            }
        }
        return true;
    }
}

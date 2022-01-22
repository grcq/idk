package cf.grcq.infection.commands;

import cf.grcq.infection.Infection;
import cf.grcq.infection.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        if (Infection.getInstance().getGameManager().getGame().isRunning()) {
            sender.sendMessage(Util.format("&cThe game is already started. Please wait for the game to end..."));
            return true;
        }

        Player player = ((Player) sender).getPlayer();
        if (Infection.getInstance().getGameManager().getGame().getPlaying().contains(player)) {
            if (Infection.getInstance().getGameManager().getGame().isRunning()) {
                player.sendMessage(Util.format("&cYou cannot leave the game now."));
                return true;
            }

            Infection.getInstance().getGameManager().getGame().getPlaying().remove(player);
            player.sendMessage(Util.format("&cYou have left the game."));
        } else {
            player.sendMessage(Util.format("&aYou have joined the game."));
            Infection.getInstance().getGameManager().getGame().getPlaying().add(player);
        }

        return false;
    }
}

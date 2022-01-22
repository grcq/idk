package cf.grcq.infection.commands;

import cf.grcq.infection.Infection;
import cf.grcq.infection.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EndGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = ((Player) sender).getPlayer();
        if (!Infection.getInstance().getGameManager().getGame().isRunning()) {
            player.setPlayerListName(Util.format("&cThe game is not running."));
            return true;
        }

        for (Player players : Infection.getInstance().getGameManager().getGame().getPlaying()) {
            players.setPlayerListName(players.getName());

            if (Infection.getInstance().getGameManager().isInfected(players)) {
                Infection.getInstance().getGameManager().getGame().getInfected().remove(players);
            }
        }

        Bukkit.broadcastMessage(Util.format("&cThe game has ended!"));

        return false;
    }
}

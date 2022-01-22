package cf.grcq.infection.commands;

import cf.grcq.infection.Infection;
import cf.grcq.infection.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class StartGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        if (Infection.getInstance().getGameManager().getGame().isRunning()) {
            sender.sendMessage(Util.format("&cThe game has already started."));
            return true;
        }

        Infection.getInstance().getGameManager().getGame().setRunning(true);
        int random = new Random().nextInt(Infection.getInstance().getGameManager().getGame().getPlaying().size());

        Player randomPlayer = Infection.getInstance().getGameManager().getGame().getPlaying().get(random - 1);
        randomPlayer.sendMessage(Util.format("&aYou have been infected!"));
        randomPlayer.setPlayerListName(Util.format("&a" + randomPlayer.getName()));

        Bukkit.broadcastMessage(Util.format("&cThe game has started!"));

        return false;
    }
}

package cf.grcq.infection;

import cf.grcq.infection.commands.EndGameCommand;
import cf.grcq.infection.commands.JoinCommand;
import cf.grcq.infection.commands.StartGameCommand;
import cf.grcq.infection.game.GameManager;
import cf.grcq.infection.util.Util;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Infection extends JavaPlugin {

    @Getter private static Infection instance;

    @Getter private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        this.gameManager = new GameManager();

        getCommand("start").setExecutor(new StartGameCommand());
        getCommand("stopgame").setExecutor(new EndGameCommand());
        getCommand("join").setExecutor(new JoinCommand());

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (gameManager.getGame().isRunning()) return;

            if (gameManager.getGame().getPlaying().size() < 2) {
                Bukkit.broadcastMessage(Util.format("&cNot enough players."));
                return;
            }

            Infection.getInstance().getGameManager().getGame().setRunning(true);
            int random = new Random().nextInt(Infection.getInstance().getGameManager().getGame().getPlaying().size());

            Player randomPlayer = Infection.getInstance().getGameManager().getGame().getPlaying().get(random - 1);
            randomPlayer.sendMessage(Util.format("&aYou have been infected!"));
            randomPlayer.setPlayerListName(Util.format("&a" + randomPlayer.getName()));

            Bukkit.broadcastMessage(Util.format("&cThe game has started!"));
        }, 0L, (20L * 30));
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

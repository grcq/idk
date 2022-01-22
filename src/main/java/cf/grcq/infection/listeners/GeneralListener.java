package cf.grcq.infection.listeners;

import cf.grcq.infection.Infection;
import cf.grcq.infection.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GeneralListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!Infection.getInstance().getGameManager().getGame().isRunning()) {
            player.sendMessage(Util.format("&eType /join to join the queue."));
            return;
        }

        Infection.getInstance().getGameManager().infectPlayer(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (Infection.getInstance().getGameManager().getGame().isRunning()) {
            Infection.getInstance().getGameManager().getGame().getPlaying().remove(player);

            if (Infection.getInstance().getGameManager().isInfected(player)) {
                Infection.getInstance().getGameManager().getGame().getInfected().remove(player);
            }

            if (Infection.getInstance().getGameManager().getGame().getPlaying().size() < 2) {
                Infection.getInstance().getGameManager().reset();
                Bukkit.broadcastMessage(Util.format("&cThere are not enough players."));
                return;
            }

            if (Infection.getInstance().getGameManager().getGame().getInfected().size() == 0) {
                Infection.getInstance().getGameManager().reset();
                Bukkit.broadcastMessage(Util.format("&cThe infected player has left."));
            }
        }

    }

}

package cf.grcq.infection.listeners;

import cf.grcq.infection.Infection;
import cf.grcq.infection.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class InfectionListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity().getType() != EntityType.PLAYER && e.getDamager().getType() != EntityType.PLAYER) return;

        Player damager = (Player) e.getDamager();
        Player target = (Player) e.getEntity();

        if (!Infection.getInstance().getGameManager().isInfected(damager)) {
            if (!Infection.getInstance().getGameManager().isInfected(target)) {
                e.setCancelled(true);
            }

            return;
        }

        if (Infection.getInstance().getGameManager().isInfected(target)) {
            e.setCancelled(true);
            damager.sendMessage(Util.format("&cYou cannot damage your team members."));
            return;
        }

        Infection.getInstance().getGameManager().infectPlayer(target);
        target.sendMessage(Util.format("&aYou have been infected."));
        damager.sendMessage(Util.format("&aYou infected " + target.getName() + "."));

        if (Infection.getInstance().getGameManager().getGame().getPlaying().size() == Infection.getInstance().getGameManager().getGame().getInfected().size()) {
            Infection.getInstance().getGameManager().reset();
            Bukkit.broadcastMessage(Util.format("&cEveryone has been infected."));
        }

    }

}

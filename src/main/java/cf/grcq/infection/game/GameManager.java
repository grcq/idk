package cf.grcq.infection.game;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {

    @Getter private Game game;

    public GameManager load() {
        this.game = new Game();

        game.setRunning(false);
        game.setInfected(new ArrayList<>());
        game.setPlaying(new ArrayList<>());

        return this;
    }

    public void infectPlayer(Player player) {
        if (!game.isRunning()) return;

        game.getInfected().add(player);
    }

    public boolean isInfected(Player player) {
        if (!game.isRunning()) return false;

        return game.getInfected().contains(player);
    }

    public void reset() {
        game.setRunning(false);
        game.setInfected(new ArrayList<>());
        game.setPlaying(new ArrayList<>());
    }

}

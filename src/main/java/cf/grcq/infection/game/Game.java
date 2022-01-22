package cf.grcq.infection.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.List;

public class Game {

    @Getter @Setter private boolean running;

    @Getter @Setter private List<Player> playing;
    @Getter @Setter private List<Player> infected;

}

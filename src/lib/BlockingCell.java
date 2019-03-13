package lib;

import config.Interaction;
import config.Sprite;
import javafx.util.Pair;

public class BlockingCell extends Cell{
    private Interaction interaction;

    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position) {
        super(sprite, position);
        this.interaction = null;
    }

    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction  interaction) {
        super(sprite, position);
        this.interaction = interaction;
    }

    public Interaction  getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction  interaction) {
        this.interaction = interaction;
    }
}

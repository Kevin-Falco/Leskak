package lib;

import config.*;
import javafx.util.Pair;

public class BlockingCell extends Cell{
    private Interaction interaction;
    private AnimationSet animationSet;
    private Direction direction;

    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position) {
        super(sprite, position);
        this.interaction = null;
        this.animationSet = AnimationSet.getAnimationSetThatHave(sprite);
        if(animationSet != null)
            this.direction = animationSet.getDirection(sprite);
    }

    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction  interaction) {
        super(sprite, position);
        this.interaction = interaction;
        this.animationSet = AnimationSet.getAnimationSetThatHave(sprite);
        if(animationSet != null)
            this.direction = animationSet.getDirection(sprite);
    }

    public Interaction  getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction  interaction) {
        this.interaction = interaction;
    }

    public AnimationSet getAnimationSet() {
        return animationSet;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        if(animationSet != null)
            this.setSprite(animationSet.getSpriteDirection(direction));
    }
}

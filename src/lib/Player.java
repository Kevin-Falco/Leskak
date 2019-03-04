package lib;

import config.Direction;
import config.Sprite;
import javafx.util.Pair;

public class Player {

    private Pair<Integer, Integer> position;
    private Direction direction = Direction.DOWN;
    private Sprite sprite;

    private static final Player INSTANCE = new Player();
    private static final String NAME = "Leskak";

    private Player(){}

    public Pair<Integer, Integer> getPosition(){
        return this.position;
    }

    public Direction getDirection() {
        return direction;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public static Player getINSTANCE() {
        return Player.INSTANCE;
    }

    public static String getNAME() {
        return Player.NAME;
    }

    public void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }
}

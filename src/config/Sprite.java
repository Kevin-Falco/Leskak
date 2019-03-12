package config;

public enum Sprite {

    UP("sprite/player_up_p1.png"),
    DOWN("sprite/player_down_p1.png"),
    RIGHT("sprite/player_right_p1.png"),
    LEFT("sprite/player_left_p1.png"),

    GRASS("sprite/grass.png"),
    BUSH("sprite/bush.png"),
    TREE("sprite/tree.png"),
    WATER("sprite/water.png"),
    ROCK("sprite/rock.png"),
    BRIDGE("sprite/bridge.png"),

    ROCKET_UP("sprite/rocket_up.png"),
    ROCKET_DOWN("sprite/rocket_down.png"),

    HOUSE_TOP_LEFT("sprite/house_top_left.png"),
    HOUSE_TOP_RIGHT("sprite/house_top_right.png"),
    HOUSE_BOTTOM_LEFT("sprite/house_bottom_left.png"),
    HOUSE_BOTTOM_RIGHT("sprite/house_bottom_right.png"),

    CINEMATIC("sprite/eevee_evolution.jpg"),
    ;

    private String spritePath;

    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}

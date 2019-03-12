package config;

public enum Sprite {

    UP("sprite/player_up_p1.png"),
    DOWN("sprite/player_down_p1.png"),
    RIGHT("sprite/player_right_p1.png"),
    LEFT("sprite/player_left_p1.png"),

    UP_BRIDGE("sprite/player_up_bridge.png"),
    DOWN_BRIDGE("sprite/player_down_bridge.png"),
    RIGHT_BRIDGE("sprite/player_right_bridge.png"),
    LEFT_BRIDGE("sprite/player_left_bridge.png"),

    GRASS("sprite/grass.png"),
    BUSH("sprite/bush.png"),
    TREE("sprite/tree.png"),
    ROCK("sprite/rock.png"),
    BRIDGE("sprite/bridge.png"),

    WATER("sprite/water.png"),
    WATER_LEFT("sprite/water_left.png"),
    WATER_RIGHT("sprite/water_right.png"),

    ROCKET_UP("sprite/rocket_up.png"),
    ROCKET_DOWN("sprite/rocket_down.png"),

    HOUSE_TOP_LEFT("sprite/house_top_left.png"),
    HOUSE_TOP_RIGHT("sprite/house_top_right.png"),
    HOUSE_BOTTOM_LEFT("sprite/house_bottom_left.png"),
    HOUSE_BOTTOM_RIGHT("sprite/house_bottom_right.png"),

    CINEMATIC("sprite/eevee_evolution.jpg"),
    CINEMATIC2("sprite/eevee2.jpg"),
    CINEMATIC3("sprite/eevee3.jpg"),
    ;

    private String spritePath;

    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}

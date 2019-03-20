package config;

public enum Sprite {

    PLAYER_UP_STOP("sprite/player/player_up_stop.png"),
    PLAYER_UP_MOVE_RIGHT("sprite/player/player_up_move_right.png"),
    PLAYER_UP_MOVE_LEFT("sprite/player/player_up_move_left.png"),
    PLAYER_DOWN_STOP("sprite/player/player_down_stop.png"),
    PLAYER_DOWN_MOVE_RIGHT("sprite/player/player_down_move_right.png"),
    PLAYER_DOWN_MOVE_LEFT("sprite/player/player_down_move_left.png"),
    PLAYER_RIGHT_STOP("sprite/player/player_right_stop.png"),
    PLAYER_RIGHT_MOVE_RIGHT("sprite/player/player_right_move_right.png"),
    PLAYER_RIGHT_MOVE_LEFT("sprite/player/player_right_move_left.png"),
    PLAYER_LEFT_STOP("sprite/player/player_left_stop.png"),
    PLAYER_LEFT_MOVE_RIGHT("sprite/player/player_left_move_right.png"),
    PLAYER_LEFT_MOVE_LEFT("sprite/player/player_left_move_left.png"),

    GRASS("sprite/env/grass.png"),
    BUSH("sprite/env/bush.png"),
    ROCK("sprite/env/rock.png"),
    BRIDGE("sprite/env/bridge.png"),

    TREE("sprite/tree/tree.png"),
    TREE_UP("sprite/tree/tree_up.png"),
    TREE_UP_LEFT("sprite/tree/tree_up_left.png"),
    TREE_UP_LEFT_RIGHT("sprite/tree/tree_up_left_right.png"),
    TREE_UP_RIGHT("sprite/tree/tree_up_right.png"),
    TREE_DOWN("sprite/tree/tree_down.png"),
    TREE_DOWN_LEFT("sprite/tree/tree_down_left.png"),
    TREE_DOWN_LEFT_RIGHT("sprite/tree/tree_down_left_right.png"),
    TREE_DOWN_RIGHT("sprite/tree/tree_down_right.png"),
    TREE_LEFT("sprite/tree/tree_left.png"),
    TREE_LEFT_UP_DOWN("sprite/tree/tree_left_up_down.png"),
    TREE_RIGHT("sprite/tree/tree_right.png"),
    TREE_RIGHT_UP_DOWN("sprite/tree/tree_right_up_down.png"),

    WATER("sprite/water/water.png"),
    WATER_UP("sprite/water/water_up.png"),
    WATER_UP_LEFT("sprite/water/water_up_left.png"),
    WATER_UP_LEFT_RIGHT("sprite/water/water_up_left_right.png"),
    WATER_UP_RIGHT("sprite/water/water_up_right.png"),
    WATER_DOWN("sprite/water/water_down.png"),
    WATER_DOWN_LEFT("sprite/water/water_down_left.png"),
    WATER_DOWN_LEFT_RIGHT("sprite/water/water_down_left_right.png"),
    WATER_DOWN_RIGHT("sprite/water/water_down_right.png"),
    WATER_LEFT("sprite/water/water_left.png"),
    WATER_LEFT_UP_DOWN("sprite/water/water_left_up_down.png"),
    WATER_RIGHT("sprite/water/water_right.png"),
    WATER_RIGHT_UP_DOWN("sprite/water/water_right_up_down.png"),

    HOUSE_UP_LEFT("sprite/house/house_up_left.png"),
    HOUSE_UP_RIGHT("sprite/house/house_up_right.png"),
    HOUSE_DOWN_LEFT("sprite/house/house_down_left.png"),
    HOUSE_DOWN_RIGHT("sprite/house/house_down_right.png"),

    FOG("sprite/fog.jpg"),

    ROCKET_UP("sprite/rocket_up.png"),
    ROCKET_DOWN("sprite/rocket_down.png"),

    CINEMATIC("sprite/eevee_evolution.jpg"),
    CINEMATIC2("sprite/eevee2.jpg"),
    CINEMATIC3("sprite/eevee3.jpg"),

    LOAD("sprite/load.jpg"),
    ;

    private String spritePath;

    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}

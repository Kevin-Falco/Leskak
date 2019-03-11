package config;

public enum Sprite {

    UP("sprite/player_up_p1.png"),
    DOWN("sprite/player_down_p1.png"),
    RIGHT("sprite/player_right_p1.png"),
    LEFT("sprite/player_left_p1.png"),

    GRASS("sprite/grass.png"),
    BUSH("sprite/bush.png"),
    WATER("sprite/water.png"),
    TREE("sprite/tree.png"),
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

package config;

public enum Sprite {

    UP("sprite/persoUp.JPG"),
    DOWN("sprite/persoDown.JPG"),
    RIGHT("sprite/persoRight.jpg"),
    LEFT("sprite/persoLeft.JPG"),

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

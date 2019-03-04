package config;

public enum Sprite {

    UP("sprite/persoUp.JPG"),
    DOWN("sprite/persoDown.JPG"),
    RIGHT("sprite/persoRight.jpg"),
    LEFT("sprite/persoLeft.JPG"),
    PNJ1("sprite/sprite.jpg"),
    UP_ARROW("sprite/haut.png"),
    DOWN_ARROW("sprite/bas.jpg"),
    RIGHT_ARROW("sprite/droite.png"),
    LEFT_ARROW("sprite/gauche.png");

    private String spritePath;

    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}

package config;

public enum Sprite {

    UP("sprite/persoUp.JPG"),
    DOWN("sprite/persoDown.JPG"),
    RIGHT("sprite/persoRight.jpg"),
    LEFT("sprite/persoLeft.JPG"),
    PNJ1("sprite/sprite.jpg"),
    PNJ2("sprite/pikachu.PNG"),
    PNJ3("sprite/dracaufeu.PNG"),
    PNJ4("sprite/ectoplasma.PNG"),
    UP_ARROW("sprite/haut.png"),
    DOWN_ARROW("sprite/bas.jpg"),
    RIGHT_ARROW("sprite/droite.png"),
    LEFT_ARROW("sprite/gauche.png"),
    ROCKET1("sprite/fusee1.png"),
    ROCKET2("sprite/fusee.png"),
    GRASS("sprite/grass.png"),
    WATER("sprite/water.jpeg"),
    BUSH("sprite/bush.jpg")
    ;

    private String spritePath;

    Sprite(String spritePath) {
        this.spritePath = spritePath;
    }

    public String getSpritePath() {
        return this.spritePath;
    }
}

package lib;

import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Cell {
    private ImageView image;
    private Sprite sprite;
    private ImageView sprite2;
    private Pair<Integer, Integer> position;
    private boolean inFogOfWar = false;

    public Cell(Sprite sprite, Pair<Integer, Integer> position) {
        this.image = new ImageView( sprite.getSpritePath());
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(50);//(37);
        this.sprite2 = new ImageView(Sprite.FOG.getSpritePath());
        this.sprite2.setPreserveRatio(true);
        this.sprite2.setFitWidth(50);//(37);
        this.sprite = sprite;//(37);
        this.position = position;
    }

    public ImageView getImage() {
        return image;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.image.setImage(new Image( sprite.getSpritePath()));
    }

    public ImageView getSprite2() {
        return sprite2;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public boolean isInFogOfWar() {
        return inFogOfWar;
    }

    public void setInFogOfWar(boolean inFogOfWar) {
        this.inFogOfWar = inFogOfWar;
    }
}

package lib;

import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Cell {
    private ImageView image;
    private Sprite sprite;
    private Pair<Integer, Integer> position;

    protected Cell(Pair<Integer, Integer> position){
        this.position = position;
    }

    public Cell(Sprite sprite, Pair<Integer, Integer> position) {
        this.image = new ImageView( sprite.getSpritePath());
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(50);
        this.sprite = sprite;
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

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    protected void setImage(ImageView image) {
        this.image = image;
    }
}

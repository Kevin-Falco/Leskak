package lib;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class Cell {
    private ImageView sprite;
    private Pair<Integer, Integer> position;

    public Cell(ImageView sprite, Pair<Integer, Integer> position) {
        this.sprite = sprite;
        this.position = position;
        this.sprite.setPreserveRatio(true);
        this.sprite.setFitWidth(50);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
        this.sprite.setPreserveRatio(true);
        this.sprite.setFitWidth(50);
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }
}

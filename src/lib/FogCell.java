package lib;

import config.Sprite;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public class FogCell extends Cell {

    public FogCell(Pair<Integer, Integer> position) {
        super(position);
        ImageView imageView = new ImageView( Sprite.FOG.getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        this.setImage(imageView );
        this.setSprite(Sprite.FOG);
    }
}

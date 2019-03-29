package lib;

import config.Sprite;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

/**
 * Classe représentant une cellule du brouillard de guerre.
 */
public class FogCell extends Cell {

    /**
     * Constructeur de la cellule du brouillard de guerre.
     * @param position position de la cellule du brouillard de guerre
     */
    public FogCell(Pair<Integer, Integer> position) {
        super(position);
        ImageView imageView = new ImageView( Sprite.FOG.getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        this.setImage(imageView );
        this.setSprite(Sprite.FOG);
    }
}

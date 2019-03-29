package lib;

import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

/**
 * Classe représentant une cellule sur laquelle Leskak peut marcher, ayant un sprite et une position.
 */
public class Cell {

    /**
     * ImageView de la cellule.
     */
    private ImageView image;

    /**
     * Sprite de la cellule.
     */
    private Sprite sprite;

    /**
     * Position de la cellule sur la grille.
     */
    private Pair<Integer, Integer> position;

    /**
     * Constructeur de la cellule avec sa position en paramètre.
     * @param position position de la cellule sur la grille
     */
    protected Cell(Pair<Integer, Integer> position){
        this.position = position;
    }

    /**
     * Constructeur de la cellule avec sa position en paramètre et son sprite.
     * @param sprite sprite de la cellule
     * @param position position de la cellule sur la grille
     */
    public Cell(Sprite sprite, Pair<Integer, Integer> position) {
        this.image = new ImageView( sprite.getSpritePath());
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(50);
        this.sprite = sprite;
        this.position = position;
    }

    /**
     * Getter de l'ImageView de la cellule.
     * @return ImageView
     */
    public ImageView getImage() {
        return this.image;
    }

    /**
     * Setter de l'ImageView de la cellule.
     * @param image image de la cellule
     */
    protected void setImage(ImageView image) {
        this.image = image;
    }

    /**
     * Getter du sprite de la cellule.
     * @return Sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Setter du sprite de la cellule.
     * @param sprite sprite de la cellule
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.image.setImage(new Image( sprite.getSpritePath()));
    }

    /**
     * Getter de la position de la cellule.
     * @return Pair
     */
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * Setter de la position de la cellule .
     * @param position position de la cellule sur la grille
     */
    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }
}

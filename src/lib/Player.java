package lib;

import config.Direction;
import config.MapConfig;
import config.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe définissant le comportement du joueur dans le jeu. Cette classe est un singleton.
 */
public class Player {

    /**
     * Position du joueur sur la carte sur laquelle il est.
     */
    private Pair<Integer, Integer> position;

    /**
     * Direction du joueur, initialisée de base vers le bas lorsque Leskak apparait dans le jeu.
     */
    private Direction direction = Direction.DOWN;

    /**
     * Sprite de Leskak, il pourra changer de tenue au cours de l'aventure.
     */
    private Sprite sprite;

    /**
     * Liste des apparences disponibles pour Leskak, initalisée de base avec uniquement sa première tenue.
     */
    private ArrayList<Integer> skinAvailables = new ArrayList<>(Collections.singletonList(0));

    /**
     * Apparence actuelle de Leskak, initialisée de base avec sa première tenue.
     */
    private Integer currentSkin = 0;

    /**
     * ImageView de Leskak.
     */
    private ImageView image = new ImageView();

    /**
     * Instance du joueur, permet à la classe d'être un singleton.
     */
    private static final Player INSTANCE = new Player();

    /**
     * Nom du joueur, valant toujours Leskak d'où le nom du jeu.
     */
    private static final String NAME = "Leskak";

    /**
     * Getter de la position actuelle de Leskak sur la carte.
     * @return Pair
     */
    public Pair<Integer, Integer> getPosition(){
        return this.position;
    }

    /**
     * Getter de la direction de Leskak à cet instant.
     * @return Direction
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Renvoie le sprite de Leskak, donc son apparence à cet instant là.
     * @return Sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Getter de l'ImageView de Leskak.
     * @return ImageView
     */
    public ImageView getImage() {
        return this.image;
    }

    /**
     * Getter de la liste des apparences disponibles à cet instant pour Leskak.
     * @return ArrayList
     */
    public ArrayList<Integer> getSkinAvailables() {
        return this.skinAvailables;
    }

    /**
     * Renvoie la position de la prochaine apparence disponible pour Leskak, afin qu'il puisse en changer.
     * @return int
     */
    public Integer getNextSkinAvailable() {
        return this.currentSkin.equals(this.skinAvailables.get(this.skinAvailables.size() -1))
                ? this.skinAvailables.get(0) : this.skinAvailables.get(this.skinAvailables.indexOf(this.currentSkin)+1);
    }

    /**
     * Getter de l'indice de l'apparence actuelle de Leskak.
     * @return int
     */
    public Integer getCurrentSkin() {
        return currentSkin;
    }

    /**
     * Renvoie l'instance du joueur, ce qui permet à la classe d'être un singleton.
     * @return Player
     */
    public static Player getINSTANCE() {
        return Player.INSTANCE;
    }

    /**
     * Setter de la position pour mettre à jour la position de Leskak sur la carte.
     * @param position nouvelle position de Leskak
     */
    public void setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    /**
     * Setter de la position pour mettre à jour la direction de Leskak sur la carte.
     * @param direction nouvelle direction de Leskak
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Setter du nouveau sprite de Leskak.
     * @param sprite nouvelle apparence pour Leskak
     */
    public void setSprite(final Sprite sprite) {
        this.sprite = sprite;
        this.image.setImage(new Image(this.sprite.getSpritePath()));
        this.image.setPreserveRatio(true);
        this.image.setFitWidth(50);
    }

    /**
     * Setter de l'ImageView de Leskak.
     * @param image nouvelle imageView de Leskak
     */
    public void setImage(ImageView image) {
        this.image = image;
    }

    /**
     * Permet de mettre Leskak au premier plan de la grille, quelque soit le sprite derrière.
     * @param nbMap carte sur laquelle est Leskak
     */
    public void setPlayerOnTop(int nbMap){
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().remove(this.image);
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().add(this.image);
    }

    /**
     * Setter de l'apparence actuelle de Leskak afin qu'il puisse changer d'apparence grâce à une touche.
     * @param currentSkin nouvelle apparence de Leskak, parmi celles disponibles
     */
    public void setCurrentSkin(Integer currentSkin) {
        this.currentSkin = currentSkin;
    }
}

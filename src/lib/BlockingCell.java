package lib;

import config.*;
import javafx.util.Pair;

/**
 * Class représentant une case bloquante sur la grille, où Leskak ne peut pas aller, qui possède une direction dans
 * laquelle elle pointe ainsi qu'une possible intéraction.
 */
public class BlockingCell extends Cell {

    /**
     * Intéraction possible de la cellule bloquante.
     */
    private Interaction interaction;

    /**
     * AnimationSet de la cellule bloquante (sprite pointant vers le haut, bas gauche, droite).
     */
    private AnimationSet animationSet;

    /**
     * Direction vers laquelle pointe la cellule bloquante.
     */
    private Direction direction;

    /**
     * Constructeur de la cellule bloquante avec son sprite ainsi que sa position, sans intéraction.
     * @param sprite sprite de la cellule bloquante
     * @param position position de la cellule bloquante
     */
    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position) {
        super(sprite, position);
        this.interaction = null;
        this.animationSet = AnimationSet.getAnimationSetThatHave(sprite);
        if(this.animationSet != null)
            this.direction = this.animationSet.getDirection(sprite);
    }

    /**
     * Constructeur de la cellule bloquante avec son sprite ainsi que sa position, avec intéraction.
     * @param sprite sprite de la cellule bloquante
     * @param position position de la cellule bloquante
     * @param interaction intéraction de la cellule bloquante
     */
    public BlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction  interaction) {
        super(sprite, position);
        this.interaction = interaction;
        this.animationSet = AnimationSet.getAnimationSetThatHave(sprite);
        if(this.animationSet != null)
            this.direction = this.animationSet.getDirection(sprite);
    }

    /**
     * Getter de l'intéraction de la cellule bloquante.
     * @return Interaction
     */
    public Interaction  getInteraction() {
        return this.interaction;
    }

    /**
     * Setter de l'intéraction de la cellule bloquante.
     * @param interaction intéraction à placer sur la cellule bloquante
     */
    public void setInteraction(Interaction  interaction) {
        this.interaction = interaction;
    }

    /**
     * Setter de la direction de la cellule bloquante, utile pour les personnages non jouables par exemple.
     * @param direction nouvelle direction de la cellule bloquante
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
        if(this.animationSet != null)
            this.setSprite(animationSet.getSpriteDirection(direction));
    }
}

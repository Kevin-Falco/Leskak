package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumération des sprites qui peuvent être adaptables, c'est à dire où il existe plusieurs versions du même sprite selon
 * la direction qu'il doit avoir.
 */
public enum SpriteSet {
    TREE_SET(new ArrayList<>(Arrays.asList(Sprite.TREE, Sprite.TREE_UP, Sprite.TREE_DOWN, Sprite.TREE_RIGHT, Sprite.TREE_LEFT,
            Sprite.TREE_UP_RIGHT, Sprite.TREE_UP_LEFT, Sprite.TREE_UP_LEFT_RIGHT, Sprite.TREE_DOWN_RIGHT, Sprite.TREE_DOWN_LEFT,
            Sprite.TREE_DOWN_LEFT_RIGHT, Sprite.TREE_RIGHT_UP_DOWN, Sprite.TREE_LEFT_UP_DOWN, Sprite.TREE_UP_DOWN, Sprite.TREE_LEFT_RIGHT ))),
    WATER_SET(new ArrayList<>(Arrays.asList(Sprite.WATER, Sprite.WATER_UP, Sprite.WATER_DOWN, Sprite.WATER_RIGHT, Sprite.WATER_LEFT,
             Sprite.WATER_UP_RIGHT, Sprite.WATER_UP_LEFT, Sprite.WATER_UP_LEFT_RIGHT, Sprite.WATER_DOWN_RIGHT, Sprite.WATER_DOWN_LEFT,
             Sprite.WATER_DOWN_LEFT_RIGHT, Sprite.WATER_RIGHT_UP_DOWN, Sprite.WATER_LEFT_UP_DOWN, Sprite.WATER_UP_DOWN, Sprite.WATER_LEFT_RIGHT ))),
    TREE2_SET(new ArrayList<>(Arrays.asList(Sprite.TREE2, Sprite.TREE2_UP, Sprite.TREE2_DOWN, Sprite.TREE2_RIGHT, Sprite.TREE2_LEFT,
             Sprite.TREE2_UP_RIGHT, Sprite.TREE2_UP_LEFT, Sprite.TREE2_UP_LEFT_RIGHT, Sprite.TREE2_DOWN_RIGHT, Sprite.TREE2_DOWN_LEFT,
             Sprite.TREE2_DOWN_LEFT_RIGHT, Sprite.TREE2_RIGHT_UP_DOWN, Sprite.TREE2_LEFT_UP_DOWN, Sprite.TREE2_UP_DOWN, Sprite.TREE2_LEFT_RIGHT ))),
    TREE3_SET(new ArrayList<>(Arrays.asList(Sprite.TREE3, Sprite.TREE3_UP, Sprite.TREE3_DOWN, Sprite.TREE3_RIGHT, Sprite.TREE3_LEFT,
            Sprite.TREE3_UP_RIGHT, Sprite.TREE3_UP_LEFT, Sprite.TREE3_UP_LEFT_RIGHT, Sprite.TREE3_DOWN_RIGHT, Sprite.TREE3_DOWN_LEFT,
            Sprite.TREE3_DOWN_LEFT_RIGHT, Sprite.TREE3_RIGHT_UP_DOWN, Sprite.TREE3_LEFT_UP_DOWN, Sprite.TREE3_UP_DOWN, Sprite.TREE3_LEFT_RIGHT ))),
    TREE4_SET(new ArrayList<>(Arrays.asList(Sprite.TREE4, Sprite.TREE4_UP, Sprite.TREE4_DOWN, Sprite.TREE4_RIGHT, Sprite.TREE4_LEFT,
            Sprite.TREE4_UP_RIGHT, Sprite.TREE4_UP_LEFT, Sprite.TREE4_UP_LEFT_RIGHT, Sprite.TREE4_DOWN_RIGHT, Sprite.TREE4_DOWN_LEFT,
            Sprite.TREE4_DOWN_LEFT_RIGHT, Sprite.TREE4_RIGHT_UP_DOWN, Sprite.TREE4_LEFT_UP_DOWN, Sprite.TREE4_UP_DOWN, Sprite.TREE4_LEFT_RIGHT ))),
    WATER2_SET(new ArrayList<>(Arrays.asList(Sprite.WATER2, Sprite.WATER2_UP, Sprite.WATER2_DOWN, Sprite.WATER2_RIGHT, Sprite.WATER2_LEFT,
            Sprite.WATER2_UP_RIGHT, Sprite.WATER2_UP_LEFT, Sprite.WATER2_UP_LEFT_RIGHT, Sprite.WATER2_DOWN_RIGHT, Sprite.WATER2_DOWN_LEFT,
            Sprite.WATER2_DOWN_LEFT_RIGHT, Sprite.WATER2_RIGHT_UP_DOWN, Sprite.WATER2_LEFT_UP_DOWN, Sprite.WATER2_UP_DOWN, Sprite.WATER2_LEFT_RIGHT ))),
    CAVE_SET(new ArrayList<>(Arrays.asList(Sprite.CAVE, Sprite.CAVE_UP, Sprite.CAVE_DOWN, Sprite.CAVE_RIGHT, Sprite.CAVE_LEFT,
            Sprite.CAVE_UP_RIGHT, Sprite.CAVE_UP_LEFT, Sprite.CAVE_UP_LEFT_RIGHT, Sprite.CAVE_DOWN_RIGHT, Sprite.CAVE_DOWN_LEFT,
            Sprite.CAVE_DOWN_LEFT_RIGHT, Sprite.CAVE_RIGHT_UP_DOWN, Sprite.CAVE_LEFT_UP_DOWN, Sprite.CAVE_UP_DOWN, Sprite.CAVE_LEFT_RIGHT ))),
    ;

    /**
     * Liste de tous les sprites d'un même élément.
     */
    private List<Sprite> sprites;

    /**
     * Constructeur du SpriteSet avec en paramètre la liste des sprites du même élément.
     * @param sprites liste des sprites
     */
    SpriteSet(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Renvoie true si l sprite est contenu par le SpriteSet, sinon false.
     * @param sprite sprite à chercher
     * @return boolean
     */
    public boolean contains(Sprite sprite){
        if(sprite == null) return false;
        return this.sprites.contains(sprite);
    }

    /**
     * Getter du sprite pointé vers le haut.
     * @return Sprite
     */
    public Sprite getUp(){ return this.sprites.get(1); }

    /**
     * Getter du sprite pointé vers le bas.
     * @return Sprite
     */
    public Sprite getDown(){ return this.sprites.get(2); }

    /**
     * Getter du sprite pointé vers la droite.
     * @return Sprite
     */
    public Sprite getRight(){ return this.sprites.get(3); }

    /**
     * Getter du sprite pointé vers la gauche.
     * @return Sprite
     */
    public Sprite getLeft(){ return this.sprites.get(4); }

    /**
     * Getter du sprite pointé vers le haut et la droite.
     * @return Sprite
     */
    public Sprite getUpRight(){ return this.sprites.get(5); }

    /**
     * Getter du sprite pointé vers le haut et la gauche.
     * @return Sprite
     */
    public Sprite getUpLeft(){ return this.sprites.get(6); }

    /**
     * Getter du sprite pointé vers le haut, la droite et la gauche.
     * @return Sprite
     */
    public Sprite getUpLeftRight(){ return this.sprites.get(7); }

    /**
     * Getter du sprite pointé vers le bas et la droite.
     * @return Sprite
     */
    public Sprite getDownRight(){ return this.sprites.get(8); }

    /**
     * Getter du sprite pointé vers le bas et la gauche.
     * @return Sprite
     */
    public Sprite getDownLeft(){ return this.sprites.get(9); }

    /**
     * Getter du sprite pointé vers le bas, la droite et la gauche.
     * @return Sprite
     */
    public Sprite getDownLeftRight(){ return this.sprites.get(10); }

    /**
     * Getter du sprite pointé vers la droite, le haut et le bas.
     * @return Sprite
     */
    public Sprite getRightUpDown(){ return this.sprites.get(11); }

    /**
     * Getter du sprite pointé vers la gauche, le haut et le bas.
     * @return Sprite
     */
    public Sprite getLeftUpDown(){ return this.sprites.get(12); }

    /**
     * Getter du sprite pointé vers le haut et le bas.
     * @return Sprite
     */
    public Sprite getUpDown(){ return this.sprites.get(13); }

    /**
     * Getter du sprite pointé vers la droite et la gauche.
     * @return Sprite
     */
    public Sprite getLeftRight(){ return this.sprites.get(14); }
}

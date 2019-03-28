package config;

/**
 * Enumération de l'ensemble des animations du jeu, c'est à dire toutes celles de Leskak et de ses différents skins, de tous
 * les animaux et pnj du jeu ainsi que du pacman et des fantômes dans celui-ci.
 */
public enum AnimationSet {

    // Tenues que peut avoir Leskak + Pacman + Panda
    PLAYER_MOVE_RIGHT(Sprite.PLAYER_UP_MOVE_RIGHT, Sprite.PLAYER_DOWN_MOVE_RIGHT, Sprite.PLAYER_RIGHT_MOVE_RIGHT, Sprite.PLAYER_LEFT_MOVE_RIGHT),
    PLAYER_STOP2(Sprite.PLAYER_UP_STOP, Sprite.PLAYER_DOWN_STOP, Sprite.PLAYER_RIGHT_STOP, Sprite.PLAYER_LEFT_STOP),
    PLAYER_MOVE_LEFT(Sprite.PLAYER_UP_MOVE_LEFT, Sprite.PLAYER_DOWN_MOVE_LEFT, Sprite.PLAYER_RIGHT_MOVE_LEFT, Sprite.PLAYER_LEFT_MOVE_LEFT),
    PLAYER_STOP(Sprite.PLAYER_UP_STOP, Sprite.PLAYER_DOWN_STOP, Sprite.PLAYER_RIGHT_STOP, Sprite.PLAYER_LEFT_STOP),

    PLAYER2_MOVE_RIGHT(Sprite.PLAYER2_UP_MOVE_RIGHT, Sprite.PLAYER2_DOWN_MOVE_RIGHT, Sprite.PLAYER2_RIGHT_MOVE_RIGHT, Sprite.PLAYER2_LEFT_MOVE_RIGHT),
    PLAYER2_STOP2(Sprite.PLAYER2_UP_STOP, Sprite.PLAYER2_DOWN_STOP, Sprite.PLAYER2_RIGHT_STOP, Sprite.PLAYER2_LEFT_STOP),
    PLAYER2_MOVE_LEFT(Sprite.PLAYER2_UP_MOVE_LEFT, Sprite.PLAYER2_DOWN_MOVE_LEFT, Sprite.PLAYER2_RIGHT_MOVE_LEFT, Sprite.PLAYER2_LEFT_MOVE_LEFT),
    PLAYER2_STOP(Sprite.PLAYER2_UP_STOP, Sprite.PLAYER2_DOWN_STOP, Sprite.PLAYER2_RIGHT_STOP, Sprite.PLAYER2_LEFT_STOP),

    PLAYER3_MOVE_RIGHT(Sprite.PLAYER3_UP_MOVE_RIGHT, Sprite.PLAYER3_DOWN_MOVE_RIGHT, Sprite.PLAYER3_RIGHT_MOVE_RIGHT, Sprite.PLAYER3_LEFT_MOVE_RIGHT),
    PLAYER3_STOP2(Sprite.PLAYER3_UP_STOP, Sprite.PLAYER3_DOWN_STOP, Sprite.PLAYER3_RIGHT_STOP, Sprite.PLAYER3_LEFT_STOP),
    PLAYER3_MOVE_LEFT(Sprite.PLAYER3_UP_MOVE_LEFT, Sprite.PLAYER3_DOWN_MOVE_LEFT, Sprite.PLAYER3_RIGHT_MOVE_LEFT, Sprite.PLAYER3_LEFT_MOVE_LEFT),
    PLAYER3_STOP(Sprite.PLAYER3_UP_STOP, Sprite.PLAYER3_DOWN_STOP, Sprite.PLAYER3_RIGHT_STOP, Sprite.PLAYER3_LEFT_STOP),

    PACMAN_MOVE_RIGHT(Sprite.PACMAN_UP_MOVE_RIGHT, Sprite.PACMAN_DOWN_MOVE_RIGHT, Sprite.PACMAN_RIGHT_MOVE_RIGHT, Sprite.PACMAN_LEFT_MOVE_RIGHT),
    PACMAN_STOP2(Sprite.PACMAN_UP_STOP, Sprite.PACMAN_DOWN_STOP, Sprite.PACMAN_RIGHT_STOP, Sprite.PACMAN_LEFT_STOP),
    PACMAN_MOVE_LEFT(Sprite.PACMAN_UP_MOVE_LEFT, Sprite.PACMAN_DOWN_MOVE_LEFT, Sprite.PACMAN_RIGHT_MOVE_LEFT, Sprite.PACMAN_LEFT_MOVE_LEFT),
    PACMAN_STOP(Sprite.PACMAN_UP_STOP, Sprite.PACMAN_DOWN_STOP, Sprite.PACMAN_RIGHT_STOP, Sprite.PACMAN_LEFT_STOP),

    PANDA_MOVE_RIGHT(Sprite.PANDA_UP_MOVE_RIGHT, Sprite.PANDA_DOWN_MOVE_RIGHT, Sprite.PANDA_RIGHT_MOVE_RIGHT, Sprite.PANDA_LEFT_MOVE_RIGHT),
    PANDA_STOP2(Sprite.PANDA_UP_STOP, Sprite.PANDA_DOWN_STOP, Sprite.PANDA_RIGHT_STOP, Sprite.PANDA_LEFT_STOP),
    PANDA_MOVE_LEFT(Sprite.PANDA_UP_MOVE_LEFT, Sprite.PANDA_DOWN_MOVE_LEFT, Sprite.PANDA_RIGHT_MOVE_LEFT, Sprite.PANDA_LEFT_MOVE_LEFT),
    PANDA_STOP(Sprite.PANDA_UP_STOP, Sprite.PANDA_DOWN_STOP, Sprite.PANDA_RIGHT_STOP, Sprite.PANDA_LEFT_STOP),

    // Sprites des 20 personnages non jouables du jeu
    PNJ1(Sprite.PNJ1_UP, Sprite.PNJ1_DOWN, Sprite.PNJ1_RIGHT, Sprite.PNJ1_LEFT),
    PNJ2(Sprite.PNJ2_UP, Sprite.PNJ2_DOWN, Sprite.PNJ2_RIGHT, Sprite.PNJ2_LEFT),
    PNJ3(Sprite.PNJ3_UP, Sprite.PNJ3_DOWN, Sprite.PNJ3_RIGHT, Sprite.PNJ3_LEFT),
    PNJ4(Sprite.PNJ4_UP, Sprite.PNJ4_DOWN, Sprite.PNJ4_RIGHT, Sprite.PNJ4_LEFT),
    PNJ5(Sprite.PNJ5_UP, Sprite.PNJ5_DOWN, Sprite.PNJ5_RIGHT, Sprite.PNJ5_LEFT),
    PNJ6(Sprite.PNJ6_UP, Sprite.PNJ6_DOWN, Sprite.PNJ6_RIGHT, Sprite.PNJ6_LEFT),
    PNJ7(Sprite.PNJ7_UP, Sprite.PNJ7_DOWN, Sprite.PNJ7_RIGHT, Sprite.PNJ7_LEFT),
    PNJ8(Sprite.PNJ8_UP, Sprite.PNJ8_DOWN, Sprite.PNJ8_RIGHT, Sprite.PNJ8_LEFT),
    PNJ9(Sprite.PNJ9_UP, Sprite.PNJ9_DOWN, Sprite.PNJ9_RIGHT, Sprite.PNJ9_LEFT),
    PNJ10(Sprite.PNJ10_UP, Sprite.PNJ10_DOWN, Sprite.PNJ10_RIGHT, Sprite.PNJ10_LEFT),
    PNJ11(Sprite.PNJ11_UP, Sprite.PNJ11_DOWN, Sprite.PNJ11_RIGHT, Sprite.PNJ11_LEFT),
    PNJ12(Sprite.PNJ12_UP, Sprite.PNJ12_DOWN, Sprite.PNJ12_RIGHT, Sprite.PNJ12_LEFT),
    PNJ13(Sprite.PNJ13_UP, Sprite.PNJ13_DOWN, Sprite.PNJ13_RIGHT, Sprite.PNJ13_LEFT),
    PNJ14(Sprite.PNJ14_UP, Sprite.PNJ14_DOWN, Sprite.PNJ14_RIGHT, Sprite.PNJ14_LEFT),
    PNJ15(Sprite.PNJ15_UP, Sprite.PNJ15_DOWN, Sprite.PNJ15_RIGHT, Sprite.PNJ15_LEFT),
    PNJ16(Sprite.PNJ16_UP, Sprite.PNJ16_DOWN, Sprite.PNJ16_RIGHT, Sprite.PNJ16_LEFT),
    PNJ17(Sprite.PNJ17_UP, Sprite.PNJ17_DOWN, Sprite.PNJ17_RIGHT, Sprite.PNJ17_LEFT),
    PNJ18(Sprite.PNJ18_UP, Sprite.PNJ18_DOWN, Sprite.PNJ18_RIGHT, Sprite.PNJ18_LEFT),
    PNJ19(Sprite.PNJ19_UP, Sprite.PNJ19_DOWN, Sprite.PNJ19_RIGHT, Sprite.PNJ19_LEFT),
    PNJ20(Sprite.PNJ20_UP, Sprite.PNJ20_DOWN, Sprite.PNJ20_RIGHT, Sprite.PNJ20_LEFT),

    // Sprites des différents animaux du jeu
    WHITE_CAT(Sprite.WHITE_CAT_UP, Sprite.WHITE_CAT_DOWN, Sprite.WHITE_CAT_RIGHT, Sprite.WHITE_CAT_LEFT),
    GREY_CAT(Sprite.GREY_CAT_UP, Sprite.GREY_CAT_DOWN, Sprite.GREY_CAT_RIGHT, Sprite.GREY_CAT_LEFT),
    BLACK_CAT(Sprite.BLACK_CAT_UP, Sprite.BLACK_CAT_DOWN, Sprite.BLACK_CAT_RIGHT, Sprite.BLACK_CAT_LEFT),
    SNAKE(Sprite.SNAKE_UP, Sprite.SNAKE_DOWN, Sprite.SNAKE_RIGHT, Sprite.SNAKE_LEFT),
    FOX(Sprite.FOX_UP, Sprite.FOX_DOWN, Sprite.FOX_RIGHT, Sprite.FOX_LEFT),
    CHICKEN(Sprite.CHICKEN_UP, Sprite.CHICKEN_DOWN, Sprite.CHICKEN_RIGHT, Sprite.CHICKEN_LEFT),

    // Sprites des fantômes du Pacman
    BLUE_GHOST(Sprite.BLUE_GHOST_UP, Sprite.BLUE_GHOST_DOWN, Sprite.BLUE_GHOST_RIGHT, Sprite.BLUE_GHOST_LEFT),
    ORANGE_GHOST(Sprite.ORANGE_GHOST_UP, Sprite.ORANGE_GHOST_DOWN, Sprite.ORANGE_GHOST_RIGHT, Sprite.ORANGE_GHOST_LEFT),
    PINK_GHOST(Sprite.PINK_GHOST_UP, Sprite.PINK_GHOST_DOWN, Sprite.PINK_GHOST_RIGHT, Sprite.PINK_GHOST_LEFT),
    RED_GHOST(Sprite.RED_GHOST_UP, Sprite.RED_GHOST_DOWN, Sprite.RED_GHOST_RIGHT, Sprite.RED_GHOST_LEFT),
    ;

    /**
     * Sprite pointant vers le haut.
     */
    private Sprite up;

    /**
     * Sprite pointant vers le bas.
     */
    private Sprite down;

    /**
     * Sprite pointant vers ka droite.
     */
    private Sprite right;

    /**
     * Sprite pointant vers la gauche.
     */
    private Sprite left;

    /**
     * Nombre d'animations de base d'un sprite animé.
     */
    private static int nbAnim = 0;

    /**
     * Nombre maximal d'animations d'un sprite animé dans une direction.
     */
    private static final int NB_MAX_ANIM = 4;

    /**
     * Constructeur d'AnimationSet permettant de définir tous les sprites d'un éléments animé dans toutes les directions.
     * @param up Sprite pointant vers le haut
     * @param down Sprite pointant vers le bas
     * @param right Sprite pointant vers la droite
     * @param left Sprite pointant vers la gauche
     */
    AnimationSet(Sprite up, Sprite down, Sprite right, Sprite left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    /**
     * Getter du sprite pointant vers le haut.
     * @return Sprite
     */
    public Sprite getUp() {
        return this.up;
    }

    /**
     * Getter du sprite pointant vers le bas.
     * @return Sprite
     */
    public Sprite getDown() {
        return this.down;
    }

    /**
     * Getter du sprite pointant vers la droite.
     * @return Sprite
     */
    public Sprite getRight() {
        return this.right;
    }

    /**
     * Getter du sprite pointant vers la gauche.
     * @return Sprite
     */
    public Sprite getLeft() {
        return this.left;
    }

    /**
     * Renvoie le numéro de l'interaction que doit faire le personnage après l'animation actuelle.
     * @param nbAnimSet animation actuelle du personnage
     * @return int
     */
    public static int getNbAnim(int nbAnimSet) {
        AnimationSet.nbAnim = (AnimationSet.nbAnim % AnimationSet.NB_MAX_ANIM == AnimationSet.NB_MAX_ANIM - 1) ? 0 : ++AnimationSet.nbAnim;
        return nbAnimSet * AnimationSet.NB_MAX_ANIM + AnimationSet.nbAnim;
    }

    /**
     * Renvoie l'AnimationSet à l'indice nb.
     * @param nb indice de l'animationSet
     * @return AnimationSet
     */
    public static AnimationSet getAnimationSet(int nb){
        if(nb < 0 || nb >= AnimationSet.values().length)
            return null;
        return AnimationSet.values()[nb];
    }

    /**
     * Renvoie l'AnimationSet d'arrêt correspondant au sprite et à la direction actuelle du personnage.
     * @return AnimationSet
     */
    public AnimationSet getStopAnimationSet(){
        return AnimationSet.values()[Math.floorDiv(this.ordinal(), AnimationSet.NB_MAX_ANIM) * AnimationSet.NB_MAX_ANIM + 1];
    }

    /**
     * Renvoie la direction d'un sprite d'une AnimationSet.
     * @param sprite sprite recherché
     * @return Direction
     */
    public Direction getDirection(Sprite sprite){
        if(sprite.equals(this.up))
            return Direction.UP;
        if(sprite.equals(this.down))
            return Direction.DOWN;
        if(sprite.equals(this.right))
            return Direction.RIGHT;
        if(sprite.equals(this.left))
            return Direction.LEFT;
        return null;
    }

    /**
     * Renvoie le sprite correspondant à la direction mise en paramètre.
     * @param direction direction du sprite voulu
     * @return Sprite
     */
    public Sprite getSpriteDirection(Direction direction){
        switch (direction){
            case UP:
                return this.up;
            case DOWN:
                return this.down;
            case RIGHT:
                return this.right;
            case LEFT:
                return this.left;
        }
        return null;
    }

    /**
     * Renvoie true si un sprite existe dans un AnimationSet, sinon false.
     * @param sprite sprite recherché
     * @return boolean
     *
     * @see config.AnimationSet#getAnimationSetThatHave(Sprite)
     */
    private boolean contains(Sprite sprite){
        return sprite.equals(this.up) || sprite.equals(this.down) || sprite.equals(this.left) || sprite.equals(this.right);
    }

    /**
     * Parcourt l'ensemble des AnimationSet et vérifie si le sprite exite, puis renvoie l'AnimationSet qui le contient.
     * @param sprite sprite recherché
     * @return AnimationSet
     */
    public static AnimationSet getAnimationSetThatHave(Sprite sprite){
        for(int i = 0; i < AnimationSet.values().length; ++i){
            if(AnimationSet.values()[i].contains(sprite)) return AnimationSet.values()[i];
        }
        return null;
    }
}

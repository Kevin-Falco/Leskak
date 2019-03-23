package config;

public enum AnimationSet {

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



    PNJ1(Sprite.PNJ1_UP, Sprite.PNJ1_DOWN, Sprite.PNJ1_RIGHT, Sprite.PNJ1_LEFT),
    PNJ2(Sprite.PNJ2_UP, Sprite.PNJ2_DOWN, Sprite.PNJ2_RIGHT, Sprite.PNJ2_LEFT),
    PNJ3(Sprite.PNJ3_UP, Sprite.PNJ3_DOWN, Sprite.PNJ3_RIGHT, Sprite.PNJ3_LEFT),
    PNJ4(Sprite.PNJ4_UP, Sprite.PNJ4_DOWN, Sprite.PNJ4_RIGHT, Sprite.PNJ4_LEFT),
    PNJ5(Sprite.PNJ5_UP, Sprite.PNJ5_DOWN, Sprite.PNJ5_RIGHT, Sprite.PNJ5_LEFT),
    PNJ6(Sprite.PNJ6_UP, Sprite.PNJ6_DOWN, Sprite.PNJ6_RIGHT, Sprite.PNJ6_LEFT),
    PNJ7(Sprite.PNJ7_UP, Sprite.PNJ7_DOWN, Sprite.PNJ7_RIGHT, Sprite.PNJ7_LEFT),

    WHITE_CAT(Sprite.WHITE_CAT_UP, Sprite.WHITE_CAT_DOWN, Sprite.WHITE_CAT_RIGHT, Sprite.WHITE_CAT_LEFT),
    GREY_CAT(Sprite.GREY_CAT_UP, Sprite.GREY_CAT_DOWN, Sprite.GREY_CAT_RIGHT, Sprite.GREY_CAT_LEFT),
    BLACK_CAT(Sprite.BLACK_CAT_UP, Sprite.BLACK_CAT_DOWN, Sprite.BLACK_CAT_RIGHT, Sprite.BLACK_CAT_LEFT),
    SNAKE(Sprite.SNAKE_UP, Sprite.SNAKE_DOWN, Sprite.SNAKE_RIGHT, Sprite.SNAKE_LEFT),
    FOX(Sprite.FOX_UP, Sprite.FOX_DOWN, Sprite.FOX_RIGHT, Sprite.FOX_LEFT),

    ;

    private Sprite up;
    private Sprite down;
    private Sprite right;
    private Sprite left;

    private static int nbAnim = 0;
    private static final int nbMaxAnim = 4;

    AnimationSet(Sprite up, Sprite down, Sprite right, Sprite left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public Sprite getUp() {
        return up;
    }

    public Sprite getDown() {
        return down;
    }

    public Sprite getRight() {
        return right;
    }

    public Sprite getLeft() {
        return left;
    }

    public static int getNbAnim(int nbAnimSet) {
        nbAnim = (nbAnim % nbMaxAnim == nbMaxAnim - 1) ? 0 : ++nbAnim;
        return nbAnimSet*nbMaxAnim + nbAnim;
    }

    public static AnimationSet getSpriteSet(int nb){
        if(nb < 0 || nb >= Sprite.values().length)
            return null;
        return AnimationSet.values()[nb];
    }

    public AnimationSet getStopSpriteSet(){
        return AnimationSet.values()[Math.floorDiv(this.ordinal(),nbMaxAnim)*nbMaxAnim];
    }

    public boolean contains(Sprite sprite){
        if(sprite.equals(this.up) || sprite.equals(this.down) || sprite.equals(this.left) || sprite.equals(this.right))
            return true;
        return false;
    }

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

    public static AnimationSet getAnimationSetThatHave(Sprite sprite){
        for(int i = 0; i < AnimationSet.values().length; ++i){
            if(AnimationSet.values()[i].contains(sprite)) return AnimationSet.values()[i];
        }
        return null;
    }


}

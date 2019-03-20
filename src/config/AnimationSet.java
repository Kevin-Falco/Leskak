package config;

public enum AnimationSet {

    PLAYER_MOVE_RIGHT(Sprite.PLAYER_UP_MOVE_RIGHT, Sprite.PLAYER_DOWN_MOVE_RIGHT, Sprite.PLAYER_RIGHT_MOVE_RIGHT, Sprite.PLAYER_LEFT_MOVE_RIGHT),
    PLAYER_STOP2(Sprite.PLAYER_UP_STOP, Sprite.PLAYER_DOWN_STOP, Sprite.PLAYER_RIGHT_STOP, Sprite.PLAYER_LEFT_STOP),
    PLAYER_MOVE_LEFT(Sprite.PLAYER_UP_MOVE_LEFT, Sprite.PLAYER_DOWN_MOVE_LEFT, Sprite.PLAYER_RIGHT_MOVE_LEFT, Sprite.PLAYER_LEFT_MOVE_LEFT),
    PLAYER_STOP(Sprite.PLAYER_UP_STOP, Sprite.PLAYER_DOWN_STOP, Sprite.PLAYER_RIGHT_STOP, Sprite.PLAYER_LEFT_STOP);

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

    public static int getNbAnim() {
        nbAnim = nbAnim % nbMaxAnim == nbMaxAnim - 1 ? 0 : ++nbAnim;
        return nbAnim;
    }

    public static AnimationSet getSpriteSet(int nb){
        if(nb < 0 || nb >= Sprite.values().length)
            return null;
        return AnimationSet.values()[nb];
    }
}

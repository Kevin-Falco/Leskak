package config;

public enum SpriteSet {
    SET_P1(Sprite.UP_P1, Sprite.DOWN_P1, Sprite.RIGHT_P1, Sprite.LEFT_P1),
    SET_BRIDGE(Sprite.UP_BRIDGE, Sprite.DOWN_BRIDGE, Sprite.RIGHT_BRIDGE, Sprite.LEFT_BRIDGE);

    private Sprite UP;
    private Sprite DOWN;
    private Sprite RIGHT;
    private Sprite LEFT;

    SpriteSet(Sprite UP, Sprite DOWN, Sprite RIGHT, Sprite LEFT) {
        this.UP = UP;
        this.DOWN = DOWN;
        this.RIGHT = RIGHT;
        this.LEFT = LEFT;
    }

    public Sprite getUP() {
        return UP;
    }

    public Sprite getDOWN() {
        return DOWN;
    }

    public Sprite getRIGHT() {
        return RIGHT;
    }

    public Sprite getLEFT() {
        return LEFT;
    }
}

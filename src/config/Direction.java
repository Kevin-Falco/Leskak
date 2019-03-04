package config;

public enum Direction {
    UP(Key.UP),
    DOWN(Key.DOWN),
    RIGHT(Key.RIGHT),
    LEFT(Key.LEFT);

    private Key key;

    Direction(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }
}

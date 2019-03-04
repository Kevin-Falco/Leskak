package config;

import javafx.scene.input.KeyCode;

public enum Key {
    UP(KeyCode.UP),
    DOWN(KeyCode.DOWN),
    RIGHT(KeyCode.RIGHT),
    LEFT(KeyCode.LEFT),
    ENTER(KeyCode.ENTER);

    private KeyCode keyCode;

    Key(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return this.keyCode;
    }
}

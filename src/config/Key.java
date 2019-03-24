package config;

import javafx.scene.input.KeyCode;

public enum Key {
    UP(KeyCode.UP),
    DOWN(KeyCode.DOWN),
    RIGHT(KeyCode.RIGHT),
    LEFT(KeyCode.LEFT),
    ENTER(KeyCode.ENTER),
    SPACE(KeyCode.SPACE),
    BACK_SPACE(KeyCode.BACK_SPACE),
    ESCAPE(KeyCode.ESCAPE),
    TELEPORT(KeyCode.R),
    CHANGE_SKIN(KeyCode.F),
    ;

    private KeyCode keyCode;

    Key(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public static boolean isKeyCodeAlreadyUsed(KeyCode keyCode){
        for(int i = 0; i < Key.values().length ; ++i){
            if(keyCode.equals(Key.values()[i].getKeyCode()))
                return true;
        }
        return false;
    }

    public static Key getKeyofKeyCode(KeyCode keyCode){
        for(int i = 0; i < Key.values().length ; ++i){
            if(keyCode.equals(Key.values()[i].getKeyCode()))
                return Key.values()[i];
        }
        return null;
    }
}

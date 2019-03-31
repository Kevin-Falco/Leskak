package config;

import javafx.scene.input.KeyCode;

/**
 * Enumération de toutes les actions clavier possibles dans le jeu et les touches initiales auxuqelles elles sont rattachées.
 */
public enum Key {

    UP(KeyCode.UP),
    DOWN(KeyCode.DOWN),
    RIGHT(KeyCode.RIGHT),
    LEFT(KeyCode.LEFT),
    ENTER(KeyCode.ENTER),
    SPACE(KeyCode.SPACE),
    ESCAPE(KeyCode.ESCAPE),
    TELEPORT(KeyCode.R),
    CHANGE_SKIN(KeyCode.F),
    ;

    /**
     * KeyCode de la touche initiale de chaque action clavier.
     */
    private KeyCode keyCode;

    /**
     * Constructeur de Key prenant en paramètre le KeyCode de la touche initiale.
     * @param keyCode KeyCode de la touche initiale
     */
    Key(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Getter du KeyCode de la touche attachée à l'action clavier.
     * @return KeyCode
     */
    public KeyCode getKeyCode() {
        return this.keyCode;
    }

    /**
     * Setter du KeyCode de la touche attachée à l'action clavier.
     * @param keyCode nouveau KeyCode à attacher à l'action clavier
     */
    public void setKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Vérifie si un KeyCode est déjà utilisé par une des actions clavier.
     * @param keyCode KeyCode à vérifier
     * @return boolean
     */
    public static boolean isKeyCodeAlreadyUsed(KeyCode keyCode){
        for(int i = 0; i < Key.values().length ; ++i){
            if(keyCode.equals(Key.values()[i].getKeyCode()))
                return true;
        }
        return false;
    }

    /**
     * Renvoie la touche correspondant à un KeyCode
     * @param keyCode KeyCode recherché
     * @return Key
     */
    public static Key getKeyofKeyCode(KeyCode keyCode){
        for(int i = 0; i < Key.values().length ; ++i){
            if(keyCode.equals(Key.values()[i].getKeyCode()))
                return Key.values()[i];
        }
        return null;
    }
}

package config;

/**
 * Enumération des directions possibles pour le joueur (soit haut, bas, gauche, droite).
 */
public enum Direction {

    UP(Key.UP),
    DOWN(Key.DOWN),
    RIGHT(Key.RIGHT),
    LEFT(Key.LEFT);

    /**
     * Touche liée à cette direction.
     */
    private Key key;

    /**
     * Constructeur de Direction avec en paramètre la touche attachée à la direction.
     * @param key touche attachée à la direction
     */
    Direction(Key key) {
        this.key = key;
    }

    /**
     * Getter de la touche attachée à la direction.
     * @return Key
     */
    public Key getKey() {
        return this.key;
    }
}

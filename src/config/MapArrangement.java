package config;

import lib.Map;

/**
 * Enumération de toutes les cartes du jeu et respectivement en paramètre la carte qui seroa au Nord, au Sud, à l'Est et
 * à l'Ouest de chacune d'entre elles. S'il n'y en a pas alors null.
 */
public enum MapArrangement {

    // Cartes de la planète 1
    MAP1(Planet.PLANET1.getMaps().get(2),null,Planet.PLANET1.getMaps().get(1),null),
    MAP2(null,null,null,Planet.PLANET1.getMaps().get(0)),
    MAP3(null,Planet.PLANET1.getMaps().get(0),null,null),

    // Cartes de la planète 2
    MAP4(Planet.PLANET2.getMaps().get(2),null,Planet.PLANET2.getMaps().get(1),null),
    MAP5(Planet.PLANET2.getMaps().get(3),null,null,Planet.PLANET2.getMaps().get(0)),
    MAP6(null,Planet.PLANET2.getMaps().get(0),Planet.PLANET2.getMaps().get(3),null),
    MAP7(null,Planet.PLANET2.getMaps().get(1),null,Planet.PLANET2.getMaps().get(2)),

    // Centre commercial
    MAP8(null,null,null,null),

    // Cartes de la planète 3
    MAP9(null, null, Planet.PLANET3.getMaps().get(1), null),
    MAP10(null, null, null, Planet.PLANET3.getMaps().get(0)),
    MAP11(null, null, Planet.PLANET3.getMaps().get(2), Planet.PLANET3.getMaps().get(2)),

    // Cartes de la planète 4
    MAP12(Planet.PLANET4.getMaps().get(1), null, null, null),
    MAP13(null, Planet.PLANET4.getMaps().get(0), null, null),
    ;

    /**
     * Carte au Nord de la carte actuelle.
     */
    private Map up;

    /**
     * Carte au Sud de la carte actuelle.
     */
    private Map down;

    /**
     * Carte à l'Est de la carte actuelle.
     */
    private Map right;

    /**
     * Carte à l'Ouest de la carte actuelle.
     */
    private Map left;

    /**
     * Constructeur de MapArrangement avec les quatres cartes présentes au Nord, Sud, Est et Ouest de celle-ci.
     * @param up carte au Nord de la carte actuelle
     * @param down carte au Sud de la carte actuelle
     * @param right carte à l'Est de la carte actuelle
     * @param left carte à l'Ouest de la carte actuelle
     */
    MapArrangement(Map up, Map down, Map right, Map left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    /**
     * Getter de la carte au Nord de la carte actuelle.
     * @return Map
     */
    public Map getUp() {
        return up;
    }

    /**
     * Getter de la carte au Sud de la carte actuelle.
     * @return Map
     */
    public Map getDown() {
        return down;
    }

    /**
     * Getter de la carte à l'Est de la carte actuelle.
     * @return Map
     */
    public Map getRight() {
        return right;
    }

    /**
     * Getter de la carte à l'Ouest de la carte actuelle.
     * @return Map
     */
    public Map getLeft() {
        return left;
    }
}

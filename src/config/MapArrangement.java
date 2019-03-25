package config;

import lib.Map;

public enum MapArrangement {
    MAP1(Planet.PLANET1.getMaps().get(2),null,Planet.PLANET1.getMaps().get(1),null),
    MAP2(null,null,null,Planet.PLANET1.getMaps().get(0)),
    MAP3(null,Planet.PLANET1.getMaps().get(0),null,null),
    MAP4(Planet.PLANET2.getMaps().get(2),null,Planet.PLANET2.getMaps().get(1),null),
    MAP5(Planet.PLANET2.getMaps().get(3),null,null,Planet.PLANET2.getMaps().get(0)),
    MAP6(null,Planet.PLANET2.getMaps().get(0),Planet.PLANET2.getMaps().get(3),null),
    MAP7(null,Planet.PLANET2.getMaps().get(1),null,Planet.PLANET2.getMaps().get(2)),
    MAP8(null,null,null,null),
    MAP9(null, null, Planet.PLANET3.getMaps().get(1), null),
    MAP10(null, null, null, Planet.PLANET3.getMaps().get(0)),
    MAP11(null, null, Planet.PLANET3.getMaps().get(2), Planet.PLANET3.getMaps().get(2)),
    ;

    private Map up;
    private Map down;
    private Map right;
    private Map left;

    MapArrangement(Map up, Map down, Map right, Map left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    public Map getUp() {
        return up;
    }

    public Map getDown() {
        return down;
    }

    public Map getRight() {
        return right;
    }

    public Map getLeft() {
        return left;
    }
}

package config;

import lib.Map;

import java.util.ArrayList;
import java.util.Arrays;


public enum Planet {
    PLANET1("Jansen", new ArrayList(Arrays.asList( new Map(), new Map(), new Map()))),
    PLANET2("Hibliss", new ArrayList(Arrays.asList( new Map(), new Map(), new Map(), new Map()))),
    ;

    private String name;
    private ArrayList<Map> maps;

    Planet(String name, ArrayList maps) {
        this.name = name;
        this.maps = maps;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }
}

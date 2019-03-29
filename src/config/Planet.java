package config;

import lib.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Enumération des planètes disponibles dans le jeu, ayant un nom et une liste de cartes disponibles.
 */
public enum Planet {
    PLANET1("Jansen", new ArrayList<>(Arrays.asList( new Map(), new Map(), new Map()))),
    PLANET2("Hibliss", new ArrayList<>(Arrays.asList( new Map(), new Map(), new Map(), new Map()))),
    COMMERCIAL_CENTER("Centre commercial", new ArrayList<>(Collections.singletonList(new Map()))),
    PLANET3("Longstar", new ArrayList<>(Arrays.asList( new Map(), new Map(), new Map()))),
    PLANET4("Kavi", new ArrayList<>(Arrays.asList( new Map(), new Map()))),
    ;

    /**
     * Nom de la planète.
     */
    private String name;

    /**
     * Liste des cartes disponibles sur la planète.
     */
    private ArrayList<Map> maps;

    /**
     * Constructeur de la planète avec en paramètre son nom, et la liste des cartes qui la compose.
     * @param name nom de la planète
     * @param maps liste des cartes de la planète
     */
    Planet(String name, ArrayList<Map> maps) {
        this.name = name;
        this.maps = maps;
    }

    /**
     * Getter du nom de la planète.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter de la liste des cartes disponibles sur cette planète.
     * @return ArrayList
     */
    public ArrayList<Map> getMaps() {
        return this.maps;
    }

    /**
     * Renvoie la planète sur laquelle se situe la carte mise en paramètre.
     * @param map carte que l'on recherche
     * @return Planet
     */
    public static Planet getPlanetOfMap(Map map){
        for(Planet planet : Planet.values())
            if(planet.getMaps().contains(map))
                return planet;
        return null;
    }
}

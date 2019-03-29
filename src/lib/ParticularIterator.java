package lib;

import java.util.Iterator;
import java.util.List;

/**
 * Itérateur générique permettant de parcourir tous les types de cellules de la grille du jeu, et pouvant être réinitialisé
 * à tout moment, ce qui n'est pas le cas d'un itérateur classique.
 * @param <T> type de cellule
 */
public class  ParticularIterator<T extends Cell> implements Iterator<T> {

    /**
     * Compteur initialisé à 0 et remis à 0 à chaque fin de parcours ou en cas de réinitialisation.
     */
    private int count = 0;

    /**
     * Liste des cellules à parcourir.
     */
    private List<Cell> cells;

    /**
     * Type de cellule à parcourir.
     */
    private Class<T> type;

    /**
     * Constructeur de l'itérateur particulier avec le type de cellules à parcourir, ainsi que la liste des cellules de la grille.
     * @param type type de cellule à parcourir
     * @param cells cellules à parcourir
     */
    public ParticularIterator(Class<T> type, List<Cell> cells) {
        this.type = type;
        this.cells = cells;
    }

    /**
     * Renvoie true s'il existe un élément à parcourir dans la liste, false sinon.
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        for(int i = count; i < cells.size(); ++i) {
                if (this.type.isInstance(cells.get(i))){
                    return true;
                }
        }
        this.count = 0;
        return false;
    }

    /**
     * Renvoie le prochain élément à parcourir dans la liste des cellules.
     * @return T soit un type de cellule
     */
    @Override
    public T next() {
        if (hasNext()) {
            for (int i = count; i < cells.size(); ++i) {
                if (this.type.isInstance(cells.get(i))) {
                    ++this.count;
                    return (T)cells.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Réinitialise le compteur à 0 pour faire redémarrer l'itérateur de 0.
     */
    public void reset(){
        this.count = 0;
    }
}

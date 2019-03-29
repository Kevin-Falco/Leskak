package lib;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Cette classe représente les cartes présentes dans le jeu où on peut parcourir les différents types de cellules de la grille
 * de jeu grâce à des itérateurs.
 */
public class Map implements Iterable<Cell> {

    /**
     * Grille de la zone de jeu afin de pouvoir placer les différents sprites et connaître leurs emplacements.
     */
    private GridPane gridPane;

    /**
     * Vaut true si un brouillard de guerre est présent sur cette carte, false sinon.
     */
    private boolean fogOfWar;

    /**
     * Liste des cellules présentes sur la grille de jeu.
     */
    private List<Cell> cells = new ArrayList<>();

    /**
     * Itérateur permettant de parcourir l'intégralité des cellules bloquantes de la grille.
     */
    private ParticularIterator<BlockingCell> blockingCellIterator = new ParticularIterator<>(BlockingCell.class, this.cells);

    /**
     * Itérateur permettant de parcourir l'intégralité des cellules de transition de la grille.
     */
    private ParticularIterator<TransitionCell> transitionCellIterator = new ParticularIterator<>(TransitionCell.class, this.cells);

    /**
     * Constructeur des cartes initialisant la grille qui sera dessus.
     */
    public Map() {
        this.gridPane = new GridPane();
    }

    /**
     * Getter de la grille contenant l'ensemble des cellules des cartes.
     * @return GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * Getter de la liste des cellules de la carte.
     * @return List
     */
    public List<Cell> getCells() {
        return this.cells;
    }

    /**
     * Getter de l'itérateur parcourant l'ensemble des cellules bloquantes de la carte.
     * @return ParticularIterator
     */
    public ParticularIterator<BlockingCell> getBlockingCellIterator() {
        return this.blockingCellIterator;
    }

    /**
     * Getter de l'itérateur parcourant l'ensemble des cellules de transition de la carte.
     * @return ParticularIterator
     */
    public ParticularIterator<TransitionCell> getTransitionCellIterator() {
        return this.transitionCellIterator;
    }

    /**
     * Permet d'ajouter une cellule à la liste des cellules de la carte.
     * @param cell cellule à ajouter à la liste
     */
    public void add(Cell cell){
        this.cells.add(cell);
        GridPane.setConstraints(cell.getImage(), cell.getPosition().getKey(), cell.getPosition().getValue());
        this.getGridPane().getChildren().add(cell.getImage());
    }

    /**
     * Permet de supprimer une cellule à la liste des cellules de la carte.
     * @param cell cellule à supprimer à la liste
     */
    public void remove(Cell cell){
        if(!this.getGridPane().getChildren().contains(cell.getImage()))
            return;
        this.cells.remove(cell);
        this.getGridPane().getChildren().remove(cell.getImage());
    }

    /**
     * Itérateur général parcourant toutes les cellules de la carte.
     * @return Iterator
     */
    @Override
    public Iterator<Cell> iterator() {
        return this.cells.iterator();
    }

    /**
     * Renvoie true s'il y a un brouillard de guerre sur la carte, false sinon.
     * @return boolean
     */
    public boolean isFogOfWar() {
        return this.fogOfWar;
    }

    /**
     * Met en place le brouillard de guerre sur cette carte ou l'enlève.
     * @param fogOfWar booléen déterminant s'il y a un brouillard de guerre ou non
     */
    public void setFogOfWar(boolean fogOfWar) {
        this.fogOfWar = fogOfWar;
        if(this.fogOfWar){
            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j){
                add(new FogCell(new Pair<>(i, j)));
            }
        }
        else{
            for(Cell cell : this){
                if(cell instanceof FogCell){
                    this.cells.remove(cell);
                }
            }
        }
    }

    /**
     * Enlève la cellule de brouillard de guerre à une certaine position, mise en paramètre.
     * @param col indice de la colonne
     * @param row indice de la ligne
     */
    public void removeFogCell(int col, int row){
        Cell cell1 = null;
        for (Cell cell : this
                ) {
            if( cell instanceof FogCell && cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                this.getGridPane().getChildren().remove(cell.getImage());
                cell1 = cell;
            }
        }
        if(cell1 != null)
            this.getCells().remove(cell1);
    }

    /**
     * Met à jour le brouillard de guerre en fonction des déplacements du joueur.
     */
    public void updateFogOfWar(){
        for(Cell cell : this){
            if(cell instanceof FogCell){
                if(cellPositionAt((FogCell)cell, 2)){
                    this.getGridPane().getChildren().remove(cell.getImage());
                }
                else{
                    if(!this.getGridPane().getChildren().contains(cell.getImage()))
                        this.getGridPane().getChildren().add(cell.getImage());
                }
            }
        }
    }

    /**
     * Permet de mettre en forme le brouillard de guerre en sachant s'il y des cellules de brouillard de guerre autour
     * du joueur ou non
     * @param cell cellule de brouillard de guerre
     * @param nb distance autour du joueur
     * @return boolean
     */
    private boolean cellPositionAt(FogCell cell, int nb){
        Player player = Player.getINSTANCE();
        return Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) <= nb &&
            Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) <= nb
                // suite à enlever pour avoir Fog of War carré
                && !(Math.abs(cell.getPosition().getKey() - player.getPosition().getKey()) == nb &&
                        Math.abs(cell.getPosition().getValue() - player.getPosition().getValue()) == nb );
    }
}

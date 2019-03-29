package lib;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * La classe GameLayout sert à mettre en place la zone de jeu (celle où Leskak peut se déplacer), le nombre de cellules qu'il
 * y aura sur sa grille. Cette classe est un singleton.
 */
public class GameLayout {

    /**
     * Nombre de colonnes de la grille du jeu.
     */
    private int nbColumns;

    /**
     * Nombre de lignes de la grille du jeu.
     */
    private int nbRows;

    /**
     * GridPane formant le GameLayout, permettant de savoir où placer chaque élément.
     */
    private GridPane gridPane;

    /**
     * Pane contenant la GridPane, permet de savoir si le focus est sur le jeu ou non.
     */
    private Pane pane;

    /**
     * Booléen valant true si le joueur a commencé la partie, false sinon.
     */
    private boolean gameHasBegun = false;

    /**
     * Instance de GameLayout, permet à la classe d'être un singleton.
     */
    private static final GameLayout INSTANCE = new GameLayout();

    /**
     * Constructeur du Gamelayout initialisant sa mise en forme, ses contraintes...
     */
    private GameLayout(){
        this.nbColumns = 32;
        this.nbRows = 12;
        this.gridPane = new GridPane();
        this.pane = new Pane();
        GridPane.setColumnSpan(this.pane, 2);
        GridPane.setConstraints(this.pane, 0, 0);
        this.pane.getChildren().add(this.gridPane);
        this.pane.setFocusTraversable(true);
        this.pane.requestFocus();
    }

    /**
     * Renvoie l'instance de GameLayout, permet à la classe d'être un singleton.
     * @return GameLayout
     */
    public static GameLayout getINSTANCE() {
        return GameLayout.INSTANCE;
    }

    /**
     * Renvoie le nombre de colones de la grille.
     * @return int
     */
    public int getNbColumns() {
        return this.nbColumns;
    }

    /**
     * Renvoie le nombre de lignes de la grille.
     * @return int
     */
    public int getNbRows() {
        return this.nbRows;
    }

    /**
     * Renvoie la pane du jeu.
     * @return Pane
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Renvoie la grille du jeu.
     * @return GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * Setter de la grille du jeu.
     * @param gridPane nouvelle grille du jeu
     */
    public void setGridPane(GridPane gridPane) {
        this.pane.getChildren().remove(this.gridPane);
        this.gridPane = gridPane;
        this.pane.getChildren().add(this.gridPane);
    }

    /**
     * Renvoie true si le jeu a commencé, false sinon.
     * @return boolean
     */
    public boolean HasGameBegun() {
        return this.gameHasBegun;
    }

    /**
     * Setter du booléen permettant de savoir si le jeu a commencé ou non.
     * @param gameHasBegun nouveau booléen (true si le jeu a commencé, false sinon)
     */
    public void setGameHasBegun(boolean gameHasBegun) {
        this.gameHasBegun = gameHasBegun;
    }
}

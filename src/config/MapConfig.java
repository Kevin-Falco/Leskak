package config;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;
import lib.*;

import java.util.ArrayList;

/**
 * Classe permettant la mise en place de la position d'apparition de Leskak dans le jeu et sur chaque planète. Elle possède
 * une donnée membre de type Task permettant d'exécuter la création de chacune des cartes dans le chargement du jeu (et
 * donc de faire une véritable barre de chargement au lancement du jeu). De plus, MapConfig est un Singleton.
 */
public class MapConfig {

    /**
     * Instance de MapConfig (nécéssaire pour créer un Singleton).
     */
    private static final MapConfig INSTANCE = new MapConfig();

    /**
     * Liste statique de toutes les cartes du jeu.
     */
    private static ArrayList<Map> maps;

    /**
     * Tâche permettant de créer, en tâche de fond, l'ensemble des cartes du jeu.
     */
    private  static Task<Void> task;

    /**
     * Constructeur de MapConfig permettant de mettre toutes les ColumnConstraints et RowConstraints ainsi que la création
     * de la tâche de fond.
     */
    private MapConfig() {
        MapConfig.maps = new ArrayList<>();
        for(Planet planet : Planet.values()){
            for(Map map : planet.getMaps()){
                for(int i = 0; i < GameLayout.getINSTANCE().getNbColumns(); ++i){
                    map.getGridPane().getColumnConstraints()
                            .add(new ColumnConstraints((float)MainLayout.getWIDTH()/GameLayout.getINSTANCE().getNbColumns()));
                }
                for(int i = 0 ; i < GameLayout.getINSTANCE().getNbRows(); ++i){
                    map.getGridPane().getRowConstraints()
                            .add(new RowConstraints(  (float)MainLayout.getHEIGHT()*2/3/GameLayout.getINSTANCE().getNbRows()));
                }
                MapConfig.maps.add(map);
            }
        }
        MapConfig.task = new Task<Void>() {
            @Override
            protected Void call() {
                Movement.configPlayerEventHandler(MainLayout.getSCENE());
                this.updateMapProgress();
                return null;
            }

            private void updateMapProgress(){
                this.updateProgress(0, 100);
                for(int i = 0; i < MapConfig.getINSTANCE().getMaps().size(); ++i){
                    this.updateMessage( " Création de la map " + i);
                    this.updateProgress(i*100/MapConfig.getINSTANCE().getMaps().size(), 100);
                    MapConfig.getINSTANCE().setupMap(i);
                }
                this.updateProgress(100, 100);
                Platform.runLater(() -> configMap(Planet.PLANET1.getMaps().get(0)));
            }
        };
        GameLayout.getINSTANCE().setGameHasBegun(true);
    }

    /**
     * Getter de l'instance de MapConfig
     * @return MapConfig
     */
    public static MapConfig getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Getter de la liste des cartes du jeu.
     * @return ArrayList
     */
    public ArrayList<Map> getMaps() {
        return maps;
    }

    /**
     * Getter de la tâche de fond.
     * @return Task
     */
    public static Task<Void> getTask() {
        return task;
    }

    /**
     * Configure le lieu d'apparition de Leskak selon la planète.
     * @param map carte sur laquelle se trouve Leskak
     */
    public void configMap(Map map){
        Planet planet = Planet.getPlanetOfMap(map);
        if(planet.equals(Planet.PLANET1)){
            configMap(map, new Pair<>(10, 6));
        }
        if(planet.equals(Planet.PLANET2)){
            configMap(map, new Pair<>(4, 8));
        }
        if(planet.equals(Planet.COMMERCIAL_CENTER)){
            configMap(map, new Pair<>(8, 9));
        }
        if(planet.equals(Planet.PLANET3)){
            configMap(map, new Pair<>(24, 5));
        }
        if(planet.equals(Planet.PLANET4)){
            configMap(map, new Pair<>(2, 8));
        }
    }

    /**
     * Configure le lieu d'apparition de Leskak selon la planète et la position à laquelle il sera.
     * @param map carte sur laquelle faire apparaître Leskak
     * @param position position à laquelle il doit apparaitre
     */
    public void configMap(Map map,Pair<Integer, Integer> position){
        Movement.setMap(map);
        Player player = Player.getINSTANCE();

        player.setPosition(position);
        if(player.getSprite() == null)
            player.setSprite(Sprite.PLAYER_DOWN_STOP);
        else
            player.setSprite(AnimationSet.getAnimationSetThatHave(Player.getINSTANCE().getSprite()).getSpriteDirection(player.getDirection()));
        player.setDirection(AnimationSet.getAnimationSetThatHave(player.getSprite()).getDirection(player.getSprite()));
        player.getImage().setTranslateX(0);
        player.getImage().setTranslateY(0);
        GridPane.setConstraints(player.getImage(), position.getKey(),position.getValue());

        if(map.getGridPane().getChildren().contains(player.getImage()))
            map.getGridPane().getChildren().remove(player.getImage());

        map.getGridPane().getChildren().add(player.getImage());

        if(Movement.getMap().isFogOfWar())
            Movement.getMap().updateFogOfWar();

        GameLayout.getINSTANCE().setGridPane(map.getGridPane());
    }

    /**
     * Permet de générer la bonne carte en fonction du numéro mis en paramètre de la fonction.
     * @param nbMap numéro de la carte à générer
     */
    public void setupMap(int nbMap){
        if(nbMap == 0){
            MapSetup.setupMap0();
        }
        else if(nbMap == 1){
            MapSetup.setupMap1();
        }
        else if(nbMap == 2){
            MapSetup.setupMap2();
        }
        else if(nbMap == 3){
            MapSetup.setupMap3();
        }
        else if(nbMap == 4){
            MapSetup.setupMap4();
        }
        else if(nbMap == 5){
            MapSetup.setupMap5();
        }
        else if(nbMap == 6){
            MapSetup.setupMap6();
        }
        else if(nbMap == 7){
            MapSetup.setupMap7();
        }
        else if(nbMap == 8){
            MapSetup.setupMap8();
        }
        else if(nbMap == 9){
            MapSetup.setupMap9();
        }
        else if(nbMap == 10){
            MapSetup.setupMap10();
        }
        else if(nbMap == 11){
            MapSetup.setupMap11();
        }
        else if(nbMap == 12){
            MapSetup.setupMap12();
        }
    }

    /**
     * Renvoie la première cellule de la grille à telle position (donc le sol à cette case).
     * @param nbMap carte sur laquelle on travaille
     * @param col indice de la colonne
     * @param row indice de la ligne
     * @return Cell
     */
    private static Cell getFirstCell(int nbMap, Integer col, Integer row){
        Map m = getINSTANCE().getMaps().get(nbMap);
        for (Cell cell : m
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                return cell;
            }
        }
        return null;
    }

    /**
     * Renvoie la seconde cellule de la grille à telle position (arbre, caillou, eau...)
     * @param nbMap carte sur laquelle on travaille
     * @param col indice de la colonne
     * @param row indice de la ligne
     * @return Cell
     */
    public static Cell getSecondCell(int nbMap, Integer col, Integer row){
        Map m = getINSTANCE().getMaps().get(nbMap);
        int nb = 0;
        for (Cell cell : m
                ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                if(nb++ == 0) continue;
                return cell;
            }
        }
        return null;
    }

    /**
     * Renvoie la dernière cellule de la grille à telle position.
     * @param nbMap carte sur laquelle on travaille
     * @param col indice de la colonne
     * @param row indice de la ligne
     * @return Cell
     */
    public static Cell getLastCell(int nbMap, Integer col, Integer row){
        Map m = getINSTANCE().getMaps().get(nbMap);
        Cell toReturn = null;
        for (Cell cell : m
                ) {
            if(!(cell instanceof FogCell) && cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                toReturn = cell;
            }
        }
        return toReturn;
    }

    /**
     * Permet de déplacer le joueur à une position de donnée.
     * @param targetPosition nouvelle position pour le joueur
     */
    public void movePlayer(Pair<Integer, Integer> targetPosition){
        Player player = Player.getINSTANCE();
        double x = (player.getPosition().getKey() - targetPosition.getKey()) * 50;
        double y = (player.getPosition().getValue() - targetPosition.getValue()) * 50;

        Player.getINSTANCE().setPosition(targetPosition);

        ImageView imageView = player.getImage();
        imageView.setImage(new Image(player.getSprite().getSpritePath()));
        player.getImage().setTranslateX(player.getImage().getX());
        player.getImage().setTranslateY(player.getImage().getY() + y);

        GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
    }

    /**
     * Permet d'échanger deux cellules sur une carte.
     * @param nbMap carte sur laquelle on travaille
     * @param positionCell1 position de la première cellule à échanger
     * @param positionCell2 position de la deuxième cellule à échanger
     * @return boolean
     */
    public boolean swapCells(int nbMap, Pair<Integer, Integer> positionCell1, Pair<Integer, Integer> positionCell2){
        if(Player.getINSTANCE().getPosition().equals(positionCell1) || Player.getINSTANCE().getPosition().equals(positionCell2)){
            return false;
        }
        Cell firstCell1 = getFirstCell(nbMap, positionCell1.getKey(), positionCell1.getValue());
        Cell firstCell2 = getFirstCell(nbMap, positionCell2.getKey(), positionCell2.getValue());
        Cell lastCell1 = getLastCell(nbMap, positionCell1.getKey(), positionCell1.getValue());
        Cell lastCell2 = getLastCell(nbMap, positionCell2.getKey(), positionCell2.getValue());

        if(firstCell1 != lastCell1 && firstCell2 != lastCell2 ){
            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().removeAll(lastCell1.getImage(), lastCell2.getImage());

            lastCell1.setPosition(positionCell2);
            GridPane.setConstraints(lastCell1.getImage(), positionCell2.getKey(), positionCell2.getValue());

            lastCell2.setPosition(positionCell1);
            GridPane.setConstraints(lastCell2.getImage(), positionCell1.getKey(), positionCell1.getValue());

            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().addAll(lastCell1.getImage(), lastCell2.getImage());
        }
        else if(firstCell1 != lastCell1){
            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().remove(lastCell1.getImage());

            lastCell1.setPosition(positionCell2);
            GridPane.setConstraints(lastCell1.getImage(), positionCell2.getKey(), positionCell2.getValue());

            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().add(lastCell1.getImage());
        }
        else if(firstCell2 != lastCell2){
            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().remove(lastCell2.getImage());

            lastCell2.setPosition(positionCell1);
            GridPane.setConstraints(lastCell2.getImage(), positionCell1.getKey(), positionCell1.getValue());

            MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().add(lastCell2.getImage());
        }

        Player.getINSTANCE().setPlayerOnTop(nbMap);
        return true;
    }

    /**
     * Classe interne permettant la mise en place, sprite par sprite, de chacune des cartes de chaque planète. Elle permet
     *  la création des cellules sur lesquelles Leskak peut marcher, être bloqué, de transition ou encore intéragir.
     */
    private static class MapSetup{

        /**
         * Crée une cellule bloquante qui empêche Leskak de marcher dessus.
         * @param sprite sprite de la cellule bloquante
         * @param position position de la cellule bloquante
         * @return BlockingCell
         */
        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position){
            return new BlockingCell(sprite, position);
        }

        /**
         * Crée une cellule bloquante qui empêche Leskak de marcher dessus mais avec laquelle il peut intéragir.
         * @param sprite sprite de la cellule bloquante
         * @param position position de la cellule bloquante
         * @param interaction intéraction de la cellule bloquante
         * @return BlockingCell
         */
        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
            return new BlockingCell(sprite, position, interaction);
        }

        /**
         * Crée une cellule de transition qui permet à Leskak de changer de carte.
         * @param sprite sprite de la cellule de transition
         * @param position position de la cellule de transition
         * @param direction direction dans laquelle Leskak doit aller afin de changer de carte
         * @return TransitionCell
         */
        private static TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
            return new TransitionCell(sprite, position, direction);
        }

        /**
         * Crée une cellule classique sur laquelle Leskak peut marcher.
         * @param sprite sprite de la cellule
         * @param position position de la cellule
         * @return Cell
         */
        private static Cell addCell(Sprite sprite, Pair<Integer, Integer> position){
            return new Cell(sprite, position);
        }

        /**
         * Crée un rectangle de BlockingCell en fonction du paramètre str permettant de savoir sur quel type de "bâtiment"
         * on travaille. Par exemple, cette fonction est très utile pour le centre commercial, où il faut placer de nombreux
         * rectangles comme des buildings, voitures... étant des BlockingCell.
         * @param m carte sur laquelle on travaille
         * @param str chaîne de caractère permettant de savoir sur quels sprites on travaille
         * @param col nombre de colones du building
         * @param row nombre de lignes du building
         * @param position poisition de départ (en haut à gauche) du building
         * @param interaction intéraction avec celui-ci si elle existe
         */
        private static void createBuilding(Map m, String str, int col, int row, Pair<Integer, Integer> position, Interaction interaction){
            for(int i = 0; i < row; ++i){
                for(int j = 0; j < col; ++j){
                    if(interaction == null)
                        m.add(addBlockingCell(Sprite.valueOf(str.concat(String.valueOf(i*col + j))), new Pair<>(position.getKey() + j, position.getValue() + i)));
                    else
                        m.add(addBlockingCell(Sprite.valueOf(str.concat(String.valueOf(i*col + j))), new Pair<>(position.getKey() + j, position.getValue() + i), interaction));
                }
            }
        }

        /**
         * Génère l'ensemble de la première carte de la planète 1.
         */
        private static void setupMap0(){
            Map m = maps.get(0);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i, j)));

            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 0), Direction.UP));
            for (int j = 2; j <= 4; ++j) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(31, j), Direction.RIGHT));

            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 15; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 17; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 20; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 20; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 18; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 0; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 0; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));

            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 0)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 1)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 2)));
            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 3)));
            for (int i = 23; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 4)));
            for (int i = 23; i <= 25; ++i) m.add(addCell(Sprite.WATER, new Pair<>(i, 5)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 6)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 7)));
            for (int i = 24; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 8)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 9)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 10)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 11)));

            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PNJ1_DOWN, new Pair<>(i, 2), Interaction.PNJ1));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.PNJ2_DOWN, new Pair<>(i, 5), Interaction.PNJ2));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PNJ3_LEFT, new Pair<>(i, 5), Interaction.PNJ3));

            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.WHITE_CAT_DOWN, new Pair<>(i, 2), Interaction.CAT1));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.BLACK_CAT_DOWN, new Pair<>(i, 3), Interaction.CAT2));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.BLACK_CAT_RIGHT, new Pair<>(i, 8), Interaction.CAT1));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.GREY_CAT_LEFT, new Pair<>(i, 8), Interaction.CAT1));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.WHITE_CAT_LEFT, new Pair<>(i, 9), Interaction.CAT1));

            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 5), Interaction.ROCKET));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 6), Interaction.ROCKET));

            for (int i = 26; i <= 27; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 4)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 5)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 7)));

            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 3)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 6)));

            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 2)));
            for (int i = 26; i <= 26; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 3)));

            createBuilding(m, "HOUSE1_", 2, 2, new Pair<>(5, 2), null);
            createBuilding(m, "HOUSE1_", 2, 2, new Pair<>(15, 3), null);
            createBuilding(m, "HOUSE1_", 2, 2, new Pair<>(15, 7), null);

            for (int i = 23; i <= 25; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 5)));

            updateSpritesOf(0, SpriteSet.TREE_SET);
            updateSpritesOf(0, SpriteSet.WATER_SET);
        }

        /**
         * Génère l'ensemble de la deuxième carte de la planète 1.
         */
        private static void setupMap1() {
            Map m = maps.get(1);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i, j)));

            for (int j = 2; j <= 4; ++j) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(0, j), Direction.LEFT));

            for (int i = 30; i <= 30; ++i) m.add(addBlockingCell(Sprite.GRASS, new Pair<>(i, 5), Interaction.CHEST_BEFORE_HIDDEN));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 2; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 3; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 17; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 5; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 18; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 5; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 13; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 19; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 5; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 13; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 6; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 11; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 11; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 10; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));

            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.PNJ4_LEFT, new Pair<>(i, 6), Interaction.PNJ4));

            for (int i = 30; i <= 30; ++i) m.add(addBlockingCell(Sprite.FOX_LEFT, new Pair<>(i, 4), Interaction.FOX));

            createBuilding(m, "HOUSE1_", 2, 2, new Pair<>(28, 5), null);

            updateSpritesOf(1, SpriteSet.TREE_SET);
        }

        /**
         * Génère l'ensemble de la troisième carte de la planète 1.
         */
        private static void setupMap2(){
            Map m = maps.get(2);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i, j)));

            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 11), Direction.DOWN));

            for (int i = 0; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 17; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 20; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 20; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 17; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 0; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 16; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 15; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));

            for (int i = 28; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 0)));
            for (int i = 25; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 1)));
            for (int i = 25; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 2)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 3)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 4)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 5)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 6)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 7)));
            for (int i = 23; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 8)));
            for (int i = 23; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 9)));
            for (int i = 23; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 10)));
            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 11)));

            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.SNAKE_UP, new Pair<>(i, 2), Interaction.SNAKE));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.SNAKE_RIGHT, new Pair<>(i, 2), Interaction.SNAKE));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.SNAKE_RIGHT, new Pair<>(i, 3), Interaction.SNAKE));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.SNAKE_LEFT, new Pair<>(i, 4), Interaction.SNAKE));
            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.SNAKE_DOWN, new Pair<>(i, 5), Interaction.SNAKE));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.SNAKE_DOWN, new Pair<>(i, 7), Interaction.SNAKE));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.SNAKE_RIGHT, new Pair<>(i, 6), Interaction.SNAKE));

            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 2)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 3)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 3)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 4)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 5)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 5)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 7), Interaction.BUSH1));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 8)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.HERB, new Pair<>(i, 6)));

            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 5)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 7)));

            updateSpritesOf(2, SpriteSet.TREE_SET);
            updateSpritesOf(2, SpriteSet.WATER_SET);
        }

        /**
         * Génère l'ensemble de la première carte de la planète 2.
         */
        private static void setupMap3(){
            Map m = maps.get(3);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SAND, new Pair<>(i,j)));

            for (int i = 1; i <= 1; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 4; i <= 4; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 6; i <= 6; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 9; i <= 9; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 11; i <= 11; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 13; i <= 13; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 17; i <= 17; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 21; i <= 21; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 23; i <= 23; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 26; i <= 26; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 29; i <= 29; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 3), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 5), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 7), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 9), Direction.RIGHT));

            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 7; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 14; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 18; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 27; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 7; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 14; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 18; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 27; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 14; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 25; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 5; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 20; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 2; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 16; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 20; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 14; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 18; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 23; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 14; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 25; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));

            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PNJ5_LEFT, new Pair<>(i, 9), Interaction.PNJ5));

            for (int i = 3; i <= 3; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 7), Interaction.ROCKET));
            for (int i = 3; i <= 3; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 8), Interaction.ROCKET ));

            createBuilding(m, "HOUSE2_", 2, 2, new Pair<>(9, 7), null);

            updateSpritesOf(3, SpriteSet.TREE2_SET);

            m.setFogOfWar(true);
            for (int i = 0; i <= 14; ++i) for(int j = 4; j <= 11; ++j) m.removeFogCell(i,j);
        }

        /**
         * Génère l'ensemble de la deuxième carte de la planète 2.
         */
        private static void setupMap4(){
            Map m = maps.get(4);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SAND, new Pair<>(i,j)));

            for (int i = 6; i <= 6; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 12; i <= 12; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 22; i <= 22; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 27; i <= 27; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 29; i <= 29; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 0), Direction.UP));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 3), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 5), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 7), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 9), Direction.LEFT));

            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 7; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 13; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 7; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 13; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 17; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 6; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 20; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 9; i <= 13; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 17; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 20; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 4; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 20; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 12; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 24; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 22; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 8; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 14; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 21; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 26; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 6; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 21; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 26; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 12; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 18; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));

            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.PNJ6_DOWN, new Pair<>(i, 9), Interaction.PNJ6));

            updateSpritesOf(4, SpriteSet.TREE2_SET);

            m.setFogOfWar(true);
        }

        /**
         * Génère l'ensemble de la troisième carte de la planète 2.
         */
        private static void setupMap5(){
            Map m = maps.get(5);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SAND, new Pair<>(i,j)));

            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 2), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 5), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 8), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 10), Direction.RIGHT));
            for (int i = 1; i <= 1; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 4; i <= 4; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 6; i <= 6; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 9; i <= 9; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 11; i <= 11; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 13; i <= 13; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 17; i <= 17; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 21; i <= 21; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 23; i <= 23; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 26; i <= 26; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 29; i <= 29; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 11; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 21; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 2; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 13; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 2; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 21; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 5; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 8; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 21; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 26; i <= 29; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 3; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 11; i <= 13; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 15; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 26; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 0; i <= 1; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 5; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 8; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 19; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 5; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 12; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 25; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 17; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 7; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 14; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 7; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 14; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 18; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 27; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));

            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.PNJ7_DOWN, new Pair<>(i, 4), Interaction.PNJ7));

            updateSpritesOf(5, SpriteSet.TREE2_SET);

            m.setFogOfWar(true);
        }

        /**
         * Génère l'ensemble de la quatrième carte de la planète 2.
         */
        private static void setupMap6(){
            Map m = maps.get(6);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SAND, new Pair<>(i,j)));

            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 2), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 5), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 8), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 10), Direction.LEFT));
            for (int i = 6; i <= 6; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 12; i <= 12; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 22; i <= 22; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 27; i <= 27; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 29; i <= 29; ++i) m.add(addTransitionCell(Sprite.SAND, new Pair<>(i, 11), Direction.DOWN));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 0)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 1)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 17; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 2)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 6; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 12; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 3)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 17; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 20; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 4)));
            for (int i = 4; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 14; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 10; i <= 12; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 17; i <= 20; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 22; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 6; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 9; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 22; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 19; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 8)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 4; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 11; i <= 19; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 26; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 9)));
            for (int i = 4; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 21; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 10)));
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 7; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 13; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 11)));

            createBuilding(m, "SPACESHIP_", 5, 2, new Pair<>(25, 3), Interaction.SPACESHIP);
            updateSpritesOf(6, SpriteSet.TREE2_SET);

            m.setFogOfWar(true);
        }

        /**
         * Génère l'ensemble du centre commercial.
         */
        private static void setupMap7(){
            Map m = maps.get(7);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.FLOOR, new Pair<>(i,j)));

            for (int i = 7; i <= 23; ++i) m.add(addBlockingCell(Sprite.FLOOR_UP, new Pair<>(i, 0)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.FLOOR_UP, new Pair<>(i, 6)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.FLOOR_DOWN, new Pair<>(i, 2)));
            for (int i = 7; i <= 23; ++i) m.add(addBlockingCell(Sprite.FLOOR_DOWN, new Pair<>(i, 10)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.FLOOR_RIGHT, new Pair<>(2, i)));
            for (int i = 1; i <= 10; ++i) m.add(addBlockingCell(Sprite.FLOOR_RIGHT, new Pair<>(24, i)));
            for (int i = 1; i <= 10; ++i) m.add(addBlockingCell(Sprite.FLOOR_LEFT, new Pair<>(6, i)));
            for (int i = 0; i <= 1; ++i) m.add(addBlockingCell(Sprite.FLOOR_LEFT, new Pair<>(28, i)));
            for (int i = 7; i <= 11; ++i) m.add(addBlockingCell(Sprite.FLOOR_LEFT, new Pair<>(28, i)));

            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.FLOOR_UP_LEFT, new Pair<>(i, 0)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.FLOOR_UP_RIGHT, new Pair<>(i, 0)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.FLOOR_UP_LEFT, new Pair<>(i, 6)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.FLOOR_DOWN_LEFT, new Pair<>(i, 2)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.FLOOR_DOWN_LEFT, new Pair<>(i, 10)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.FLOOR_DOWN_RIGHT, new Pair<>(i, 10)));

            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(3, i)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(5, i)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(25, i)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(27, i)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(i, 3)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(i, 5)));
            for (int i = 6; i <= 24; ++i) m.add(addBlockingCell(Sprite.ROAD, new Pair<>(i, 11)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD_UP_DOWN, new Pair<>(4, i)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROAD_UP_DOWN, new Pair<>(26, i)));
            for (int i = 26; i <= 26; ++i) m.add(addBlockingCell(Sprite.ROAD_UP_RIGHT, new Pair<>(i, 4)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.ROAD_LEFT_RIGHT, new Pair<>(i, 4)));

            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 8), Interaction.ROCKET));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 9), Interaction.ROCKET));

            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.PNJ8_RIGHT, new Pair<>(i, 2), Interaction.PNJ8));
            for (int i = 19  ; i <= 19; ++i) m.add(addBlockingCell(Sprite.PNJ9_LEFT, new Pair<>(i, 2), Interaction.PNJ9));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.PNJ10_DOWN, new Pair<>(i, 4), Interaction.PNJ10));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PNJ11_RIGHT, new Pair<>(i, 7), Interaction.PNJ11));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PNJ12_DOWN, new Pair<>(i, 8), Interaction.PNJ12));

            createBuilding(m, "STORE_", 4, 4, new Pair<>(13, 0), null);
            createBuilding(m, "BURGER_", 4, 4, new Pair<>(7, 2), null);
            createBuilding(m, "FOUNTAIN_", 3, 3, new Pair<>(10, 6), null);
            createBuilding(m, "CAR_", 4, 2, new Pair<>(15, 10), null);
            createBuilding(m, "BUILDING_", 3, 8, new Pair<>(21, 0), null);
            createBuilding(m, "BUILDING2_", 4, 4, new Pair<>(17, 5), null);
        }

        /**
         * Génère l'ensemble de la première carte de la planète 3.
         */
        private static void setupMap8() {
            Map m = maps.get(8);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SPACE, new Pair<>(i, j)));

            for (int j = 5; j <= 6; ++j) m.add(addTransitionCell(Sprite.SPACE, new Pair<>(31, j), Direction.RIGHT));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 0)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 1)));
            for (int i = 23; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 1)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 2)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 2)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 6)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 9)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 9)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 10)));
            for (int i = 23; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 11)));

            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 4)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 5)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 6)));
            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 7)));

            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.ROCK2, new Pair<>(i, 2)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.ROCK2, new Pair<>(i, 4)));
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.ROCK2, new Pair<>(i, 4)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.ROCK2, new Pair<>(i, 7)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.ROCK2, new Pair<>(i, 9)));

            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 4), Interaction.ROCKET));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 5), Interaction.ROCKET));

            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.PNJ13_RIGHT, new Pair<>(i, 4), Interaction.PNJ13));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.PNJ14_DOWN, new Pair<>(i, 4), Interaction.PNJ14));
            for (int i = 12; i <= 12; ++i) m.add(addBlockingCell(Sprite.PNJ15_RIGHT, new Pair<>(i, 8), Interaction.PNJ15));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.PNJ16_DOWN, new Pair<>(i, 9), Interaction.PNJ16));

            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.STATUE_UP, new Pair<>(i, 5), Interaction.STATUE));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.STATUE_DOWN, new Pair<>(i, 6), Interaction.STATUE));

            createBuilding(m, "HOUSE3_", 2, 2, new Pair<>(12, 2), null);
            createBuilding(m, "HOUSE3_", 2, 2, new Pair<>(19, 2), null);
            createBuilding(m, "HOUSE3_", 2, 2, new Pair<>(11, 6), null);
            createBuilding(m, "HOUSE3_", 2, 2, new Pair<>(20, 7), null);

            updateSpritesOf(8, SpriteSet.TREE3_SET);
        }

        /**
         * Génère l'ensemble de la deuxième carte de la planète 3.
         */
        private static void setupMap9() {
            Map m = maps.get(9);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SPACE, new Pair<>(i, j)));

            for (int j = 5; j <= 6; ++j) m.add(addTransitionCell(Sprite.SPACE, new Pair<>(0, j), Direction.LEFT));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 0)));
            for (int i = 0; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 1)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 1)));
            for (int i = 0; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 2)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 2)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 7; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 13; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 3)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 8; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 14; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 4)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 5)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 6)));
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 8; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 14; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 7)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 7; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 13; i <= 15; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 8)));
            for (int i = 0; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 9)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 9)));
            for (int i = 0; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 10)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE3, new Pair<>(i, 11)));

            for (int i = 20; i <= 23; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 3)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 4)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 4)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 5)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 6)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 7)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 7)));
            for (int i = 20; i <= 23; ++i) m.add(addBlockingCell(Sprite.BUSH2, new Pair<>(i, 8)));

            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.FOX_DOWN, new Pair<>(i, 1), Interaction.FOX1));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.FOX_RIGHT, new Pair<>(i, 4), Interaction.FOX1));
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.FOX_LEFT, new Pair<>(i, 4), Interaction.FOX1));
            for (int i = 26; i <= 26; ++i) m.add(addBlockingCell(Sprite.FOX_UP, new Pair<>(i, 6), Interaction.FOX1));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.FOX_LEFT, new Pair<>(i, 9), Interaction.FOX1));

            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.CHICKEN_LEFT, new Pair<>(i, 3), Interaction.CHICKEN1));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.CHICKEN_DOWN, new Pair<>(i, 8), Interaction.CHICKEN2));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.CHICKEN_LEFT, new Pair<>(i, 10), Interaction.CHICKEN3));

            createBuilding(m, "VORTEX_", 2, 2, new Pair<>(21,5), Interaction.PACMAN_IN);

            updateSpritesOf(9, SpriteSet.TREE3_SET);
        }

        /**
         * Génère l'ensemble de la carte du pacman personnalisé
         */
        private static void setupMap10() {
            Map m = maps.get(10);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.PACMAN_BG, new Pair<>(i, j)));
            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 4; ++j) m.add(addCell(Sprite.PACGUM, new Pair<>(i, j)));
            for (int i = 3; i <= 13; ++i) m.add(addCell(Sprite.PACGUM, new Pair<>(i, 5)));
            for (int i = 18; i <= 28; ++i) m.add(addCell(Sprite.PACGUM, new Pair<>(i, 5)));
            for (int i = 0; i <= 31; ++i) for (int j = 6; j <= 11; ++j) m.add(addCell(Sprite.PACGUM, new Pair<>(i, j)));

            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.PACMAN_BG, new Pair<>(i, 5), Direction.LEFT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.PACMAN_BG, new Pair<>(i, 5), Direction.RIGHT));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 0)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 1)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 1)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 1)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 1)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 1)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 2; i <= 2; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 4; i <= 5; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 11; i <= 13; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 18; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 26; i <= 27; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 2)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 3)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 4; i <= 7; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 13; i <= 14; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_SEMI_WALL, new Pair<>(i, 4)));
            for (int i = 17; i <= 18; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 4)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 5)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 5)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 5)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 5)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 4; i <= 7; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 13;  i <= 18; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 6)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 20; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 7)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 2; i <= 2; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 6; i <= 7; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 11; i <= 13; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 18; i <= 20; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 8)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 2; i <= 3; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 24; i <= 26; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 28; i <= 29; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 9)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 10)));
            for (int i = 9; i <= 11; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 10)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 10)));
            for (int i = 20; i <= 22; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 10)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 10)));
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.PACMAN_WALL, new Pair<>(i, 11)));

            createBuilding(m, "LETTER_", 8, 1, new Pair<>(12, 0), null);
        }

        /**
         * Génère l'ensemble de la première carte de la planète 4.
         */
        private static void setupMap11() {
            Map m = maps.get(11);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.SNOW, new Pair<>(i, j)));

            for (int i = 12; i <= 12; ++i) m.add(addCell(Sprite.TREE4, new Pair<>(i, 0)));
            for (int i = 12; i <= 12; ++i) m.add(addTransitionCell(Sprite.CAVE_ENTRANCE, new Pair<>(i, 0), Direction.UP));

            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 0)));
            for (int i = 8; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 0)));
            for (int i = 13; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 0)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 1)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 1)));
            for (int i = 15; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 1)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 1)));
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 2)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 2)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 2)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 2)));
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 3)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 3)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 3)));
            for (int i = 0; i <= 1; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 4)));
            for (int i = 3; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 4)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 4)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 4)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 5)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 5)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 5)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 6)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 6)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 7)));
            for (int i = 6; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 7)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 7)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 8)));
            for (int i = 6; i <= 7; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 8)));
            for (int i = 17; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 8)));
            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 8)));
            for (int i = 0; i <= 0; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 9)));
            for (int i = 6; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 9)));
            for (int i = 16; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 9)));
            for (int i = 21; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 9)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 9)));
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 10)));
            for (int i = 6; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 10)));
            for (int i = 15; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 10)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 10)));
            for (int i = 0; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 11)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE4, new Pair<>(i, 11)));

            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 0)));
            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 1)));
            for (int i = 17; i <= 21; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 1)));
            for (int i = 5; i <= 7; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 2)));
            for (int i = 17; i <= 22; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 2)));
            for (int i = 6; i <= 8; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 3)));
            for (int i = 16; i <= 18; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 3)));
            for (int i = 21; i <= 23; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 3)));
            for (int i = 6; i <= 8; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 4)));
            for (int i = 16; i <= 18; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 4)));
            for (int i = 21; i <= 23; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 4)));
            for (int i = 7; i <= 9; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 5)));
            for (int i = 15; i <= 17; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 5)));
            for (int i = 22; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 5)));
            for (int i = 7; i <= 9; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 6)));
            for (int i = 15; i <= 17; ++i) m.add(addCell(Sprite.WATER2, new Pair<>(i, 6)));
            for (int i = 22; i <= 27; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 6)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 7)));
            for (int i = 14; i <= 17; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 7)));
            for (int i = 23; i <= 28; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 7)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 8)));
            for (int i = 13; i <= 16; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 8)));
            for (int i = 25; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 8)));
            for (int i = 9; i <= 15; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 9)));
            for (int i = 27; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 9)));
            for (int i = 10; i <= 14; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 10)));
            for (int i = 28; i <= 30; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 10)));
            for (int i = 28; i <= 30; ++i) m.add(addBlockingCell(Sprite.WATER2, new Pair<>(i, 11)));

            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.BUSH3, new Pair<>(i, 1)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.BUSH3, new Pair<>(i, 2)));
            for (int i = 30; i <= 30; ++i) m.add(addBlockingCell(Sprite.BUSH3, new Pair<>(i, 2), Interaction.BUSH2));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.BUSH3, new Pair<>(i, 3)));
            for (int i = 25; i <= 25; ++i) m.add(addBlockingCell(Sprite.BUSH3, new Pair<>(i, 4)));

            for (int i = 21; i <= 23; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 3)));
            for (int i = 21; i <= 23; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 4)));
            for (int i = 7; i <= 9; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 5)));
            for (int i = 15; i <= 17; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 5)));
            for (int i = 7; i <= 9; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 6)));
            for (int i = 15; i <= 17; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 6)));

            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 5)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 6), Interaction.ROCK));

            for (int i = 1; i <= 1; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 7), Interaction.ROCKET));
            for (int i = 1; i <= 1; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 8), Interaction.ROCKET));

            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.PNJ17_DOWN, new Pair<>(i, 3), Interaction.PNJ17));
            for (int i = 8; i <= 8; ++i) m.add(addBlockingCell(Sprite.PNJ18_RIGHT, new Pair<>(i, 5), Interaction.PNJ18));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.PNJ19_LEFT, new Pair<>(i, 10), Interaction.PNJ19));

            createBuilding(m, "HOUSE4_", 2, 2, new Pair<>(4, 8), null);

            updateSpritesOf(11, SpriteSet.TREE4_SET);
            updateSpritesOf(11, SpriteSet.WATER2_SET);
        }

        /**
         * Génère l'ensemble de la deuxième carte de la planète 4.
         */
        private static void setupMap12() {
            Map m = maps.get(12);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.CAVE_FLOOR, new Pair<>(i, j)));

            for (int i = 12; i <= 12; ++i) m.add(addTransitionCell(Sprite.CAVE_FLOOR, new Pair<>(i, 11), Direction.DOWN));

            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 0)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 1)));
            for (int i = 15; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 1)));
            for (int i = 0; i <= 8; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 2)));
            for (int i = 16; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 2)));
            for (int i = 0; i <= 7; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 3)));
            for (int i = 17; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 3)));
            for (int i = 0; i <= 7; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 4)));
            for (int i = 17; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 4)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 5)));
            for (int i = 18; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 5)));
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 6)));
            for (int i = 18; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 6)));
            for (int i = 0; i <= 7; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 7)));
            for (int i = 17; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 7)));
            for (int i = 0; i <= 7; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 8)));
            for (int i = 17; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 8)));
            for (int i = 0; i <= 8; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 9)));
            for (int i = 16; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 9)));
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 10)));
            for (int i = 15; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 10)));
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 11)));
            for (int i = 13; i <= 31; ++i) m.add(addBlockingCell(Sprite.CAVE, new Pair<>(i, 11)));

            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.PNJ20_DOWN, new Pair<>(i, 6), Interaction.PNJ20));

            updateSpritesOf(12, SpriteSet.CAVE_SET);
        }

        /**
         * Permet de déterminer, en analysant chacun des sprites entourant un élément, le sprite à placer pour un élément,
         * comme l'arbre ou l'eau (savoir s'il s'agissait du dernier arbre de la ligne et placer le sprite adapté par
         * exemple).
         * @param nbMap carte sur laquelle l'algorithme doit être appliqué
         * @param spriteSet le type de sprite qui doit être adapté à son environnement
         */
        private static void updateSpritesOf(int nbMap, SpriteSet spriteSet){
            for(int i = 0; i <= 31; i++){
                for(int j = 0; j <= 11; ++j){
                    Cell currentCell = getSecondCell(nbMap, i, j);
                    Cell upCell = getSecondCell(nbMap, i, j-1);
                    Cell downCell = getSecondCell(nbMap, i, j+1);
                    Cell rightCell = getSecondCell(nbMap, i+1, j);
                    Cell leftCell = getSecondCell(nbMap, i-1, j);
                    if(currentCell != null && spriteSet.contains(currentCell.getSprite())){
                        if((downCell == null || !spriteSet.contains(downCell.getSprite())) && j!= 11){
                            if((rightCell == null || !spriteSet.contains(rightCell.getSprite())) &&
                                    (leftCell == null || !spriteSet.contains(leftCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getDown());
                            }
                            else if(rightCell != null && spriteSet.contains(rightCell.getSprite()) &&
                            (leftCell != null && spriteSet.contains(leftCell.getSprite())) && j > 0 &&
                                    (upCell == null || !spriteSet.contains(upCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getLeftRight());
                            }
                            else if (upCell != null && spriteSet.contains(upCell.getSprite())){
                                if( rightCell == null || !spriteSet.contains(rightCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getDownRight());
                                }
                                else if(leftCell == null || !spriteSet.contains(leftCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getDownLeft());
                                }
                                else{
                                    currentCell.setSprite(spriteSet.getDownLeftRight());
                                }
                            }
                            else if(j == 0){
                                if(rightCell == null || !spriteSet.contains(rightCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getRight());
                                }
                                else if(leftCell == null || !spriteSet.contains(leftCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getLeft());
                                }
                                else
                                    currentCell.setSprite(spriteSet.getDownLeftRight());
                            }
                            else if(rightCell != null && spriteSet.contains(rightCell.getSprite())){
                                currentCell.setSprite(spriteSet.getLeft());
                            }
                            else{
                                currentCell.setSprite(spriteSet.getRight());
                            }
                        }
                        else if(j-1 >= 0 && (upCell == null || !spriteSet.contains(upCell.getSprite()))){
                            if((rightCell == null || !spriteSet.contains(rightCell.getSprite())) &&
                                    (leftCell == null || !spriteSet.contains(leftCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getUp());
                            }
                            else if((downCell != null && spriteSet.contains(downCell.getSprite()) || j == 11)){
                                if( rightCell == null || !spriteSet.contains(rightCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getUpRight());
                                }
                                else if( leftCell == null || !spriteSet.contains(leftCell.getSprite())){
                                    currentCell.setSprite(spriteSet.getUpLeft());
                                }
                                else{
                                    currentCell.setSprite(spriteSet.getUpLeftRight());
                                }
                            }
                        }
                        else if( i+1 <= 31 && (rightCell == null || !spriteSet.contains(rightCell.getSprite()))){
                            if((upCell == null || !spriteSet.contains(upCell.getSprite())) &&
                                    (downCell == null || !spriteSet.contains(downCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getRight());
                            }
                            else if(upCell != null && spriteSet.contains(upCell.getSprite()) &&
                                    (downCell != null && spriteSet.contains(downCell.getSprite())) && i > 0 &&
                                    (leftCell == null || !spriteSet.contains(leftCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getUpDown());
                            }
                            else if((leftCell == null || !spriteSet.contains(leftCell.getSprite())) && i > 0){
                                currentCell.setSprite(spriteSet.getUpDown());
                            }
                            else{
                                currentCell.setSprite(spriteSet.getRightUpDown());
                            }

                        }
                        else if( i-1 >= 0 && (leftCell == null || !spriteSet.contains(leftCell.getSprite()))){
                            if((upCell == null || !spriteSet.contains(upCell.getSprite())) &&
                                    (downCell == null || !spriteSet.contains(downCell.getSprite()))){
                                currentCell.setSprite(spriteSet.getLeft());
                            }
                            else{
                                currentCell.setSprite(spriteSet.getLeftUpDown());
                            }
                        }
                    }
                }
            }
        }
    }
}

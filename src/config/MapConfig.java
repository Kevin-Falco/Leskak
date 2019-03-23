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


public class MapConfig {
    private static final MapConfig INSTANCE = new MapConfig();

    private static ArrayList<Map> maps;
    private  static Task<Void> task;

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
        task = new Task<Void>() {
            @Override
            protected Void call() {
                Movement.configPlayerEventHandler(MainLayout.getSCENE());
                MapConfig.getINSTANCE();
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

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public static MapConfig getINSTANCE() {
        return INSTANCE;
    }

    public static Task<Void> getTask() {
        return task;
    }

    public void configMap(Map map){
        Planet planet = Planet.getPlanetOfMap(map);
        if(planet.equals(Planet.PLANET1)){
            configMap(map, new Pair<>(10, 6), Sprite.PLAYER_DOWN_STOP);
        }
        if(planet.equals(Planet.PLANET2)){
            configMap(map, new Pair<>(4, 8), Sprite.PLAYER_DOWN_STOP);
        }
        if(planet.equals(Planet.COMMERCIAL_CENTER)){
            configMap(map, new Pair<>(10, 6), Sprite.PLAYER_DOWN_STOP);
        }
    }

    public void configMap(Map map,Pair<Integer, Integer> position, Sprite sprite){
        Movement.setMap(map);

        Player player = Player.getINSTANCE();

        player.setPosition(position);
        player.setSprite(sprite);
        player.setDirection(AnimationSet.getAnimationSetThatHave(sprite).getDirection(sprite));
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
    }

    public static Cell getFirstCell(int nbMap, Integer col, Integer row){
        Map m = getINSTANCE().getMaps().get(nbMap);
        for (Cell cell : m
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                return cell;
            }
        }
        return null;
    }

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


    public void movePlayer(Pair<Integer, Integer> targetPosition){
        Player player = Player.getINSTANCE();
        double x = (player.getPosition().getKey() - targetPosition.getKey()) * 50;
        double y = (player.getPosition().getValue() - targetPosition.getValue()) * 50;
        System.out.println(player.getPosition() + " : " + targetPosition + x);

        Player.getINSTANCE().setPosition(targetPosition);

        ImageView imageView = player.getImage();
        imageView.setImage(new Image(player.getSprite().getSpritePath()));
        player.getImage().setTranslateX(player.getImage().getX());
        player.getImage().setTranslateY(player.getImage().getY() + y);

        GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
    }

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

    private static class MapSetup{

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position){
            return new BlockingCell(sprite, position);
        }

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
            return new BlockingCell(sprite, position, interaction);
        }

        private static TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
            return new TransitionCell(sprite, position, direction);
        }

        private static Cell addCell(Sprite sprite, Pair<Integer, Integer> position){
            return new Cell(sprite, position);
        }
        private static void setupMap0(){
            Map m = maps.get(0);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i, j)));

            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 0), Direction.UP));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 2), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 3), Direction.RIGHT));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 4), Direction.RIGHT));

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

        private static void setupMap1() {
            Map m = maps.get(1);

            for (int i = 0; i <= 31; ++i) for (int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i, j)));

            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 2), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 3), Direction.LEFT));
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 4), Direction.LEFT));

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
            for (int i = 12; i <= 13; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 15; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 26; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 6)));
            for (int i = 0; i <= 1; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 5; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
            for (int i = 8; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE2, new Pair<>(i, 7)));
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

        private static void setupMap7(){
            Map m = maps.get(7);

            for(int i = 0; i <= 31; ++i) for(int j = 0; j <= 11; ++j) m.add(addCell(Sprite.GRASS, new Pair<>(i,j)));
        }

        private static void updateSpritesOf(int nbMap, SpriteSet spriteSet){
            for(int i = 0; i <= 31; i++)
            {
                for(int j = 0; j <= 11; ++j){
                    Cell currentCell = getSecondCell(nbMap, i, j);
                    Cell upCell = getSecondCell(nbMap, i, j-1);
                    Cell downCell = getSecondCell(nbMap, i, j+1);
                    Cell rightCell = getSecondCell(nbMap, i+1, j);
                    Cell leftCell = getSecondCell(nbMap, i-1, j);
                    if(currentCell != null && spriteSet.contains(currentCell.getSprite())){
                        if((downCell == null || !spriteSet.contains(downCell.getSprite()))){
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
    }
}

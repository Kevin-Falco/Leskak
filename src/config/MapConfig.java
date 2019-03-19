package config;

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
        for(int nbMap = 0; nbMap < 4; ++nbMap){
            Map newMap = new Map();
            for(int i = 0; i < GameLayout.getINSTANCE().getNbColumns(); ++i){
                newMap.getGridPane().getColumnConstraints()
                        .add(new ColumnConstraints((float)MainLayout.getWIDTH()/GameLayout.getINSTANCE().getNbColumns()));
            }
            for(int i = 0 ; i < GameLayout.getINSTANCE().getNbRows(); ++i){
                newMap.getGridPane().getRowConstraints()
                        .add(new RowConstraints(  (float)MainLayout.getHEIGHT()*2/3/GameLayout.getINSTANCE().getNbRows()));
            }
            MapConfig.maps.add(newMap);
            //setupMap(nbMap);
        }
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // code to execute on background thread here:
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
                   //try {
                   //    Thread.sleep(1000);
                   //} catch (InterruptedException e) {
                   //    e.printStackTrace();
                   //}
                }
                this.updateProgress(100, 100);
            }
        };
        //this.configMap(0);
        //CinematicConfig.setupGame();
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

    public void configMap(int nbMap){
        if(nbMap == 0){
            configMap(nbMap, new Pair<>(10, 6), Sprite.PLAYER_DOWN_STOP);
        }
        if(nbMap == 1){
            configMap(nbMap, new Pair<>(15, 5), Sprite.PLAYER_DOWN_STOP);
        }
        if(nbMap == 2){
            configMap(nbMap, new Pair<>(6, 5), Sprite.PLAYER_DOWN_STOP);
        }
        if(nbMap == 3){
            configMap(nbMap, new Pair<>(6, 5), Sprite.PLAYER_DOWN_STOP);
        }
    }

    public void configMap(int nbMap,Pair<Integer, Integer> position, Sprite sprite){
        Movement.setMap(MapConfig.maps.get(nbMap));

        Player player = Player.getINSTANCE();

        player.setPosition(position);
        player.setSprite(sprite);

        //ImageView imageView = new ImageView(player.getSprite().getSpritePath());
        //imageView.setPreserveRatio(true);
        //imageView.setFitWidth(50);//(37);
        player.getImage().setTranslateX(0);
        player.getImage().setTranslateY(0);
        GridPane.setConstraints(player.getImage(), position.getKey(),position.getValue());

        //MapConfig.maps.get(nbMap).getGridPane().setGridLinesVisible(true);
        MapConfig.maps.get(nbMap).getGridPane().getChildren().add(player.getImage());

        if(Movement.getMap().isFogOfWar())
            Movement.getMap().updateFogOfWar();

        GameLayout.getINSTANCE().setGridPane(MapConfig.maps.get(nbMap).getGridPane());
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
    }

    private static Cell getCell(int nbMap, Integer col, Integer row){
        Map m = getINSTANCE().getMaps().get(0);
        for (Cell cell : m
        ) {
            if(cell.getPosition().getKey().equals(col) && cell.getPosition().getValue().equals(row)){
                return cell;
            }
        }
        return null;
    }

    public void movePlayer(Pair<Integer, Integer> targetPosition){
        Player player = Player.getINSTANCE();
        double x = (player.getPosition().getKey() - targetPosition.getKey()) * 50;
        double y = (player.getPosition().getValue() - targetPosition.getValue()) * 50;
        System.out.println(player.getPosition() + " : " + targetPosition + x);

        Player.getINSTANCE().setPosition(targetPosition);


        //ImageView imageView = (ImageView) Movement.getMap().getGridPane().getChildren().get(Movement.getMap().getGridPane().getChildren().size() - 1);
        ImageView imageView = player.getImage();
        imageView.setImage(new Image(player.getSprite().getSpritePath()));
        player.getImage().setTranslateX(player.getImage().getX());
        player.getImage().setTranslateY(player.getImage().getY() + y);

        GridPane.setConstraints(imageView, player.getPosition().getKey(), player.getPosition().getValue());
        //GridPane.setConstraints(new ImageView(Player.getINSTANCE().getSprite().getSpritePath()), targetPosition.getKey(), targetPosition.getValue());
    }

    public boolean swapCells(int nbMap, Pair<Integer, Integer> positionCell1, Pair<Integer, Integer> positionCell2){
        if(Player.getINSTANCE().getPosition().equals(positionCell1) || Player.getINSTANCE().getPosition().equals(positionCell2)){
            return false;
        }
        Cell cell1 = getCell(nbMap, positionCell1.getKey(), positionCell1.getValue());
        Cell cell2 = getCell(nbMap, positionCell2.getKey(), positionCell2.getValue());
        //cell1.setInFogOfWar(false);
        //cell2.setInFogOfWar(false);
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().removeAll(cell1.getSprite(), cell2.getSprite());

        cell1.setPosition(positionCell2);
        GridPane.setConstraints(cell1.getSprite(), positionCell2.getKey(), positionCell2.getValue());


        cell2.setPosition(positionCell1);
        GridPane.setConstraints(cell2.getSprite(), positionCell1.getKey(), positionCell1.getValue());
        MapConfig.getINSTANCE().getMaps().get(nbMap).getGridPane().getChildren().addAll(cell1.getSprite(), cell2.getSprite());
        Player.getINSTANCE().setPlayerOnTop(nbMap);
        return true;
    }

    private static class MapSetup{

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position){
            BlockingCell pnj = new BlockingCell(sprite, position);
            //GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
            return pnj;
        }

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
            BlockingCell pnj = new BlockingCell(sprite, position, interaction);
            //GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
            return pnj;
        }

        private static TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
            TransitionCell pnj = new TransitionCell(sprite, position, direction);
            //GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
            return pnj;
        }

        private static Cell addCell(Sprite sprite, Pair<Integer, Integer> position){
            Cell pnj = new Cell(sprite, position);
            //GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
            return pnj;
        }
        private static void setupMap0(){
            Map m = maps.get(0);
            m.setFogOfWar(true);

            /*LIGNE 1*/
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 0), Direction.UP));
            for (int i = 15; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.WATER_LEFT, new Pair<>(i, 0)));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 0)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER_RIGHT, new Pair<>(i, 0)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            /*LIGNE 2*/
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 4; i <= 16; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 1)));
            for (int i = 17; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 1)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 26; i <= 29; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            /*LIGNE 3*/
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 4; i <= 4; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_LEFT, new Pair<>(i, 2)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_RIGHT, new Pair<>(i, 2)));
            for (int i = 7; i <= 8; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.DOWN_P1, new Pair<>(i, 2), Interaction.PNJ1));
            for (int i = 10; i <= 19; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 20; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 2)));
            for (int i = 24; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 25; i <= 28; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 2)));
            for (int i = 30; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 2), Direction.RIGHT));
            /*LIGNE 4*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 3; i <= 4; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_LEFT, new Pair<>(i, 3)));
            for (int i = 6; i <= 6; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_RIGHT, new Pair<>(i, 3)));
            for (int i = 7; i <= 13; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_LEFT, new Pair<>(i, 3)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_RIGHT, new Pair<>(i, 3)));
            for (int i = 16; i <= 18; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 19; i <= 19; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 3)));
            for (int i = 20; i <= 20; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 3)));
            for (int i = 25; i <= 25; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 26; i <= 26; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 3)));
            for (int i = 27; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 3), Direction.RIGHT));
            /*LIGNE 5*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 3; i <= 13; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_LEFT, new Pair<>(i, 4)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_RIGHT, new Pair<>(i, 4)));
            for (int i = 16; i <= 21; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 23; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 4)));
            for (int i = 25; i <= 25; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 26; i <= 27; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 4)));
            for (int i = 28; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 4), Direction.RIGHT));
            /*LIGNE 6*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 3; i <= 8; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCKET_UP, new Pair<>(i, 5), Interaction.ROCKET));
            for (int i = 10; i <= 13; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 14; i <= 14; ++i) m.add(addBlockingCell(Sprite.DOWN_P1, new Pair<>(i, 5), Interaction.PNJ2));
            for (int i = 15; i <= 21; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 22; i <= 22; ++i) m.add(addBlockingCell(Sprite.LEFT_P1, new Pair<>(i, 5), Interaction.PNJ3));
            for (int i = 23; i <= 24; ++i) m.add(addCell(Sprite.BRIDGE, new Pair<>(i, 5)));
            for (int i = 25; i <= 28; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 5)));
            for (int i = 30; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            /*LIGNE 7*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 3; i <= 3; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_LEFT, new Pair<>(i, 6)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_RIGHT, new Pair<>(i, 6)));
            for (int i = 6; i <= 8; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.ROCKET_DOWN, new Pair<>(i, 6), Interaction.ROCKET));
            for (int i = 10; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 6)));
            for (int i = 19; i <= 22; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 23; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 6)));
            for (int i = 26; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            /*LIGNE 8*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 3; i <= 3; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 4; i <= 4; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_LEFT, new Pair<>(i, 7)));
            for (int i = 5; i <= 5; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_RIGHT, new Pair<>(i, 7)));
            for (int i = 6; i <= 14; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_LEFT, new Pair<>(i, 7)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_RIGHT, new Pair<>(i, 7)));
            for (int i = 17; i <= 21; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 7)));
            for (int i = 26; i <= 27; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 7)));
            for (int i = 29; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            /*LIGNE 9*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 3; i <= 14; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_LEFT, new Pair<>(i, 8)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_RIGHT, new Pair<>(i, 8)));
            for (int i = 17; i <= 19; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 20; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 24; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 8)));
            for (int i = 27; i <= 29; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            /*LIGNE 10*/
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 4; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 9)));
            for (int i = 18; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 9)));
            for (int i = 27; i <= 28; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 9)));
            for (int i = 29; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            /*LIGNE 11*/
            for (int i = 0; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 10)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            /*LIGNE 12*/
            for (int i = 0; i <= 24; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 25; i <= 26; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 11)));
            for (int i = 27; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
        }

        private static void setupMap1() {
            Map m = maps.get(1);

            /*LIGNE 1*/
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            /*LIGNE 2*/
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            /*LIGNE 3*/
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 2), Direction.LEFT));
            for (int i = 1; i <= 1; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 2; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            /*LIGNE 4*/
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 3), Direction.LEFT));
            for (int i = 1; i <= 2; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 3; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 12; i <= 16; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 17; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 27; i <= 29; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            /*LIGNE 5*/
            for (int i = 0; i <= 0; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 4), Direction.LEFT));
            for (int i = 1; i <= 4; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 5; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 11; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 18; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 26; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            /*LIGNE 6*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 3; i <= 4; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 5; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 10; i <= 12; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 13; i <= 14; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 15; i <= 18; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 19; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 23; i <= 27; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_LEFT, new Pair<>(i, 5)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.HOUSE_TOP_RIGHT, new Pair<>(i, 5)));
            for (int i = 30; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            /*LIGNE 7*/
            for (int i = 0; i <= 2; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 3; i <= 4; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 5; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 9; i <= 12; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 13; i <= 16; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 17; i <= 20; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 22; i <= 26; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.LEFT_P1, new Pair<>(i, 6), Interaction.PNJ4));
            for (int i = 28; i <= 28; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_LEFT, new Pair<>(i, 6)));
            for (int i = 29; i <= 29; ++i) m.add(addBlockingCell(Sprite.HOUSE_BOTTOM_RIGHT, new Pair<>(i, 6)));
            for (int i = 30; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            /*LIGNE 8*/
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 4; i <= 5; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 6; i <= 8; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 9; i <= 10; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 11; i <= 17; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 18; i <= 23; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 26; i <= 30; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 31; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            /*LIGNE 9*/
            for (int i = 0; i <= 3; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 4; i <= 10; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 11; i <= 18; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 19; i <= 22; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 23; i <= 26; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 27; i <= 29; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            /*LIGNE 10*/
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 5; i <= 9; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 9)));
            for (int i = 10; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            /*LIGNE 10*/
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            /*LIGNE 11*/
            for (int i = 0; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
        }

        private static void setupMap2(){
            Map m = maps.get(2);

            /*LIGNE 1*/
            for (int i = 0; i <= 27; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 0)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 0)));
            /*LIGNE 2*/
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 10; i <= 16; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 1)));
            for (int i = 17; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            for (int i = 25; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 1)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 1)));
            /*LIGNE 3*/
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 7; i <= 15; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 16; i <= 16; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 2)));
            for (int i = 17; i <= 19; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 2)));
            for (int i = 20; i <= 25; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            for (int i = 25; i <= 29; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 2)));
            for (int i = 30; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 2)));
            /*LIGNE 4*/
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 6; i <= 8; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 9; i <= 9; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 3)));
            for (int i = 10; i <= 12; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 3)));
            for (int i = 14; i <= 20; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 3)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 3)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 3)));
            /*LIGNE 5*/
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 5; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 4)));
            for (int i = 19; i <= 21; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 4)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            for (int i = 24; i <= 27; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 4)));
            for (int i = 28; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 4)));
            /*LIGNE 6*/
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 5; i <= 6; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 7; i <= 7; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 5)));
            for (int i = 8; i <= 10; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 5)));
            for (int i = 12; i <= 20; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 5)));
            for (int i = 21; i <= 21; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 5)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 5)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 5)));
            /*LIGNE 7*/
            for (int i = 0; i <= 4; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 5; i <= 14; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 15; i <= 15; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 6)));
            for (int i = 16; i <= 21; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 6)));
            for (int i = 22; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 6)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 6)));
            /*LIGNE 8*/
            for (int i = 0; i <= 5; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 6; i <= 9; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 7), Interaction.BUSH1));
            for (int i = 11; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.ROCK, new Pair<>(i, 7)));
            for (int i = 19; i <= 20; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 21; i <= 23; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            for (int i = 24; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 7)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 7)));
            /*LIGNE 9*/
            for (int i = 0; i <= 6; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 7; i <= 12; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 13; i <= 13; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 8)));
            for (int i = 14; i <= 19; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 8)));
            for (int i = 20; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            for (int i = 23; i <= 25; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 8)));
            for (int i = 26; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 8)));
            /*LIGNE 10*/
            for (int i = 0; i <= 9; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 10; i <= 16; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 9)));
            for (int i = 17; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            for (int i = 23; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 9)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 9)));
            /*LIGNE 11*/
            for (int i = 0; i <= 10; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 11; i <= 15; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 10)));
            for (int i = 16; i <= 22; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            for (int i = 23; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 10)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 10)));
            /*LIGNE 12*/
            for (int i = 0; i <= 11; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 11), Direction.DOWN));
            for (int i = 15; i <= 21; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
            for (int i = 22; i <= 24; ++i) m.add(addBlockingCell(Sprite.WATER, new Pair<>(i, 11)));
            for (int i = 25; i <= 31; ++i) m.add(addBlockingCell(Sprite.TREE, new Pair<>(i, 11)));
        }

        private static void setupMap3(){
            Map m = maps.get(3);

            for(int i = 0; i <= 31; i++)
            {
                for(int j = 0; j <= 11; ++j){
                    m.add(addCell(Sprite.GRASS, new Pair<>(i,j)));
                }
            }
        }
    }
}

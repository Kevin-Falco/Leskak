package config;

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
            setupMap(nbMap);
        }
        this.configMap(0);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public static MapConfig getINSTANCE() {
        return INSTANCE;
    }



    public void configMap(int nbMap){
        if(nbMap == 0){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 1){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 2){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
        if(nbMap == 3){
            configMap(nbMap, new Pair<>(6, 5), Sprite.DOWN);
        }
    }

    public void configMap(int nbMap,Pair<Integer, Integer> position, Sprite sprite){
        Movement.setMap(MapConfig.maps.get(nbMap));
        Player player = Player.getINSTANCE();
        player.setPosition(position);
        player.setSprite(sprite);

        ImageView imageView = new ImageView(player.getSprite().getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);//(37);

        //MapConfig.maps.get(nbMap).getGridPane().setGridLinesVisible(true);

        GridPane.setConstraints(imageView, position.getKey(),position.getValue());

        MapConfig.maps.get(nbMap).getGridPane().getChildren().add(imageView);

        GameLayout.getINSTANCE().setGridPane(MapConfig.maps.get(nbMap).getGridPane());
    }

    private void setupMap(int nbMap){
        if(nbMap == 0){
            MapSetup.setupMap0();
        }
        else if(nbMap == 1){
            MapSetup.setupMap1();
        }
        else if(nbMap == 2){
            MapSetup.setupMap2();
        }
    }

    private static class MapSetup{

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position){
            BlockingCell pnj = new BlockingCell(new ImageView( sprite.getSpritePath()), position);
            GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
            return pnj;
        }

        private static BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
            BlockingCell pnj = new BlockingCell(new ImageView( sprite.getSpritePath()), position, interaction);
            GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
            return pnj;
        }

        private static TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
            TransitionCell pnj = new TransitionCell(new ImageView( sprite.getSpritePath()), position, direction);
            GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
            return pnj;
        }

        private static Cell addCell(Sprite sprite, Pair<Integer, Integer> position){
            Cell pnj = new Cell(new ImageView( sprite.getSpritePath()), position);
            GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
            return pnj;
        }
        private static void setupMap0(){
            Map m = maps.get(0);

            for (int i = 12; i <= 14; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(i, 0), Direction.UP));
            for (int i = 2; i <= 4; ++i) m.add(addTransitionCell(Sprite.GRASS, new Pair<>(31, i), Direction.RIGHT));
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
            for (int i = 27; i <= 27; ++i) m.add(addBlockingCell(Sprite.LEFT, new Pair<>(i, 6), Interaction.PNJ2));
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
            for (int i = 11; i <= 11; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 5)));
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
            for (int i = 10; i <= 10; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 7)));
            for (int i = 11; i <= 17; ++i) m.add(addCell(Sprite.GRASS, new Pair<>(i, 7)));
            for (int i = 18; i <= 18; ++i) m.add(addBlockingCell(Sprite.BUSH, new Pair<>(i, 7)));
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
    }
}

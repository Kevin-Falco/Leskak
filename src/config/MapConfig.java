package config;

import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;
import lib.*;

public enum MapConfig {
    MAP1(),
    MAP2(),
    MAP3();

    private Map map;

    MapConfig() {
        this.map = new Map();
        for(int i = 0; i < GameLayout.getINSTANCE().getNbColumns(); ++i){
            map.getGridPane().getColumnConstraints()
                    .add(new ColumnConstraints((float)GameLayout.getWIDTH()/GameLayout.getINSTANCE().getNbColumns()));
        }
        for(int i = 0 ; i < GameLayout.getINSTANCE().getNbRows(); ++i){
            map.getGridPane().getRowConstraints()
                    .add(new RowConstraints(  (float)GameLayout.getHEIGHT()*2/3/GameLayout.getINSTANCE().getNbRows()));
        }
    }

    public Map getMap() {
        return map;
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

    public static void configMap(Map map,Pair<Integer, Integer> position, Sprite sprite){
        Movement.setMap(map);
        Player player = Player.getINSTANCE();
        player.setPosition(position);
        player.setSprite(sprite);

        ImageView imageView = new ImageView(player.getSprite().getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        map.getGridPane().setGridLinesVisible(true);

        GridPane.setConstraints(imageView, position.getKey(),position.getValue());

        map.getGridPane().getChildren().add(imageView);

        GameLayout.getINSTANCE().setGridPane(map.getGridPane());
    }

    public void setupMap( ){
        if(this.equals(MAP1)){
            setupMap(new Pair<>(5,5), Sprite.DOWN);
        }
        else if(this.equals(MAP2)){
            setupMap(new Pair<>(10,5), Sprite.DOWN);
        }

    }

    public void setupMap(Pair<Integer, Integer> position, Sprite sprite){
        if(this.equals(MAP1)){
            MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>(i, i), Interaction.PNJ));
                else
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>(i, i), Interaction.PNJ2));
            }
            map.add(MapConfig.addTransitionCell(Sprite.RIGHT_ARROW, new Pair<>(31, 5), Direction.RIGHT));
            map.add(MapConfig.addTransitionCell(Sprite.UP_ARROW, new Pair<>(4, 0), Direction.UP));
        }
        else if(this.equals(MAP2)){
            MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>(32 - i, i), Interaction.PNJ2));
                else
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>( 32 - i, i), Interaction.PNJ));
            }
            map.add(MapConfig.addTransitionCell(Sprite.LEFT_ARROW, new Pair<>(0, 2), Direction.LEFT));
        }
        else if(this.equals(MAP3)){
            MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>(i+5, 0), Interaction.PNJ2));
                else
                    map.add(MapConfig.addBlockingCell(Sprite.PNJ1, new Pair<>( i+5, 1), Interaction.PNJ));
            }
            map.add(MapConfig.addTransitionCell(Sprite.DOWN_ARROW, new Pair<>(4, 11), Direction.DOWN));
        }


    }
}

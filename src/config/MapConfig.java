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

    private ArrayList<Map> maps;

    private MapConfig() {
        this.maps = new ArrayList<>();
        for(int nbMap = 0; nbMap < 3; ++nbMap){
            Map newMap = new Map();
            for(int i = 0; i < GameLayout.getINSTANCE().getNbColumns(); ++i){
                newMap.getGridPane().getColumnConstraints()
                        .add(new ColumnConstraints((float)GameLayout.getWIDTH()/GameLayout.getINSTANCE().getNbColumns()));
            }
            for(int i = 0 ; i < GameLayout.getINSTANCE().getNbRows(); ++i){
                newMap.getGridPane().getRowConstraints()
                        .add(new RowConstraints(  (float)GameLayout.getHEIGHT()*2/3/GameLayout.getINSTANCE().getNbRows()));
            }
            this.maps.add(newMap);
            setupMap(nbMap);
        }
        this.configMap(0, new Pair<>(6,5), Sprite.DOWN);
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }

    public static MapConfig getINSTANCE() {
        return INSTANCE;
    }

    private BlockingCell addBlockingCell(Sprite sprite, Pair<Integer, Integer> position, Interaction interaction){
        BlockingCell pnj = new BlockingCell(new ImageView( sprite.getSpritePath()), position, interaction);
        GridPane.setConstraints(pnj.getSprite(), position.getKey(),position.getValue());
        return pnj;
    }

    private TransitionCell addTransitionCell(Sprite sprite, Pair<Integer, Integer> position, Direction direction){
        TransitionCell pnj = new TransitionCell(new ImageView( sprite.getSpritePath()), position, direction);
        GridPane.setConstraints(pnj.getSprite(), position.getKey(), position.getValue());
        return pnj;
    }

    public void configMap(int nbMap,Pair<Integer, Integer> position, Sprite sprite){
        //System.out.println( this.maps.get(0).getGridPane().getChildren().size());

        Movement.setMap(this.maps.get(nbMap));
        Player player = Player.getINSTANCE();
        player.setPosition(position);
        player.setSprite(sprite);

        ImageView imageView = new ImageView(player.getSprite().getSpritePath());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        this.maps.get(nbMap).getGridPane().setGridLinesVisible(true);

        GridPane.setConstraints(imageView, position.getKey(),position.getValue());

        this.maps.get(nbMap).getGridPane().getChildren().add(imageView);

        GameLayout.getINSTANCE().setGridPane(this.maps.get(nbMap).getGridPane());
    }

    public void setupMap(int nbMap){
        if(nbMap == 0){
            setupMap(nbMap, new Pair<>(6,7), Sprite.DOWN);
        }
        else if(nbMap == 1){
            setupMap(nbMap, new Pair<>(10,5), Sprite.DOWN);
        }
        else if(nbMap == 2){
            setupMap(nbMap, new Pair<>(15,5), Sprite.DOWN);
        }
    }

    public void setupMap(int nbMap, Pair<Integer, Integer> position, Sprite sprite){
        if(nbMap == 0){
            //MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    this.maps.get(0).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(i, i), Interaction.PNJ));
                else
                    this.maps.get(0).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(i, i), Interaction.PNJ2));
            }
            //if(Movement.getSprite(31, 5) != null)
            this.maps.get(0).add(this.addTransitionCell(Sprite.RIGHT_ARROW, new Pair<>(31, 5), Direction.RIGHT));
            //if(Movement.getSprite(4, 0) == null)
            this.maps.get(0).add(this.addTransitionCell(Sprite.UP_ARROW, new Pair<>(4, 0), Direction.UP));
        }
        else if(nbMap == 1){
            //MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    this.maps.get(1).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(32 - i, i), Interaction.PNJ2));
                else
                    this.maps.get(1).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>( 32 - i, i), Interaction.PNJ));
            }
            this.maps.get(1).add(this.addTransitionCell(Sprite.LEFT_ARROW, new Pair<>(0, 2), Direction.LEFT));
        }
        else if(nbMap == 2){
            //MapConfig.configMap(this.map, position, sprite);
            for (int i = 0; i < 10; ++i){
                if(i%2 == 0)
                    this.maps.get(2).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>(i+5, 0), Interaction.PNJ2));
                else
                    this.maps.get(2).add(this.addBlockingCell(Sprite.PNJ1, new Pair<>( i+5, 1), Interaction.PNJ));
            }
            this.maps.get(2).add(this.addTransitionCell(Sprite.DOWN_ARROW, new Pair<>(4, 11), Direction.DOWN));
        }
    }
}

package lib;


import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class DialogLayout {
    private Text text;
    private GridPane gridPane;

    private static final DialogLayout INSTANCE = new DialogLayout();

    private DialogLayout() {
        this.text = new Text();
        this.text.setWrappingWidth((float) (GameLayout.getWIDTH()*2/3)*2/3);
        GridPane.setConstraints(this.text, 0, 0);

        this.gridPane = new GridPane();
        this.gridPane.getChildren().add(this.text);
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(GameLayout.getWIDTH()*2/3)*2/3));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(GameLayout.getWIDTH()*2/3)/3));
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public static DialogLayout getINSTANCE() {
        return INSTANCE;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void setText(String string){
        this.text.setText(string);
    }

    public void removeContent(){
        this.text.setText("");
        if(this.gridPane.getChildren().size() > 1){
            this.gridPane.getChildren().remove(1, this.gridPane.getChildren().size());
        }
    }

    public void addButton(String name, EventHandler eventHandler){
        Button button = new Button(name);
        //button.setFocusTraversable(false);
        button.setOnAction(eventHandler);
        GridPane.setConstraints(button, 1, this.gridPane.getChildren().size() - 1);
        DialogLayout.getINSTANCE().getGridPane().getChildren().add(button);
    }
}

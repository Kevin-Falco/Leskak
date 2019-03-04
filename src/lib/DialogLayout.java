package lib;

import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;

public class DialogLayout {
    private GridPane gridPane;
    private DialogPane dialogPane;
    private String text;

    private static final DialogLayout INSTANCE = new DialogLayout();

    private DialogLayout() {
        this.gridPane = new GridPane();
        this.dialogPane = new DialogPane();
        this.dialogPane.setMinWidth((float) GameLayout.getWIDTH()*2/3);
        this.gridPane.getChildren().add(this.dialogPane);
        this.text = "";
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

    public void addText(String string){
        this.text += '\n' + string;
        this.dialogPane.setContentText(this.text);
    }

    public void setText(String string){
        this.text = string;
        this.dialogPane.setContentText(this.text);
    }
}

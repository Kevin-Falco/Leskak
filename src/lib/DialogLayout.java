package lib;


import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class DialogLayout {
    private Text text;
    private GridPane gridPane;
    private VBox buttons;
    private int money;

    private static final DialogLayout INSTANCE = new DialogLayout();

    private DialogLayout() {
        this.text = new Text();
        this.text.setWrappingWidth((float) (MainLayout.getWIDTH()*2/3)*2/3);
        GridPane.setConstraints(this.text, 0, 0);

        this.money = 0;
        Text text = new Text();
        GridPane.setConstraints(text, 1, 1 );

        this.buttons = new VBox();
        GridPane.setConstraints(this.buttons, 1, 0 );

        this.gridPane = new GridPane();
        this.gridPane.getChildren().add(this.text);
        this.gridPane.getChildren().add(text);
        updateMoney();
        this.gridPane.getChildren().add(this.buttons);
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(MainLayout.getWIDTH()*2/3)*2/3));
        this.gridPane.getColumnConstraints().add(new ColumnConstraints(  (float)(MainLayout.getWIDTH()*2/3)/3));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)(MainLayout.getHEIGHT()/3)*2/3));
        this.gridPane.getRowConstraints().add(new RowConstraints(  (float)(MainLayout.getHEIGHT()/3)/3));
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
        this.buttons.getChildren().remove(0, this.buttons.getChildren().size());
        if(this.gridPane.getChildren().size() > 2){
            //this.gridPane.getChildren().remove(2, this.gridPane.getChildren().size());
        }
    }

    public void addButton(String name, EventHandler eventHandler){
        Button button = new Button(name);
        //button.setFocusTraversable(false);
        button.setOnAction(eventHandler);

        this.buttons.getChildren().add(button);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        this.updateMoney();
    }

    public void addMoney(int money) {
        this.money += money;
        this.updateMoney();
    }

    public void removeMoney(int money) {
        this.money -= money;
        if(this.money < 0)
            this.money = 0;
        this.updateMoney();
    }

    public void updateMoney(){
        Text text = (Text) this.getGridPane().getChildren().get(1);
        text.setText("Pokédollars : " + String.valueOf(this.money));
    }
}

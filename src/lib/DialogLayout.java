package lib;


import config.Action;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class DialogLayout {
    private Text text;
    private GridPane gridPane;
    private VBox buttons;
    private int money;

    private static final DialogLayout INSTANCE = new DialogLayout();

    private DialogLayout() {
        this.text = new Text();
        this.text.setWrappingWidth((float) (MainLayout.getWIDTH()*2/3)*2/3);
        this.text.setTextAlignment(TextAlignment.JUSTIFY);
        this.text.setFont(Font.font("Lucida Console"));
        this.text.setFill(Color.WHITE);
        VBox pane = new VBox();
        pane.getChildren().add(this.text);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50));
        GridPane.setConstraints(pane, 0, 0);

        this.money = 0;
        VBox pane1 = new VBox();
        pane1.setAlignment(Pos.CENTER);
        Text text = new Text();
        text.setFill(Color.WHITE);

        pane1.getChildren().add(text);
        GridPane.setConstraints(pane1, 1, 1 );

        this.buttons = new VBox();
        this.buttons.setSpacing(10);
        this.buttons.setAlignment(Pos.CENTER);
        GridPane.setConstraints(this.buttons, 1, 0 );

        this.gridPane = new GridPane();
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        this.gridPane.getChildren().add(pane);
        this.gridPane.getChildren().add(pane1);
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
        button.setOnAction(eventHandler);
        button.getStyleClass().add("interact_button");
        button.focusedProperty().addListener((observable, oldValue, newValue) -> button.setStyle(observable.getValue() ? "-fx-background-color: lightgrey;" : ""));

        this.buttons.getChildren().add(button);

        Movement.removeMovement();
        button.requestFocus();
    }

    public void addReturnButton(){
        Button button = new Button("Retour");
        button.setOnAction(Action.RETURN.getEventHandler());
        button.setCancelButton(true);
        button.getStyleClass().add("interact_button");
        button.focusedProperty().addListener((observable, oldValue, newValue) -> button.setStyle(observable.getValue() ? "-fx-background-color: lightgrey" : ""));

        this.buttons.getChildren().addAll(button);

        Movement.removeMovement();
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
        VBox pane = (VBox) this.getGridPane().getChildren().get(1);
        Text text = (Text) pane.getChildren().get(0);
        text.setText("Argent : " + String.valueOf(this.money));
    }
}

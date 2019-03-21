package lib;


import config.Action;
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
        this.text.setTextAlignment(TextAlignment.CENTER);
        this.text.setFont(Font.font("Lucida Console"));
        this.text.setFill(Color.WHITE);
        //this.text.setStyle("-fx-text-inner-color: white;");
        Pane pane = new Pane();
        //pane.setMinWidth((float) (MainLayout.getWIDTH()*2/3)*2/3);
        //pane.setMinHeight((float) (MainLayout.getHEIGHT()/3));
        pane.getChildren().add(this.text);
        GridPane.setConstraints(this.text, 0, 0);

        this.money = 0;
        Text text = new Text();
        text.setFill(Color.WHITE);
        GridPane.setConstraints(text, 1, 1 );

        this.buttons = new VBox();
        this.buttons.setSpacing(10);
        this.buttons.setAlignment(Pos.CENTER);
        GridPane.setConstraints(this.buttons, 1, 0 );

        this.gridPane = new GridPane();
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

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
        button.setOnAction(eventHandler);
        button.setStyle("-fx-background-color: white;" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 12px;");

        this.buttons.getChildren().add(button);

        Movement.removeMovement();
        button.requestFocus();
    }

    public void addReturnButton(){
        Button button = new Button("Retour");
        button.setOnAction(Action.RETURN.getEventHandler());
        button.setCancelButton(true);
        button.setStyle("-fx-background-color: white;" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-font-size: 12px;");

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
        Text text = (Text) this.getGridPane().getChildren().get(1);
        text.setText("Pokédollars : " + String.valueOf(this.money));
    }
}

package view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;

public class MainView {

    public StackPane root = new StackPane();

    public Rectangle elevator = new Rectangle(120, 160, Color.LIGHTGRAY);

    public Rectangle leftDoor = new Rectangle(60, 160, Color.BLACK);
    public Rectangle rightDoor = new Rectangle(60, 160, Color.BLACK);

    public Button floor1btn = new Button("1");
    public Button floor2btn = new Button("2");
    public Button floor3btn = new Button("3");

    public Label doorStateLbl = new Label("Door: ");
    public Label elevatorStateLbl = new Label("Elevator: ");
    public Label currFloorLbl = new Label("Current floor: ");

    public TextArea textArea = new TextArea();

    public MainView() {

        textArea.setEditable(false);
        textArea.setPrefRowCount(5);
        textArea.setPrefWidth(300);

        HBox doors = new HBox(leftDoor, rightDoor);

        StackPane elevatorStack = new StackPane(elevator, doors);

        HBox buttons = new HBox(10, floor1btn, floor2btn, floor3btn);
        buttons.setAlignment(Pos.CENTER);
        VBox status = new VBox(5, doorStateLbl, elevatorStateLbl, currFloorLbl);

        HBox mainLayout = new HBox(40);
        
        VBox leftSide = new VBox(elevatorStack);
        leftSide.setAlignment(Pos.CENTER);

        VBox rightSide = new VBox(15, buttons, textArea, status);
        rightSide.setAlignment(Pos.TOP_CENTER);

        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(leftSide, rightSide);

        root.getChildren().add(mainLayout);
    }

    public Parent getBorderPane() {
        return root;
    }

    public Rectangle getElevatorView() {
        return elevator;
    }

    public Rectangle getLeftDoor() {
        return leftDoor;
    }

    public Rectangle getRightDoor() {
        return rightDoor;
    }

    public Button getFloor1btn() {
        return floor1btn;
    }

    public Button getFloor2btn() {
        return floor2btn;
    }

    public Button getFloor3btn() {
        return floor3btn;
    }

    public Label getDoorStatelbl() {
        return doorStateLbl;
    }

    public Label getElevatorStatelbl() {
        return elevatorStateLbl;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Label getCurrentFloor(){
        return currFloorLbl;
    }

}

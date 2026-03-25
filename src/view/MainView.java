package view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;

public class MainView {

    BorderPane root = new BorderPane();

    Pane elevatorView = new Pane();
    Rectangle leftDoor = new Rectangle(115, 240);
    Rectangle rightDoor = new Rectangle(115, 240);

    Button floor1btn = new Button();
    Button floor2btn = new Button();
    Button floor3btn = new Button();

    Label doorStatelbl = new Label();
    Label elevatorStatelbl = new Label();
    TextArea textArea = new TextArea();

    public MainView() {

        Pane content = new Pane();

        content.getChildren().addAll(
            new Rectangle(90, 40, 230, 720),
            new Line(90, 280, 320, 280),
            new Line(90, 520, 320, 520)
        );

        elevatorView.setLayoutX(75);
        elevatorView.setLayoutY(40);

        rightDoor.setLayoutX(115);

        elevatorView.getChildren().addAll(
            new Rectangle(15, 0, 230, 240),
            leftDoor,
            rightDoor
        );

        Pane panel = new Pane();
        panel.setLayoutX(470);
        panel.setLayoutY(120);

        floor3btn.setLayoutY(0);
        floor2btn.setLayoutY(50);
        floor1btn.setLayoutY(100);

        elevatorStatelbl.setLayoutY(170);
        doorStatelbl.setLayoutY(200);

        textArea.setLayoutY(240);
        textArea.setPrefSize(140, 100);
        textArea.setEditable(false);

        panel.getChildren().addAll(
            floor3btn, floor2btn, floor1btn,
            elevatorStatelbl, doorStatelbl, textArea
        );

        content.getChildren().addAll(elevatorView, panel);
        root.setCenter(content);
    }

    public BorderPane getBorderPane() { return root; }

    public Pane getElevatorView() { return elevatorView; }
    public Rectangle getLeftDoor() { return leftDoor; }
    public Rectangle getRightDoor() { return rightDoor; }

    public Button getFloor1btn() { return floor1btn; }
    public Button getFloor2btn() { return floor2btn; }
    public Button getFloor3btn() { return floor3btn; }

    public Label getDoorStatelbl() { return doorStatelbl; }
    public Label getElevatorStatelbl() { return elevatorStatelbl; }
    public TextArea getTextArea() { return textArea; }
}

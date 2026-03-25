package view;

import controller.BindingsHub;
import controller.EventHub;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DoorModel;
import model.ElevatorModel;
import model.FloorModel;
import service.ElevatorService;

public class MainView extends Application {

    private static final double SHAFT_X = 90, SHAFT_Y = 40, SHAFT_W = 230, FLOOR_H = 240;
    private static final double ELEVATOR_X = 75, OPENING_X = 15, OPENING_W = 230, OPENING_H = 240, DOOR_W = 115;

    private Pane elevatorView, leftDoor, rightDoor;
    private Button floor1btn, floor2btn, floor3btn;
    private Label doorStatelbl, elevatorStatelbl;
    private TextArea textArea;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-color:#eeeeee;");

        root.getChildren().addAll(createShaft(), createElevatorView(), createControlPanel());

        ElevatorModel elevatorModel = new ElevatorModel();
        DoorModel doorModel = new DoorModel();
        FloorModel floorModel = new FloorModel();
        ElevatorService elevatorService = new ElevatorService();

        BindingsHub bindingsHub = new BindingsHub(elevatorModel, this, doorModel);
        new EventHub(this, bindingsHub, floorModel, elevatorService, elevatorModel, doorModel);

        stage.setScene(new Scene(root, 700, 800));
        stage.setTitle("Shoji Elevator");
        stage.show();
    }

    private Pane createShaft() {
        Pane pane = new Pane();

        Rectangle shaft = new Rectangle(SHAFT_X, SHAFT_Y, SHAFT_W, FLOOR_H * 3);
        shaft.setFill(Color.rgb(230, 230, 230));
        shaft.setStroke(Color.GRAY);
        pane.getChildren().add(shaft);

        for (int i = 1; i < 3; i++) {
            double y = SHAFT_Y + i * FLOOR_H;
            pane.getChildren().add(new Line(SHAFT_X, y, SHAFT_X + SHAFT_W, y));
        }
        pane.getChildren().filtered(n -> n instanceof Line).forEach(n -> ((Line) n).setStroke(Color.DARKGRAY));
        pane.getChildren().filtered(n -> n instanceof Line).forEach(n -> ((Line) n).setStrokeWidth(3));

        for (int i = 0; i < 3; i++) {
            Text t = new Text(String.valueOf(3 - i));
            t.setX(420);
            t.setY(SHAFT_Y + 120 + i * FLOOR_H);
            pane.getChildren().add(t);
        }

        return pane;
    }

    private Pane createElevatorView() {
        elevatorView = new Pane();
        elevatorView.setLayoutX(ELEVATOR_X);
        elevatorView.setLayoutY(SHAFT_Y);
        elevatorView.setPrefSize(260, OPENING_H);

        Rectangle bg = new Rectangle(OPENING_X, 0, OPENING_W, OPENING_H);
        bg.setFill(Color.rgb(190, 190, 190));

        Image img = new Image(getClass().getResourceAsStream("anime.png"));
        ImageView girl = new ImageView(img);
        girl.setFitHeight(210);
        girl.setPreserveRatio(true);
        girl.setSmooth(true);
        girl.setCache(true);
        girl.setLayoutX(OPENING_X + (OPENING_W - 210 * img.getWidth() / img.getHeight()) / 2);
        girl.setLayoutY(47);

        Rectangle frame = new Rectangle(OPENING_X, 0, OPENING_W, OPENING_H);
        frame.setFill(Color.TRANSPARENT);
        frame.setStroke(Color.rgb(70, 45, 25));
        frame.setStrokeWidth(4);

        elevatorView.getChildren().addAll(bg, girl, createDoorLayer(), frame);
        return elevatorView;
    }

    private Pane createDoorLayer() {
        Pane doorLayer = new Pane();
        doorLayer.setLayoutX(OPENING_X);
        doorLayer.setPrefSize(OPENING_W, OPENING_H);
        doorLayer.setClip(new Rectangle(0, 0, OPENING_W, OPENING_H));

        leftDoor = makeDoor(true);
        rightDoor = makeDoor(false);
        rightDoor.setLayoutX(DOOR_W);

        doorLayer.getChildren().addAll(leftDoor, rightDoor);
        return doorLayer;
    }

    private Pane makeDoor(boolean left) {
        Color wood = Color.rgb(120, 78, 45);
        Color dark = Color.rgb(85, 52, 30);
        Color paper = Color.rgb(234, 228, 210);
        Color grid = Color.rgb(125, 95, 70);

        Pane door = new Pane();
        door.setPrefSize(DOOR_W, OPENING_H);

        Rectangle mask = left ? new Rectangle(47, 52, 68, 136) : new Rectangle(0, 52, 68, 136);
        Shape paperHalf = Shape.intersect(new Circle(left ? DOOR_W : 0, 120, 68), mask);
        paperHalf.setFill(paper);
        paperHalf.setStroke(dark);
        paperHalf.setStrokeWidth(3);

        Pane gridPane = new Pane();
        gridPane.setClip(Shape.intersect(new Circle(left ? DOOR_W : 0, 120, 68), mask));

        for (int i = -4; i <= 4; i++) {
            Line line = new Line((left ? DOOR_W : 0) + i * 17, 52, (left ? DOOR_W : 0) + i * 17, 188);
            line.setStroke(grid);
            gridPane.getChildren().add(line);
        }

        for (int i = 0; i <= 6; i++) {
            Line line = new Line(left ? 47 : 0, 54 + i * 22, left ? DOOR_W : 68, 54 + i * 22);
            line.setStroke(grid);
            gridPane.getChildren().add(line);
        }

        Rectangle border = new Rectangle(DOOR_W, OPENING_H);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(dark);

        door.getChildren().addAll(new Rectangle(DOOR_W, OPENING_H, wood), paperHalf, gridPane, border);
        return door;
    }

    private Pane createControlPanel() {
        Pane panel = new Pane();
        panel.setLayoutX(470);
        panel.setLayoutY(120);

        floor3btn = createButton("3", 0);
        floor2btn = createButton("2", 50);
        floor1btn = createButton("1", 100);

        elevatorStatelbl = new Label("ElevatorStatus:");
        elevatorStatelbl.setLayoutX(0);
        elevatorStatelbl.setLayoutY(170);

        doorStatelbl = new Label("DoorStatus:");
        doorStatelbl.setLayoutX(0);
        doorStatelbl.setLayoutY(200);

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(140, 100);
        textArea.setLayoutX(0);
        textArea.setLayoutY(240);

        panel.getChildren().addAll(floor3btn, floor2btn, floor1btn, elevatorStatelbl, doorStatelbl, textArea);
        return panel;
    }

    private Button createButton(String text, double y) {
        Button button = new Button(text);
        button.setPrefWidth(60);
        button.setAlignment(Pos.CENTER);
        button.setLayoutY(y);
        return button;
    }

    public Pane getElevatorView() { return elevatorView; }
    public Pane getLeftDoor() { return leftDoor; }
    public Pane getRightDoor() { return rightDoor; }
    public Button getFloor1btn() { return floor1btn; }
    public Button getFloor2btn() { return floor2btn; }
    public Button getFloor3btn() { return floor3btn; }
    public Label getDoorStatelbl() { return doorStatelbl; }
    public Label getElevatorStatelbl() { return elevatorStatelbl; }
    public TextArea getTextArea() { return textArea; }

    public static void main(String[] args) {
        launch();
    }
}

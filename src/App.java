import controller.BindingsHub;
import controller.EventHub;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DoorModel;
import model.ElevatorModel;
import model.FloorModel;
import service.ElevatorService;
import view.MainView;

@SuppressWarnings("unused")
public class App extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){
        MainView mainView = new MainView();
        ElevatorModel elevatorModel = new ElevatorModel();
        FloorModel floorModel = new FloorModel();
        ElevatorService elevatorService = new ElevatorService();
        DoorModel doorModel = new DoorModel();

        BindingsHub bindingsHub = new BindingsHub(elevatorModel, mainView, doorModel);
        EventHub eventHub = new EventHub(mainView, bindingsHub, floorModel, elevatorService, elevatorModel, doorModel);


        Scene scene = new Scene(mainView.getBorderPane(), 720, 720);
        stage.setScene(scene);
        stage.setTitle("Elevator");
        stage.show();
    }


}

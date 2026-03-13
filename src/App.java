import controller.BindingsHub;
import controller.EventHub;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ElevatorService;

public class App extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){

        ElevatorService elevatorService = new ElevatorService();

        //BindingsHub bindingsHub = new ElevatorController(elevatorModel, mainView, doorModel);
        //EventHub eventHub = new EventHub(mainView, bindingsHub, floorModel, elevatorService, elevatorModel, doorModel);


        Scene scene = new Scene(null);
        stage.setScene(scene);
        stage.setTitle("Elevator");
        stage.show();
    }


}

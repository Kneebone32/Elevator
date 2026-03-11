import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage){





        Scene scene = new Scene(null);
        stage.setScene(scene);
        stage.setTitle("Elevator");
        stage.show();
    }


}

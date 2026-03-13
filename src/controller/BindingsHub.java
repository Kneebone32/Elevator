package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import model.DoorModel;
import model.ElevatorModel;
import view.MainView;

//Tilpasninger blir gjort når View og Model begynner å ta form
public class BindingsHub {
    private ElevatorModel elevatorModel; 
    private MainView mainView;
    private DoorModel doorModel;


    public ElevatorController(ElevatorModel elevatorModel, MainView mainView, DoorModel doorModel){
        this.elevatorModel = elevatorModel;
        this.mainView = mainView;
        this.doorModel = doorModel;
        bindingsSetup();
    }

    //bindinghub??
    private void bindingsSetup(){
        mainView.getElevatorView().translateYProperty().bind(elevatorModel.getElevatorYCoordProperty());

        elevatorModel.getMovingQueue().addListener((ListChangeListener<String>) c -> {
            String movingQueueTxt = String.join("\n", elevatorModel.getMovingQueue());
            mainView.getTextArea().setText(movingQueueTxt);
        });

        mainView.getLeftDoor().translateYProperty().bind(doorModel.getLeftDoorYCoordProperty());
        mainView.getLeftDoor().translateXProperty().bind(doorModel.getLeftDoorXCoordProperty());

        mainView.getRightDoor().translateYProperty().bind(doorModel.getRightDoorYCoordProperty());
        mainView.getRightDoor().translateXProperty().bind(doorModel.getRightDoorXCoordProperty());

        mainView.getDoorStatelbl().textProperty().bind(Bindings.concat("DoorStatus: ", doorModel.getDoorStatusProperty()));
        mainView.getElevatorStatelbl().textProperty().bind(Bindings.concat("ElevatorStatus: ", elevatorModel.getElevatorStatusProperty()));
        
    }

}

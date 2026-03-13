package controller;

import service.ElevatorService;
import javafx.scene.control.Label;
import model.DoorModel;
import model.ElevatorModel;
import model.FloorModel;
import view.MainView;

//Tilpasninger blir gjort når View og Model begynner å ta form
public class EventHub {
    private MainView mainView;
    private FloorModel floorModel;
    private ElevatorService elevatorService;
    private ElevatorModel elevatorModel;
    private DoorModel doorModel;
    private Label doorStatuslbl;

    
    public EventHub(
        MainView mainView, 
        ElevatorController elevatorController, 
        FloorModel floorModel, 
        ElevatorService elevatorService,
        ElevatorModel elevatorModel,
        DoorModel doorModel
    ){

        this.mainView = mainView;
        this.floorModel = floorModel;
        this.elevatorService = elevatorService;
        this.elevatorModel = elevatorModel;
        this.doorModel = doorModel;
        this.doorStatuslbl = mainView.getDoorStatelbl();
        eventsSetup();
    }

    private void eventsSetup(){
        mainView.getFloor1btn().setOnAction(e -> {
            String floorName = "Floor 1";
            elevatorModel.getMovingQueue().add(floorName);
            double targetY = floorModel.getFloor1YCoord();
            elevatorService.queueElevatorMove(elevatorModel, targetY, floorName, doorModel, doorStatuslbl);
        });

        mainView.getFloor2btn().setOnAction(e -> {
            String floorName = "Floor 2";
            elevatorModel.getMovingQueue().add(floorName);
            double targetY = floorModel.getFloor2YCoord();
            elevatorService.queueElevatorMove(elevatorModel, targetY, floorName, doorModel, doorStatuslbl);
        });

        mainView.getFloor3btn().setOnAction(e -> {
            String floorName = "Floor 3";
            elevatorModel.getMovingQueue().add(floorName);
            double targetY = floorModel.getFloor3YCoord();
            elevatorService.queueElevatorMove(elevatorModel, targetY, floorName, doorModel, doorStatuslbl);
        });
    }


}

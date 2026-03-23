package controller;

import service.ElevatorService;
import javafx.scene.control.Button;
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
        BindingsHub bindingsHub, 
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
        setUpButtons(mainView.getFloor1btn(), 1);
        setUpButtons(mainView.getFloor2btn(), 2);
        setUpButtons(mainView.getFloor3btn(), 3);
    }

    private void setUpButtons(Button button, int floorNumber){
        button.setOnAction(e -> {
            String floorName = "Floor " + floorNumber;
            double targetY = floorModel.getFloorYCoord(floorNumber);
            elevatorModel.getMovingQueue().add(floorName);
            elevatorService.queueElevatorMove(elevatorModel, doorModel, targetY, floorName);
        });
    }

}

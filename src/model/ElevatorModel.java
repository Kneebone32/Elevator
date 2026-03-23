package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ElevatorModel {

    private ObjectProperty<ElevatorStatus> elevatorStatus = new SimpleObjectProperty<>(ElevatorStatus.IDLE);
    private ObservableList<String> movingQueue = FXCollections.observableArrayList();
    private DoubleProperty elevatorYCoord = new SimpleDoubleProperty(200.0);



    public ObjectProperty<ElevatorStatus> getElevatorStatusProperty() {
        return elevatorStatus;
    }

    public ObservableList<String> getMovingQueue() {
        return movingQueue;
    }

    public DoubleProperty getElevatorYCoordProperty() {
        return elevatorYCoord;
    }

    public double getElevatorYCoord() {
        return elevatorYCoord.get();
    }

    public void setElevatorYCoord(double newYCoord) {
        this.elevatorYCoord.set(newYCoord);
    }

    public void setElevatorStatus(ElevatorStatus elevatorStatus) {
        this.elevatorStatus.set(elevatorStatus);
    }



}

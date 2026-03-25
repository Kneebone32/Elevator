package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DoorModel {

    private double rightDoorStartX = -1.00;
    private double leftDoorStartX = 1.00;
    private final double openCoordsAmount = 60.00;
    private ObjectProperty<DoorStatusEnum> doorStatus = new SimpleObjectProperty<>(DoorStatusEnum.CLOSED);

    private DoubleProperty rightDoorXCoord = new SimpleDoubleProperty(rightDoorStartX);
    private DoubleProperty rightDoorYCoord = new SimpleDoubleProperty(200.00);
    private DoubleProperty leftDoorXCoord = new SimpleDoubleProperty(leftDoorStartX);
    private DoubleProperty leftDoorYCoord = new SimpleDoubleProperty(200.00);



    public DoubleProperty getRightDoorXCoordProperty() {
        return rightDoorXCoord;
    }

    public DoubleProperty getRightDoorYCoordProperty() {
        return rightDoorYCoord;
    }

    public DoubleProperty getLeftDoorXCoordProperty() {
        return leftDoorXCoord;
    }

    public DoubleProperty getLeftDoorYCoordProperty() {
        return leftDoorYCoord;
    }

    public ObjectProperty<DoorStatusEnum> getDoorStatusProperty() {
        return doorStatus;
    }

    public double getRightDoorXCoord() {
        return rightDoorXCoord.get();
    }

    public double getRightDoorYCoord() {
        return rightDoorYCoord.get();
    }

    public double getLeftDoorXCoord() {
        return leftDoorXCoord.get();
    }

    public double getLeftDoorYCoord() {
        return leftDoorYCoord.get();
    }

    public double getOpenCoordsAmount() {
        return openCoordsAmount;
    }

    public double getRightDoorStartX() {
        return rightDoorStartX;
    }

    public double getLeftDoorStartX() {
        return leftDoorStartX;
    }
    
}

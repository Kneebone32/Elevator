package service;

import javafx.application.Platform;
import model.DoorModel;
import model.DoorStatusEnum;
import model.ElevatorModel;
import model.ElevatorStatus;

public class DoorService implements Runnable {
    private DoorModel doorModel;
    private ElevatorModel elevatorModel;

    // Konstruktør
    public DoorService(DoorModel doorModel, ElevatorModel elevatorModel) {
        this.doorModel = doorModel;
        this.elevatorModel = elevatorModel;
    }

    @Override
    public void run() {
        try {
            openDoors();
            Thread.sleep(5000);
            closeDoors();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Metode som åpner døren
    private void openDoors() throws InterruptedException {
        Platform.runLater(() -> elevatorModel.setElevatorStatus(ElevatorStatus.DoorOpening));
        Platform.runLater(() -> doorModel.getDoorStatusProperty().set(DoorStatusEnum.Opening));

        double targetRightX = doorModel.getRightDoorStartX() + doorModel.getOpenCoordsAmount();

        while (doorModel.getRightDoorXCoord() < targetRightX) {
            double nextRight = doorModel.getRightDoorXCoord() + 1.0;
            double nextLeft = doorModel.getLeftDoorXCoord() - 1.0;

            Platform.runLater(() -> {
                doorModel.getRightDoorXCoordProperty().set(nextRight);
                doorModel.getLeftDoorXCoordProperty().set(nextLeft);
            });

            Thread.sleep(10);
        }

        Platform.runLater(() -> doorModel.getDoorStatusProperty().set(DoorStatusEnum.Open));
    }

    // Metode som lukker døren
    private void closeDoors() throws InterruptedException {
        Platform.runLater(() -> doorModel.getDoorStatusProperty().set(DoorStatusEnum.Closing));

        double targetRightX = doorModel.getRightDoorStartX();

        while (doorModel.getRightDoorXCoord() > targetRightX) {
            double nextRight = doorModel.getRightDoorXCoord() - 1.0;
            double nextLeft = doorModel.getLeftDoorXCoord() + 1.0;

            Platform.runLater(() -> {
                doorModel.getRightDoorXCoordProperty().set(nextRight);
                doorModel.getLeftDoorXCoordProperty().set(nextLeft);
            });

            Thread.sleep(10);
        }

        Platform.runLater(() -> {
            doorModel.getDoorStatusProperty().set(DoorStatusEnum.Closed);
            elevatorModel.setElevatorStatus(ElevatorStatus.Idle);
        });
    }
}

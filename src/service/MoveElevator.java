package Service;

import javafx.application.Platform;

// Runnable slik at MoveElevator kan kjøres på en bakgrunnstråd via ExecutorService
public class MoveElevator implements Runnable {

    private ElevatorModel elevatorModel;
    private DoorModel doorModel;
    private double targetFloor; 
    private String floorName;  

    public MoveElevator(ElevatorModel elevatorModel, DoorModel doorModel, double targetFloor, String floorName) {
        this.elevatorModel = elevatorModel;
        this.doorModel = doorModel;
        this.targetFloor = targetFloor;
        this.floorName = floorName;
    }

    @Override
    public void run() {
        // Bestem retningen heisen skal bevege seg i: opp eller ned
        double step = (targetFloor > elevatorModel.getElevatorYCoord()) ? 1.0 : -1.0;

        try {
            // Oppdater heisstatusen til å vise riktig bevegelsesretning
            Platform.runLater(() ->
                elevatorModel.setElevatorStatus(step > 0.0 ? ElevatorStatus.MOVING_DOWN : ElevatorStatus.MOVING_UP)
            );

            // Flytt heisen ett steg om gangen til den er fremme ved måletasjen
            while (Math.abs(elevatorModel.getElevatorYCoord() - targetFloor) > 0.1) {
                double nextYCoord = elevatorModel.getElevatorYCoord() + step;

                // Oppdater Y-posisjonen til heisen og begge dørene samtidig på JavaFX-tråden
                Platform.runLater(() -> {
                    elevatorModel.getElevatorYCoordProperty().set(nextYCoord);
                    doorModel.getLeftDoorYCoordProperty().set(nextYCoord);
                    doorModel.getRightDoorYCoordProperty().set(nextYCoord);
                });

                // Liten pause mellom hvert steg for å skape en jevn animasjon
                Thread.sleep(10);
            }

            // Oppdater status og kø etter at denne etasjen er ferdig behandlet
            Platform.runLater(() -> {
                elevatorModel.setElevatorStatus(ElevatorStatus.STOPPED);

                // Fjern den nettop behandlede etasjen fra køen
                if (!elevatorModel.getMovingQueue().isEmpty()) {
                    elevatorModel.getMovingQueue().remove(0);
                }

                // Oppdater "nåværende etasje" kun hvis køen nå er tom
                if (elevatorModel.getMovingQueue().isEmpty()) {
                    elevatorModel.setCurrentFloor(floorName);
                }
            });

        } catch (InterruptedException e) {
            // Tråden ble avbrutt
            Thread.currentThread().interrupt();
        }
    }
}

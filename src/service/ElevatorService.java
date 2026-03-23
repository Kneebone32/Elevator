package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.DoorModel;
import model.ElevatorModel;

public class ElevatorService {

    private ExecutorService executor = Executors.newSingleThreadExecutor(a -> {
        Thread thread = new Thread(a);
        thread.setDaemon(true);
        return thread;
    });

    public void queueElevatorMove(ElevatorModel elevatorModel, DoorModel doorModel) {
        executor.submit(new DoorService(doorModel, elevatorModel));
    }

    public void shutdown(){
        executor.shutdownNow();
    }


}

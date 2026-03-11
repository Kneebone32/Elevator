package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorService {

    private ExecutorService executor = Executors.newSingleThreadExecutor(a -> {
        Thread thread = new Thread(a);
        thread.setDaemon(true);
        return thread;
    });


 //   public void queueElevatorMove(){
 //       //MoveElevator
 //       //DoorService
//
 //       executor.submit(null); //MoveElevator
 //       executor.submit(null); //Doorservice
 //   }




    public void shutdown(){
        executor.shutdownNow();
    }


}

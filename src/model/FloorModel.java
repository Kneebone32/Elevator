package model;

import java.util.HashMap;
import java.util.Map;

public class FloorModel {
    private final Map<Integer, Double> floorCoords = new HashMap<>();

    public FloorModel(){
        floorCoords.put(1, 200.0);
        floorCoords.put(2, 100.0);
        floorCoords.put(3, 0.0);
    }

    public double getFloorYCoord(int floorNumber){
        return floorCoords.get(floorNumber);
    }

}
    
    
    
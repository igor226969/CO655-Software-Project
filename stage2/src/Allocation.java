import java.awt.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of class Allocation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Allocation    
{
    private int[][] rideInformation;
    private ArrayList<Integer> carAllocation = new ArrayList<Integer>();
    private int vehicles;
    private int rides;
    
    public Allocation(String worldAndRides) {
        try{
            WorldAndRides war  = new WorldAndRides(worldAndRides);
            vehicles = war.getVehicles();
            rideInformation = war.getRideInfo();
            rides = war.getRides();
            for(int x = 0; x < rides; x++){
                carAllocation.add(rideInformation[x][6]);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }   
    }
    
    public void printAllocation()
    {
        System.out.print(carAllocation.size() + " ");
        for(int no: carAllocation){
            System.out.print(no + " ");
        }
    }
}
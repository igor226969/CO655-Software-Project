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
    private ArrayList<CarAllocation> carAllocation = new ArrayList<CarAllocation>();
    private int vehicles;
    private int rides;
    
    public Allocation(String worldAndRides) {
        try{
            WorldAndRides war  = new WorldAndRides(worldAndRides);
            vehicles = war.getVehicles();
            rideInformation = war.getRideInfo();
            rides = war.getRides();
            ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
            allocateFirstRide();
            for(int x = vehicles; x < rides; x++){
                int y = 0;
                int positionX = rideInformation[y][2];
                int positionY = rideInformation[y][3];
                
                int sx = rideInformation[x][0];
                //sy = start y coordinate
                int sy = rideInformation[x][1];
                   
               //ex = end x coordinate
                int ex = rideInformation[x][2];
                int ey = rideInformation[x][3];
               
                int earliestStart = rideInformation[x][4];
                int latestFinish = rideInformation[x][5];
               
                int tripDistance = 0;
                int distanceToStart = 0;
                   
                
                CarAllocation car = carAllocation.get(0);
                car.addRideNumber(rideInformation[x][6]);
                
                y += 1;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }   
    }
  
    public void allocateFirstRide()
    {
        for(int x = 0; x < vehicles; x++){
            ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
            rideNumbers.add(rideInformation[x][6]);
            carAllocation.add(new CarAllocation(rideNumbers));
        }
    }
    
    public int calculateDistance(int sx, int sy, int ex, int ey)
    {
       int distance = (Math.abs(ex - sx)) + (Math.abs(ey - sy));
       return distance;
    } 
    
    public void printAllocation()
    {
        String newLine = System.getProperty("line.separator");
        for(int x = 0; x < vehicles; x++){
                CarAllocation car = carAllocation.get(x);
                car.printRideNumbers();
                System.out.print(newLine);
        }
    }
}
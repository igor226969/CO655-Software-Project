import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of class Simulation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Simulation
{
   String worldAndRidesFileName;
   String allocationFileName; 
   private int rows;
   private int columns;
   private int vehicles;
   private int rides;
   private int bonus;
   private int steps;
   private int[][] allRideInformation;
   private ArrayList<Integer> carAllocation = new ArrayList<Integer>();
   
   public Simulation(String worldAndRidesFileName, String allocationFileName) {
       this.worldAndRidesFileName = worldAndRidesFileName;
       this.allocationFileName = allocationFileName;
   }
   
   public void run() {
       
       try {
           WorldAndRides worldAndRides = new WorldAndRides(worldAndRidesFileName);
           Allocation allocation = new Allocation(allocationFileName, worldAndRides);
           
           rows = worldAndRides.getRows();
           columns = worldAndRides.getColumns();
           vehicles = worldAndRides.getVehicles();
           rides = worldAndRides.getRides();
           bonus = worldAndRides.getBonus();
           steps = worldAndRides.getSteps();
           allRideInformation = worldAndRides.getRideInfo();
           
           int score = 0;
           for(int x = 0; x < vehicles; x++){
               int currentLocationX = 0;
               int currentLocationY = 0;
               carAllocation = allocation.getCarAllocation(x);
               int currentSteps = 0;
               
               for(int rideNo: carAllocation)
               {
                   int sx = allRideInformation[rideNo][0];
                   int sy = allRideInformation[rideNo][1];
                   
                   int ex = allRideInformation[rideNo][2];
                   int ey = allRideInformation[rideNo][3];
                   
                   int earliestStart = allRideInformation[rideNo][4];
                   int latestFinish = allRideInformation[rideNo][5];
                 
                   
                   if((currentLocationX != sx || currentLocationY != sy) && currentSteps <= steps)
                   {    
                       int distanceToStart = calculateDistance(currentLocationX,currentLocationY,sx,sy);
                       currentSteps += distanceToStart;
                       int distance = calculateDistance(sx,sy,ex,ey);
                       if(currentSteps <= earliestStart && currentSteps + distance <= steps){
                           currentSteps = earliestStart;
                           int tripFinish = distance + currentSteps;
                           if(tripFinish <= latestFinish && tripFinish <= steps){
                               distance = calculateDistance(sx,sy,ex,ey);
                               score += bonus;
                               score += distance;
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps = tripFinish;
                               
                           }
                       }
                       else if(currentSteps > earliestStart && currentSteps + distance <= steps){
                           distance = calculateDistance(sx,sy,ex,ey);
                           int tripFinish = distance + currentSteps;
                           if(tripFinish <= latestFinish && tripFinish <= steps){
                               distance = calculateDistance(sx,sy,ex,ey);
                               score += distance;
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps = tripFinish;
                               
                           }
                       }
                   }
                   else if(currentLocationX == sx && currentLocationY == sy && currentSteps <= steps){
                       int distance = calculateDistance(sx,sy,ex,ey);
                       if(currentSteps <= earliestStart && currentSteps + distance <= steps){
                           currentSteps = earliestStart;
                           int tripFinish = distance + currentSteps;
                           if(tripFinish <= latestFinish && tripFinish <= steps){
                               distance = calculateDistance(sx,sy,ex,ey);
                               score += bonus;
                               score += distance;
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps = tripFinish;
                               
                           }
                       }
                       else if(currentSteps > earliestStart && currentSteps + distance <= steps){
                           int tripFinish = distance + currentSteps;
                           if(tripFinish <= latestFinish && tripFinish <= steps){
                               distance = calculateDistance(sx,sy,ex,ey);
                               score += distance;
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps = tripFinish;
                               
                           }
                       }
                   }
               }
           }
           System.out.println(score);
       } catch (FileFormatException e) {
           System.out.println ("ERROR "+ e.description());
       }
   }
   
   public int calculateDistance(int sx, int sy, int ex, int ey)
   {
       int distance = (Math.abs(ex - sx)) + (Math.abs(ey - sy));
       return distance;
   }
}

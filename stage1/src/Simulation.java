import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * Point calcualtion
 *
 * @author Igor Drobnica
 * @version 18/02/2020
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
           //File loaded
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
           
           //loops through all cars and the car rides assigned to the car
           for(int x = 0; x < vehicles; x++){
               //resets the position of car
               int currentLocationX = 0;
               int currentLocationY = 0;
               //gets the rides allocated to the car
               carAllocation = allocation.getCarAllocation(x);
               //resests steps
               int currentSteps = 0;
               
               //loops through ridenumbers and retrieves the ride
               for(int rideNo: carAllocation)
               {
                   //all variables needed initialised
                   //sx = start x coordinate
                   int sx = allRideInformation[rideNo][0];
                   //sy = start y coordinate
                   int sy = allRideInformation[rideNo][1];
                   
                   //ex = end x coordinate
                   int ex = allRideInformation[rideNo][2];
                   int ey = allRideInformation[rideNo][3];
                   
                   int earliestStart = allRideInformation[rideNo][4];
                   int latestFinish = allRideInformation[rideNo][5];
                   
                   int tripDistance = 0;
                   int distanceToStart = 0;
                   
                   //checks for car position 
                   if(currentLocationX != sx || currentLocationY != sy){
                       //calculates distance to the first trip and increments the steps
                       distanceToStart = calculateDistance(currentLocationX,currentLocationY,sx,sy);
                       currentLocationX = sx;
                       currentLocationY = sy;
                       currentSteps += distanceToStart;
                       if(currentSteps <= earliestStart && currentSteps < steps){
                           //checks if the car arrives before earliest start and calculates the distance of the trip
                           currentSteps = earliestStart;
                           tripDistance = calculateDistance(currentLocationX,currentLocationY,ex,ey);
                           if(currentSteps + tripDistance <= latestFinish && currentSteps + tripDistance < steps){
                               //checks if car finishes on time and adds score,bonus and steps
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                               score += tripDistance;
                               score += bonus;
                           }
                           else if(currentSteps + tripDistance > latestFinish && currentSteps + tripDistance < steps){
                               //if not finished on time no points added but car finishes journey and steps increase
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                           }
                       }
                       else if(currentSteps >= earliestStart && currentSteps < steps){
                           tripDistance = calculateDistance(currentLocationX,currentLocationY,ex,ey);
                           if(currentSteps + tripDistance <= latestFinish && currentSteps + tripDistance < steps){
                               //if earliest start exceeded but finished on time points are added but bonus omitted
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                               score += tripDistance;
                           }
                           else if(currentSteps + tripDistance > latestFinish && currentSteps + tripDistance < steps){
                               //if not finished on time no points added but car finishes journey and steps increase
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                           }
                       }
                   }
                   else if(currentLocationX == sx && currentLocationY == sy){
                       //if car finishes trip where the next trip starts
                       if(currentSteps <= earliestStart && currentSteps < steps){
                           currentSteps = earliestStart;
                           tripDistance = calculateDistance(currentLocationX,currentLocationY,ex,ey);
                           if(currentSteps + tripDistance <= latestFinish && currentSteps + tripDistance < steps){
                               //earliest start achieved so it checks if it ends on time and adds points,bonus and steps
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                               score += tripDistance;
                               score += bonus;
                           }
                           else if(currentSteps + tripDistance > latestFinish && currentSteps + tripDistance < steps){
                               //if not finished on time no points added but car finishes journey and steps increase
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                           }
                       }
                       else if(currentSteps > earliestStart && currentSteps < steps){
                           tripDistance = calculateDistance(currentLocationX,currentLocationY,ex,ey);
                           if(currentSteps + tripDistance <= latestFinish && currentSteps + tripDistance < steps){
                               //if earliest start exceeded but finished on time points are added but bonus omitted
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                               score += tripDistance;
                           }
                           else if(currentSteps + tripDistance > latestFinish && currentSteps + tripDistance < steps){
                               //if not finished on time no points added but car finishes journey and steps increase
                               currentLocationX = ex;
                               currentLocationY = ey;
                               currentSteps += tripDistance;
                           }
                       }
                   }
               }
           }
           System.out.println(score);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
   }
   
   //calculates the distance between (sx,sy) and (ex,ey)
   public int calculateDistance(int sx, int sy, int ex, int ey)
   {
       int distance = (Math.abs(ex - sx)) + (Math.abs(ey - sy));
       return distance;
   }
}

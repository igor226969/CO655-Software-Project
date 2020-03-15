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
    private ArrayList<Integer> highestPoints = new ArrayList<Integer>();
    private ArrayList<Integer> closestRide = new ArrayList<Integer>();
    private ArrayList<Integer> stepsForRide = new ArrayList<Integer>();
    private int[][] rideInformation;
    private ArrayList<CarAllocation> carAllocation = new ArrayList<CarAllocation>();
    private int vehicles;
    private int rides;
    private int bonus;
    private int steps;
    private int currentSteps;
    private int score;
    private int carNumber;
    
    public Allocation(String worldAndRides) {
        try{
            WorldAndRides war  = new WorldAndRides(worldAndRides);
            vehicles = war.getVehicles();
            bonus = war.getBonus();
            steps = war.getSteps();
            rideInformation = war.getRideInfo();
            rides = war.getRides();
            boolean noRush = false;
            ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
            allocateFirstRide();
            for(int x = vehicles; x < rides; x++){
                int sx = rideInformation[x][0];
                int sy = rideInformation[x][1];
                int ex = rideInformation[x][2];
                int ey = rideInformation[x][3];
                int earliestStart = rideInformation[x][4];
                int latestFinish = rideInformation[x][5];
                for(int y = 0; y < vehicles; y++){
                    if(earliestStart == 0 && latestFinish == steps){
                        noRush = true;
                        CarAllocation car = carAllocation.get(y);
                        currentSteps = car.getSteps();
                        int currentX = car.getX();
                        int currentY = car.getY();
                        closestRide.add(calculateDistance(currentX,currentY,sx,sy));
                        score = calculatePoints(currentX,currentY,sx,sy,ex,ey,earliestStart,latestFinish);
                        highestPoints.add(score);
                    }
                    else{
                        noRush = false;
                        CarAllocation car = carAllocation.get(y);
                        currentSteps = car.getSteps();
                        int currentX = car.getX();
                        int currentY = car.getY();
                    
                        score = calculatePoints(currentX,currentY,sx,sy,ex,ey,earliestStart,latestFinish);
                        highestPoints.add(score);
                    }
                }
                if(noRush){
                    carNumber = getClosestRide();
                }
                else{
                    carNumber = getHighestPointRide();
                }
                CarAllocation car = carAllocation.get(carNumber);
                car.addRideNumber(rideInformation[x][6]);
                car.setX(ex);
                car.setY(ey);
                car.setSteps(stepsForRide.get(carNumber));
                highestPoints.clear();
                stepsForRide.clear();
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
            int sx = rideInformation[x][0];
            int sy = rideInformation[x][1];
            int ex = rideInformation[x][2];
            int ey = rideInformation[x][3];
            int earliestStart = rideInformation[x][4];
            int steps1 = 0;
            carAllocation.add(new CarAllocation(rideNumbers));
            CarAllocation car = carAllocation.get(x);
            if(sx == 0 && sy == 0){
                car.setSteps(calculateDistance(0,0,ex,ey));
            }
            else if(sx != 0 || sy != 0){
                steps1 += calculateDistance(0,0,sx,sy);
                if(steps1 < earliestStart)
                {
                    steps1 = earliestStart;
                    car.setSteps(steps1 + calculateDistance(sx,sy,ex,ey));
                }
                else{
                    car.setSteps(steps1 + calculateDistance(sx,sy,ex,ey));
                }
            }
            car.setX(ex);
            car.setY(ey);
        }
    }

    // public void allocateFirstRide1()
    // {
        // for(int x = 0; x < vehicles; x++){
            // for(int y = 0; y < rides; y++){
                // int sx = rideInformation[y][0];
                // int sy = rideInformation[y][1];
                // int ex = rideInformation[y][2];
                // int ey = rideInformation[y][3];
                // int earliestStart = rideInformation[y][4];
                // int latestFinish = rideInformation[y][5];
                // int score = calculatePoints(0,0,sx,sy,ex,ey,earliestStart,latestFinish);
                // highestPoints.add(score);
            // }
            // int carNumber = getHighestPointRide();
            // CarAllocation car = carAllocation.get(carNumber);
            // car.addRideNumber(rideInformation[x][6]);
            // car.setX(ex);
            // car.setY(ey);
            // car.setSteps(stepsForRide.get(carNumber));
            // highestPoints.clear();
            // stepsForRide.clear();
        // }
        
    // }
    
    public int getClosestRide()
    {
        int low = closestRide.get(0);
        int index = 0;
        for(int i = 0; i < closestRide.size(); i++){
            if(closestRide.get(i) < low){
                low = highestPoints.get(i);
                index = i;
            }
        }
        return index;
    }
    
    public int getHighestPointRide()
    {
        int max = 0;
        int index = 0;
        for(int i = 0; i < highestPoints.size(); i++){
            if(highestPoints.get(i) > max){
                max = highestPoints.get(i);
                index = i;
            }
        }
        return index;
    }
    
    
    public int calculateDistance(int sx, int sy, int ex, int ey)
    {
       int distance = (Math.abs(ex - sx)) + (Math.abs(ey - sy));
       return distance;
    } 
    
    public int calculatePoints(int currentX,int currentY, int sx, int sy,int ex,int ey,int earliestStart,int latestFinish)
    {
        int cSteps = currentSteps;
        int score = 0;
        int tripDistance = 0;
        int distanceToStart = 0;
        if(currentX != sx || currentY != sy){
           //calculates distance to the first trip and increments the steps
           distanceToStart = calculateDistance(currentX,currentY,sx,sy);
           cSteps += distanceToStart;
           if(cSteps <= earliestStart && cSteps < steps){
               //checks if the car arrives before earliest start and calculates the distance of the trip
               cSteps = earliestStart;
               tripDistance = calculateDistance(sx,sy,ex,ey);
               if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   //checks if car finishes on time and adds score,bonus and steps
                   cSteps += tripDistance;
                   score += tripDistance;
                   score += bonus;
               }
               else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                   //if not finished on time no points added but car finishes journey and steps increase
                   cSteps += tripDistance;
               }
           }
           else if(cSteps >= earliestStart && cSteps < steps){
               tripDistance = calculateDistance(sx,sy,ex,ey);
               if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   //if earliest start exceeded but finished on time points are added but bonus omitted
                   cSteps += tripDistance;
                   score += tripDistance;
               }
               else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                   //if not finished on time no points added but car finishes journey and steps increase
                   currentSteps += tripDistance;
               }
           }
       }
       else if(currentX == sx && currentY == sy){
           //if car finishes trip where the next trip starts
           if(cSteps <= earliestStart && cSteps < steps){
               cSteps = earliestStart;
               tripDistance = calculateDistance(sx,sy,ex,ey);
               if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   cSteps += tripDistance;
                   score += tripDistance;
                   score += bonus;
               }
               else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                   //if not finished on time no points added but car finishes journey and steps increase
                   cSteps += tripDistance;
               }
           }
           else if(cSteps > earliestStart && cSteps < steps){
               tripDistance = calculateDistance(sx,sy,ex,ey);
               if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   //if earliest start exceeded but finished on time points are added but bonus omitted
                   cSteps += tripDistance;
                   score += tripDistance;
               }
               else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                   //if not finished on time no points added but car finishes journey and steps increase
                   cSteps += tripDistance;
               }
           }
       }
       stepsForRide.add(cSteps);
       return score;
    }
    // public int calculatePoints(int currentX,int currentY, int sx, int sy,int ex,int ey,int earliestStart,int latestFinish)
    // {
        // int cSteps = currentSteps;
        // int score = 0;
        // int tripDistance = 0;
        // int distanceToStart = 0;
        // if(currentX != sx || currentY != sy){
            // distanceToStart = calculateDistance(currentX,currentY,sx,sy);
            // currentX = sx;
            // currentY = sy;
            // cSteps = distanceToStart;
            // if(cSteps <= earliestStart && cSteps < steps){
                // cSteps = earliestStart;
                // tripDistance = calculateDistance(sx,sy,ex,ey);
                // if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                    // score += tripDistance;
                    // score += bonus;
                    // cSteps += tripDistance;
                // }
                // else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                    // cSteps += tripDistance;
                // }
            // }
            // else if(cSteps >= earliestStart && cSteps < steps){
               // tripDistance = calculateDistance(sx,sy,ex,ey);
               // if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   // cSteps += tripDistance;
                   // score += tripDistance;
               // }
               // else if(currentSteps + tripDistance > latestFinish && currentSteps + tripDistance < steps){
                   // cSteps += tripDistance;
               // }
            // }
        // }
        // else if(currentX == sx && currentY == sy){
            // if(cSteps <= earliestStart && cSteps < steps){
               // cSteps = earliestStart;
               // tripDistance = calculateDistance(currentX,currentY,ex,ey);
               // if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   // cSteps += tripDistance;
                   // score += tripDistance;
                   // score += bonus;
               // }
               // else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                   // cSteps += tripDistance;
               // }
            // }
            // else if(cSteps > earliestStart && cSteps < steps){
                // tripDistance = calculateDistance(currentX,currentY,ex,ey);
                // if(cSteps + tripDistance <= latestFinish && cSteps + tripDistance < steps){
                   // cSteps += tripDistance;
                   // score += tripDistance;
                // }
                // else if(cSteps + tripDistance > latestFinish && cSteps + tripDistance < steps){
                    // cSteps += tripDistance;
                // }
            // }
        // }
        // stepsForRide.add(cSteps);
        // return score;
    // }
    
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
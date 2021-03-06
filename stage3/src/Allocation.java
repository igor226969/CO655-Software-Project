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
    private ArrayList<Integer> closestRides = new ArrayList<Integer>();
    private ArrayList<Integer> stepsForRide = new ArrayList<Integer>();
    private ArrayList<Integer> takenRides = new ArrayList<Integer>();
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
                        CarAllocation car = carAllocation.get(y);
                        int currentX = car.getX();
                        int currentY = car.getY();
                        closestRides.add(calculateDistance(currentX,currentY,sx,sy));
                        currentSteps = car.getSteps();
                    }
                    carNumber = getClosestRide();
                    CarAllocation car = carAllocation.get(carNumber);
                    car.setX(ex);
                    car.setY(ey);
                    car.addRideNumber(rideInformation[x][6]);
                    car.addRides();
                    if(x < rides - 3){
                        for(int z = x + 1; z < x + 3; z++){
                            car.addRideNumber((rideInformation[z][6]));
                            car.addRideNumber((rideInformation[z][6]));
                            car.addRides();
                        }
                        car.addRideNumber(rideInformation[x][6]);
                        x += 3;
                        closestRides.clear();
                    }
                    else{
                        car.addRideNumber(rideInformation[x][6]);
                        closestRides.clear();
                    }
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
            rideNumbers.add(rideInformation[x][6]);
            int sx = rideInformation[x][0];
            int sy = rideInformation[x][1];
            int ex = rideInformation[x][2];
            int ey = rideInformation[x][3];
            int earliestStart = rideInformation[x][4];
            int steps1 = 0;
            carAllocation.add(new CarAllocation(rideNumbers));
            CarAllocation car = carAllocation.get(x);
            car.addRides();
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
            takenRides.add(rideInformation[x][6]);
            car.setX(ex);
            car.setY(ey);
        }
    }

    public int getClosestRide()
    {
        int max = closestRides.get(0);
        int index = 0;
        for(int i = 0; i < closestRides.size(); i++){
            if(closestRides.get(i) < max){
                max = closestRides.get(i);
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
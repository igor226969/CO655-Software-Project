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
    private ArrayList<CarAllocation> allocatedRides = new ArrayList<CarAllocation>();
    
    public Allocation(String allocationFileName, WorldAndRides worldAndRides) throws FileFormatException {
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(allocationFileName)));
            while(sc.hasNextLine()){
                ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
                String[] line = sc.nextLine().split(" ");
                for(int i = 1; i < line.length; i++){
                    rideNumbers.add(Integer.parseInt(line[i]));
                }
                allocatedRides.add(new CarAllocation(rideNumbers));
            }
        }
        catch(IOException ex){
            System.out.println ("ERROR "+ ex);
        }
    }
    
    public ArrayList getAllocatedRides()
    {
        return allocatedRides;
    }
    
    public ArrayList getCarAllocation(int index)
    {
        CarAllocation car = allocatedRides.get(index);
        return car.getRideNumbers();
    }
    
    public int getNumberOfRides(int index)
    {
        CarAllocation car = allocatedRides.get(index);
        return car.getRideNumbers().size();
    }
}
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
    Set<Integer> set = new HashSet<Integer>();
    
    public Allocation(String allocationFileName, WorldAndRides worldAndRides) throws FileFormatException {
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(allocationFileName)));
            while(sc.hasNextLine()){
                ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
                String[] line = sc.nextLine().split(" ");
                if(Integer.parseInt(line[0]) == line.length - 1){
                    for(int i = 1; i < line.length; i++){
                        if(set.add(Integer.parseInt(line[i]))){
                            rideNumbers.add(Integer.parseInt(line[i]));
                        }
                        else{
                            throw new FileFormatException("Duplicate rides found, Invalid Allocation file format.");
                        }
                    }
                    allocatedRides.add(new CarAllocation(rideNumbers));
                }
                else{
                    throw new FileFormatException("Invalid Allocation file format.");
                }
            }
            sc.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
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
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
            //scanner initiated
            Scanner sc = new Scanner(new BufferedReader(new FileReader(allocationFileName)));
            while(sc.hasNextLine()){
                //array list that stores car number created
                ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
                //array that stoes scanned line created
                String[] line = sc.nextLine().split(" ");
                //chbecks if there are the amount of rides specified or throws exception
                if(Integer.parseInt(line[0]) == line.length - 1){
                    //loops through array and extracts 
                    for(int i = 1; i < line.length; i++){
                        //checks for duplicate ride nubers and throws exception
                        if(set.add(Integer.parseInt(line[i]))){
                            rideNumbers.add(Integer.parseInt(line[i]));
                        }
                        else{
                            throw new FileFormatException("Duplicate rides found, Invalid Allocation file format.");
                        }
                    }
                    //creates object for car that stores car rides and adds it to an array list
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
    
    //accessor methods
    
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
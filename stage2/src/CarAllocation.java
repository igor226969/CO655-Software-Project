import java.util.*;

public class CarAllocation
{
    public ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
    
    /**
     * Constructor for objects of class CarAllocation
     */
    public CarAllocation(ArrayList rideNum)
    {
        rideNumbers = rideNum;
    }

    public ArrayList getRideNumbers()
    {
        return rideNumbers;
    }
    
    public void addRideNumber(int i)
    {
        rideNumbers.add(i);
    }
    
    public void printRideNumbers()
    {
        System.out.print(rideNumbers.size()+" ");
        for(int no: rideNumbers){
            System.out.print(no + " ");
        }
    }
}

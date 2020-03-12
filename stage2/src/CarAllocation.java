import java.util.*;

public class CarAllocation
{
    public ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
    public int positionX;
    public int positionY;
    
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
    
    public int getX()
    {
        return positionX;
    }
    
    public int getY()
    {
        return positionY;
    }
    
    public void setY(int i)
    {
        positionY = i;
    }
    
    public void setX(int i)
    {
        positionX = i;
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

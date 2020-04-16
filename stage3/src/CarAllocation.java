import java.util.*;

public class CarAllocation
{
    public ArrayList<Integer> rideNumbers = new ArrayList<Integer>();
    public int positionX;
    public int positionY;
    public int steps;
    
    /**
     * Constructor for objects of class CarAllocation
     */
    public CarAllocation(ArrayList rideNum)
    {
        rideNumbers = rideNum;
        steps = 0;
    }

    public ArrayList getRideNumbers()
    {
        return rideNumbers;
    }
    
    public void delete()
    {
        rideNumbers.remove(rideNumbers.size() - 1);
    }
    
    public int getSteps()
    {
        return steps;
    }
    
    public void setSteps(int i)
    {
        steps = i;
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
        ArrayList<Integer> pickUp = new ArrayList<Integer>();
        System.out.print((rideNumbers.size())/2+" ");
        for(int no: rideNumbers){
            
            if(pickUp.contains(no)){
                System.out.print("d"+no + " ");
            }
            else{
                System.out.print("p"+no + " ");
                pickUp.add(no);
            }
        }
    }
}

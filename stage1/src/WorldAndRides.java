import java.awt.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of class WorldAndRides here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WorldAndRides
{
    private int rows;
    private int columns;
    private int vehicles;
    private int rides;
    private int bonus;
    private int steps;
    private int[][] rideInformation;
    
    public WorldAndRides(String worldAndRidesFileName) throws FileFormatException {
        try{ 
            Scanner sc = new Scanner(new BufferedReader(new FileReader(worldAndRidesFileName)));
            String[] line = sc.nextLine().split(" ");
            
            rows = Integer.parseInt(line[0]);
            columns = Integer.parseInt(line[1]);
            vehicles = Integer.parseInt(line[2]);
            rides = Integer.parseInt(line[3]);
            bonus = Integer.parseInt(line[4]);
            steps = Integer.parseInt(line[5]);
            
            rideInformation = new int[rides][6];
            
            for(int x = 0; x < rides; x++)
            {
                line = sc.nextLine().split(" ");
                if(verify(line)){
                    for(int y = 0; y < 6; y++)
                    {
                        rideInformation[x][y] = Integer.parseInt(line[y]);
                    }
                }
                else{
                    throw new FileFormatException("X or Y coordinates out of bounds, Invalid file format");
                }
            }
            sc.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean verify(String[] line)
    {
        if(Integer.parseInt(line[0]) > getRows() || Integer.parseInt(line[1]) > getColumns() || Integer.parseInt(line[2]) > getRows() || Integer.parseInt(line[3]) > getColumns()){
            return false;
        }
        else if(Integer.parseInt(line[0]) < 0 || Integer.parseInt(line[1]) < 0 || Integer.parseInt(line[3]) < 0 || Integer.parseInt(line[4]) < 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int[][] getRideInfo()
    {
        return rideInformation;
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public int getColumns()
    {
        return columns;
    }
    
    public int getVehicles()
    {
        return vehicles;
    }
    
    public int getRides()
    {
        return rides;
    }
    
    public int getBonus()
    {
        return bonus;
    }
    
    public int getSteps()
    {
        return steps;
    }
}

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
    private int row;
    private int column;
    private int vehicles;
    private int rides;
    private int bonus;
    private int steps;
    private int[][] rideInformation;
    
    public WorldAndRides(String worldAndRidesFileName) throws FileFormatException {
        try{ 
            Scanner sc = new Scanner(new BufferedReader(new FileReader(worldAndRidesFileName)));
            String[] line = sc.nextLine().split(" ");
            
            row = Integer.parseInt(line[0]);
            column = Integer.parseInt(line[1]);
            vehicles = Integer.parseInt(line[2]);
            rides = Integer.parseInt(line[3]);
            bonus = Integer.parseInt(line[4]);
            steps = Integer.parseInt(line[5]);
            rideInformation = new int[rides][6];
            
            for(int x = 0; x < rides; x++)
            {
                line = sc.nextLine().split(" ");
                for(int y = 0; y < 5; y++)
                {
                    rideInformation[x][y] = Integer.parseInt(line[y]);
                }
            }
        }
        catch(IOException ex){
            System.out.println("file loading error");
        }
    }
}

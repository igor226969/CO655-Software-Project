import java.awt.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Igor Drobnica
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
            
            //creates variables about world and rides
            rows = Integer.parseInt(line[0]);
            columns = Integer.parseInt(line[1]);
            vehicles = Integer.parseInt(line[2]);
            rides = Integer.parseInt(line[3]);
            bonus = Integer.parseInt(line[4]);
            steps = Integer.parseInt(line[5]);
            
            rideInformation = new int[rides][8];
            
            for(int x = 0; x < rides; x++)
            {
                line = sc.nextLine().split(" ");
                rideInformation[x][6] = x;
                rideInformation[x][7] = rideInformation[x][0] + rideInformation[x][1];
                //checks the foramat and throws exception
                if(verify(line)){
                    for(int y = 0; y < 6; y++)
                    {
                        //rides stored in nested array
                        rideInformation[x][y] = Integer.parseInt(line[y]);
                    }
                }
                else{
                    throw new FileFormatException("X or Y coordinates out of bounds, Invalid file format.");
                }
            }
            sc.close();
            QuickSort(rideInformation,0,rides-1);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    private static void QuickSort(int[][] a, int l, int r) {
        int i;
        if (r > l){
            i = partition(a, l, r);
            QuickSort(a, l, i-1);
            QuickSort(a, i+1, r);
        }
    }

    private static int partition(int[][] a, int l, int r) {
        int v = a[r][7];
        int i = l;
        int j = r;
        int[] temp;
        while (i < j){
            while (a[i][7] < v){
                i = i + 1;
            }
            while ((i < j) && (a[j][7] >= v)){
                j = j - 1;
            }
            temp = a[i];
            if (i < j){
                a[i] = a[j];
                a[j] = temp;
            }else{
                a[i] = a[r];
                a[r] = temp;
            }
        }
        return i;
    }

    //verifies the x and y coordinates
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
    
    //accessor mehtods
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

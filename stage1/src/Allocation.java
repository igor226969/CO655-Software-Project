import java.awt.*;
import java.util.Arrays;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
/**
 * Write a description of class Allocation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Allocation    
{
   ArrayList<ArrayList<String>> carInfo;
    
    public Allocation(String allocationFileName, WorldAndRides worldAndRides) throws FileFormatException {
        
        carInfo = readFile(allocationFileName);
    }
    
    public String[][] readFile(String filename)
    {
        int y = 0;
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
            while(sc.nextLine().split("") != null){
                String[] line = sc.nextLine().split("");
                for (String s: line){
                    int x = 0;
                    temp[x][y] = line[x];
                    
                }
            }
        }
        catch(IOException ex){
            return null;
        }
    }
}
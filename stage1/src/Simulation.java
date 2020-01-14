
/**
 * Write a description of class Simulation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Simulation
{
   String worldAndRidesFileName;
   String allocationFileName; 
    
   public Simulation(String worldAndRidesFileName, String allocationFileName) {
       this.worldAndRidesFileName = worldAndRidesFileName;
       this.allocationFileName = allocationFileName;
   }
    
    
   public void run() {
       
       try {
           WorldAndRides worldAndRides = new WorldAndRides(worldAndRidesFileName);
       
           Allocation allocation = new Allocation(allocationFileName, worldAndRides);
           
           int score = 0;
           //compute score of allocation
           //....
           System.out.println(score);
       } catch (FileFormatException e) {
           System.out.println ("ERROR "+ e.description());
       }
       
   }
}

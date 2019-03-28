
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SaveOurPlanet 
{
    public static void main(String[] args)throws IOException 
    {
        double milesPerDay, gasCost, mpgOld; // inputs
        double avgMPG, avgMpg, annualSavings, lifeSavings, elecMiles, gasMiles;
        double electricGallons, gasGallons;
        double gasMPG = 55.0; double elecMPG = 135.0; 
        DecimalFormat df = new DecimalFormat("0.00");
        FileReader fr = new FileReader("SaveOurPlanetIn.txt");
	Scanner in = new Scanner(fr);
        int nCases = in.nextInt();
        for(int cn = 1; cn <=nCases; cn++)
        {
            milesPerDay = in.nextDouble();
            gasCost = in.nextDouble();
            mpgOld = in.nextDouble();
            if(milesPerDay <= 25)
            {
                elecMiles = milesPerDay;
                avgMPG = elecMPG;
            }
            else
            {
                elecMiles = 25.0;
                gasMiles = milesPerDay - 25.0;
                electricGallons = elecMiles / elecMPG;
                gasGallons = gasMiles / gasMPG;
                avgMPG = (milesPerDay) / (electricGallons + gasGallons);
            }

            annualSavings = gasCost * ((365 * milesPerDay / mpgOld)  - (365 * milesPerDay /avgMPG))  ;
            lifeSavings = annualSavings * 200000 / (365 * milesPerDay);

            System.out.println(df.format(avgMPG) + " " + 
                                df.format(annualSavings) + " " + 
                                df.format(lifeSavings));
        }
    }
    
}

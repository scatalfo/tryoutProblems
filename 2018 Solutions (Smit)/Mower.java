
package mower;

import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Mower 
{
    public static void main(String[] args)throws IOException
	{
	   FileReader fr = new FileReader("MowerIn.txt");
	   Scanner in = new Scanner(fr);
	   int nCases = in.nextInt();
           double height = 300.0;
           double width = 300.0;
           double mowerWidth = 50.0;
           double speed  = 10.0;
           double turnTime = 1.0;
	   DecimalFormat df = new DecimalFormat("0.00");	   
	   for(int cn = 1; cn <=nCases; cn++)
	   {
             mowerWidth = in.nextDouble();
             height = in.nextDouble();
             width = in.nextDouble();
             speed = in.nextDouble();
             turnTime = in.nextDouble();
             
             System.out.println(df.format(mower(height, width, mowerWidth, speed, turnTime)) );
           }
    }
    
    public static double mower(double height, double width, double mowerWidth, 
                               double speed, double turnTime)
    { // recursinve solution
        double rp, gs;
        //double time = 0;
        if(height <= mowerWidth ) return 0;
        
        if(width <= mowerWidth) 
        {
            return ((height - mowerWidth)/ speed);
        }
        
        if(width <= 2 * mowerWidth)
        {
            return (height - mowerWidth)/ speed + turnTime + turnTime + (height - 2 * mowerWidth) / speed; 
        } 

        rp = mower(height - (2 * mowerWidth), width - (2 * mowerWidth), mowerWidth, speed, turnTime);
        gs = rp + (height - mowerWidth) / speed + turnTime + (width - 2 * mowerWidth) / speed + turnTime + 
                  (height - 2 * mowerWidth) / speed + 2 * turnTime ;
        
        return gs;
    }        
}



import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class WeakBridges 
{    
    public static void main(String[] args) throws IOException
    {
	   FileReader fr = new FileReader("WeakBridgesIn.txt");
	   Scanner in = new Scanner(fr);
           int from, to, nPassengers, totalPassengers, nLastTrip;
           int nCities, nBridges;
	   int nCases = in.nextInt();
           int row, col, bCapacity;
           int [][] adjacent; //stores bridge capacities, 0 if no bridge exists
           int max, newMax, result, nTrips;

           for(int cn = 1; cn <=nCases; cn++)
	   {
               from = in.nextInt()-1; to = in.nextInt()-1; 
               nPassengers = in.nextInt();
               totalPassengers = nPassengers;
               nCities = in.nextInt(); nBridges = in.nextInt();
               adjacent = new int[nCities][nCities]; //row from city, col to city
               for(int i = 1; i<=nBridges; i++)//fill in the adjacency matrix
               {
                    row = in.nextInt() - 1; col = in.nextInt() - 1; 
                    bCapacity = in.nextInt();
                    adjacent[row][col] = bCapacity; adjacent[col][row] = bCapacity;
               }
               result = findMax(from, to, -1, 9999, adjacent, nCities);
               nTrips = 0;
               while(nPassengers > 0)
               {
                   nPassengers++;
                   nTrips++;
                   nPassengers = nPassengers - result;        
               }    
               if(totalPassengers % (result-1) != 0)
                   System.out.println(nTrips+ " " + totalPassengers % (result-1));
               else
                   System.out.println(nTrips+ " " + (result-1));
            }
    } 

    public static int findMax(int from, int to, int result, int maxThisRoute, 
                              int adjacent[][], int size) 
    {   // returns the maximum number of tourists per trip between the from and 
        // to cities recusively
        int newMax;
        int[][] copy = new int[size][size];
        if(from == to) // at destination, base case
        {
           return maxThisRoute;
        }
        
        for(int nextCityCol = 0; nextCityCol< size; nextCityCol++) //every city
        {
            if(nextCityCol == from || adjacent[from][nextCityCol] == 0) 
            {    
                continue; //no bridge between from city & nextCityCol city 
            } 
            else //found a city, nextCityCol, adjacent to from city; proceed to it
            {    
                if(adjacent[from][nextCityCol] < maxThisRoute)// weakest bridge so far 
                {
                    maxThisRoute = adjacent[from][nextCityCol];
                }
                // copy matrix and eliminate paths back to from city 
                copyMatrix(size, adjacent, copy);
                for(int copyRow = 0; copyRow < size; copyRow++)
                {
                    copy[copyRow][from] = 0; //eliminate paths back to from city
                }
                //find maximum tourists from nextCityCol to destination city 
                newMax = findMax(nextCityCol, to, result, maxThisRoute,
                                 copy, size); // find max tourists from 
                if(newMax < maxThisRoute) //weaker bridge in path from nextCityCol  
                {
                     maxThisRoute = newMax;
                }    
             }
            if(result < maxThisRoute) //found a route with more tourists per trip
            {
                result = maxThisRoute;
            }
            maxThisRoute = 99999;
        }
        return result;
    }
 
    public static void copyMatrix(int size, int[][] adjacent, int[][] copy)
    {
        
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                copy[row][col] = adjacent[row][col];
            }
        }
    }
    
    public static void outputMatrix(int size, int[][] array)
    {
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }
    }        
}

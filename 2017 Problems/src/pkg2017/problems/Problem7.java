/*
 7 Coverage
Input File: CoverageIn.txt
Old McDonald has a farm, and has hired a firm to partially cover several of his planting fields
with rectangular tarpaulins to reduce weed growth. He has specified that the width and height
of the tarpaulins run east-west and north-south respectively. After completing the task, the
workers realized that some of the tarpaulins overlap, and the firm agreed to only charge Mr.
McDonald for the net area covered. That is, they will only charge him once for areas of the
field covered by two or more tarpaulins. For example, Mr. McDonald would be charged for
34 square feet of net coverage for the three tarpaulins positioned as shown below.
Your task is to determine the net coverage and net cost of the tarpaulin installation on each
planting field given the locations and sizes of the tarpaulins, and the cost per square foot of
net coverage.
Inputs:
The first line of input will be the number of planting fields to consider, p, followed by one
group of inputs for each field. The first line in a grouping will contain an integer, n, that
represents the number of tarpaulins placed in the field, followed by an integer that represents
the cost of the tarpaulin installation per net square foot. This will be followed by n lines of
input, one line per tarpaulin that contains four integers. These integers represent the width of
the tarpaulin, followed by its height, followed by the x and y coordinates of its lower left
corner. The units of coordinate system and the tarpaulin sizes is feet, and all inputs on a line
are separated by a space.
Outputs:
There will be one line of output per planting field that contains two integers separated by a
space. The first number will be the net area of the planting field covered by the tarpaulins, and
the second number will be the cost of the installation.
(Sample inputs and outputs on next page)
 5 x 5
5 x 1
3 x 4
(1, 2)
(2, 4)
(3, 5)
Y (north)
X (east)
Sample Inputs
2
3 100
3 4 1 2
5 5 2 4
5 1 3 5
5 200
5 5 2 1
5 2 0 2
4 5 1 0
3 4 3 3
1 2 0 1
Sample Outputs:
34 3400
39 7800 
 */
package pkg2017.problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author benjamincatalfo
 */
public class Problem7 {
    public static void main(String[] args) throws IOException{ //IOException in case we can't find the file
        FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/CoverageIn.txt"); //reads the text file
        BufferedReader in = new BufferedReader(fr); //reads the text file line by line
        
        int numberOfInputs = Integer.parseInt(in.readLine()); //The first line contains one int, which represents the number of inputs
        
        for (int currentInput = 0; currentInput < numberOfInputs; currentInput++){ //for each input
            String line = in.readLine(); //Store the text of the next line
            Scanner scanner = new Scanner(line); //To scan through the line for taking out numbers
            int numberOfTarps = scanner.nextInt(); //The first number in the first line of each input is the number of tarps
            int costPerTarp = scanner.nextInt(); //The second number in the first line of each input is the cost per tarp
            ArrayList<ArrayList<Boolean>> field = new ArrayList<>(); //each square foot on the field is either covered or not covered with tarping
            //Lets give it a million empty square feet to be safe
            for (int i = 0; i < 1000; i++){
                ArrayList<Boolean> row = new ArrayList<>();
                for (int j = 0; j < 1000; j++){
                    row.add(false);
                }
                field.add(row);
            }
            for (int tarp = 0; tarp < numberOfTarps; tarp++){ //for each piece of tarp
                String tarpLine = in.readLine(); //Store the text for this line that represents the tarping dimensions
                Scanner tarpScanner = new Scanner(tarpLine); //to scan through the tarping data and extract the numbers
                int width = tarpScanner.nextInt(); //the first value on this line is the width of the tarp
                int height = tarpScanner.nextInt(); //the second value on this line is the height of the tarp
                int xCoor = tarpScanner.nextInt(); //the third value on this line is the x coordinate of its lower left corner
                int yCoor = tarpScanner.nextInt(); //the fourth value on this line is the y coordinate of its lower left corner
                for (int x = xCoor; x < xCoor + width; x++){ //spans the width of the tarping
                    for (int y = yCoor; y < yCoor + height; y++){ //spans the height of the tarping
                        field.get(x).set(y, true); //makes the current square covered
                    }
                }
            }
            //Now that we've applied all of the tarp, let's count it!
            int netArea = 0;
            for (int row = 0; row < field.size(); row++){ //for each row in the field
                for (int col = 0; col < field.get(row).size(); col++){ //for each column in the field
                    if (field.get(row).get(col)){ //if it is covered
                        netArea++; //then increment netArea
                    }
                }
            }
            int netCost = netArea * costPerTarp; //cost is the area times the cost per square foot
            System.out.println(netArea + " " + netCost);
        }
    }
}

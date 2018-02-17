/*
 5 Lock Ticks
Input File: LockTicksIn.txt
Logan, being always pressed for time, wants to purchase a standard combination lock that
opens with the least number of “ticks”. The ticks are numbered from 0 to 39 on the rotatable
dial (center portion) of the lock, increasing in the clockwise direction. The fixed part of the
lock has an arrow on it, which always "points to" one of the ticks on the dial. Of course, the
arrow points to different ticks as the dial is turned.
The lock comes with three code numbers T1, T2, T3. These are non-negative integers less
than 40, and no two of the three are the same. The lock is opened using a three step procedure:
1. Turn the dial clockwise exactly two full revolutions, and continue to turn it clockwise until
the arrow points to tick T1.
2. Turn the dial one full revolution counterclockwise and continue to turn it counterclockwise
until the arrow points to tick T2.
3. Turn the dial clockwise until the arrow points to tick T3.
The lock should then open. Your task is to tell Logan how many ticks are required to open a
lock, given the lock’s starting tick, and the lock’s combination: T1, T2, T3.
Inputs:
There will be one line of input that contains the number of locks Logan is considering. This
will be followed by one line per lock that contains four integers each separated by a space.
These integers represent the starting tick, followed by the lock’s code numbers: T1, T2, T3.
Output:
There will be one line of output for each lock that contains the number of ticks required to
open the lock.
Sample input
6
0 30 0 35
9 19 6 32
35 0 10 5
0 39 0 38
4 5 6 7
7 6 5 4
Sample output

145
191
170
124
199
161
 */
package pkg2017.problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author benjamincatalfo
 */
public class Problem5 {
    public static void main(String[] args) throws IOException{ //throws IOException because the file might not be found
        FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/LockTicksIn.txt"); //get file
        BufferedReader in = new BufferedReader(fr); //Reads file line by line
        
        int numInputs = Integer.parseInt(in.readLine()); //the first line contains one int, which is the number of inputs
        
        for (int currentLine = 0; currentLine < numInputs; currentLine++){ //for every line of input
            String line = in.readLine(); //store the line as a string
            Scanner scanner = new Scanner(line); //run a scanner through it to get 
            int startingTick = scanner.nextInt(); //first num in line is the tick you start at
            int tick1 = scanner.nextInt(); //second num in line is T1
            int tick2 = scanner.nextInt(); //third num in line is T2
            int tick3 = scanner.nextInt(); //fourth num in line is T3
            int totalTicks = 0; //total ticks travelled
            int currentTick = startingTick; //the tick we are currently on
            
            //Step one: Turn the dial clockwise exactly two full revolutions, and continue to turn it clockwise until the arrow points to tick T1.
            totalTicks += 80; //turns it two full revolutions
            if (currentTick <= tick1){ //if our subtraction would go negative
                totalTicks += 40 + currentTick - tick1; //add 40 and go to T1
            }
            else{ //otherwise
                totalTicks += currentTick - tick1; //just do the subtraction, going to T1 as normal
            }
            currentTick = tick1; //we are now on tick1
            
            //Step two: Turn the dial one full revolution counterclockwise and continue to turn it counterclockwise until the arrow points to tick T2.
            totalTicks += 40; //turns it one full revolution
            if (currentTick <= tick2){ //if our subtraction won't go negative
                totalTicks += tick2 - currentTick; //go to T2 normally
            }
            else{ //otherwise
                totalTicks += 40 + tick2 - currentTick; //add 40 and go to T2
            }
            currentTick = tick2; //we are now on tick2
            
            //Step three: Turn the dial clockwise until the arrow points to tick T3.
            if (currentTick <= tick3){ //if our subtraction would go negative
                totalTicks += 40 + currentTick - tick3; //add 40; going to T3
            }
            else{ //otherwise
                totalTicks += currentTick - tick3; //go to T3 without adding 40
            }
            currentTick = tick3; //we are now on tick3
            
            System.out.println(totalTicks); //We're done!
        }
    }
}

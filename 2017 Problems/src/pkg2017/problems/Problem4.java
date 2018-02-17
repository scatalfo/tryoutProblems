/*
4 Running Rabbits
Input File: RunningIn.txt
Two hungry rabbits, named Skyler and Nora, are running in a straight line through a field
towards a carrot. Skyler is approaching it from the left, and Nora is approaching it from the right
as shown below. On their way to the carrot, they pass under a fence at exactly the same time.
Skylers’s path is inclined to the fence at s degrees, and Nora’s path is inclined to the fence at n
degrees. They both run at a constant, but most times different, speed and they want to arrive at
the carrot at the same time.
Given Skyler’s speed and the inclination of the rabbits’ running directions, your task is to
compute Nora’s speed such that they will arrive at the carrot at the same time.
Inputs:
The first line of input will be the number of cases to consider, followed by one line of input per
case containing three integers each separated by a space. The first of these integers will represent
Skyler’s running speed. The second integer will represent Skyler’s running angle relative to the
fence, s, and the third integer will represent Nora’s running angle relative to the fence, n. The
units of both angles is degrees.

Outputs
There will be one output line per case that represents Nora’s speed such that Nora and Skyler
will arrive at the carrot at the same time. All output should be expressed with one digit of
precision rounded to the nearest tenth.
Sample Input
3
30 45 45
10 60 10
20 30 60
Sample output
30.0
49.9
11.5 
 */
package pkg2017.problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Math.sin;
/**
 *
 * @author benjamincatalfo
 */
public class Problem4 {
    public static void main(String[] args) throws IOException{ //throws IOException because the file might not be found
        FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/RunningIn.txt"); //gets access to the input text file
        BufferedReader in = new BufferedReader(fr); //scans the text file line by line
        
        int numInputLines = Integer.parseInt(in.readLine()); //the first line contains one number, which represents the number of lines of input that will be recieved
        for (int currentInput = 0; currentInput < numInputLines; currentInput++){ //for every input live
            String line = in.readLine(); //store the text of the next line
            Scanner scanner = new Scanner(line); //scan through the text of the line
            
            int skylerSpeed = scanner.nextInt(); //the first int of the input line represents Skyler's running speed
            double skylerAngle = Math.toRadians(scanner.nextInt()); //the second int of the input line represents Skyler's angle (in degrees) towards the fence
            double noraAngle = Math.toRadians(scanner.nextInt()); //the third int of the input line represents Nora's angle (in degrees) towards the fence
            
            double noraSpeed = skylerSpeed * sin(skylerAngle) / sin(noraAngle); //math justification: https://i.imgur.com/MHGX1yO.jpg
            double roundedNoraSpeed = Math.round(noraSpeed * 10.0) / 10.0; //the round method rounds to the nearest int, so you need to multiply up to the amount of digits you're rounding to, then round, then divide again so you get the right answer.
            System.out.println(roundedNoraSpeed);
        }
    }
}

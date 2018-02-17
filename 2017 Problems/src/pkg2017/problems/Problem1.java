/*
1 Seconds To Go
Input File: SecondsToGoIn.txt
Nadia is very excited to be returning home after being away for two months. The pilot of the
airplane she is on has just announced the hours and minutes remaining in the flight, and she
would like to begin counting down the seconds until touchdown. Your task is to convert the
remaining hours and minutes of flight time to seconds, so that she can begin her countdown.
Inputs:
There will be one line of input that contains two integers separated by a space. These represent
the number of hours, followed by the number of minutes, until the airplane lands.

Outputs
There will be one integer output: the number of seconds remaining in Nadia’s flight, annotated as
shown below.
Sample Input
1 10
Sample output
4200 seconds to go 
*/

package pkg2017.problems;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author benjamincatalfo
 */
public class Problem1{   
    public static void main(String[] args) throws IOException{ //throws IOException because the file might not be found
               FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/SecondsToGoIn.txt"); //get access to file
               Scanner in = new Scanner(fr);
               int hours, mins, seconds;
          
               hours = in.nextInt(); //the first num in the input is the number of hours
               mins = in.nextInt(); //the second is the number of minutes
               
               seconds = 60*60*hours + 60*mins; //there are 60*60 seconds in an hour and 60 seconds in a minute
               System.out.println(seconds + " seconds to go");
     }
}
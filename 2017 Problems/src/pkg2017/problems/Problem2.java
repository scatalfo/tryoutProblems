/*
 2 Seven Times Tables
Input File: SevenTimesIn.txt
Bob has been asked to identify which numbers in a given sequence of numbers, each of which
are less than one billion, are members of the seven times table. Although he memorized the table
in grammar school: 7, 14, 21, 28, 35, 42, … he was not required to go past 70. Therefore, he has
asked you to write a program to identify the members of the seven times table that appear in the
sequence of numbers he will be given.
Inputs:
The first line of input will contain an integer that is the number of sequences Bob will be asked
to examine. This will be followed by one line of input per sequence that contains integers, each
separated by a space. The first integer on a line will be a count of the numbers in the sequence,
and the remaining integers on the line will be the numbers that make up the sequence.

Outputs
There will be one line of output per sequence that contains the numbers in the sequence that are
part of the seven times table.
Sample Input
2
6 32 70 62 147 626326 10326722
4 41132 61334 712349 98765436
Sample output
70 147 10326722
41132 61334 98765436 
 */
package pkg2017.problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author benjamincatalfo
 */
public class Problem2 {
    public static void main(String[] args) throws IOException{ //throws IOException because the file might not be found
               FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/SevenTimesIn.txt"); //get access to file
               BufferedReader in = new BufferedReader(fr); //a bufferedreader can read line by line
               
               int numInputLines = Integer.parseInt(in.readLine()); //the first line contains one number, which represents the number of lines of input that will be recieved
               
               //Now we need to store all of the data
               ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>(); //We will store the data in a 2d arraylist
               for (int currentLine = 0; currentLine < numInputLines; currentLine++){ //go through every line
                   String line = in.readLine(); //store the data on the current line
                   Scanner scanner = new Scanner(line); //make a scanner of the current line
                   ArrayList<Integer> nextLine = new ArrayList<Integer>(); //create an empty array list for the next line
                   data.add(nextLine); //and add this new empty arraylist to data
                   while (scanner.hasNextInt()){ //go through every int on this line
                       data.get(currentLine).add(scanner.nextInt()); //and add it to the end of the current line of data
                   }
               }
               
               assert(numInputLines == data.size()); //There should be an arraylist in data for every line of data in the input file
               //Now we need to output the data that is divisible by seven
               for (int i=0; i<data.size(); i++){ //for every arraylist in data
                   for (int j=0; j<data.get(i).size(); j++){ //for every element in each of these arraylists
                       if (data.get(i).get(j) % 7 == 0){ //if the element is divisible by seven
                           System.out.print(data.get(i).get(j) + " "); //then print it out
                       }
                   }
                   System.out.println(""); //Insert an empty line after every line of input
               }
     }
}

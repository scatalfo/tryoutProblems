/*
 3 Ronnie’s Ribbons
Input File: RonniesIn.txt
Ronnie has collected pieces of red ribbons of various lengths. When she needs a length of ribbon
that she does not have, she locates two pieces of ribbon from her collection whose combined
length is the length she needs. Then she tapes them together end-to-end (with no overlap).
Your task is to determine how many ribbons of a given length she could produce from her
collection of ribbon pieces, given the length of ribbon she needs and the length of each piece of
ribbon in the collection.
Inputs:
The first line of input will be the number of ribbon piece collections to consider, followed by two
lines of input per collection. The first of these two lines contains two integers that represent the
length of ribbon Ronnie needs follow by the number of ribbon pieces, n, in the collection. The
second line will contain n integers that represent the length of each of the ribbon pieces in the
collection. The inputs will be separated by a space.
Outputs
There will be one output per ribbon collection that represents the number of ribbons of the given
length she could produce from the collection.
Sample Input
2
7 6
3 4 3 6 1 1
20 10
2 5 18 15 2 15 16 30 6 14
Sample output
2
3
 */
package pkg2017.problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author benjamincatalfo
 */
public class Problem3 {
    public static void main(String[] args) throws IOException{ //throws IOException because the file might not be found
        FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/RonniesIn.txt"); //get access to file
        BufferedReader in = new BufferedReader(fr); //reads the txt file
        
        int numInputLines = Integer.parseInt(in.readLine()); //the first line contains one number, which represents the number of lines of input that will be recieved
        for (int currentLine = 0; currentLine < numInputLines; currentLine++){ //iterate through every 'input' in the file. each input has two lines of information
            String line = in.readLine(); //stores the text of the current line
            Scanner scanner = new Scanner(line); //scanner for this line
            //Each 'input' contains two lines. The first line contains two numbers- the first being the needed ribbon length and the second being the number of ribbon pieces
            int ribbonLength = scanner.nextInt();
            int numPieces = scanner.nextInt();
            //The second line contains the length of each ribbon piece.
            line = in.readLine(); //store the text of the next line
            scanner = new Scanner(line); //and scan through it
            int[] ribbonPieces = new int[numPieces]; //this array will store the length of each ribbon piece
            for (int currentPiece = 0; currentPiece < numPieces; currentPiece++){ //loop for every ribbon piece
                ribbonPieces[currentPiece] = scanner.nextInt(); //and add each piece to our array
            }
            
            //Now we will loop through every possible combination of ribbon pieces. We will keep track of the number found and, once we find on, we will get rid of both ribbons because you can't use them twice
            int possibleCombinations = 0;
            int alreadyCheckedCounter = 0; //counter for alreadyChecked
            int[] alreadyChecked = new int[ribbonPieces.length]; //keep track of the ribbons already used in a combination, you aren't allowed to use a ribbon twice
            for (int i=0; i<ribbonPieces.length; i++){
                for (int j=0; j<ribbonPieces.length; j++){
                    if (i != j && !contains(alreadyChecked, i) && !contains(alreadyChecked, j) && (ribbonPieces[i] + ribbonPieces[j] == ribbonLength)){ //If we found two different pieces that weren't used already and sum up to our ideal ribbon length
                        alreadyChecked[alreadyCheckedCounter] = i; //add i to alreadyChecked
                        alreadyChecked[alreadyCheckedCounter + 1] = j; //add j to alreadyChecled
                        alreadyCheckedCounter += 2; //increment the counter by two since we just added two elements
                        possibleCombinations++; //increment possibleCombinations
                    }
                }
            }
            System.out.println(possibleCombinations); //output our result
        }
    }
    private static boolean contains(int[] arr, int pos){//removes two elements from an array and retursn the new array
        boolean foundIt = false; //initialize as false so if nothing is found it returns false
        for (int i=0; i<arr.length; i++){ //iterate through every element of the array
            if (arr[i] == pos){ //if this element is the one that we are looking for
                foundIt = true; //then change foundIt to true so that we return true
            }
        }
        return foundIt;
    }
}

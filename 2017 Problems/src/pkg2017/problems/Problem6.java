/*
 6 Word Worm
Input File: WordWormIn.txt
Walter, like other members of his Word Worm species, crawls around rectangular grids of text
looking for hidden words. One such grid is shown below. He can crawl either straight to the right,
left, up, or down, or along any of the four diagonal directions: up and to the right, down and to the
right, up and to the left, or down and to the left. When he finds a word along one of these eight
directions, he records the word in his journal, and then crawls along the letters of the word from
its first letter to its last. As he does this, he also enters the row and column numbers of his path
along the word into his journal.
 Column number 0 1 2 3 4 5 6 7 8 9
 0 R T W W I L N P Z C
 1 I E K X L M J E I O
 2 P Q T S N A G O L O
3 V X P U F I W C V L
 4 T O P L P Q E R D S
5 H I I I E M X S J H
6 N O M W E Q O S U S
7 M C P E U I C C P I
 Row number
For example, as he crawled around the above grid, one of the entries he would make in his journal
is PIE (4, 2) (5, 3) (6, 4). Your task is to produce the contents of Walters’s journal, for a given
rectangular grid of text.
Inputs:
The first line of input will be the number rectangular grids of text to consider, followed by one
group of inputs for each grid. The first line in a group will contain the words to find, each
separated by a space. The second line of will contain one integer that is the number of rows in the
rectangular text grid, r. This will be followed by r lines that contain the letters in each row of the
grid, with each letter on a line separated by a space.
Outputs:
There will be one line of output per word searched for. It will contain the word being searched for
followed by the text: Not Found if the word is not found. Otherwise the word being searched for
will be followed by the row and column location of the first letter of the word, followed by the
row and column location of the second letter of the word, etc. The row and column numbers will
be output as: (row, column) with each row and column number output separated by a space.
(Sample inputs and outputs are on the next page) 
Sample Input
2
COMPUTER LOGAN EGG PIE HI COOL TO
8
R T W W I L N P Z C
I E K X L M J E I O
P Q T S N A G O L O
T X P U F I W C V L
T O P L P Q E R D S
H I I I E M X S J H
N O M W E Q O S U S
M C P E U I C C P I
HELP HEAD WALK
4
H O L W
H E A M
T L L C
K H A P
Sample Output
COMPUTER (7, 7) (6, 6) (5, 5) (4, 4) (3, 3) (2, 2) (1, 1) (0, 0)
LOGAN (2, 8) (2, 7) (2, 6) (2, 5) (2, 4)
EGG NOT FOUND
PIE (4, 2) (5, 3) (6, 4)
HI (5, 0) (5, 1)
COOL (0, 9) (1, 9) (2, 9) (3, 9)
TO (4, 0) (4, 1)
HELP (0, 0) (1, 1) (2, 2) (3, 3)
HEAD NOT FOUND
WALK (0, 3) (1, 2) (2, 1) (3, 0)
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
public class Problem6 {
    public static void main(String[] args) throws IOException{ //IOexception incase file not found
        FileReader fr = new FileReader("/Users/benjamincatalfo/NetBeansProjects/2017 Problems/src/pkg2017/problems/WordWormIn.txt"); //reads input txt file
        BufferedReader in = new BufferedReader(fr); //reads file line by line
        
        int numInputs = Integer.parseInt(in.readLine()); //the first line contains one int, representing the number of inputs
        
        for (int currentInput = 0; currentInput < numInputs; currentInput++){ //loops through every input
            ArrayList<String> wordsToFind = new ArrayList<>(); //diamond operator gets rid of redundantly type <String>
            String line = in.readLine(); //line now contains the words that we need to find, we just need to reformat it into an arraylist
            Scanner scanner = new Scanner(line); //scanner to scan through the line for words
            scanner = scanner.useDelimiter(" "); //gtet the words inbetween the spaces
            while (scanner.hasNext()){ //loop until there's nothing left
                wordsToFind.add(scanner.next()); //add each word to our arraylist
            }
            int numRows = Integer.parseInt(in.readLine()); //the next line contains one int, which is the number of rows of our crossword puzzle
            ArrayList<ArrayList<String>> puzzleGrid = new ArrayList<>(); //to store the rectangular array that we will have to find the words in
            for (int row = 0; row < numRows; row++){ //for every row in the grid
                String rowText = in.readLine(); //store this row as a string
                Scanner rowScanner = new Scanner(rowText); //scans through the row text so we can split it up into individual letters
                rowScanner = rowScanner.useDelimiter(" "); //to find all the letters inbetween spaces
                ArrayList<String> rowArray = new ArrayList<>(); //the individual letters will be stored in this arraylist
                while (rowScanner.hasNext()){ //loop until there's nothing left
                    rowArray.add(rowScanner.next()); //add each letter to our arraylist
                }
                puzzleGrid.add(rowArray); //add this arraylist to the grid
            }
            //Now that we've stored everything properly we just need to find the words.
            for (int word = 0; word < wordsToFind.size(); word++){ //for every word we need to find
                ArrayList<ArrayList<Integer>> wordCoordinates = new ArrayList<>(); //to store the coordinates of the word
                ArrayList<ArrayList<Integer>> tempCoordinates = new ArrayList<>(); //so we don't lose track of our coordinates
                for (int row = 0; row < puzzleGrid.size(); row++){ //for every row in the grid
                    for (int col = 0; col < puzzleGrid.get(row).size(); col++){ //for every col in the grid
                        if (puzzleGrid.get(row).get(col).equals(Character.toString(wordsToFind.get(word).charAt(0)))){ //if this grid location has the same letter as the first letter of our word
                            ArrayList<Integer> firstLocation = new ArrayList<>(); //to store the first location
                            firstLocation.add(row); //append row to it
                            firstLocation.add(col); //append col to it
                            tempCoordinates.add(firstLocation);
                            //Check straight and to the right
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((puzzleGrid.get(row).size() > col + letter) && puzzleGrid.get(row).get(col+letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row); //add the row to this enw arraylist
                                    nextLocation.add(col+letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check straight and to the left
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((col - letter >= 0) && puzzleGrid.get(row).get(col-letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row); //add the row to this enw arraylist
                                    nextLocation.add(col-letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check straight and up
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((row - letter >= 0) && puzzleGrid.get(row - letter).get(col).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row-letter); //add the row to this enw arraylist
                                    nextLocation.add(col); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check straight and down
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((puzzleGrid.size() > row + letter) && puzzleGrid.get(row + letter).get(col).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row + letter); //add the row to this enw arraylist
                                    nextLocation.add(col); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check up and to the right
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((row - letter >= 0) && (puzzleGrid.get(row).size() > col + letter) && puzzleGrid.get(row-letter).get(col+letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row-letter); //add the row to this enw arraylist
                                    nextLocation.add(col+letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check down and to the right
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((puzzleGrid.size() > row + letter) && (puzzleGrid.get(row).size() > col + letter) && puzzleGrid.get(row+letter).get(col+letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row+letter); //add the row to this enw arraylist
                                    nextLocation.add(col+letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check up and to the left
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((row - letter >= 0) && (col - letter >= 0) && puzzleGrid.get(row-letter).get(col-letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row-letter); //add the row to this enw arraylist
                                    nextLocation.add(col-letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                            tempCoordinates.add(firstLocation);
                            //Check down and to the left
                            for (int letter = 1; letter < wordsToFind.get(word).length(); letter++){ //for every letter othen than the first one
                                if ((puzzleGrid.size() > row + letter) && (col - letter >= 0) && puzzleGrid.get(row+letter).get(col-letter).equals(Character.toString(wordsToFind.get(word).charAt(letter)))){ //if the word one to the right matches
                                    ArrayList<Integer> nextLocation = new ArrayList<>(); //to store the location of the next word
                                    nextLocation.add(row+letter); //add the row to this enw arraylist
                                    nextLocation.add(col-letter); //also add the column
                                    tempCoordinates.add(nextLocation); //add this coordinate to our arraylist of coordinates
                                }
                            }
                            if (tempCoordinates.size() == wordsToFind.get(word).length()){ //if we found the word
                                wordCoordinates.clear();
                                wordCoordinates.addAll(tempCoordinates);
                            }
                            tempCoordinates.clear();
                        }
                    }
                }
                if (wordCoordinates.size() == wordsToFind.get(word).length()){ //if we found it
                    System.out.print(wordsToFind.get(word) + " ");
                    for (int coor = 0; coor < wordCoordinates.size(); coor++){
                        System.out.print("(" + wordCoordinates.get(coor).get(0) + ", " + wordCoordinates.get(coor).get(1) + ") ");
                    }
                    System.out.println("");
                }
                else{ //if we didn't find it
                    System.out.println(wordsToFind.get(word) + " NOT FOUND");
                }
            }
        }
    }
}

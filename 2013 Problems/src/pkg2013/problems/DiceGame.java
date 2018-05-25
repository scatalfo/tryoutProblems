/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013.problems;

import java.util.*;
import java.io.*;
/**
 *
 * @author Varun Jindal
 */
public class DiceGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("DiceGameIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine();
        while(s.hasNextLine()) {
            String[] params = s.nextLine().split(" ");
            String[] rounds = s.nextLine().split(" ");
            int numPlayers = Integer.parseInt(params[0]);
            int numRounds = Integer.parseInt(params[1]);
            ArrayList<Integer> roundResults = new ArrayList<Integer>();
            for(int i = 0; i < rounds.length; i++) {
                roundResults.add(Integer.parseInt(rounds[i]));
            }
            int largestPlayerSum = 0;
            int winnerIndex = 1;
            int currentPlayerIndex = 1;
            int totalRounds = 0;
            ArrayList<Integer> xWinners = new ArrayList<Integer>();
            while(currentPlayerIndex <= numPlayers) {
                int playerSum = 0;
                for(int i = 0; i < numRounds; i++) {
                    totalRounds++;
                    playerSum += roundResults.remove(0);
                    if(playerSum + ((numRounds - (i + 1)) * 60) < largestPlayerSum) {
                        break;
                    } else if(currentPlayerIndex == numPlayers && playerSum + ((numRounds - (i + 1)) * 10) > largestPlayerSum) {
                        break;
                    }
                }
                if(playerSum > largestPlayerSum) {
                    largestPlayerSum = playerSum;
                    winnerIndex = currentPlayerIndex;
                } else if(playerSum == largestPlayerSum) {
                    xWinners.add(currentPlayerIndex);
                }
                currentPlayerIndex++;
            }
            System.out.println(totalRounds + " " + largestPlayerSum);
            System.out.print(winnerIndex);
            for(Integer player : xWinners) {
                System.out.print(" " + player);
            }
            System.out.println("");
        }
    }
    
}

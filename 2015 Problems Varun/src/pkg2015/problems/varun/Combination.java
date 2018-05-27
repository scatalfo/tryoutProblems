/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2015.problems.varun;
import java.util.*;
import java.io.*;


/**
 *
 * @author Varun Jindal
 */
public class Combination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("CombinationIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine(); // don't need number of locks because using resizable arrays (ArrayLists)
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> box = new ArrayList<ArrayList<Integer>>();
        while(s.hasNextLine()) {
            
            ////////////////// STRING PROCESSING START /////////////////
            
            int n = Integer.parseInt(s.nextLine());
            edges.clear();
            box.clear();
            
            // Could have used s.nextInt() instead of parsing String to Int
            for(int i = 0; i < 4; i++) {
                ArrayList<Integer> line = new ArrayList<Integer>();
                String[] edge = s.nextLine().split(" ");
                for(int j = 0; j < edge.length; j++) {
                    line.add(Integer.parseInt(edge[j]));
                }
                edges.add(line);
            }
            
            // create top row
            ArrayList<Integer> tempTop = new ArrayList<Integer>();
            tempTop.add(0);
            for(int i = 0; i < edges.get(0).size(); i++) {
                tempTop.add(edges.get(0).get(i));
            }
            tempTop.add(0);
            box.add(tempTop);
            
            // create lock rows
            for(int i = 1; i < edges.get(0).size() + 1; i++) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(edges.get(2).get(i - 1));
                for(int j = 1; j < edges.get(0).size() + 1; j++) {
                    temp.add(0);
                }
                temp.add(edges.get(3).get(i - 1));
                box.add(temp);
            }
            
            // create bottom row
            ArrayList<Integer> tempBottom = new ArrayList<Integer>();
            tempBottom.add(0);
            for(int i = 0; i < edges.get(1).size(); i++) {
                tempBottom.add(edges.get(1).get(i));
            }
            tempBottom.add(0);
            box.add(tempBottom);
            
            ///////////////// STRING PROCESSING DONE ///////////////////
            
            
            // print box nicely with edges given and zeros inside
            for(ArrayList<Integer> temp : box) {
                System.out.println(temp);
            }
            System.out.println("");
            
            
            ////////////////////// ALGORITHM START /////////////////////
            
            // when I refer to the lock - means the inner part of box: the n x n square grid inside box
            // when I refer to the box - means the entire box matrix
            
            
            // go through all permutations of first row
            // counter is transformed from base 10 to base n to go through all permutations
            int counter = 0;
            // Math.pow(n, n) is number of possible permutations
            while(counter < Math.pow(n, n)) {
                int place = n;
                int tempCount = counter;
                // set first row to permutation
                while(place >= 1) {
                    box.get(1).set(place, tempCount % n);
                    place--;
                    tempCount /= n;
                }
                counter++;
                
                // set all inner places based on first row
                // r and c represent row and column of lock other than first row of lock (which was set earlier)
                for (int r = 2; r <= n; r++) {
                    for (int c = 1; c <= n; c++) {
                        // computes sum as described in question
                        int sum = box.get(r - 2).get(c) + box.get(r - 1).get(c) + box.get(r - 1).get(c - 1) + box.get(r - 1).get(c + 1);
                        if (sum % n == 0) {   // no number within lock can go over n
                            box.get(r).set(c, 0);
                        } else {
                            box.get(r).set(c, n - (sum % n));
                        }
                    }
                }

                // check if everything is good
                // only have to check last row of lock
                boolean works = true;
                for (int c = 1; c <= n; c++) {
                    int sum = box.get(n - 1).get(c) + box.get(n).get(c) + box.get(n + 1).get(c) + box.get(n).get(c - 1) + box.get(n).get(c + 1);
                    if (sum % n != 0) {
                        works = false;
                    }
                }
                
                // prints solution if there is one
                if (works) {
                    System.out.println("SOLUTION: ");
                    for(ArrayList<Integer> temp : box) {
                        System.out.println(temp);
                    }
                    System.out.println("");
                }
            }   
        }
        
    }
    
}

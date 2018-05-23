/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2015.problems.varun;
import java.util.*;
import java.io.*;


// NOT DONE only did string processing

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
        s.nextLine();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> box = new ArrayList<ArrayList<Integer>>();
        while(s.hasNextLine()) {
            int n = Integer.parseInt(s.nextLine());
            edges.clear();
            box.clear();
            for(int i = 0; i < 4; i++) {
                ArrayList<Integer> line = new ArrayList<Integer>();
                String[] edge = s.nextLine().split(" ");
                for(int j = 0; j < edge.length; j++) {
                    line.add(Integer.parseInt(edge[j]));
                }
                edges.add(line);
            }
            ArrayList<Integer> tempTop = new ArrayList<Integer>();
            tempTop.add(0);
            for(int i = 0; i < edges.get(0).size(); i++) {
                tempTop.add(edges.get(0).get(i));
            }
            tempTop.add(0);
            box.add(tempTop);
            for(int i = 1; i < edges.get(0).size() + 1; i++) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(edges.get(2).get(i - 1));
                for(int j = 1; j < edges.get(0).size() + 1; j++) {
                    temp.add(0);
                }
                temp.add(edges.get(3).get(i - 1));
                box.add(temp);
            }
            ArrayList<Integer> tempBottom = new ArrayList<Integer>();
            tempBottom.add(0);
            for(int i = 0; i < edges.get(1).size(); i++) {
                tempBottom.add(edges.get(1).get(i));
            }
            tempBottom.add(0);
            box.add(tempBottom);
            
            // done with one box processing
            
            // print box nicely for check
            for(ArrayList<Integer> temp : box) {
                System.out.println(temp);
            }
            System.out.println("");
            
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    for(int k = 0; k < n; k++) {
                        
                    }
                }
            }
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2016.problems.varun;

import java.io.*;
import java.util.*;

public class InsideOutside {
    public static void main(String[] args) throws IOException {
        File file = new File("InsideOutsideIn.txt");
        Scanner s = new Scanner(file);
        ArrayList<ArrayList<Integer>> circles = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> insideCircles = new ArrayList<Integer>();
        ArrayList<Integer> outsideCircles = new ArrayList<Integer>();
        int interestDisk;
        int numCircles;
        s.nextLine();
        while(s.hasNextLine()) {
            circles.clear();
            insideCircles.clear();
            outsideCircles.clear();
            String[] params = s.nextLine().split(" ");
            interestDisk = Integer.parseInt(params[1]) - 1;
            numCircles = Integer.parseInt(params[0]);
            String[] circleStr;
            for(int j = 0; j < numCircles; j++) {
                circleStr = s.nextLine().split(" ");
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int i = 0; i < circleStr.length; i++) {
                    temp.add(Integer.parseInt(circleStr[i]));
                }
                circles.add(temp);
            }
            for(int i = 0; i < circles.size(); i++) {
                if(i == interestDisk){
                } else {
                    double centerDist = Math.pow(Math.pow((double) circles.get(i).get(0) - (double) circles.get(interestDisk).get(0), 2) + 
                            Math.pow((double) circles.get(i).get(1) - (double) circles.get(interestDisk).get(1), 2), .5);
                    if(centerDist + circles.get(i).get(2) <= circles.get(interestDisk).get(2)) {
                        insideCircles.add(i + 1);
                    }
                    if(centerDist - circles.get(i).get(2) >= circles.get(interestDisk).get(2)) {
                        outsideCircles.add(i + 1);
                    }
//                    System.out.println((centerDist + circles.get(i).get(2)) + " " + circles.get(interestDisk).get(2));
                }
            }
            
            for(Integer num : insideCircles) {
                System.out.print(num + " ");
            }
            
            for(Integer num : outsideCircles) {
                boolean isThere = false;
                for(Integer numInside : insideCircles) {
                    if(num == numInside) {
                        isThere = true;
                    }
                }
                if(!isThere) {
                    System.out.print(num + " ");
                }
            }
            System.out.println("");
        }
    }
}

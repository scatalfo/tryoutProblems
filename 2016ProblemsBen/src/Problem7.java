
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bcata
 */
public class Problem7 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\InsideOutsideIn.txt");
        BufferedReader br = new BufferedReader(fr);
        int numberOfFloors = Integer.parseInt(br.readLine());
        for (int floor = 0; floor < numberOfFloors; floor++){
            Scanner line = new Scanner(br.readLine());
            int numberOfDisks = line.nextInt();
            int diskOfInterest = line.nextInt();
            ArrayList<Integer> inside = new ArrayList<Integer>();
            ArrayList<Integer> outside = new ArrayList<Integer>();
            ArrayList<ArrayList<Double>> disks = new ArrayList<>();
            for (int disk = 0; disk < numberOfDisks; disk++){
                line = new Scanner(br.readLine());
                double x = line.nextDouble();
                double y = line.nextDouble();
                double radius = line.nextDouble();
                ArrayList<Double> temp = new ArrayList<>();
                temp.add(x);
                temp.add(y);
                temp.add(radius);
                disks.add(temp);
            }
            for (int disk = 0; disk < numberOfDisks; disk++){
                if (disk != diskOfInterest - 1){
                    double distanceBetweenCenters = Math.sqrt(Math.pow((disks.get(diskOfInterest-1).get(0)-disks.get(disk).get(0)),2) + Math.pow((disks.get(diskOfInterest-1).get(1)-disks.get(disk).get(1)),2));
                    if (disks.get(disk).get(2) + disks.get(diskOfInterest-1).get(2) <= distanceBetweenCenters){
                        outside.add(disk);
                    }
                    if (distanceBetweenCenters + disks.get(disk).get(2) <= disks.get(diskOfInterest-1).get(2)){
                        inside.add(disk);
                    }
                }
            }
            Collections.sort(inside);
            Collections.sort(outside);
            for (int i = 0; i < inside.size(); i++){
                System.out.print((inside.get(i) + 1) + " ");
            }
            for (int i = 0; i < outside.size(); i++){
                System.out.print((outside.get(i) + 1) + " ");
            }
            System.out.println();
        }
    }
}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class Problem3 {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\WindyWalkIn.txt");
        BufferedReader br = new BufferedReader(fr);
        int numberOfWalks = Integer.parseInt(br.readLine());
        for (int walk = 0; walk < numberOfWalks; walk++){
            int position = 0;
            int time = 0;
            int distance = Integer.parseInt(br.readLine());
            Scanner scanner = new Scanner(br.readLine());
            boolean doneWalking = false;
            while (scanner.hasNextInt() && !doneWalking){
                int nextSteps = scanner.nextInt();
                time += Math.abs(nextSteps);
                if ((position + nextSteps) >= distance){
                    System.out.println(time - ((position + nextSteps)-distance));
                    doneWalking = true;
                }
                position += nextSteps;
            }
        }
    }
}

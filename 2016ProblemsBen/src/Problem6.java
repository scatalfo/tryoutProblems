
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
public class Problem6 {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\DartEavder.txt");
        BufferedReader br = new BufferedReader(fr);
        int numberOfGames = Integer.parseInt(br.readLine());
        for (int game = 0; game < numberOfGames; game++){
            Scanner line = new Scanner(br.readLine());
            double width = line.nextDouble();
            double height = line.nextDouble();
            double upMax = line.nextDouble();
            double downMax = line.nextDouble();
            double leftMax = line.nextDouble();
            double rightMax = line.nextDouble();
            
            int numDarts = Integer.parseInt(br.readLine());
            //System.out.println(numDarts);
            int breanneScore = 0;
            int lukeScore = 0;
            for (int d = 0; d < numDarts; d++){
                line = new Scanner(br.readLine());
                double x = line.nextDouble();
                //System.out.println(x);
                double y = line.nextDouble();
                if (x + rightMax > width/2 || x - leftMax < (-width/2) || y + upMax > height/2 || y - downMax < (-height/2)){
                    breanneScore++;
                }
                else{
                    lukeScore++;
                }
            }
            System.out.println(breanneScore + " " + lukeScore);
        }
    }
}

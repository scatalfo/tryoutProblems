package pkg2016.problems.varun;
import java.io.*;
import java.util.*;

public class WindyWalk
{

  public static void main(String[] args) throws IOException
  {
    File file = new File("WindyWalkIn.txt");
    Scanner scan = new Scanner(file);
    scan.nextLine();
    while(scan.hasNextLine()) {
      int steps = Integer.parseInt(scan.nextLine());
      ArrayList<Integer> stepSeq = new ArrayList<Integer>();
      String[] line = scan.nextLine().split(" ");
      for(int i = 0; i < line.length; i++) {
        stepSeq.add(Integer.parseInt(line[i]));
      }
      int time = 0;
      int currentSteps = 0;
      while(currentSteps < steps) {
        for(Integer step : stepSeq) {
          if(steps - currentSteps > step) {
            time += Math.abs(step);
            currentSteps += step;
          } else {
            time += steps - currentSteps;
            currentSteps = steps;
          }
          if(currentSteps >= steps) {
            break;
          }
        }
      }
      System.out.println(time);
    }
  }

}

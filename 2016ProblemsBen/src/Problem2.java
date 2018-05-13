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
public class Problem2 {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\IntersectionIn.txt");
		BufferedReader br = new BufferedReader(fr);
		int numberOfIntersections = Integer.parseInt(br.readLine());
		for (int intersection = 0; intersection < numberOfIntersections; intersection++){
			String line = br.readLine();
			Scanner scanner = new Scanner(line);
			int color = scanner.nextInt();
			int direction = scanner.nextInt();
			if (color == 1){
				if (direction == 1){
					System.out.println("P");
				}
				else if (direction == 2){
					System.out.println("LT");
				}
				else{
					System.out.println("RT");
				}
			}
			else{
				if (direction == 1){
					System.out.println("S");
				}
				else if (direction == 2){
					System.out.println("S");
				}
				else{
					System.out.println("SRT");
				}
			}
		}
	}

}
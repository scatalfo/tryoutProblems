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
public class Problem1 {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\MpgIn.txt");
		BufferedReader br = new BufferedReader(fr);
		Scanner input = new Scanner(br.readLine());
		double DMT = input.nextInt();
		double MPG = input.nextInt();
		double RMT = input.nextInt();
		double G = input.nextInt();
		double NDMT = DMT + RMT;
		double NMPG = NDMT / (DMT/MPG + G);
		System.out.println((int)(NDMT) + " miles " + (int)NMPG + " mpg");
	}

}

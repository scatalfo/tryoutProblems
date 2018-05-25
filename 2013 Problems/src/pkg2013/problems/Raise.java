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
public class Raise {
    
    public static void main(String args[]) throws IOException {
        File file = new File("RaiseIn.txt");
        Scanner s = new Scanner(file);
        s.nextInt();
        while(s.hasNextDouble()) {
            double pay = s.nextDouble();
            double newPay;
            if(pay * 1.05 < pay + 1.0) {
                System.out.print("$1.00 " + "$" + (pay + 1.0) );
                newPay = pay + 1.0;
            } else {
                int raise = (int) (pay * 105);
                double r = raise / 100.0;
                System.out.print("5% " + "$" + r);
                newPay = r;
            }
//            int tens = (int) (newPay * 10 % 10);
            int ones = (int) (newPay * 100 % 10);
            if(ones == 0) {
                System.out.print("0");
            }
            System.out.println("");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2004.problems.varun;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


/**
 *
 * @author Varun Jindal
 */
public class Floats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File file = new File("FloatsIn.txt");
        Scanner s = new Scanner(file);
        
        while(s.hasNext()) {
            String num = s.nextLine();
            int neg = Integer.parseInt(num.substring(0,1));
            String e = num.substring(1,9);
            String f = num.substring(9);
            double fraction = 0;
            System.out.println(f);
            for(int i = 0; i < f.length(); i++) {
                if(f.charAt(i) == '1') {
                    fraction += Math.pow(.5, i + 1);
                    System.out.println(fraction);
                }
            }
            fraction+=1f;
            System.out.println(fraction);
            int eTotal = 0;
            for(int i = e.length() - 1; i >= 0; i--) {
                if(e.charAt(i) == '1') {
                    eTotal += Math.pow(2, (e.length() - 1) - i);
                }
            }
            System.out.println("" + (fraction * Math.pow(2, eTotal - 128)) + " " + eTotal);
            double fin = (Math.pow(-1, neg)) * fraction * Math.pow(2, eTotal - 128);
            System.out.println(fin);
            DecimalFormat df = new DecimalFormat("#0.000000000000");
            System.out.println(df.format(fin));
            System.out.println();
        }
    }
    
}

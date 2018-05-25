/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2013.problems;

import java.util.*;
import java.io.*;
import java.text.*;

/**
 *
 * @author Varun Jindal
 */
public class Vases {
    
    public static void main(String args[]) throws IOException {
        File file = new File("VasesIn.txt");
        Scanner s = new Scanner(file);
        DecimalFormat test = new DecimalFormat("#" + (Math.log10(100 + 1)/Math.log10(2)));
//        System.out.println(test.format());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2015.problems.varun;

import java.util.*;
import java.io.*;

/**
 *
 * @author Varun Jindal
 */
public class Latin {
    public static void main(String args[]) throws IOException {
        File file = new File("LatinIn.txt");
        Scanner s = new Scanner(file);
        s.nextLine();
        while(s.hasNextLine()) {
            String[] sentence = s.nextLine().split(" ");
            for(int i = 0; i < sentence.length; i++) {
                boolean isUpper = false;
                char temp = sentence[i].charAt(0);
                if(Character.isUpperCase(temp)) {
                    temp = Character.toLowerCase(temp);
                    isUpper = true;
                }
                if(temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u') {
                    sentence[i] = isUpper ? Character.toString(sentence[i].charAt(0)).toUpperCase() + sentence[i].substring(1) + "way" : sentence[i] + "way";
                } else {
                    sentence[i] = isUpper ? Character.toString(sentence[i].charAt(1)).toUpperCase() + sentence[i].substring(2) + temp + "ay" : sentence[i].substring(1) + temp + "ay";
                }
            }
            for(String word : sentence) {
                System.out.print(word + " ");
            }
            System.out.println("");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyprogrammer;

import java.util.*;


/**
 *
 * @author Varun Jindal
 */
public class ElsieFour {
    
    private static String alphabet = "#_23456789abcdefghijklmnopqrstuvwxyz";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        decrypt("s2ferw_nx346ty5odiupq#lmz8ajhgcvk79b", "tk5j23tq94_gw9c#lhzs");
    }
    
    public static String decrypt(String key, String cryptMsg) {
        HashMapBoosted vectorDict = new HashMapBoosted();
        for(int i = 0; i < alphabet.length(); i++) {
            vectorDict.put(alphabet.charAt(i), new Integer[] {i%6, i/6});            
        }
        Integer[][][] vectorMatrix = new Integer[6][6][2];
        Character[][] charMatrix = new Character[6][6];
        for(int i = 0; i < key.length(); i++) {
            vectorMatrix[i / 6][i % 6] = vectorDict.get(alphabet.charAt(i));
            charMatrix[i / 6][i % 6] = key.charAt(i);
        }
        System.out.println(vectorDict);
        System.out.println(vectorDict.getKey(new Integer[] {1, 0}));
        Integer[] marker = vectorMatrix[0][0];
        String output = "";
        for(int i = 0; i < key.length(); i++) {
            
        }
        return "";
    }
    
}


class HashMapBoosted extends HashMap<Character, Integer[]> {
    
    private static String alphabet = "#_23456789abcdefghijklmnopqrstuvwxyz";
    
    public HashMapBoosted() {
        super();
    }
    
    public Character getKey(Integer[] def) {
        for(int i = 0; i < alphabet.length(); i++) {
            if(Arrays.equals(this.get(alphabet.charAt(i)), def)) {
                return alphabet.charAt(i);
            }
        }
        return 0;
    }
    
    public String toString() {
        String temp = "[";
        for(int i = 0; i < this.size(); i++) {
            temp += alphabet.charAt(i) + " = {" + this.get(alphabet.charAt(i))[0] + ", " + this.get(alphabet.charAt(i))[1] + "}, ";
        }
        return temp.substring(0, temp.length() - 2) + "]";
    }
}

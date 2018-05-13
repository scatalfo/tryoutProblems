
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bcata
 */
public class Problem5 {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\MathSyntaxIn.txt");
        BufferedReader br = new BufferedReader(fr);
        int numOfExpressions = Integer.parseInt(br.readLine());
        for (int expression = 0; expression < numOfExpressions; expression++){
            String line = br.readLine();
            String[] expr = line.split(" ");
            if (!isNumber(expr[0])){
                //System.out.println("test case num: " + expression + " phrase is: " + expr[0]);
                System.out.println(1);
            }
            else if (!isNumber(expr[expr.length - 1])){
                //System.out.println("test case num: " + expression + " phrase is: " + expr[expr.length - 1]);
                System.out.println(line.length() + 1);
                //System.out.println(charsBefore(expr, expr.length - 1));
            }
            else{
                boolean shouldBeNum = true;
                boolean foundError = false;
                for (int i = 0; i < expr.length; i++){
                    if (shouldBeNum && !isNumber(expr[i]) && !foundError){
                        System.out.println(charsBefore(expr, i));
                        foundError = true;
                    }
                    if (!shouldBeNum && isNumber(expr[i]) && !foundError){
                        System.out.println(charsBefore(expr, i));
                        foundError = true;
                    }
                    if (!shouldBeNum && !isOperator(expr[i]) && !foundError){
                        System.out.println(charsBefore(expr, i));
                        foundError = true;
                    }
                    if (line.indexOf("  ") >= charsBefore(expr, i) && !foundError){
                        System.out.println(line.indexOf("  ") + 2); //+1 because we are looking at index and need placement, another +1 because we want to go to the second space not the first space
                        foundError = true;
                    }
                    shouldBeNum = !shouldBeNum;
                }
                if (!foundError){
                    System.out.println("correct");
                }
            }
        }
    }
    public static boolean isNumber(String str){
        try{
            double num = Double.parseDouble(str);
        }
        catch (NumberFormatException exception){
            return false;
        }
        return true;
    }
    public static int charsBefore(String[] arr, int index){
        int charsBefore = 0;
        for (int i = 0; i < arr.length; i++){
            if (i != index){
                charsBefore += arr[i].length() + 1; //+1 for space delimiter
            }
            else{
                return charsBefore + 1; //because character place not index
            }
        }
        return charsBefore + 1;
    }
    public static boolean isOperator(String str){
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
            return true;
        }
        else{
            return false;
        }
    }
}

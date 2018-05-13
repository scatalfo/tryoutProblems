
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
public class Problem4 {
    public static void main(String[] args) throws IOException{
        FileReader fr = new FileReader("C:\\Users\\bcata\\Documents\\NetBeansProjects\\2016Problems\\2016Problems\\src\\NumbersGameIn.txt");
        BufferedReader br = new BufferedReader(fr);
        int numberOfMessages = Integer.parseInt(br.readLine());
        for (int message = 0; message < numberOfMessages; message++){
            String encryptedMessage = br.readLine();
            int keySize = Integer.parseInt(br.readLine());
            ArrayList<String> decryptedMessage = new ArrayList<String>();
            Scanner keyScanner = new Scanner(br.readLine());
            for (int i = 0; i < keySize; i++){
                decryptedMessage.add("");
            }
            for (int i = 0; i < keySize; i++){
                int num = keyScanner.nextInt();
                if (num > 20){
                    decryptedMessage.add(i, " ");
                }
                else{
                    decryptedMessage.add(i, "" + encryptedMessage.charAt(num-1));
                }
            }
            String strDecryptedMessage = "";
            for (String str: decryptedMessage){
                strDecryptedMessage = strDecryptedMessage + str;
            }
            System.out.println(strDecryptedMessage);
        }
    }
}

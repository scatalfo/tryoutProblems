import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LookAndSay 
{
    public static void main(String[] args) throws IOException 
    {
	FileReader fr = new FileReader("LookAndSayIn.txt");
	Scanner in = new Scanner(fr);
        String input, look, say;
        int nTerms, cCount;
        int nCases = in.nextInt();
        input = in.nextLine();
        char c0, c;
        for(int cn = 1; cn <=nCases; cn++)
        {
            input = in.nextLine();
            StringTokenizer tokens = new StringTokenizer(input);
            look = tokens.nextToken();
            System.out.print(look + " ");
            nTerms = Integer.parseInt(tokens.nextToken());
            
            for(int term = 1; term < nTerms; term++)
            {
                cCount = 0;
                say = "";
                c0 = look.charAt(0);
                cCount = 1;
                for(int i = 1; i< look.length(); i++) //each character in look
                {
                    c = look.charAt(i);
                    if(c0 == c) //not a new number
                    {
                        cCount++;
                    }
                    else // new number found
                    {
                       say = say + String.valueOf(cCount) + String.valueOf(c0);
                       c0 = c;
                       cCount = 1;
                    }    
                }
                say = say + String.valueOf(cCount) + String.valueOf(c0);
                System.out.print(say + " ");
                look = say;
            }
            System.out.println();
        }    
    }
    
}

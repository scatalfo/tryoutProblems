
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BoxerBilly 
{

    public static void main(String[] args) throws IOException 
    {
        FileReader fr = new FileReader("BoxerBillyIn.txt");
	Scanner in = new Scanner(fr);
        int capacity = in.nextInt();
        int nBooks = in.nextInt();
        int nBoxes = nBooks / capacity;
        int nLastBox = nBooks % capacity;
        if(nLastBox == 0) nLastBox = capacity;
        System.out.println("Purchase " + nBoxes + " boxes and put " +
                           nLastBox + " books in the last box packed");
    }
    
}

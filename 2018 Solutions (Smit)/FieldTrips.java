
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FieldTrips 
{

    public static void main(String[] args)throws IOException
    {
        FileReader fr = new FileReader("FieldTripsIn.txt");
	Scanner in = new Scanner(fr);
        int i, j, w, h, total; 
        int nCases = in.nextInt();
        for(int cn = 1; cn <=nCases; cn++)
        {
            w = in.nextInt(); // width of the field
            h = in.nextInt();; // depth d of the field
            total = 0; //(total points reachable
            if(w > h) {int temp = w; w = h; h = temp;} // symmetry about 45 degs. permits this
            {
                for(int nMoves = 1; nMoves <= 2*h-1; nMoves = nMoves + 2)// = 2T-1
                {
                    j = nMoves;
                    i = 0;
                    while(i <= w && j >= 0) 
                    {  
                        if(i <= w && j <= h)
                        {
                            total = total + 1;
                        }
                        i = i + 1;//increment;
                        j = j - 1;//increment;
                    }
                } 
            }
            System.out.println(total);
        }    
    }
    
}

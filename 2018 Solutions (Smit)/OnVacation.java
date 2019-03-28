
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class OnVacation 
{
    public static void main(String[] args) throws IOException
    {
	FileReader fr = new FileReader("OnVacationIn.txt");
	Scanner in = new Scanner(fr);
        int nDaysOpen;
        int maxSumSoFar = 0;
        int maxLeftSoFar = 0;
        int maxRightSoFar = 0;
        int sum;
        int[] profit;
        int nDays;
                                        
	int nCases = in.nextInt();
        for(int cn = 1; cn <=nCases; cn++)
	{   
            nDaysOpen = 9999;
            maxSumSoFar=0;
            maxLeftSoFar=0;
            maxRightSoFar=-1;
            sum=0;
            nDays = in.nextInt();
            profit = new int[nDays];

            for(int i = 0; i< nDays; i++)
            {
                profit[i] = in.nextInt();
            }    
            for(int left=0; left < nDays; left++)
            {
                sum = 0;
                for(int right=left; right<nDays; right++)
                {
                    sum = sum + profit[right]; //sum is that of A[left..right]
                    if(sum > maxSumSoFar || sum == maxSumSoFar && nDaysOpen > right - left + 1 )
                    {    
                        maxSumSoFar=sum;
                        maxLeftSoFar=left;
                        maxRightSoFar=right;
                        nDaysOpen = right - left + 1;
                    }
                }                                        
            }
            System.out.println(maxSumSoFar + " " + (maxLeftSoFar + 1) + " " + (maxRightSoFar + 1));
        }    
    }
    
}

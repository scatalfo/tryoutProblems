import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NarrowArtGallary 
{
    static int[] status;  //1 of 4 parallel arrays
    static int[] r; 
    static int[] c ; 
    static int[] N;  
    static int[][] gallary; // values of each room
    static int rows; // number of rows in the gallary
    static int n; // number of rooms to close
    static int[][] gallaryStatus; 
    static int[][] roomsClosed; // [r][col] of rooms closed to make the highest 
    static int[][] roomsClosedTemp; // value walk
    static int rcti; // column index into the roomsClosedTemp array
    static int maxI; // max index into the parallel arrays

    public static void main(String[] args) throws IOException
    {
        FileReader fr = new FileReader("NarrowGalleryIn.txt");
	Scanner in = new Scanner(fr);
        int lowTotal = Integer.MAX_VALUE; 
        int thisTotal; 
        int startIndex = -1; 
        int nTaken;
        int i;
        int toTake;
        int totalAllRoomsValue; // total of all of the rooms' values
        int nCases = in.nextInt();
        for(int cn = 1; cn <=nCases; cn++)
        {
          lowTotal = Integer.MAX_VALUE;
          startIndex = -1; 
          rows = in.nextInt();
          n = in.nextInt();
          status = new int[rows*2]; //first of 4 parallel array; == 0, -1, or -2
          r = new int[rows*2]; // gallary room row number
          c = new int[rows*2]; // gallary room col number
          N = new int[rows*2]; // gallary room value
          gallary = new int[rows][2];  
          roomsClosed = new int[rows][2];
          roomsClosedTemp = new int[rows][2];
          gallaryStatus = new int[rows][rows];
          maxI = rows * 2 - 1;
          for(int r = 0; r < rows; r++)
          {
              gallary[r][0]= in.nextInt();
              gallary[r][1]= in.nextInt();
          }    
          // build the parallel arrays
          buildParallelArrays(gallary, r, c, N);
          
          while(startIndex  < maxI) // there are more starting rooms to consider
          {
          //A- set all array values to reflect this candidate for first room closed
            //1- intalize arrays for start index as the first room closed
            gallaryStatus = new int[rows][rows];// reintialize it to zero
            init(); // set status of each room to zero
            nTaken = 1; // intialize number of rooms currently closed
            startIndex++; //index of first (starting) room to close for this iteration
            thisTotal = N[startIndex]; // add its value to total

            // 2- mark the first room closed in temporary, and status arrays
            rcti = 0; // row index into roomClosedTemorary array
            roomsClosedTemp[rcti][0] = r[startIndex]; // row of closed room
            roomsClosedTemp[rcti][1] = c[startIndex]; // col of closed room
            status[startIndex] = -1;

            // 3- mark the 3 rooms adjacent to it as can't close 
            mark(r[startIndex], c[startIndex]);
            i = 0; // index of next room ro be considered for closing
            //System.out.print("\n" + startIndex +  " " + N[startIndex] + "  " );
            
          //B- add the remaining best n-1 closed rooms to this candidate first room 
            while(nTaken < n && i <= maxI) //more rooms are needed and available
            {                              //look for the next best room to close
                //if(status[i] == 0 ) // found an available room
                if(status[i] == 0 && i != startIndex) //found an available room
                {                                     //at index i
                     //1- decide if that room or one of its adjacent room should be closed
                     toTake = whatToTake(i, nTaken, n); //the best choice of [i] 
                                                        //and its 3 adjacent rooms
                                                        
                     //2- record the best (max can't close sum) choice to close  
                     thisTotal = thisTotal + N[toTake];
                     //System.out.print(toTake + " " + N[toTake] + "  " );
                     nTaken++;
                     status[toTake] = -1;
                     gallaryStatus[r[toTake]][c[toTake]] = -1;
                     mark(r[toTake], c[toTake]);
                     rcti++;
                     roomsClosedTemp[rcti][0] = r[toTake];
                     roomsClosedTemp[rcti][1] = c[toTake];
                }
                if(nTaken == n)// found n rooms to close for this starting at i
                {
                //System.out.println("This total open " + thisTotal);
                    if(thisTotal < lowTotal) // low closing total so far
                    {   // record interim solution
                        lowTotal = thisTotal;
                        roomsClosed = roomsClosedTemp;
                        roomsClosedTemp = new int[rows][2];;
                    }
                    break;
                }

                i++; // index of the next first room to close
            }// end serch for the n-1 best rooms to close for room index i 
          }// // tried all rooms as first room to close 

          //C- output the total of the value of the open rooms  
          //   and the closed rooms [r]{c]
          totalAllRoomsValue = 0;
          for(int room = 0; room <rows*2; room ++)
          {
            totalAllRoomsValue = totalAllRoomsValue + N[room];
          }    
          if(n == 1)
          {
            //System.out.println("total value of open rooms: " + 
                               //(totalAllRoomsValue - N[0]));
            System.out.println((totalAllRoomsValue - N[0]));
          }
          else
          {
            //System.out.println("total of all rooms and closed rooms" + 
                               //totalAllRoomsValue + " " + lowTotal); 
            System.out.println((totalAllRoomsValue - lowTotal) ); 
          }
          //System.out.println("\nrooms\nr c");
            //System.out.println("total value of all rooms " + totalAllRoomsValue);
          for(int k = 0; k<n; k++)
          {
            //System.out.println(roomsClosed[k][0] + " " + roomsClosed[k][1]);
          }
      } // end num of cases for loop    
        
    }
    public static void buildParallelArrays(int[][] gallary, int[] r, int[] c, 
                                           int[] N)
    {
        int[] cGallary = new int[rows*2];
        for(int row = 0; row < rows; row++)
        {
            cGallary[row*2] = gallary[row][0]; 
            cGallary[row*2 + 1] = gallary[row][1]; 
            N[row*2] = gallary[row][0]; 
            N[row*2 + 1] = gallary[row][1]; 
        }    
        Arrays.sort(N);
        for(int index = 0; index < rows*2; index++)
        {
            for(int row = 0; row < rows*2; row++)
            {
                if(cGallary[row] == N[index])
                {
                    r[index] = row / 2;
                    c[index] = row % 2;
                    cGallary[row] = -1;
                    break;
                }    
            }    
        }    
    }
    public static int smallestIndexFor2Remainig(int i)
    {
        int iAdjacentMin = 9999;
        int adjacentMin = 9999999;
        int iSum = minSumForTwoRooms(r[i], c[i]);
        int adjacentSum = minSumForTwoRooms(r[i], (c[i] + 1) % 2);
        
        //System.out.println(adjacentMin + " " + iSum + " " + adjacentSum);
        if(iSum <= adjacentSum)
        {
            return i;
        }
        else //find min value in adjacent column
        {
            if(gallaryStatus[r[i]][(c[i] + 1) % 2] == 0 &&
               gallary[r[i]][(c[i] + 1) % 2] < adjacentMin)
            {
               adjacentMin = gallary[r[i]][(c[i] + 1) % 2]; 
               iAdjacentMin = findIndex(r[i], (c[i] + 1) % 2);  
            }    
            if(r[i] - 1 >= 0 && gallaryStatus[r[i] - 1][(c[i] + 1) % 2] == 0 &&
               gallary[r[i] - 1][(c[i] + 1) % 2] < adjacentMin)
            {
               adjacentMin = gallary[r[i] - 1][(c[i] + 1) % 2]; 
               iAdjacentMin = findIndex(r[i] - 1, (c[i] + 1) % 2);  
            }    
            if(r[i] + 1 < rows && gallaryStatus[r[i] + 1][(c[i] + 1) % 2] == 0 &&
               gallary[r[i] + 1][(c[i] + 1) % 2] < adjacentMin)
            {
               iAdjacentMin = findIndex(r[i] + 1, (c[i] + 1) % 2);  
            }
            return iAdjacentMin;
        }    
    }
    public static int minSumForTwoRooms(int pivotR, int pivotC)    // i's min sum of two rooms
    {
        int sum, sumUp, sumDown, sumOver; 
        int pivot = gallary[pivotR][pivotC];
        sumUp = 9999999;
        sumDown = 9999999;
        sumOver = 9999999;
        
        if(pivotR - 1 >= 0 && gallaryStatus[pivotR - 1][pivotC] == 0)// upper room available
        {
            sumUp = pivot + gallary[pivotR - 1][pivotC];
        }            
        if(pivotR + 1 < rows && gallaryStatus[pivotR + 1][pivotC] == 0)// lower room available
        {
            sumDown = pivot + gallary[pivotR + 1][pivotC];
        }
        if(pivotR - 1 >= 0  &&   gallaryStatus[pivotR - 1][pivotC] == 0 &&
           pivotR + 1 < rows  && gallaryStatus[pivotR + 1][pivotC] == 0)// upper and lower room available
        {
            sumOver = gallary[pivotR - 1][pivotC] + gallary[pivotR + 1][pivotC];
        }

        sum = sumDown;
        if(sumUp < sum)
        {
            sum = sumUp;
        }
        if(sumOver < sum)
        {
            sum = sumOver;
        }
        return sum;
    }
    public static int findIndex(int row, int col)
    {
        int i = 0;
        while(true)
        {
            if(r[i] == row && c[i] == col) break;
            i++;
        }
        return i;
    }        
    public static int smallestIndexFor3Remainig(int i)
    {
        int iAdjacentMin = 9999;
        int adjacentMin = 9999999;
        int iSum = sumForThreeRooms(r[i], c[i]);
        int adjacentSum = sumForThreeRooms(r[i], (c[i] + 1) % 2);
        
        //System.out.println(adjacentMin + " * " + iSum + " " + adjacentSum);
        if(iSum <= adjacentSum)
        {
            return i;
        }
        else //find min value in adjacent column
        {
            if(gallaryStatus[r[i]][(c[i] + 1) % 2] == 0 &&
               gallary[r[i]][(c[i] + 1) % 2] < adjacentMin)
            {
               adjacentMin = gallary[r[i]][(c[i] + 1) % 2]; 
               iAdjacentMin = findIndex(r[i], (c[i] + 1) % 2);  
            }    
            if(r[i] - 1 >= 0 && gallaryStatus[r[i] - 1][(c[i] + 1) % 2] == 0 &&
               gallary[r[i] - 1][(c[i] + 1) % 2] < adjacentMin)
            {
               adjacentMin = gallary[r[i] - 1][(c[i] + 1) % 2]; 
               iAdjacentMin = findIndex(r[i] - 1, (c[i] + 1) % 2);  
            }    
            if(r[i] + 1 < rows && gallaryStatus[r[i] + 1][(c[i] + 1) % 2] == 0 &&
               gallary[r[i] + 1][(c[i] + 1) % 2] < adjacentMin)
            {
               iAdjacentMin = findIndex(r[i] + 1, (c[i] + 1) % 2);  
            }
            return iAdjacentMin;
        }    
    }
    public static int sumForThreeRooms(int pivotR, int pivotC)
    {
        int sum = 9999999;
        
        if(gallaryStatus[pivotR][pivotC] == 0 &&
           pivotR - 1 >= 0 && gallaryStatus[pivotR - 1][pivotC] == 0 &&
           pivotR + 1 < rows && gallaryStatus[pivotR + 1][pivotC] == 0) // three rooms available
        {
            return gallary[pivotR][pivotC] + gallary[pivotR - 1][pivotC] +
                   gallary[pivotR + 1][pivotC];
        }    
        else 
        {
            return minSumForTwoRooms(pivotR, pivotC);
        }
    }        
    public static int whatToTake(int i, int nTaken, int n)
    {  // returns the index of the room to close, i or one of its adjacnet rooms,
       // that maximizes the sum of the values of the rooms that can't be closed 
       // that are adjacent to the room to close 
        if(n - nTaken == 1 || nAdjacentToTake(i) <= 1 ) //last room to be taken
        {  // or only one room adjacent to it, so i's closure gives min closure
            return i; // because i has to be less than adjacent, if adjacent 
        }             // is still available or it would be == i
        else if((n - nTaken == 2) || nIToTake(i) <= 2) //two more rooms to be taken
        {                                             // or only two adjacent to it
            return smallestIndexFor2Remainig(i); //its closure gives largest
        }
        else // three or more rooms to be to be taken 
        {
            return smallestIndexFor3Remainig(i);
        }
    }

    public static int nAdjacentToTake(int i)
    {// determines how many unclosed rooms are adjacent to i
        int n = 0;
        if(gallary[r[i]][(c[i]+1) % 2] == 0) // i's same row room unclosed
        {
            n++;
        }
        if( r[i] - 1 >= 0 && gallaryStatus[r[i] - 1][(c[i]+1) % 2] == 0) // 
        {   // i's up diagonal room exists and is unclosed
            n++;
        }
         if(r[i] + 1 < rows && gallaryStatus[r[i] + 1][(c[i]+1) % 2] == 0) // 
        {   // i's down diagonal room exists and is unclosed
            n++;
        }
         return n;
   }        
    public static int nIToTake(int i)
    {
        int n = 0;
        if(gallary[r[i]][c[i]] == 0) // 
        {
            n++;
        }
        if( r[i] - 1 >= 0 && gallaryStatus[r[i] - 1][c[i]] == 0) // 
        {
            n++;
        }
         if(r[i] + 1 < rows && gallaryStatus[r[i] + 1][c[i]] == 0) // 
        {
            n++;
        }
         return n;
   }        
    
    public static void init() // set closed status to false (==0)
    {
        for(int i = 0; i <= maxI; i++)
        {
            status[i] = 0;
        }
    }
    
    public static void mark(int row, int col) // set can't close (-2) into 
    {                                         // cells adjacent to closed room
        for(int i = 0; i <= maxI; i++)//all   // ( which is gallary[row][col] )
        {                             //room
            if(r[i] == row && c[i] == (col + 1) % 2 || // same row and adjacent
               r[i] == row + 1 && c[i] == (col + 1) % 2 ||//"   "   "  below
               r[i] == row - 1 && c[i] == (col + 1) % 2)  //"   "   "  above
            {
               status[i] = -2;
            }
            gallaryStatus[row][(col +1) % 2] = -2; // adjacent cell
            if(row + 1 < rows)
            {
                gallaryStatus[row + 1][(col +1) % 2] = -2; // adjacent below exists
            }    
            if(row - 1 >= 0)
            {
                gallaryStatus[row -1][(col +1) % 2] = -2; // adjacent above exists
            }    
        }
    }
}

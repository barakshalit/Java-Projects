
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int TargetNumber = n-1; //Finding the targeted number
        boolean found = false; //Boolean parameter to check if we have found s and d
        int PowerOfTwo = 1; // Calculating 2^s
        int d = 1; // initializing d
        int s = 1; // initializing s
        
        for (int i = 1; !found ; i = i+1) //loop to go through all the d's until we found the correct one
        {
        	PowerOfTwo = PowerOfTwo * 2;
        	d=1;
        	for (int j = 0 ; PowerOfTwo * d < n & !found ; j= j+1) //loop to go through all the d's until we found the correct one
        	{
        		d = 2*j + 1;
        		if (PowerOfTwo * d == TargetNumber) //Checking if the current combination of s and r is the correct one
        		{
        			found = true; //Breaking the loops
        			s = i;
        		}
        	}
        }
        
        ans1 = s;
        ans2 = d;
        
        
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
		scanner.close();
    }
}
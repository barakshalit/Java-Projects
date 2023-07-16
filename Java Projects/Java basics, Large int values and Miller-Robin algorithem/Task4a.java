
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        for (int i = 2; i*i < n & ans; i = i+1) // Loop for all the numbers from 2 to i*i<n
        {
        	if(n % i == 0) // For this current number (i), check if its a divider of n, is yes, n is not prime.
        	{
        		ans = false;
        	}
        }

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}
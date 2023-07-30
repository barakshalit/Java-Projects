
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        boolean prime = true;
        int counterOfPrimes = 0; // Counts the number of primes up to n
        for (int j = 2; j <= n; j=j+1)
        {
        	for (int i = 2; i*i <= j & prime; i = i+1) // Loop for all the numbers from 2 to i*i<n
              {
              	if(j % i == 0) // For this current number (i), check if its a divider of n, is yes, n is not prime.
              	{
              		prime = false;
              	}
              }
        	  
        	  if (prime == true) //Checking if the number that was tested is prime
        	  {
        		  counterOfPrimes = counterOfPrimes +1;
        	  }
        	  
        	  prime = true; // resetting the value of "prime" parameter for the next number

        }
        
        ans = counterOfPrimes;
      

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);    
		scanner.close();    
    }
}
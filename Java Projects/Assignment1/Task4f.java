
import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int k = scanner.nextInt();
        int b;
        
        int TwoInPoweri = 1; // Value of 2^i for current i
        int bPowerforFirstRule = 1; // Value of b^d%n
        int bPowerTwoPoweri = 1; // Value of b^(2^i)%n
        int bPowerforSecondRule = 1; // Value of (b^(2^i))^d%n
        boolean firstRule = true; // Boolean Parameter for the first condition
        boolean secondRule = true;// Boolean Parameter for the second condition
        
        for (int y = 0 ; y < k & ans ; y = y + 1) //Checking b k times
        {
        	b = 2 + (int) (Math.random() * (n - 2)); //Getting random b
        	
        	 for (int i = 0; i < d; i = i + 1) // Loop to calculate the value of b^d%n
             {
             	bPowerforFirstRule = (bPowerforFirstRule * b)%n;
             }
             
             if (bPowerforFirstRule == 1) // Checking if first rule has fulfilled
             {
             	firstRule = false;
             }
             
           //----------------------------------------------
             
             //Checking the second rule 
             
             for (int i = 0; i <= s - 1 & secondRule; i = i + 1) // Loop for all the i
             {
             	bPowerTwoPoweri = 1;
             	bPowerforSecondRule = 1;
             	if(i == 0) // Getting 2^i for current i
             	{
             		TwoInPoweri = 1;
             
             	}
             	else
             	{
             		TwoInPoweri = TwoInPoweri * 2;
             	}
             	
             	for (int j = 0; j < TwoInPoweri; j = j + 1) // Getting b^(2^i)%n
                 {
             		bPowerTwoPoweri = (bPowerTwoPoweri * b)%n;
                 }
             	
             	for (int l = 0; l < d ; l = l + 1) //Getting (b^(2^i))^d%n
             	{
             		bPowerforSecondRule = (bPowerforSecondRule * bPowerTwoPoweri)%n;
             	}
             	
             	if (bPowerforSecondRule == n-1) // Checking if first rule has fulfilled (for current i)
             	{
             		secondRule = false;
             	}
             	
             }
             
             //----------------------------------------------
             
             if (firstRule & secondRule) // Checking if both of the rules are fulfilled and returning correct values for print
             {
             	ans = false;
             }
             else 
             {
             	ans = true;
             }
             
        	
        	
        }
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}
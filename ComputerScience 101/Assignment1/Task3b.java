
import java.util.Scanner;


public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int Result = 1; // Value of (2^n) % k
        
        //Multiplying current (2^i) % k in 2 (2%k) in order to get 2^(i+1) % k (Using the formula (a*b)%k = ((a%k) * (b%k))%k) :
        
        for (int i = 0; i < n ; i = i + 1) 
        {
        	Result = (Result * 2) % k;
        }
        
        ans = Result; // Returning the Result
  
        
        
      
       
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}
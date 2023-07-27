
import java.math.BigInteger;
import java.util.Random;




class Assignment3BigInteger{
	
	 public static void main(String[] args) {

		 System.out.println(randomPrime(50));
	    }

	//assumes that n is no null
	// returns the sum of the positive numbers that are smaller than n
    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        //Task 1.1
        BigInteger One =  new BigInteger("1");
        for (BigInteger i = new BigInteger("0") ; i.compareTo(n) < 0; i = i.add(One) ) {
        	sum = sum.add(i);
        }
        return sum;
    }
    
    //assumes that n >= 0
    // prints 5 pseudorandom generated numbers from the complete bound of int
    public static void printRandoms(int n){
        //Task 1.2
    	Random rand = new Random();
    	
    	for (int i = 0; i < n ; i = i + 1) {
    		System.out.println(rand.nextInt());
    	}
    }
    
    // assumes n >= 0 and not null
    // returns true if the value of n is prime and false if not
    public static boolean isPrime(BigInteger n){
        boolean ans= true;
        //Task 1.3
        
        BigInteger One =  new BigInteger("1");
        BigInteger Zero =  new BigInteger("0");
        if (n.equals(One) | n.equals(Zero)) {
        	ans = false;
        }
        for (BigInteger i = new BigInteger("2") ; i.multiply(i).compareTo(n) <= 0 & ans; i = i.add(One) ) {
        	if (n.mod(i).equals(Zero)) {
        		ans = false;
        	}
        }
        return ans;
    }
    // assumes n > 1
    // returns a random generated prime BigInteger between 2 and 2^n
    public static BigInteger randomPrime(int n){
        BigInteger randBig = new BigInteger("0");
        //Task 1.4
        Random rand = new Random();
        while (!isPrime(randBig)) {
        	BigInteger temp = new BigInteger(n,rand);
        	randBig = temp;
        }
        return randBig;
    }

}
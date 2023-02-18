/*
I, <Barak Shalit> (<316222280>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;
import java.lang.Math;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not chainge this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not chainge this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
   
    // BinaryNumber constructor that gets char as decimal value and constructs a BinaryNumber that represents this value
    public BinaryNumber(char c) {
        if (!(c <= '9' & c >= '0')) { // checking if the char is legal digit between 0-9
        	throw new IllegalArgumentException("c is not a digit between 0 and 9");
        }
        int num = c - '0';
        bits = new BitList();
        if (num == 0) {
        	bits.addFirst(Bit.ZERO);
        }
        
        while(num > 0) {
        	Bit bit = new Bit (num % 2);
        	bits.addLast(bit);
        	num = num / 2;
        }
        
        bits.addLast(Bit.ZERO);
        bits.reduce();
        

    }
    
  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    
    // returns a string representation of the BinaryNumber
    public String toString() { 
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        String s = this.bits.toString(); 
        s = s.substring(1,s.length()-1); // removing "<" and ">"
        return s; 
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    
    // returns a boolean answer for whether or not the two BinaryNumbers are 	l
    // returns false if one of them is illegal/ "other" is null
    public boolean equals(Object other) {
    	boolean ans = false;
        if (other instanceof BinaryNumber) {
        	BinaryNumber temp = new BinaryNumber ((BinaryNumber) other);
        	if (this.bits.equals(temp.bits) & isLegal() & temp.isLegal()) { // checks if both of the Binary Number can be comparable
        		ans = true;
        	}
        }
        
        
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    
    // returns a BinaryNumber which is a sum of the operating BinaryNumber and addMe
    // assumes addMe is not null and both of the BinaryNumbers are legal
    public BinaryNumber add(BinaryNumber addMe) {
        if (addMe == null) {
        	throw new IllegalArgumentException("cannot add null");
        }
        if (!isLegal() & !addMe.isLegal()) {
        	throw new IllegalArgumentException("both Binary numbers needs to be legal");
        }
        
        BinaryNumber Sum = new BinaryNumber(0);
        Sum.bits.removeFirst();
        BinaryNumber addMeNum = new BinaryNumber (addMe);
        BinaryNumber addToNum = new BinaryNumber (this);
        Bit carry = new Bit (false);
        
        int thisSize = addToNum.bits.size();
        int addMeSize = addMeNum.bits.size();
        
        if (thisSize > addMeSize) { // padding with extra digit to handle end carry
        	addMeNum.bits.padding(thisSize + 1);
        	addToNum.bits.padding(thisSize + 1);
        }
        else {
        	addToNum.bits.padding(addMeSize + 1);
        	addMeNum.bits.padding(addMeSize + 1);
        }
       

        Iterator <Bit> iterThis = addToNum.bits.iterator();
        Iterator <Bit> iterAddme = addMeNum.bits.iterator();
        
        while (iterThis.hasNext()) {
        	Bit b1 = (Bit) iterThis.next();
        	Bit b2 = (Bit) iterAddme.next();
        	Bit b3 = carry;
     
        	Sum.bits.addLast(Bit.fullAdderSum(b1,b2,b3));
        	carry = Bit.fullAdderCarry(b1,b2,b3);

        }
 
        Sum.bits.reduce();
        return Sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
   
    // returns a BinaryNumber which is negated form of the operating BinaryNumber
    // assumes the BinaryNumber is legal
    public BinaryNumber negate() {
    	if (!isLegal()) {
    		throw new IllegalArgumentException("I am not legal");
    	}
        BinaryNumber negated = new BinaryNumber(BinaryNumber.ZERO);
        Iterator <Bit> iter = this.bits.iterator();
        while (iter.hasNext()) {
        	Bit output = iter.next();
        	negated.bits.addLast(output.negate());
        }
        negated.bits.removeFirst();
        negated = negated.add(BinaryNumber.ONE);
        negated.bits.reduce();
        return negated;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    
    // returns a BinaryNumber which is a subtraction result of the operating BinaryNumber and subtractMe
    // assumes subtractMe is not null and both of the BinaryNumbers are legal
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	  if (subtractMe == null) {
          	throw new IllegalArgumentException("cannot substract null");
          }
          if (!isLegal() & !subtractMe.isLegal()) {
          	throw new IllegalArgumentException("both Binary numbers needs to be legal");
          }
        
          BinaryNumber Sub = this.add(subtractMe.negate()); // adding the negated form of subtractMe to the operating BinaryNumber
   
          return Sub;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
   
    // returns an int value according to the sign of the binary number (1 if positive, 0 is 0 and -1 if negative)
    // assumes the BinaryNumber is not null\illegal
    public int signum() {
        if (!isLegal()) {
        	throw new IllegalArgumentException("I am not legal / null");
        }
        
        int signum;
        if (this.equals(BinaryNumber.ZERO)) {
        	signum = 0;
        }
        else {
        	if (bits.getLast().toInt() == 1) {
        		signum = -1;
        	}
        	else {
        		signum = 1;
        	}
        	
        }
        
                
        return signum;
        
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    
    // compares between two BinaryNumbers
    // returns 0 if both binary numbers are equal equal, 1 if the operating BinaryNumber is bigger, and -1 if its smaller
    // assumes both binary numbers are not null and both legal
    public int compareTo(BinaryNumber other) {

    	 if (this == null | other == null) {
    		 throw new IllegalArgumentException("both BinaryNumbers must not be null");
    	 }
    	 
    	 if (!isLegal() | !other.isLegal()) {
         	throw new IllegalArgumentException("both BinaryNumbers must be legal");
         }
    	 
    	 int ans = 0;
    	 BinaryNumber firstNum = new BinaryNumber (this);
    	 BinaryNumber secNum = new BinaryNumber (other);
    	
    	 if (this.signum() == other.signum()){
    		 if (this.equals(other)) {
        		 ans = 0;
        	 }
    		 else {
    			 if (this.bits.size() == other.bits.size()) {
    				 boolean found = false;
    				
    				 for (int i = 0; i < this.bits.size() & !found; i = i + 1) {
    					 if (firstNum.bits.getLast().toInt() != secNum.bits.getLast().toInt()) {
    						 ans = firstNum.bits.getLast().toInt() - secNum.bits.getLast().toInt();
    						 found = true;
    					 }
    					 firstNum.bits.removeLast();
    					 secNum.bits.removeLast();
    				 }
    			 }
    			 else {
    				 ans = (int) Math.signum(this.bits.size() - other.bits.size());
    			 }
    			 
    		 }
    		
    	 }
    	 else {
    		 ans = (int) Math.signum(this.signum() - other.signum());
    	 }
    	 
    	 return ans;
         
         
    	 
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
   
    // returns an int value of BinaryNumber
    // assumes the BinaryNumber decimal value is suitable for "int"
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
       
        if ((this.signum() == 1 & this.bits.size() > 32) | (this.signum() == -1 & this.bits.size() >= 33 & this.bits.getNumberOfOnes() > 2)) {
            throw new RuntimeException("I am illegal.");
        }
      
        
        int signum = this.signum();
        int index = 0;
        int ans = 0;
        BinaryNumber toInt = new BinaryNumber (this);
        toInt = absoluteVal(toInt);
        Iterator <Bit> iter = toInt.bits.iterator();
        
        while (iter.hasNext() & index < toInt.bits.size() - 1) {
        	Bit bit = iter.next();
        	if (bit.toInt() == 1) {
        		ans = ans + (int) Math.pow(2,index);
        	}
        	index = index + 1;
        	
      
        }
        
        if (signum < 0) {
        	ans = ans * -1;
        }
        
        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
    	
    	if (!this.isLegal() | !multiplyMe.isLegal()) {
    		throw new IllegalArgumentException("both BinaryNumbers must be legal");
    	}
    	BinaryNumber ans = absoluteVal(this).multiplyPositive(absoluteVal(multiplyMe));
    	if (this.signum() != multiplyMe.signum()) {
    		ans = ans.negate();
    	}
    	
    	return ans;
    }
    
    //returns a BinaryNumber which is a multiplication of the operating BinaryNumber and "multyplyMe"
    public BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	
        int index = 0;
        BinaryNumber ans = new BinaryNumber (BinaryNumber.ZERO);
        int NumberOfZeros = 0;
        Iterator <Bit> iter = multiplyMe.bits.iterator();
        BinaryNumber currentMulty = new BinaryNumber (this);
        
        while (iter.hasNext()) {
        	
        	if (iter.next().equals(Bit.ONE)) { // padding the operating BinaryNumber with zeros in the beginning of it, according to the current digit's index
        		for (int i = 0; i < index - NumberOfZeros; i = i + 1) {
        			currentMulty.bits.addFirst(Bit.ZERO);
      
        		}
        		
        		ans = ans.add(currentMulty); // adding it to the total ans
        		NumberOfZeros = index;
            	
        	}
        	
        	index = index + 1;

        }
        
        return ans;
    }
    
    // functions for task 3.10 + 3.9
    public static BinaryNumber absoluteVal (BinaryNumber number) {
    	BinaryNumber CheckNum = new BinaryNumber (number);
    	if (CheckNum.bits.getLast().toInt() == 1) {
    		CheckNum = CheckNum.negate();
    	}
    	
    	return CheckNum;
    }
    
    // end of functions for task 3.10 + 3.9
   
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	//
    	BinaryNumber ans = absoluteVal(this).dividePositive(absoluteVal(divisor));
    	if (this.signum() != divisor.signum()) {
    		ans = ans.negate();
    	}
    	
    	return ans;
    }

    // recursion function that returns a BinaryNumber which is a division between two positive BinaryNumber - the operating BinaryNumber and "divisor"
    public BinaryNumber dividePositive(BinaryNumber divisor) {
    	BinaryNumber ans = new BinaryNumber (BinaryNumber.ZERO);
    	
    	if (this.compareTo(divisor) == -1) {
    		ans = BinaryNumber.ZERO;
    	}
    	if (this.compareTo(divisor) == 0) {
    		ans = BinaryNumber.ONE;
    	}
    	
    	if (this.compareTo(divisor) == 1) {
    		BinaryNumber firstBranch = this.divideBy2();
    		ans = firstBranch.dividePositive(divisor).add(firstBranch.dividePositive(divisor));
    		
    		BinaryNumber check = ans.add(BinaryNumber.ONE);
    		check = check.multiply(divisor);
    		if (check.compareTo(this) <= 0) {
    			ans = ans.add(BinaryNumber.ONE);
  
    			
    		}
    		
    	
    	}
    
    	
    	return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    // a class constructors which creates a new BinaryNumber which has the decimal value of the value represented in "s"
    // assumes all chars in "s" are decimal digits
    public BinaryNumber(String s) {
    	
    	String temp = s;
    	
    	if (!s.equals("") && s.charAt(0) == '-') {
    		temp = s.substring(1);
    	}
        for (int i = 0; i < temp.length(); i = i + 1) {
        	if (toInt(temp.charAt(i)) == -1) {
        		throw new IllegalArgumentException("the string must contain only decimal chars or minus");
        	}
        }
    	BinaryNumber num = new BinaryNumber (BinaryNumber.ZERO);
       	BinaryNumber bn10 = new BinaryNumber ('9');
    	bn10 = bn10.add(BinaryNumber.ONE);
    	
    	boolean negative = false;
    	if (s.charAt(0) == '-') {
    		negative = true;
    		s= s.substring(1);
    	}
    	for (int i = 0; i < s.length(); i = i + 1) {
    		BinaryNumber powerOfTen = new BinaryNumber (BinaryNumber.ONE);
    		for (int j = 0; j < s.length() - i - 1 ; j = j + 1) {
    			powerOfTen = powerOfTen.multiply(bn10);
    		}
    		BinaryNumber currentChar = new BinaryNumber(s.charAt(i));
    		num = num.add(currentChar.multiply(powerOfTen));
    	}
    	
    	if (negative) {
    		num = num.negate();
    	}
    	
    	
    	bits = num.bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    // returns a String which is a decimal representation of the BinaryNumber value
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        
        boolean negative = false;
        BinaryNumber bn = new BinaryNumber (this);
        
        if (this.signum() < 0) {
        	negative = true;
        	bn = bn.negate();
        }
        String s = reversedstring(BinaryNumberToString(bn));
        bn.bits.removeLast();
        
     
        String ans = binary2DecimalRec(s,"0",s.length()); // calling the recursion function
        ans = reversedstring(ans);
        
        if (negative) { // adding negative signum
        	ans = '-' + ans;
        }
       
        return ans;
    }
    
    // functions for task 3.13
    
    // returns a string that is the decimal value of the binary string represented in "s"
    // using a recursion function "binary2DecimalRec" to generate the decimal value
    public static String binary2DecimalRec(String s,String currentVal, int index) {
        String ans="";
        
       if (index == 0) { 
    	   ans = currentVal;
       }
       else { 
    	   currentVal = decimalDouble(currentVal); 
    	   
    	   if (s.charAt(index-1) == '1') { 
    		   currentVal = decimalIncrement(currentVal); 
    		   ans = binary2DecimalRec(s,currentVal,index -1);
    				   
    	   }
    	   else {
    		   ans = binary2DecimalRec(s,currentVal,index -1);
    	   }

       }

        return ans; 
    }
    
    // returns a string which is a decimal increment of String "s" by 1
    // throw an exception if "s" is not a valid string for base 10
    public static String decimalIncrement(String s) {
        String ans = "";
     
        ans = decimalIncrementRec(s); 

        return ans;
    }
    
    
    // returns a String which is a decimal increment by 1 for "s"
    // assumes "s" is a valid string for base 10
    public static String decimalIncrementRec(String s) {
    	String ans = "";
    	
        if(s.length() == 1 & s.charAt(0) == '9') { // checkes if the only char at s is 9
        	ans = "01";
        }
        else if (s.charAt(0) == '9') { // checks if the first char of s is 9, if so, concat "0" with a call for the function again with substring of s (without the first char)
        	ans = "0" + decimalIncrementRec(s.substring(1));
        }
        else {
        	ans = (toInt(s.charAt(0)) + 1 ) + s.substring(1); // increments the value of the last digit by 1
        }
    	
    	return ans;
    }
    
    
    // returns a string in base 10 which is a double value from "s"
    // throw out an exception if "s" is not from base 10
    public static String decimalDouble(String s) {
        String ans = "";
        //Task 3.3
     
        ans = decimalDoubleRec(s); // calling the recursion function
        
        return ans;
    }
    
    
    // returns a string in base 10 which is a double value from "s"
    // assumes "s" is a valid String for base 10
    public static String decimalDoubleRec(String s) {
        String ans = "";
        //Task 3.3
        if (s.length() > 0) { 
        	 if (s.length() == 1) { 
             	if (toInt(s.charAt(0)) * 2 >= 10) { 
             		ans =(toInt(s.charAt(0)) * 2) - 10 +  "1" ;
             	}
             	else { 
             		ans = toInt(s.charAt(0)) * 2 + "";
             	}
             }
             else if (toInt(s.charAt(0)) * 2 >= 10) { 
             	ans = decimalDoubleRec(s.charAt(0) + "").charAt(0) + decimalIncrement(decimalDoubleRec(s.substring(1)));
             }
             else {
            	ans = decimalDoubleRec(s.charAt(0) + "") + decimalDoubleRec(s.substring(1));
             }
        	
        }

        return ans; 
    }
    
    // returns an int value "c" represents
    public static int toInt(char c) {
    	return "0123456789".indexOf(c);
    	}
    
    
    public static String reversedstring(String s) {
    	String ans = "";
    	for (int i = 0; i < s.length(); i = i + 1) {
    		ans = s.charAt(i) + ans;
    	}
    	
    	return ans;
    }
    
   
    // end of functions for task 3.13 -----------------------------
    
    // returns a String of the BinaryNumber
    public static String BinaryNumberToString (BinaryNumber number) {
    	String s = "";
    	Iterator <Bit> iter  = number.bits.iterator();
    	
    	while (iter.hasNext()) {
    		s = iter.next() + s;
    	}
    	
    	return s;
    }
    
    // Returns this * 2
    public BinaryNumber multiplyBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divideBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
    
    


}

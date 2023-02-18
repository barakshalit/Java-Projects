public class NumericalString { 
	
	// returns a boolean answer according to waether or not the string "s" is a valid string for the recieved base "b"
	// throw an exception if s is null or b is not legal (not between 2 and 10)
    public static boolean legalNumericString(String s, int b) {
        boolean ans = false;
        //Task 3.1
        if((s == null) | (b < 2) | (b > 10)) { // checks "s" and "b" are legal
        	throw new IllegalArgumentException("s is null or b is not between 2 and 10");
        }
        
        if (s != "" & !((s.length() > 1) && (s.charAt(s.length()-1) == '0'))) { // s is not empty nor has '0' as last charecter and its length is bigger than 1
        	boolean isLegal = true;
        	for (int i = 0; i < s.length() & isLegal; i = i + 1) { // checking if all the charecters of "s" are from base "b"
        		if (toInt(s.charAt(i)) >= b | toInt(s.charAt(i)) < 0) {
        			isLegal = false;
        		}
        	}
        	ans = isLegal;
        }
        
        
        
        return ans;
    }

    // returns a string which is a decimal increment of String "s" by 1
    // throw an exception if "s" is not a valid string for base 10
    public static String decimalIncrement(String s) {
        String ans = "";
        //Task 3.2
        if (!legalNumericString(s,10)) { // checks if "s" is valid string for base 10
        	throw new IllegalArgumentException("s is not a valid string for base 10");
        }
        
       
        ans = decimalIncrementRec(s); // calling the recursion function
        	
        
        return ans;
    }
    
    // functions for task 3.2 -------------------------------------------
    
    // assumes "s" is a valid string for base 10
    // returns a String which is a decimal increment by 1 for "s"
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
    
    // end of functions for task 3.2 ------------------------------------
    
    // returns a string in base 10 which is a double value from "s"
    // throw out an exception if "s" is not from base 10
    public static String decimalDouble(String s) {
        String ans = "";
        //Task 3.3
        if (!legalNumericString(s,10)) { // checks if "s" is valid string for base 10
        	throw new IllegalArgumentException("s is not a valid string for base 10");
        }
       
        ans = decimalDoubleRec(s); // calling the recursion function
        
        return ans;
    }
    
    // functions for task 3.2 -------------------------------------------
    
    // returns a string in base 10 which is a double value from "s"
    // assumes "s" is a valid String for base 10
    public static String decimalDoubleRec(String s) {
        String ans = "";
        //Task 3.3
        if (s.length() > 0) { // cheking the length of "s"
        	 if (s.length() == 1) { // if the length is 1 (only 1 digit), proceed
             	if (toInt(s.charAt(0)) * 2 >= 10) { // in case s*2 will have 2 digits
             		ans =(toInt(s.charAt(0)) * 2) - 10 +  "1" ;
             	}
             	else { // in case s*2 has only 1 digit
             		ans = toInt(s.charAt(0)) * 2 + "";
             	}
             }
             else if (toInt(s.charAt(0)) * 2 >= 10) { // breaking the string into two parts = first digit and the reset of the string, calling the recursion function in each case
             	ans = decimalDoubleRec(s.charAt(0) + "").charAt(0) + decimalIncrement(decimalDoubleRec(s.substring(1)));
             }
             else {
            	ans = decimalDoubleRec(s.charAt(0) + "") + decimalDoubleRec(s.substring(1));
             }
        	
        }

        return ans; 
    }

    // end of functions for task 3.2 --------------------------------------
    
    // returns a string that is the decimal value of the binary string represented in "s"
    // using a recursion function "binary2DecimalRec" to generate the decimal value
    public static String binary2Decimal(String s) {
        String ans = "";
        //Task 3.4
        if (!legalNumericString(s,2)){ // checks if "s" is a valid string for base 2
        	throw new IllegalArgumentException("s is not a valid string for base 2");
        }
        ans = binary2DecimalRec(s,"0",s.length()); // calls the recursion function
        return ans;
    }
    
    // functions for task 3.2 -------------------------------------------

    public static String binary2DecimalRec(String s,String currentVal, int index) {
        String ans="";
        
       if (index == 0) { // checks if the string came to an end
    	   ans = currentVal;
       }
       else { // "s" still has digits
    	   currentVal = decimalDouble(currentVal); // doubling the value of the value calculated so far (currentVal)
    	   
    	   if (s.charAt(index-1) == '1') { // checks if the current char is 1 or 0
    		   currentVal = decimalIncrement(currentVal); // incrementing currentVal by 1
    		   ans = binary2DecimalRec(s,currentVal,index -1); // caliing the recursion
    				   
    	   }
    	   else {
    		   ans = binary2DecimalRec(s,currentVal,index -1); // caliing the recursion
    	   }

       }

        return ans; // returns the value calculated
    }
    
    // end of functions for task 3.2 -------------------------------------

    public static void main(String[] args) {
    	
    
    }
    
    // returns the decimal value 'c' represents
    public static int toInt(char c) {
    	return "0123456789".indexOf(c);
    	}
    
  
}

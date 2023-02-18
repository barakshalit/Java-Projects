

class Change{
	
	
	// assumes coins != null, has only positive values and sorted, n >= 0
	// returns boolean answer if a change for n could be generated with the available coins or not
    public static boolean change(int [] coins, int n){
        boolean ans = false;
        //Task 2.1
        ans = changeRec(coins,n); // calling the recursion to get the change
        
        return ans;
    }
    
    // functions for task 2.1 --------------------------------------------------------------------------
    
    // assumes coins != null, has only positive values and sorted, n >= 0
    // returns a boolean answer if a change was found for n
    public static boolean changeRec(int [] coins, int n){
        boolean ans = false; // initializing the boolean answer
        
        if (n == 0) { //if n == 0 (a change was found) - ans is true
        	ans = true;
        }
        
        if (n > 0) { //if n is still > 0, continue searching
        	
        	for (int i = 0; i < coins.length & !ans ; i = i + 1) {
        		ans = changeRec(coins,n-coins[i]);
        	}
        }
        return ans;
    }
    
    // end of functions for task 2.1 ---------------------------------------------------------------------

    
    
    
    // assumes coins != null, has only positive values and sorted, n >= 0, numOfCoinsToUse >= 0
    // returns a boolean answer to weather a change could be generated for n with the limitations of available coins and number of coins available or not
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false; 
        //Task 2.2
        ans = changeLimitedRec(coins,n,numOfCoinsToUse); // calling the recursion
        return ans;
    }
    
    // functions for task 2.2 ----------------------------------------------------------------------------
    
    // assumes coins != null, has only positive values and sorted, n >= 0, numOfCoinsToUse >= 0
    // in each step, checking if the last recurtion function was able to find a change combination (n == 0 with number of coins >=0), if not yet, starts the recurtion again with n-coins[i] (every coin combination)
    // if a combination was found, returns true
    // if numOfCoinsToUse < 0 , returns false
    public static boolean changeLimitedRec(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false; // initializing the boolean answer
        if (n == 0 & numOfCoinsToUse >= 0) { // a change was found with the limitations of coins and numOfCoinsToUse
        	ans = true;
        }
        
        if (n > 0 & numOfCoinsToUse >= 0) { // if a change was still not found, call the recursion once again 
        	
        	for (int i = 0; i < coins.length & !ans ; i = i + 1) {
        		ans = changeLimitedRec(coins,n-coins[i],numOfCoinsToUse-1); // sending the recusrion with n-coins[i] (new value of n) and numOfCoinsToUse-1 (new value of numOfCoinsToUse)
        	}
        }
    
        return ans; // returns the boolean answer
    }
    // end of functions for task 2.2 ----------------------------------------------------------------------
    
    
    
    
    // assumes coins != null, has only positive values and sorted, n >= 0, numOfCoinsToUse >= 0.
    // prints a possible combination of change according to the types of coins, n, and numOfCoinsToUse
    // if no combination was found, print nothig
    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.3
    	printChangeLimitedRec(coins,n,numOfCoinsToUse, ""); // calling the recursion function
    	
    }
    

    // functions for task 2.3 -----------------------------------------------------------------------------
    
    // prints a possible combination of change according to the types of coins, n, and numOfCoinsToUse
    // in each step, checking if the last recurtion function was able to find a change combination (n == 0 with number of coins >=0), if not yet, starts the recurtion again with n-coins[i] (every coin combination) with the current tested combination (str)
    // if a coin combinations was found and numOfCoinsToUse >= 0, print the combination.
    // if numOfCoinsToUse < 0 , print nothing.
    public static boolean printChangeLimitedRec(int[] coins, int n, int numOfCoinsToUse, String str){
        boolean ans = false; // initializing the boolean answer
        if (n == 0 & numOfCoinsToUse >= 0) { // checks if the current combination is valid
        	ans = true;
        	if (str.length() > 0) {
        		System.out.print(str.substring(0,str.length()-1)); //prints it
        	}
        	else {
        		System.out.print(str); // if the string is empty - print nothing
        	}
        	
        }
        
        if (n > 0 & numOfCoinsToUse >= 0) { // if a combination was still not found, call the recursion function again
        	
        	for (int i = 0; i < coins.length & !ans ; i = i + 1) {
        		ans = printChangeLimitedRec(coins,n-coins[i],numOfCoinsToUse-1, str + coins[i] + ",");
        	}
        }
    
        return ans; // returns boolean answer
    }
    
    // end of functions for task 2.3 ------------------------------------------------------------------------
    
    
    
    
    
    // assumes coins != null, has only positive values and sorted, n >= 0, numOfCoinsToUse >= 0
    // returns the number of possible combinations to create a change for n with the limitations of coins and numOfCoinsToUse
    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
        //Task 2.4
        int [][] combinations = new int [0][0]; // initializing 2D combinations array
        int [] currentCombination = new int [numOfCoinsToUse]; // initializing currentCombination array
        ans = countChangeLimitedRec(coins,n,numOfCoinsToUse,currentCombination,combinations).length; // calling the recursion function and checking the size of the 2D array (=number of possible unique combinations)
       
        return ans;
    }
    
   // functions for task 2.4
    
    // assumes coins != null, has only positive values and sorted, n >= 0, numOfCoinsToUse >= 0
    // checking all the possible combinations to create a change for n with the limitations of coins and numOfCoinsToUse - for each combination an array is built and store in a 2D array
    // if a combination was found (n == 0 and numOfCoinsToUse >= 0), count the current combination as a possible combination ONLY if ther is no similar combination that was already added
    // if not, calling the recursion again if n > 0 and numOfCoinsToUse > 0
    // returns the 2D array at the end
    public static int [][] countChangeLimitedRec(int[] coins, int n, int numOfCoinsToUse,int [] currentCombination, int [][] combinations){
   	 	
        if (n == 0 & numOfCoinsToUse >= 0) { // checking if the current array is a valid combination
        	combinations = addArrayTo2D(combinations,currentCombination); // adding the array to the 2D combinations array (addArrayTo2D will handle weather or not to add it)
      
        	
        }
        
        if (n > 0 & numOfCoinsToUse > 0) { // continue searching for other combinations
        	
        	for (int i = 0; i < coins.length; i = i + 1) {
        		
        		int [] temp = new int[currentCombination.length]; // creating a temp aray to save "currentCombination" current values
        		for (int j = 0; j < temp.length; j = j + 1) {
        			temp[j]=currentCombination[j];
        		}
        		
        		currentCombination[currentCombination.length - numOfCoinsToUse] = coins[i]; // adding the new coin to the currentCombination array
        		combinations = countChangeLimitedRec(coins,n-coins[i],numOfCoinsToUse-1, currentCombination, combinations); // calling the recursion fiunction to check weather or not the currentCombination is a valid solution
        		currentCombination = temp; // resetting "currentCombination" to its original value for this run
        	}
        }
        
        return combinations;
    
    }
    
    
    
   // end of functions for task 2.4
     
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
    	 int [][] combinations = new int [0][0]; // initializing 2D combinations array
    	 int [] currentCombination = new int [numOfCoinsToUse]; // initializing currentCombination array
    	 combinations = printAllChangeLimitedRec(coins,n,numOfCoinsToUse,currentCombination,combinations); // calling the recursion function to get the final 2D array with the valid and unique combinations
    	 printCombinations(combinations); // prints all the combinations
    }
   
    
    public static int [][] printAllChangeLimitedRec(int[] coins, int n, int numOfCoinsToUse,int [] currentCombination, int [][] combinations){
   	 	
        if (n == 0 & numOfCoinsToUse >= 0) { // checking if the current array is a valid combination
        	combinations = addArrayTo2D(combinations,currentCombination); // adding the array to the 2D combinations array (addArrayTo2D will handle weather or not to add it)
        }
        
        if (n > 0 & numOfCoinsToUse > 0) { 
        	
        	for (int i = 0; i < coins.length; i = i + 1) { // continue searching for other combinations
        		
        		int [] temp = new int[currentCombination.length]; // creating a temp aray to save "currentCombination" current values
        		for (int j = 0; j < temp.length; j = j + 1) {
        			temp[j]=currentCombination[j];
        		}
        		
        		currentCombination[currentCombination.length - numOfCoinsToUse] = coins[i]; // adding the new coin to the currentCombination array
        		combinations = printAllChangeLimitedRec(coins,n-coins[i],numOfCoinsToUse-1, currentCombination, combinations); // calling the recursion fiunction to check weather or not the currentCombination is a valid solution
        		currentCombination = temp; // resetting "currentCombination" to its original value for this run
        	}
        }
        
        return combinations;
    
    }
    
// functions for task 2.5
    
    // adding a new array to 2D array and returns the new 2D array
    public static int [][] addArrayTo2D(int [][] old2D, int [] array){
    	boolean exist = false; // initializing boolena flag to check if the current combination already exist
    	int [][] newarray = new int [0][]; // initializing the new 2D array
    	for (int i = 0; i < old2D.length & !exist; i = i + 1) { // checking if the combination already exist in the old2D array
    		if (sameCombination(old2D[i],array)) { // if already exist, turn flag on and change the return value to the old2D array
    			exist = true; 
    			newarray = old2D;
    		}
    	}
    	
    	if (!exist) { // if the current combination is not already in the old2D array, adding it
    		int [][] new2D = new int [old2D.length + 1][];
    		for (int i = 0; i < new2D.length -1 & !exist ; i = i +1) {
        		new2D[i] = old2D[i];
        	}
        	new2D[new2D.length-1] = array;
        	newarray = new2D;
    	}
    	

    	return newarray; // returns the 2D array
    	
    	
    }
    
    // gets 2 arrays and checking if they are the same combination of coins
    public static boolean sameCombination(int [] first, int [] second) {
    	boolean ans = true; // initializing the boolean answer
  
    	for (int i = 0; i < first.length & ans ; i = i + 1) { // checking the number of occurences of the current value in the first array is the same at the second array
    		int value = first[i];
    		int firstCounter = 0;
    		
    		for (int j = 0; j < first.length ; j = j + 1) {
    			if(first[j] == value) {
    				firstCounter = firstCounter + 1;
    			}
    		}
    		
    		int secCounter = 0;
    		for (int k = 0; k < second.length; k = k + 1) {
    			if (second[k] == value) {
    				secCounter = secCounter + 1;
    			}
    		}
    		
    		if (firstCounter != secCounter) { // if the number of occurences is different, returns false
    			ans = false;
    		}
	
    	}

    	return ans; 
    }
    
    // gets a 2D array and prints all of it combinations (arrays) without 0
    public static void printCombinations(int [][] combinations) {
    	
    	for (int i = 0; i < combinations.length; i = i + 1) { // prints the current array 
    		String str = "";
    		for (int j = 0; j < combinations[i].length ; j = j + 1) {
    			if(combinations[i][j] != 0) { // checking if the current value is not 0
    				str = str + combinations[i][j] + ",";
    			}
    		}
    		if (str.length()>1) { // printing the string
    			System.out.print(str.substring(0,str.length()-1));
    		}
    		System.out.println();
    		str = "";
    	}
    }

    // end of functions for task 2.5
    

}

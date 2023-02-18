/*
I, <Barak Shalit> (<316222280>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    // adding element as the last link in the BitList 
    public void addLast(Bit element) {
    	if (element == null) {
    		throw new IllegalArgumentException ("Can't add a null element as Last!"); // throwing an exception if element = null
    	}
    	super.addLast(element); // adding element as the last Link in the Bitlist
        numberOfOnes = numberOfOnes + element.toInt(); // increasing numberOfOnes by 1 is element = 1
    }
    
    // adding element as the first link in Bitlist
    public void addFirst(Bit element) {
    	if (element == null) {
    		throw new IllegalArgumentException ("Can't add a null element as First!"); // throw out an exception if element = null
    	}
    	super.addFirst(element); // adding element as the first element
    	numberOfOnes = numberOfOnes + element.toInt(); // increasing numberOfOnes by 1 is element = 1
    	
    }
    
    // removing the last element in the Bitlist and returns the removed Bit
    public Bit removeLast() {
    	Bit removedBit = super.removeLast();
    	numberOfOnes = numberOfOnes - removedBit.toInt(); // decresing numberOfOnes if the removed bit = 1
    	
    	return removedBit; // returns the removed bit
    }

    // removing the first element in the Bitlist and returns the removed Bit
    public Bit removeFirst() {
    	Bit removedBit = super.removeFirst();
    	numberOfOnes = numberOfOnes - removedBit.toInt(); // decresing numberOfOnes if the removed bit = 1
    	
    	return removedBit;
    }
    
  

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    
    // prints the Bitlist
    public String toString() {
    	String ans = "<";
    	Iterator<Bit> iter = iterator(); // initializing iterator
    	String binary = "";
    	if (iter.hasNext()) {
    		
    		while (iter.hasNext()) {
    			binary = binary + iter.next();
        	}
    		binary = reversedString (binary);
        	
    	}
    	
    	ans = ans + binary + ">";
    	
    	return ans; // returns the String representation of the list
    }
    
    // functions for task 2.2
    
    // returns a reversed string of s
    public String reversedString (String s) {
    	String reversedString = "";
    	for (int i = 1; i <= s.length(); i = i + 1) {
    		reversedString = reversedString + s.charAt(s.length() - i);
    	}
    	
    	return reversedString;
    	
    }
    
    // end of functions for task 2.2
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    
    // copy constructor, which construct a copied version of "other" Bitlist
    // creates a new Bitlist which is equal to "other" Bitlist but are not the same object
    public BitList(BitList other) {
    	if (other == null) {
    		throw new IllegalArgumentException ("element is null");
    	}
    	Iterator<Bit> iter = other.iterator();
    	while(iter.hasNext()) {
    		this.addLast(iter.next());
    		
    	}
    	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
   
    // returns a boolean answer for whether or not the Bitlist is a valid Number
    public boolean isNumber() {
 
    	boolean ans = false;
        if (this.size() > 0) { // checks if the size of Bitlist is atleast 1
        	if (this.getLast().toInt() == 0 | this.getNumberOfOnes() > 1 ) { // chekcs if the MSB is 0 or has more than one instcnace of the Bit "1"
        		ans = true;
        	}
        		
        }
        
        return ans;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    
    // returns a boolean answer for whether or not the Bitlist is in reduced representaion
    public boolean isReduced() {
        boolean ans = false;
        
        if (this.isNumber()) { // first, check if the list is a valid number
        	if (this.firstCondition() | this.secondCondition() | this.thirdCondition()){ // checks all 3 required conditions
        		ans = true;
        	}
        }
        
        return ans;
    }

    // makes the Bitlist a reduced representaion of itself
    // if the Bitlist is not a valid number, does not make any changes to it
    public void reduce() {
    	if (this.isNumber()) { // checks if the Bitlist is a valid number
    		while (!this.isReduced()) { // aslong as the Bitlist is not in its reduced form - keep removing Bits from the end of it
        		this.removeLast();
        		
        		}
    	}
    }
    

    // Functions for task 2.5
    
    // first condition for isReduced function - checks if the Bitlist is one of the following Bitlists - "<0>" \ "<01>" \ "<11>"
    // returns a boolean answer for whether or not the first condition is fullfiled
    private  boolean firstCondition() {
    	boolean ans = false;
    	if (this.toString().equals("<0>") | this.toString().equals("<01>") | this.toString().equals("<11>")) {
    		ans = true;
    	}
    
    	
    	return ans;
    }
    
    // second condition for isReduced function - checks if the Bitlist has atleast 3 Bits, and its last 2 Bits are 01 or 10 
    // returns a boolean answer for whether or not the second condition is fullfiled
    private  boolean secondCondition() {
    	boolean ans = false;
    	
    	if (this.size() >= 3) {
    		 Bit Last  = this.get(this.size()-1);
    		 Bit secondToLast  = this.get(this.size()-2);
    		if ((Last.toInt() == 0 & secondToLast.toInt() == 1) | (Last.toInt() == 1 & secondToLast.toInt() == 0)) {
    			ans = true;
    		}
    		
    	}
    	
    	return ans;
    }
    
    // third condition for isReduced function - checks if the Bitlist has atleast 3 Bits, from them only 2 Bits are 1 and they are the last two Bits
    // returns a boolean answer for whether or not the second condition is fullfiled
    private  boolean thirdCondition() {
    	boolean ans = false;
    	
    	if (this.size() >= 3) {
    		 Bit Last  = this.get(this.size()-1);
    		 Bit secondToLast  = this.get(this.size()-2);
    		if ((this.numberOfOnes == 2 & (Last.toInt() == 1 & secondToLast.toInt() == 1))) {
    			ans = true;
    		}
    		
    	}
    	
    	return ans;
    }
    // end of functions for task 2.5
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
   
    // returns a new Bitlist which is a compliment representation of the original Bitlist
    public BitList complement() {
        Iterator <Bit> iter = this.iterator();
        Bit ONE = new Bit(true);
        Bit ZERO = new Bit(false);
        BitList b1 = new BitList();
        Bit Output;
        
        int size = this.size();
        
        while (iter.hasNext() & size > 0){
        	size = size - 1;
        	Output = iter.next();
        	if (Output.toInt() == 0) {
        		b1.addLast(ONE);
        	}
        	else {
        		b1.addLast(ZERO);
        	}
        	
        }
        
        return b1;
        
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
   
    // shifting the Bitlist by 1 bit to the right (removing the LSB Bit) and returning the value of the removed bit
    // if the list is empty, the function wont change the list and will return null
    public Bit shiftRight() {
    	Bit ans;
        if (this.size() == 0) { // checking if the size of the list is 0
        	ans = null;
        }
        else {
        	ans = this.getFirst();
        	this.removeFirst();
        }
        
        return ans; // returns the removed bit (if found) or null (if the list size == 0)
    }

    // adding a new 0 bit to the Bitlist to the right
    public void shiftLeft() {
    	Bit ZERO = new Bit (false);
        this.addFirst(ZERO); // adding 0 to the right
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
   
    // creates a padded-version of the operating Bitlist according to newLength paremeter
    // the function will add the required number of Bits ( which will be the same as the last bit of the list) to reach newLength length
    // this function wont change the Bitlist if newlength <= Bitlist size.
    public void padding(int newLength) {
    	if (newLength > this.size()) { // checks if newLength is bigger than the size of the list
    		int requiredPadding = newLength - this.size();
    		Iterator <Bit> iter = this.iterator();
    		Bit bit = null;
    		while (iter.hasNext()) { // getting the value of the last bit in the list 
    			bit = iter.next();
    		}
    		
    		for (int i = 0; i < requiredPadding; i = i + 1) { // adding the bit as last until the list size = newLegnth
    			this.addLast(bit);
    		}
    		
    		
    	}
    	 
    	 
        
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
    
    public static void main(String[] args){
	 

	
	}
    
}

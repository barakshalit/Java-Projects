/*
I, <Barak Shalit> (<316222280>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

public class Bit {

    private  boolean value;
    public static  final Bit ONE  = new Bit(true);
    public static  final Bit ZERO = new Bit(false);

    public Bit(boolean value) {
        this.value = value;
    }

    public Bit(int intValue) {
        if (intValue == 0)
            value = false;
        else {
            if (intValue == 1)
                value = true;
            else throw new IllegalArgumentException(value + " is neither 0 nor 1.");
        }
    }

    public String toString() {
        return "" + toInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Bit))
            return false;
        else return value == ((Bit) obj).value;
    }

    public Bit negate() {
        Bit output;
        if (value)
            output = ZERO;
        else output = ONE;
        return output;
    }

    public int toInt() {
        int output;
        if(value)
            output = 1;
        else
            output = 0;
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 1.1 ================================================
    
    // assumes bit1, bit2, bit3 != null
    // returns the "sum" Bit of the sum from bits 1,2,3 
    public static Bit fullAdderSum(Bit bit1, Bit bit2, Bit bit3) {
    	if (bit1 == null | bit2 == null | bit3 == null) {
    		throw new IllegalArgumentException("none of the bits can have null as value");
    	}
    	
        Bit Sum = XOR(XOR(bit1,bit2),bit3); // calling a XOR function to get the value
        
        return Sum; // returns the sum
    }

    // assumes bit1, bit2, bit3 != null
    // returns the "carry" Bit of the sum from bits 1,2,3 
    public static Bit fullAdderCarry(Bit bit1, Bit bit2, Bit bit3) {
    	if (bit1 == null | bit2 == null | bit3 == null) {
    		throw new IllegalArgumentException("none of the bits can have null as value");
    	}
        Bit Carry = new Bit ((bit1.value & bit2.value) | (bit3.value & (bit1.value | bit2.value))); //using the formula OR(AND(bit1,bit2),AND(bit3, OR(bit1,bit2)))
        return Carry; // returns the carry
    }
    
   
    // functions for task 1.1
    
    // assumes bit1, bit 2 != null
    // returns the XOR value of bit1 and bit 2
    private static Bit XOR(Bit bit1, Bit bit2) {
    	Bit XORresult;
    	int counter = 0;
    	if (bit1.toInt() == 1) {
    		counter = counter + 1;
    	}
    	if (bit2.toInt() == 1) {
    		counter = counter + 1;
    	}
    	
    	if (counter % 2 == 0) {
    		XORresult = ZERO;
    	}
    	else {
    		XORresult = ONE;
    	}
    	
    	return XORresult;
    }
    
    // end o functions for task 1.1
    
		

}

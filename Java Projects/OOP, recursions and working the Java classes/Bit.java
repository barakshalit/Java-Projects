
public class Bit {

    private boolean value;

    // constructor
    
    public Bit(boolean value){
        //Task 4.1
    	this.value = value;
    }
    
    // methods
    
    //returns an int value for bit (true = 1, false = 0)
    public int toInt(){ 
        int ans = 0;
        //Task 4.2
        if (value) {
        	ans = 1;
        }
        return ans;

    }

    // returns String value for bit (true = "1", false = "1")
    public String toString(){
        String ans = "";
        //Task 4.3
        if (value) {
        	ans = "1";
        }
        else {
        	ans = "0";
        }
        return ans;
    }
    
    
}


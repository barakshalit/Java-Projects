
public class Assignment2 {
	

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	
	// "isSquareMatrix" returns boolean answer after checking if a 2D array is Square Matrix
	public static boolean isSquareMatrix(boolean[][] matrix) {
		
		boolean isMatrix = true; //initializing boolean answer
		
		if (matrix != null & matrix.length != 0){ // checks if the array is null of its length is 0
		
			for (int i = 0; i< matrix.length & isMatrix ; i = i+1){
			
				if (matrix[i].length != matrix.length){ // checks if each row length in the array is equal to the numbers of arrays (#columns == #rows)
				
					isMatrix = false;
				}
						
			}
	
		}
		else {
		
			return false;
		}
		
		return isMatrix; // returns the boolean answer
		
	}
	
	// task 2
	
	// "isSymmetricMatrix" returns boolean answer after checking if a 2D array has symmetric values (for (0 <= i < j < n) matrix[i][j] == matrix[j][i])
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
	
		boolean isSymmetric = true; //initializing boolean answer
			
		for (int i = 0; i < matrix.length & isSymmetric ; i = i +1){
		
			for (int j = i + 1; j < matrix[i].length; j = j +1){ // going through the array
			
				if (matrix[i][j] != matrix[j][i]) { //
				
					isSymmetric = false; // breaks the loop if the matrix is not symmetric
				}
			}
				
		}
		
		return isSymmetric; // returning the boolean answer
		}
	
		
		
	

	// task 3
	
	// "isAntiReflexiveMatrix" returns boolean answer after checking if the matrix is AntiReflexsive (for (0 < i < matrix-length) matrix [i][i] == false)
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		
		boolean isAntiReflexive = true; // initializing the boolean answer
		
		for (int i = 0; i < matrix.length & isAntiReflexive ; i = i + 1)
		{
			if (matrix [i][i] != false) // checks it current index is Anti reflexive
			{
				isAntiReflexive = false; // breaks the loop
			}
		}
		
		
		return isAntiReflexive; // returning the boolean answer
	}
	
	// task 4
	
	// "isLegalInstance" returns a boolean answer after checking if the matrix is a legal instance (is a square, symmetric and anti reflexive matrix)
	public static boolean isLegalInstance(boolean[][] matrix) {
	

		boolean isLegal = true; // initializing the boolean answer
		
		if (matrix != null) // checks if the matrix is null
		{
			if (!isSquareMatrix(matrix) | !isSymmetricMatrix(matrix) | !isAntiReflexiveMatrix (matrix)) //checks if the matrix is NOT square/symmetric/anti reflexive
			{
				isLegal = false;
			}
		}
		else
		{
			isLegal = false;
		}
		
		
		
		return isLegal; // returns the boolean answer
	}
	
	// task 5
	
	// "isPermutation" returns a boolean answer after checking if an array is Permutation
	public static boolean isPermutation(int[] array) {
		
		boolean isPermutation = true; // initializing the boolean answer
		int counter = 0; // initializing a counter to keep track of the number of multiple values in the array
		
		
		for (int i = 0; i < array.length & counter < 2; i = i +1)
		{
			counter = 0; // reset s the counter for the next object
			
			for (int j = 0; j < array.length & counter < 2; j = j +1)
			{
				if (array[j] == i)
				{
					counter = counter + 1;
				}
				
			}
			
			if (counter != 1) //breaks the loop
			{
				isPermutation = false;
			}
			
		}
		return isPermutation; // returns the boolean answer
	}
	
	// task 6
	
	// "hasLegalSteps" returns a boolean answer after checking if the tour array has legal steps between cities -
	// (each step is valid according to the flights matrix - there is a flight between the cities)
	
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {

		boolean hasLegalSteps = true; // initializing the boolean answer
		int firstCity; //index of the first city
		int secondCity; // index of the second city
		
		if (!flights[tour[tour.length-1]][tour[0]]) //checking if the last city has flight back to the origin city
		{
			hasLegalSteps = false;
		}
		
		for (int i = 0; i < tour.length -1 & hasLegalSteps ; i = i +1) // for each city - checking if there is a flight to the next one
		{
			
			firstCity = tour [i]; // getting the first city index
			secondCity = tour [i+1]; // getting the second city index
			
			if (!flights [firstCity][secondCity]) //checks if there is a flight between the 2 cities
			{
				hasLegalSteps= false;
			}
		}
		
		
		
		return hasLegalSteps; // returns the boolean answer
	}
	
	// task 7
	
	// "isSolution" returns a boolean answer after checking the the "tour" array is a valid solution according to the flights matrix -
	// "tour" array is permutation & the first city in the "tour array" is 0 & there is a flight between every steps in the "tours" array tour[i] -> tour[i + 1] and tour[n-1] -> tour[0]
	public static boolean isSolution(boolean[][] flights, int[] tour) {
		
		boolean isSolution = true;// initializing the boolean answer
		
		if (tour == null || tour.length != flights.length) { // checks if the array is either null/has the wring length according to the flights matrix
		
			throw new UnsupportedOperationException("tour array is not legal"); //throwing out an exception if the "tour" array is not in the correct length
		}
			
			if (!isPermutation(tour) | tour[0] !=0 | !hasLegalSteps(flights,tour)) { //checks if the array is either NOT permutation/tour[0] is NOT equals to the 0 city/the array have ILLEGAL steps
			
				isSolution = false;	// the array is not a valid solution		
			}
		
		
		
		
		return isSolution; // returns the boolean answer
	}
	
	// task 8
	
	// "evaluate" returns boolean answer after checking if the "assign" array satisfies the "cnf" array
	public static boolean evaluate(int[][] cnf, boolean[] assign) {
		//throw new UnsupportedOperationException("Not Implemented yet.");		
		boolean evaluate = true; //initializing the boolean answer
		
		
		for (int i = 0 ; i < cnf.length & evaluate; i = i +1) {
		
			if (!clauseCheck(cnf[i],assign)) {
				evaluate = false;
			}
		}
		
		
			
		return evaluate;
	}
	
	//functions for task 8
	
	// "clauseCheck" returns boolean answer after checking if a single cnf clause is satisfied according to the "assign" array
	public static boolean clauseCheck(int[] clouse, boolean [] assign) {
		
		boolean output = false; // initializing the boolean answer
		
		for (int i = 0; i < clouse.length & !output; i = i + 1) {
			
			if (checkSingelLiteral(clouse[i],assign)) { //for each literal in the clause, checks if the literal satisfy the clause according to the assign array
				output = true;
				
			}
			
		}
		
		return output; //returns boolean answer
		
	}
	
	// "checkSingleLiteral" returns a boolean answer for the literal value according the the assign array
	public static boolean checkSingelLiteral(int literal, boolean assign []) {
		
		boolean output;
		
		if (literal < 0) { //literal is negative
			output = !assign[literal * (-1)]; //checks what is the value of the literal according to the assign array
			
		}
		else { //literal is positive
			output = assign[literal]; //checks what is the value of the literal according to the assign array
		}
			
		return output; //returns boolean answer
		
	}
	
	// end of functions for task 8
	
	// task 9
	
	// "atLeastOne" returns a 2D cnf array in which at least one of the literals of "lits" array is satisfied
	public static int[][] atLeastOne(int[] lits) {
		
		int [][] atLeastOne = new int [1][lits.length]; // initializing the cnf array
		
		for (int i = 0 ; i < lits.length ; i = i + 1) { // building a single clause cnf with all the literals values from "lits" array (this will make sure at least one of the literals is satisfied)
			atLeastOne[0][i] = lits[i];
		}
		
		return atLeastOne; //returns the cnf array
		
	}

	// task 10
	
	// "atMostOne" returns a 2D cnf array in which at most one of the literals from "lits" array is satisfied
	public static int[][] atMostOne(int[] lits) {
		
		int n = lits.length; // gets the length of "lits" array
		int numOfClauses = n * (n-1) / 2; // calculating the required number of clauses for the cnf
		int [][] cnf = new int[numOfClauses][]; // initializing the cnf
		
		int cnfIndex = 0; //initializing cnf index
		
		for (int i = 0; i < n ; i = i + 1) { // build cnf clauses with the negative valuse of each pair of literals in "lits" array
			
			for (int j = i + 1; j < n ; j = j + 1) {
				
				int [] clouse = {-lits[i],-lits[j]}; 
				cnf[cnfIndex] = clouse;
				cnfIndex = cnfIndex + 1;
				
			}
			
		}
		
		
		return cnf; //returns the cnf
	}
	
	// task 11
	
	// "exactlyOne" returns a 2D cnf array in which exactly one of the literals from "lits" array is satisfied
	public static int[][] exactlyOne(int[] lits) {
		
		int n = lits.length; // getting "lits" array length
		int numOfClauses = n * (n-1) / 2; // getting the number of clauses (without the extra one)
		int [][] cnf = new int[numOfClauses + 1][]; // initializing the cnf array
		
		int cnfIndex = 0; //initializing cnf index
		
		for (int i = 0; i < n ; i = i + 1) { // build cnf clauses with the negative valuse of each pair of literals in "lits" array
			
			for (int j = i + 1; j < n ; j = j + 1) {
				
				int [] clouse = {-lits[i],-lits[j]};
				cnf[cnfIndex] = clouse;
				cnfIndex = cnfIndex + 1;
				
			}
			
			
		}
		
		int [] extraClause = new int [n]; //initializing extra clause for the cnf array
		
		for (int i = 0 ; i < n ; i = i + 1) { //builds clause with all the literals for "lits" array (so the assign for all the negative values of the literals wont be valid)
			
			extraClause[i] = lits[i];
		}
		
		cnf[numOfClauses] = extraClause; //adding the extra clause to the cnf array
	
		
		
		return cnf; //returns the cnf
	}
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	
	// "map" returns int value for step according to the number of cities, stages and cities
	public static int map(int i, int j, int n) {
		
		int k = i * n + (j + 1); // calculates the stage according to the number of cities, stages and cities
		
		return k; //returns the stage
	}
	
	// task 12b
	// "reverseMap" returns an array of the pair "i" and "j" that represents the stage and city that matches the step ("k")
	public static int[] reverseMap(int k, int n) {
		
		int[] pair = new int [2]; // initializing the "pair" array
		boolean found = false;
		
		for (int i = 0 ; i < n & !found ; i = i + 1) {
			
			for (int j = 0; j < n ; j = j + 1) {
				
				if (k == i * n + (j + 1)) { // checking if the current i and j (stage and city) is a match for the "k" stage
					pair[0] = i;
					pair[1] = j;
					
					found = true; // breaks the loop
				}
				
			}
			
		}
			
		return pair; //returns the "pair" array
	}
	
	// task 13
	
	// "oneCityInEachStep" gets the number of cities and returns a 2D cnf array in which for each step, exactly one of the literals map(i,0,n),...,map(i,n-1,n) gets a true value
	public static int[][] oneCityInEachStep(int n) {
	
		int lits[] = new int [n]; //initializing the lits array
		int [][] cnf = new int [0][0]; //initializing the cnf array
		int litsIndex = 0; ////initializing the lits index to 0
		
		
		for (int i = 0; i < n ; i = i + 1) { 
			
			for ( int j = 0 ; j < n ; j = j + 1) { // for each stage, going through all the cities  and gets the literals from map function into the "lits" array
				
				lits[litsIndex] = map(i,j,n); 
				litsIndex = litsIndex + 1;
			}
			
			litsIndex = 0;	// reseting the litsIndex
			cnf = cnfConnector(cnf,exactlyOne(lits)); // generating a new cnf with "exactlyOne" function for the lits of the current stage and connects it with the current cnf with "cnfConnector"
			
		}
		
		
		
		return cnf; //returns the cnf
	}
	
	// functions for task 13
	
	// "cnfConnector" gets 2 cnf 2D arrays and connects them to 1 cnf array
	public static int [][] cnfConnector (int[][] baseCNF, int[][] cnfToAdd){
		
		int [][] newCNF = new int [baseCNF.length + cnfToAdd.length][]; //initializing the new cnf 
		
		for (int i = 0 ; i < baseCNF.length ; i = i + 1) { // adding the first cnf
			
			newCNF[i] = baseCNF[i];
		}
		
		for (int i = 0 ; i < cnfToAdd.length; i = i + 1) { // adding the second cnf
			newCNF[i + baseCNF.length] = cnfToAdd[i];
		}
		
		
		return newCNF; //returns the new cnf
	}
	
	// end of functions for task 13

	// task 14
	
	// "eachCityIsVisitedOnce" gets the number of cities and returns a cnf array in which for each city, the literal map(i,j,n) will get true value in only one stage
	public static int[][] eachCityIsVisitedOnce(int n) {
		
		int lits[] = new int [n]; // initializing the "lits" array
		int [][] cnf = new int [0][0]; // initializing the cnf array
		int litsIndex = 0; // initializing litsIndex to 0
		
		
		for (int i = 0; i < n ; i = i + 1) {
			
			for ( int j = 0 ; j < n ; j = j + 1) {
				
				lits[litsIndex] = map(j,i,n); // for each city, going through all the stages and gets the literals from map function into the "lits" array
				litsIndex = litsIndex + 1;
			}
			
			litsIndex = 0;	// resetting the listIndex
			cnf = cnfConnector(cnf,exactlyOne(lits)); // generating a new cnf with "exactlyOne" function for the lits of the current city and connects it with the current cnf with "cnfConnector"

			
		}
		
		
		
		return cnf; //returns the cnf
	}
	
	// task 15
	
	// "fixSourceCity" gets the number of cities and returns a 2D cnf array in which the literal map(0,0,n) will get a true value for each valid assign
	public static int[][] fixSourceCity(int n) {
				
		int [][] cnf = {{map(0,0,n)}}; // generates a {{0}} cnf
		
		return cnf; //returns the cnf
		
		}
	
	// task 16
	
	// "noIllegalSteps" gets a 2D flights array and returns a 2D cnf array in which at most 1 of the literals: map(i,j,n) and map((i +1) %n, k ,n) will get a true value
	public static int[][] noIllegalSteps(boolean[][] flights) {
		
		int n = flights.length; //getting the flights array length
		int [][] cnf = new int [0][0]; //initializing the cnf
		int [] lits = new int [2]; // initializing the lits array

		
		for (int i = 0 ; i < flights.length ; i = i + 1) {
			
			for (int j = 0 ; j < flights.length ; j = j + 1) {
				
				if ((i != j) & (!flights[i][j])) { //checks if the current object is not reflexive, and if not - checks if there is no flight between city i and j
					
					for (int v = 0 ; v < n ; v = v + 1) { //if there is no flight - making sure that for each stage (v) at most one of the literals: map(v,i,n) and map((v + 1) % n,j,n) will get the value true
						
						lits[0] = map(v,i,n); //current stage literal
						lits[1] = map((v + 1) % n,j,n); //next stage literal
						
						cnf = cnfConnector(cnf,atMostOne(lits)); // generates a cnf in which at most one of the literals of "lits" array will get true value with "atMostOne" function and connects it to the current cnf with "cnfConnector" function
						
					}
				}
				
			}
		}
		
		return cnf; //returns the cnf
	}
	
	// task 17
	// "encode" gets a 2D flights array and returns a cnf that is a conjunction of all the cnf arrays for each required constraint
	public static int[][] encode(boolean[][] flights) {
	

		int [][] cnf = new int[0][0]; // initializing a new cnf
		int n = flights.length; // getting "flights" array length
		cnf = cnfConnector(cnf,oneCityInEachStep(n)); // connects the existing (empty) cnf to a generated cnf with with "one city in each step" constraint
		cnf = cnfConnector(cnf,eachCityIsVisitedOnce(n)); // connects the existing cnf to a generated cnf with with "each city is visited once" constraint
		cnf = cnfConnector(cnf,fixSourceCity(n)); // connects the existing cnf to a generated cnf with with "value of map(0,0,n) will get true for every assign" constraint
		cnf = cnfConnector(cnf,noIllegalSteps(flights)); // connects the existing cnf to a generated cnf with with "no illegal steps" constraint
		
		return cnf; // returns the cnf
	}

	// task 18
	
	// "decode" gets a boolean array of "assignment" and "n" and returns a "tour" array in the length of "n" that is compatible to the "assignment" array
	public static int[] decode(boolean[] assignment, int n) {
		
		if (assignment == null) { // checks if the "assignment" array is null, is so - throw an exception
			throw new UnsupportedOperationException("assignment is null");		
		}
		
		if (assignment.length != (n*n + 1)){ // checks if the "assignment" array length is incorrect, is so - throw an exception
			throw new UnsupportedOperationException("assignment is not is the correct length (n*n + 1");		
		}
		
		int [] tour = new int [n]; //initializing "tour" array
		
		for (int i = 0 ; i < n ; i = i + 1) {
			
			for (int j = 0 ; j < n ; j = j + 1) {
				
				if (assignment[map(i,j,n)]){ // checks if assignment[map(i,j,n)] == true, if so, inject the value of j to tour[i]
					tour[i] = j;
					
				}
			}
		}
		
		return tour; // returns "tour" array
	}
	
	// task19
	
	// "solve" gets a 2D flights array and returns a "tour" array which is a solution for the "great trip" problem
	public static int[] solve(boolean[][] flights) {
		
		if (!isLegalInstance(flights)) { // checks it "flights" array is a illegal instance, if so, throw out an exception
			throw new UnsupportedOperationException("Flights is not a legal Instance.");
		}
		
		int n = flights.length; // getting the length of "flights" array
		int [][] cnf = encode(flights); // generates a cnf with "encode" function from "flights" array
		int nVars = n*n; //getting the number of required literals for the SATSolver
		int [] tour = null; // initializing "tour" array
		
		
		SATSolver.init(nVars); // initializing SATSolver
		SATSolver.addClauses(cnf); // adding the generated cnf to the SATSolver
		boolean solution [] = SATSolver.getSolution(); // running the SAT solver and trying to find a solution
		
		if (solution == null) { //checks if the "solution" array is null, is so, throw an exception
			throw new UnsupportedOperationException("TIMEOUT");		
		}
		
		if (solution.length == nVars+1) { //checks if the SATsolver found a solution
			tour = decode(solution,n); // decoding the solution array
			if (!isSolution(flights,tour)) { // checks if the solution array is not a valid solution, if so, throw an exception
				
				throw new UnsupportedOperationException("the assignment is satisfying, but not a valid one");		

			}
		}
		
		return tour; // returns the "tour" array;
			
	}
	
	// task20
	
	// "solve2" gets a 2D "flights" array and returns a boolean answer if this instance has at least 2 different solutions
	public static boolean solve2(boolean[][] flights) {
		
		if (!isLegalInstance(flights)) { // checks it "flights" array is a illegal instance, if so, throw out an exception
			throw new UnsupportedOperationException("Flights is not a legal Instance.");
		}
		
		boolean ans = false; // initializing the boolean answer
		int n = flights.length; // getting the length of "flights" array
		int nVars = n*n; //getting the number of required literals for the SATSolver
		int [] firstSolution = solve(flights); // using "solve" to get the first solution
		int [][] cnfHelper = {reversedLits(firstSolution,n)}; // initializing a cnf array with 1 clause that contains all the reversed values of lits from the first solution
	
		if (firstSolution != null) { //checks if the "flights" instance has a solution at all
			
			int [][] originalCnf = encode(flights); // generates the original cnf from the "flights" instance
			int [][] newCnf = cnfConnector(originalCnf,cnfHelper); // connecting the original cnf with the new cnf (with the reversed lits clause)
			int [] secondSolution = null; //initializing the second solution array
		
		
			SATSolver.init(nVars); //initializing SATSolver
			SATSolver.addClauses(newCnf); // adding the new cnf to the SATSolver
			boolean solution [] = SATSolver.getSolution(); //trying to find if there is a solution for the new cnf
		

		
			if (solution == null) {
				throw new UnsupportedOperationException("TIMEOUT");	//checks if the "solution" array is null, is so, throw an exception	
			}
		
			if (solution.length == nVars+1) { //checks if the SATsolver found a solution
				secondSolution = decode(solution,n); // decoding the solution array
				if (!isSolution(flights,secondSolution)) { // checks if the solution array is not a valid solution, if so, throw an exception
				
					throw new UnsupportedOperationException("the assignment is satisfying, but not a valid one");		

				}
				else if (!reversedTour(firstSolution,secondSolution) & !equalsArrays(firstSolution,secondSolution)) { // checking if the second solution is not a reversed or equal solution to the original one
					ans = true;

				}
			
			}
		}
		
		return ans; // returns the boolean answer
	
	}
	
	// function for task20
	
	// "reversedTour" gets 2 arrays and returns a boolean answer if the arrays represents 2 tours that are a reversed version of each other
	public static boolean reversedTour(int [] tour1, int [] tour2) { 
		
		boolean isReversed = true; // initializing the boolean answer
		int arrayLength = tour1.length; // getting the length of the arrays
		
		
		if (tour1.length != tour2.length) { // checks if the arrays are in equal length, if not - throw out an exception
			throw new UnsupportedOperationException("The arrays length are not equals");

		}
		else {
			
			for (int i = 1 ; i < arrayLength & isReversed ; i = i + 1) { // checking if the arrays are reversed
				if (tour1[i] != tour2[arrayLength-i]) {
					isReversed = false;
				}
			}
			
		}
		
		return isReversed; // returns the boolean answer
		
	}
	
	// "equalsArrays" gets two arrays and returns a boolean answer after checking if they are equal
	public static boolean equalsArrays(int [] array1 , int [] array2) { 
		
		boolean ans = true; // initializing the boolean answer
		
		if (array1.length != array2.length) { // checks if the arrays are in the same length, if not - throw out an exception
			throw new UnsupportedOperationException("The arrays length are not equals");
		}
		else {
			int arraysLength = array1.length; // getting the array's length
			for (int i = 0 ; i < arraysLength & ans; i = i + 1) { // checking if the arrays are equals
				if (array1[i] != array2[i]) {
					ans = false;
				}
			}
		}
		
		
		return ans; // returns the boolean answer
	}
	
	// "reversedLits" gets a int array (which is a solution from to the "big trip" problem) and "n" (number of cities) and returns an int array with reversed literals
	public static int[] reversedLits(int [] firstSolution, int n) { 
		int k = firstSolution.length; // getting the solution array's length
		int [] reversedLits = new int [k]; // initializing the int array
		
		
		for (int i = 0 ; i < k ; i = i + 1) { // getting the literals from the firstSolution array, reversing them and putting them into "reversedLits" array
			int temp = map(i,firstSolution[i],n);
			reversedLits[i] = -temp;
		}
		
				
		return reversedLits; // returns the "reversedLits" array
		
	}

	//end of functions for task20
		
}

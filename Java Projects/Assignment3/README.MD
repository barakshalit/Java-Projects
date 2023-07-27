# Assignment 3 - getting to know and work with Java's Classes, recursions and OOP

## Overview:
In this assignment i've built some functions that uses Java classes: Big Integer and Random, solved some problems with recursions and ??????

#### Task1 - warmup:
Working with BigInteger and Random Java class, and creating the following functions:

BigInteger sumSmaller(BigInteger n) // Getting a var n as BigInteger and returns a BigInteger which is the sum of all the positive numbers that are smaller than n.
public static void printRandoms(int n) // Getting n as int and prints n random-generated numbers in the scope of 0-int's MAX VALUE.
public static boolean isPrime(BigInteger n) // Getting a var n as non-negative BigInteger and returns boolean answer regarding n being a prime number or not with 100% certainty.
this function is using more efficiant algorithem, that checks all the numbers up to n's root and not all the numbers up to n.
public static BigInteger randomPrime(int n) // getting a var n as int and returns a random-generated prime number which is smaller than 2^n (using isPrime).

#### Task2 - recursions:
In this task iv'e build some recursions that provides several solutions to the "change" problem.

public static boolean change(int[] coins, int n) // recursions that solves the change problem - this function gets array of available coins and a value n and returns a boolean answer wether or not n can be broke into change with the coins available ot us.
public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse) // similar to the previouse function, but with the limitation of only using numOfCoinsToUse coins from the coins available for us.
public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse) // similar to the previouse function but instead of a boolean answer, prints a solution (any) if such a solution exists. otherwise, prints nothing.
public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse) // similar to the previouse function but instead of a boolean answer, returns the number of possible solutions for the problem.
public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse) // similar to the previouse function but instead of a boolean answer, printing all the possible solutions for the problem.

#### Task3 - representing big numbers as strings:
In this task iv'e implemented some function that helps representing very large numbers using strings (to overcome int MAX VALUE).
public static boolean legalNumericString(String s, int b) // this function get a string s and returns a boolean answer whether or not s is a string representing a number in base b.
public static String decimalIncrement(String s) // getting a string s which is a string representing number in base 10, and returns a string that represents the number of s + 1.
public static String decimalDouble(String s) // similar to the previouse function, but instead returns a string which represents s*2.
public static String binary2Decimal(String s) // getting a string that represents a number in base 2 and returns the number represented in base 10.

#### Task4 - OOP:
In this task iv'e implemented the Classes "Bit" - which is a clas representing a binary digit, and "BitVector" which represents a binary number (array of Bit objects).




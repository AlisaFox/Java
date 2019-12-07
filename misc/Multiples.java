/**
 * A program that takes 2 integers as input and prints whether is true
 * that the first one is a multiple of the second one. 
 */
public class Multiples {
    
    public static void main(String[] args) { 
        // 2 integers as input
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        // boolean expressions to check whether x is a multiple of y
        boolean isMultiple = x%y==0;
        // different print statements depending on the whether x is a multiple of y or not
        if(isMultiple) {
            System.out.println(x + " is a multiple of " + y);
        } else {
            System.out.println(x + " is not a multiple of " + y);
        }
    }
    
}

/**
 * A program that rolls three dice whose largest value is specified by the user via args
 */
public class ThreeDice {
    
    
    public static void main(String[] args) { 
        // max values taken from the user
        int max1, max2, max3;
        max1 = Integer.parseInt(args[0]);
        max2 = Integer.parseInt(args[1]);
        max3 = Integer.parseInt(args[2]);
        // three dice rolls
        int roll1 = (int)(Math.random()*max1 + 1);
        int roll2 = (int)(Math.random()*max2 + 1);
        int roll3 = (int)(Math.random()*max3 + 1);
        // Display result on screen
        System.out.println("The first roll is: " + roll1);
        System.out.println("The second roll is: " + roll2);
        System.out.println("The third roll is: " + roll3);
    }
    
    
}

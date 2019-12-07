/**
 * A program that takes 2 int as input and prints which ever value
 * is the nearest to 10.
 */
public class NearestToTen {
    
    public static void main(String[] args) { 
        // 2 integers as input
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        // using Math.abs to check how far each number if from 10
        int xFromTen = Math.abs(10-x);
        int yFromTen = Math.abs(10-y);
        // checking which one is closer to 10
        if(xFromTen > yFromTen) {
            System.out.println(y + " is the nearest to 10");
        } else if(xFromTen < yFromTen) {
            System.out.println(x + " is the nearest to 10");
        } else {
            System.out.println("The numbers are both equally distant from 10");
        }
    }
    
}

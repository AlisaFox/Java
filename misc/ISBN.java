public class ISBN {
	
	public static void main(String[] args) {
		
		
	
		//Declaring the variable to represent the ISBN number
        int n = Integer.parseInt(args[0]);
        
/* First I will obtain the values of d5, d4, d3 and d2 as I am working with digits and not numbers 
(ie. two, zero, one, one instead of two thousand eleven)   */
int d5 = n/1000;
int d4 = (n-(d5*1000))/100;
int d3 = (n-(d5*1000)-(d4*100))/10;
int d2 = n-(d5*1000)-(d4*100)-(d3*10);

/* Now that I have every needed value other than d1, I will start plugging in the 11 possible digits while using 
modulo to test if they are multiples of eleven or not */

boolean d10 = (d5*5 +d4*4 + d3*3 + d2*2 + 0)%11==0;
	if (d10) {
		System.out.println("0");
	}
boolean d11 = (d5*5 +d4*4 + d3*3 + d2*2 + 1)%11==0;
	if (d11){
		System.out.println("1");
	}
boolean d12 = (d5*5 +d4*4 + d3*3 + d2*2 + 2)%11==0;
	if (d12) {
	System.out.println("2");
}
boolean d13 = (d5*5 +d4*4 + d3*3 + d2*2 + 3)%11==0;
	if (d13) {
	System.out.println("3");
}
boolean d14 = (d5*5 +d4*4 + d3*3 + d2*2 + 4)%11==0;
	if (d14) {
	System.out.println("4");
}
boolean d15 = (d5*5 +d4*4 + d3*3 + d2*2 + 5)%11==0;
	if (d15) {
	System.out.println("5");
}
boolean d16 = (d5*5 +d4*4 + d3*3 + d2*2 + 6)%11==0;
	if (d16) {
	System.out.println("6");
}
boolean d17 = (d5*5 +d4*4 + d3*3 + d2*2 + 7)%11==0;
	if (d17) {
	System.out.println("7");
}
boolean d18 = (d5*5 +d4*4 + d3*3 + d2*2 + 8)%11==0;
	if (d18) {
	System.out.println("8");
}
boolean d19 = (d5*5 +d4*4 + d3*3 + d2*2 + 9)%11==0;
	if (d19) {
	System.out.println("9");
}
boolean d1x = (d5*5 +d4*4 + d3*3 + d2*2 + 10)%11==0;
	if (d1x) {
	System.out.println("X");
}
	}}  
       
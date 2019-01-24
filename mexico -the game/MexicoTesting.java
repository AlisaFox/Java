import java.util.Scanner;

public class MexicoTesting {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double b;
		double p;
		System.out.println("Please enter the buy in value");
		b = scanner.nextDouble();
		System.out.println("Please enter the base bet value");
		p = scanner.nextDouble();
		playMexico(b,p);
		scanner.close();
	}
	public static int diceRoll() {
		int x = (int) (Math.random()*6+1);
		/* I generated a random integer between 1 and 6
		to represent the dice sides. I also made sure that the 
		result would be an integer, as the Math.random() function
		results in doubles*/
		return(x);
	}
	public static int getScore(int x,int y) {
		//this method takes two Dice roll results, and finds the score
		if (x>=y) {
			//If the first toll was more or equal to the second one. int m stands for more
			int m = (x*10+y);
			return m;
		} else {
			//If the first roll was less than the second one, switch spots. int l stands for less
			int l = (y*10+x);
			return l;
		}
	}
	public static void playOneRound(int G, int D) {
		//this method takes two getScore results as input
		// I will first determine the numbers for Giulia
		//The two rolls (I realize that this is not the most elegant way of doing this, but it is the fastest)
		System.out.println("Giulia rolled:"+" "+(G/10)+" "+(G%10));
		//and the score
		System.out.println("Giulia's score is: "+G);
		//same thing for David
		System.out.println("David rolled:"+" "+(D/10)+" "+(D%10));
		System.out.println("David's score is: "+D);
		}
	public static String getWinner(int G, int D) {
		//time to determine the winner of each round by comparing the scores
		//Check for tie, 
		if (G==D) {
			return "tie";
		}else {
			//check for Mexico
			if (G==21||G==12){
				return "Giulia";
			}else {
				if(D==21||D==12) {
					return "David";
				}else {
					//check for doubles
					if(G==11||G==22||G==33||G==44||G==55||G==66){
						return "Giulia";
					}else{
						if (D==11||D==22||D==33||D==44||D==55||D==66){ 
							return "David";
						}else {
							//if it is none of the above, check which number is bigger
							if (G>D){
								return "Giulia";
							}else {
								return "David";
								}	
							}
						}
					}
				}
			}
				
		}	
	public static boolean canPlay(double b, double p) {
		//These are the conditions that must be satisfied to play the game, so I used a boolean method
		//The numbers can't be negative, the buy in (b) must be larger, and the pay (p) must be a multiple of b
		if(b>0 && p>0 && b>=p && b%p==0) {
			return true;
		}else {
			return false;
		}
	}
	public static void playMexico(double b, double p){		
		
		if(canPlay(b,p)==false) {
			//first, check if the game can even be played through boolean method canPlay()
			System.out.println("Insufficient funds. The game cannot be played");
		}else {
				//if it worked out, start assigning needed variables
				//each player starts off with the buy in
				double David = b;
				double Giulia = b;
				//int r represents the round #
				int r = 1;
				//start a loop that plays round by round until one of the players reaches 0 as their buy in decreases
			
			while(Giulia>0 && David>0) {
				//first print the round number
				if(r!=1){
					//this is just here for formating, so after round 1, there is a line printed before
					System.out.println(" ");
				}
				System.out.println("Round " + r);
				//formatting again, so it doesn't look clumped. I didn't know how to add a line otherwise
				System.out.println(" ");
				//Round increases by one each time
				r++;
				
				//int G is Guilia's score
				int G= getScore(diceRoll(),diceRoll());
				//int D is David's scpre
				int D = getScore(diceRoll(),diceRoll());
				playOneRound(G,D);	
				if(getWinner(G,D)=="tie") {
					//in case of a tie, nothing happens to the amount of money the layers have
					System.out.println("It's a tie. Roll again!");
				}else if (getWinner(G,D)=="Giulia") {
						//If Giulia wins, the amount of money David has decreases
						David=David-p;
						//The variable David has a new value, while still having the same name
						System.out.println("Giulia wins this round");
							if(David==0) {
								//If David looses all his money after a round when Giulia wins, he looses the game
								//Line 122 is there for formating, so that there is a space before declaring a winner
								System.out.println(" ");
								System.out.println("Giulia won the game!");
							}		
					}else if(getWinner(G,D)=="David") {
							//same thing here, if David wins, Giulia looses money
							Giulia=Giulia-p;
							System.out.println("David wins this round");
								}if(Giulia==0){
									//If the variable Giulia reaches 0, she looses the game
									System.out.println(" ");
									System.out.println("David won the game!");
								}
				
				}
				}
		}
	}

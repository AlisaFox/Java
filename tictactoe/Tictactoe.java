import java.util.Arrays;
import java.util.Scanner;
//Alisa Gagina Assignment 3 260770497

public class Tictactoe {
	public static char[][] createBoard(int n){
		// made an square multidimensional array
		char[][] board = new char [n][n];
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				// have to make sure that it is filled with spaces, and not just void
				board [i][j]= (char)' ';
			}
		}
		return board;
	}
	public static void displayBoard(char[][] board) {
		// int n is a limiter for the loop
		int n = board.length;
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				//print the top of the border
				System.out.print("+-");
				}
			// once the loop is done, need an additional + to close it, and then begin at a new line
			System.out.print("+"+"\n");
			for (int k = 0; k<n; k++) {
				//print a divider and then the character in the array
				System.out.print("|"+board[i][k]);
				}
			// same thing as before, need an additional | to close the board, and begin at the next line
			System.out.print("|"+"\n");
		}
		//that took care of the top and the insides, now we only need to print the bottom of the board
		for (int j=0; j<n; j++) {
			System.out.print("+-");
		}
		System.out.print("+"+"\n");
	}
	public static void writeOnBoard(char [][] board, char c, int x, int y) {
		// again, int n is the limiter
		int n = board.length;
		// the user move can not be outside the limits or negative
		// note, you start counting from 0 when talking about a slot on the board
		if (x>=n || x<0 || y >=n || y<0) {
			throw new IllegalArgumentException("There is no such space on the board. Please make sure that you are counting from zero");		
		} else if(board[x][y]!=' '){
			// the spot can also be already taken (x or o instead of a space)
			throw new IllegalArgumentException("This spot is already taken, please pick another");
		}else{
			// otherwise, you're good to go, you assign that value to the slot
			board[x][y]= c;
		}
		//and show the newly updated board
		displayBoard(board);
	}	
	public static void getUserMove(char [][] board) {
		Scanner scanner = new Scanner(System.in);
		//int x is the row, y is the column. (Or which array and which position in the array)
		int x;
		int y;
		// ask for the move using scanner. MAKE SURE THAT THE INPUT IS SEPARATED BY A SPACE
		//so 2 2 instead of 22, 0 1 instead of 01, etc
		System.out.println("Please enter your move:");
		x= scanner.nextInt();
		y= scanner.nextInt();
		try {
			//try writing on the board, if the method does not agree, catch the exception
			writeOnBoard(board, 'x', x, y);
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid move. Please enter a different move");
			x= scanner.nextInt();
			y= scanner.nextInt();
			writeOnBoard(board, 'x', x, y);
		}
		//scanner.close();
		//the user move should be printed on the board if all goes well
	}
	public static boolean checkForObviousMove(char [][] board) {
		//I have made three helper methods, check them out first, they are right below this one
		//If there is an obvious move, they return the place where the move should be. 
		//Otherwise, they return {-1, -1}, which is not a valid space on the board
		//No obvious move is "bad", and I made such an array for comparison
		int [] bad = {-1, -1};
		//basically, if all the methods return {-1, -1} for both the AI and the player, return false (no obvious move)
		if(Arrays.equals(checkRows(board, 'x'), bad) && Arrays.equals(checkColumns(board, 'x'), bad) &&Arrays.equals(checkDiagonals(board, 'x'), bad) && Arrays.equals(checkRows(board, 'o'), bad) && Arrays.equals(checkColumns(board, 'o'), bad) &&Arrays.equals(checkDiagonals(board, 'o'), bad)) {
			return false;
		}else {
			// yay, there is an obvious move, now you just have to find it
			//check the diagonals for obvious moves for the AI
			if(!Arrays.equals(checkDiagonals(board, 'x'), bad)) {
				//if checkDiagonals for o is not "bad", write the obvious move on the board
				int r = checkDiagonals(board, 'x')[0];
				int c = checkDiagonals(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				//and return that yes, an obvious move exists and has been made
				return true;
			}
			//Now check the columns for the AI
			if(!Arrays.equals(checkColumns(board, 'x'), bad)) {
				int r = checkColumns(board, 'x')[0];
				int c = checkColumns(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				return true;
			}
			//Rows for the AI
			if(!Arrays.equals(checkRows(board, 'x'), bad)) {
				int r = checkRows(board, 'x')[0];
				int c = checkRows(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				return true;
			}
			//For the AI, it is more important to win, so check if it can move before worrying about preventing the player from winning
			//Diagonals for the player (if the player is about to win, prevent them!)
			if(!Arrays.equals(checkDiagonals(board, 'o'), bad)){
					int r = checkDiagonals(board, 'o')[0];
					int c = checkDiagonals(board, 'o')[1];
					writeOnBoard(board, 'o', r, c);
					return true;
				}
			//Columns for the player
			if(!Arrays.equals(checkColumns(board, 'o'), bad)){			
					int r = checkColumns(board, 'o')[0];
					int c = checkColumns(board, 'o')[1];
					writeOnBoard(board, 'o', r, c);
					return true;
				}
			//Rows for the player
			if(!Arrays.equals(checkRows(board, 'o'), bad)) {
					int r = checkRows(board, 'o')[0];
					int c = checkRows(board, 'o')[1];
					writeOnBoard(board, 'o', r, c);
					return true;
				}
			}
		return true;
	}
	
 		public static int[] checkRows(char [][] board, char against) {	
 			//takes the board as the input, and the opponent's sign (x or o) as inputs
			//count the spaces
 			int counter = 0;
 			//and the opponent's moves
			int opponent = 0;
			// these two integers represent the row and the column where the obvious move should be made
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j< board.length; j++) {
					if (board[i][j]==' '){
						//if it a space, that's good, and it could be the location of the obvious move, so set the r and c
						counter++;
						r = i;
						c = j;
					} if (board[i][j]==against){
						//if there is an opponent's sign in the row, you can't win there
						opponent++;
					}if (counter > 1 || opponent > 0) {
						continue;
						//if there are more than one spaces or if there is an opponent's sign, skip this iteration of the loop
					} if (counter == 1 && j == board.length-1 && opponent == 0) {
						// for an obvious move, by the end of the j loop, there must be only one space and no opponenet's signs
						int[] spot = {r, c};
						// return its location
						return spot;			
					}
				}
			//reset the counter and the opponent for each new row
			counter = 0;
			opponent = 0;
			}
			//if there is no obvious row, return a non-existent spot, which represents the fact that there no good move
			//this method must return something, so my strategy is to fill the array with an already predetermined "bad" numbers
			r = -1;
			c = -1;
			int[] spot = {r, c};
			return spot;
		}
		public static int[] checkColumns(char [][] board, char against) {
			//exactly same as before, but this time you check columns
			int counter = 0;
			int opponent = 0;
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j< board.length; j++) {
					//this is the only difference in this method, you have to switch j and i 
					// so it goes 0 0 --> 1 0 --> 2 0 --> etc
					if (board[j][i]==' '){
						counter++;
						r = j;
						c = i;
					} if (board[j][i]==against){
						opponent++;
					}if (counter > 1 || opponent > 0) {
						continue;				
					} if (counter == 1 && j == board.length-1 && opponent == 0) {
						//once again, these are the conditions for the obvious move, at the end of the j loop
						int[] spot = {r, c};
						return spot;
					}
				}
			// reset for each new column
			counter = 0;
			opponent = 0;
			}
			// if no obvious move return {-1, -1}
			r = -1;
			c = -1;
			int[] spot = {r, c};
			return spot;
		}
		public static int[] checkDiagonals(char [][] board, char against) {
			//this is the helper method for checking diagonals, it is a bit more complicated than the other two
			int counter = 0;
			int opponent = 0;
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
				//first you check for a diagonal going from top left to bottom right
				// so 0 0-->1 1 --> 2 2 --> etc
					if (board[i][i]==' '){
						counter++;
						r = i;
						c = i;
					} if (board[i][i]==against){
						opponent++;
					}if (counter > 1 || opponent > 0) {
						continue;				
					} if (counter == 1 && i == board.length-1 && opponent == 0) {
						int [] spot = {r, c};
						return spot;				
					}
				}
			//reset for the other diagonal
			counter = 0;
			opponent = 0;
			for(int j=0; j<board.length; j++) {
				//this diagonal is going from top right to bottom left
				// it was a bit more complicated, let's take a 3x3 board for example
				//it has to go 0 2 --> 1 1 --> 2 0 without going out of bounds
				if (board[j][board.length-1-j]==' '){
					counter++;
					r = j;
					c = board.length-1-j;
				} if (board[j][board.length-1-j]==against){
					opponent++;
				}if (counter > 1 || opponent > 0) {
					continue;		
				} if (counter == 1 && j == board.length-1 && opponent == 0) {
					//same story as before, fulfill these conditions to return an actual slot instead of a "bad" / nonexistent move
					int [] spot = {r, c};
					return spot;				
				}
			}
			r = -1;
			c = -1;
			int [] spot = {r, c};
			return spot;
			}
	public static void getAIMove(char [][] board){	
		// the AI either has an obvious move or not
		// so we implement checkForObviousMove method
		if(checkForObviousMove(board)==false) {
			//if there was no such move, we need to make a random move
			// create place-holders for row and column
			int r = 0;
			int c = 0;
			while (board[r][c]!=' ') {
				//make a loop that keeps creating random move locations if it is already taken (must be occupied by a space to be implemented)
				// don't want a double, so have to cast the math.random method
				//also don't want it to be only 0, so increase the range
				r = (int) (Math.random()*board.length);
				c = (int) (Math.random()*board.length);
			}
			//if the condition of the while loop has been made, write the AI's move on the board
			writeOnBoard(board, 'o', r, c);
		}
	}	
	public static char checkForWinner(char [][] board) {
		//create two separate counters for the player (x) and the AI (o)
		int counterx = 0;
		int countero = 0;
		//this is basically the same thing as the checkRows method, but without the space requirement
		for(int i=0; i<board.length; i++) {
			for(int j=0; j< board.length; j++) {
				//check for x's in the same row
				if(board[i][j]=='x') {
					counterx++;
					if(counterx == board.length) {
						//so if the board is 3x3, and there are 3 x's, that means that the player won
						return 'x';	
					}				
				}
				//check for o's in the same row
				if(board[i][j]=='o') {
					countero++;
					if(countero == board.length) {
						// if this condition is met, the AI won
						return 'o';					
					}
				}
			}
		//reset the counter 
		counterx = 0;
		countero = 0;
		}
		//check the columns now
		//again, similar, to the checkColumns method
		for(int i=0; i<board.length; i++) {
			for(int j=0; j< board.length; j++) {
				//check for x's in the same column
				if (board[j][i]=='x'){
					counterx++;
					if(counterx == board.length) {
						return 'x';	
					}				
				}
				if(board[j][i]=='o') {
					//check for o's
					countero++;
					if(countero == board.length) {
						return 'o';					
					}
				}
			}
		counterx = 0;
		countero = 0;
		//reset again
		}
		//check the diagonals now
		//top right to bottom left diagonal
		for(int i=0; i<board.length; i++) {
			if (board[i][i]=='x'){
				
				counterx++;
				if(counterx == board.length) {
					return 'x';
				} 					
			}
			if(board[i][i]=='o') {
				countero++;
				if(countero == board.length) {
					return 'o';					
				}
			}
		}
		counterx = 0;
		countero = 0;
		//Check the top left to bottom right diagonal
		for(int j=0; j<board.length; j++) {
			if (board[j][board.length-1-j]=='x'){
				counterx++;
				if(counterx == board.length) {
					return 'x';					
				}
			}
			if(board[j][board.length-1-j]=='o') {
				countero++;
				if(countero == board.length) {
					return 'o';		
				}			
			}
		}
		return ' ';
	}
	public static void play() {
		//Welcome to the final method! So first make sure that you can the scanner system
		Scanner scanner = new Scanner(System.in);
		//get the user name
		System.out.println("Please enter your name");
			String name = scanner.next();
			System.out.println("Welcome, "+name+ "! Are you ready to play?");
		// next, get the dimensions of the board
		System.out.println("Please choose the dimensions of your board");
			//To catch any improper input, I need to set up a loop. I will first create dummy variables for future access outside the loop
			int size = 0;
			String input = "meow";
			boolean notAnInt = true;
			while(notAnInt == true){
			     input = scanner.next();
			     //the scanner accepts everything, not just integers
			     try{
			    	 //need a temp integer to store the values
			         int copy = Integer.parseInt(input);
			         // but here, if it can not be converted to an int, the catch activates
			         if (copy < 1) {
			        	 //also, since the integer can't be negative, I set up an if statement that acts like another catch
			        	 System.out.println("Really? A POSITIVE integer please");
			        	 continue;
			         }
			         //if the input is a proper int, change the boolean so the loop can be broken
			         notAnInt = false;
			         // and copy the copy so it can be used outside the loop
			         size = copy;
			     }catch(Exception e){
			         System.out.println("Please input a proper positive integer");
			     }
			}
			// and finally, we can create the board
			char[][] board = createBoard(size);
		//next up, the coin toss
		// once, again, use math random to either get 0 or 1
		// if 0, the player goes first, if 1, the AI goes first
		int n = (int) (Math.random()*2);
		if(n==0) {
			//so if you go first
			System.out.println("The result of the coin toss is 0");
			System.out.println("You have the first move");
			//since the game could be a tie, set up a counter
			//the counter represents the maximum moves (size of board x size of board)
			int counter = 0;
			while (checkForWinner(board)== ' ') {
				//loop runs while there is no winner
				getUserMove(board);
					counter ++;
					//after each move, the counter goes up
					//if at any time there is a winner, stop the loop, and announce the winner
						if(checkForWinner(board) == 'x') {
							//check if the player won
							System.out.println("CONGRATS!");
							System.out.println("You won!");
							break;
						}
						if(checkForWinner(board) == 'o') {
							//check if the AI won
							System.out.println("GAME OVER!");
							System.out.println("You lost");
							break;
						}
						if(counter >= (size*size)) {
							//check if all the spaces on the board are filled up, if yes, the game is a tie
							System.out.println("GAME OVER!");
							System.out.println("It's a tie!");
							break;
						}
				//same process once again, but this time after the AI's move
				System.out.println("The AI made its move:");
				getAIMove(board);
					counter ++;
						if(checkForWinner(board) == 'x') {
							System.out.println("CONGRATS!");
							System.out.println("You won!");
							break;
						}
						if(checkForWinner(board) == 'o') {
							System.out.println("GAME OVER!");
							System.out.println("You lost");
							break;
						}
						if(counter >= (size*size)) {
							System.out.println("GAME OVER!");
							System.out.println("It's a tie!");
							break;
						}
				}
		}
		//or the coin toss can go the other way, and the AI goes first
		else {
			System.out.println("The result of the coin toss is 1");
			System.out.println("The AI has the first move");
			int counter = 0;
			while (checkForWinner(board)== ' '){
				System.out.println("The AI made its move:");
				getAIMove(board);
				counter ++;
				//again, after every move, raise the counter and check for wins / a tie
					if(checkForWinner(board) == 'x') {
						System.out.println("CONGRATS!");
						System.out.println("You won!");
						break;
					}
					if(checkForWinner(board) == 'o') {
						System.out.println("GAME OVER!");
						System.out.println("You lost");
						break;
					}
					if(counter >= (size*size)) {
						System.out.println("GAME OVER!");
						System.out.println("It's a tie!");
						break;
					}
				getUserMove(board);
				counter ++;
					if(checkForWinner(board) == 'x') {
						System.out.println("CONGRATS!");
						System.out.println("You won!");
						break;
					}
					if(checkForWinner(board) == 'o') {
						System.out.println("GAME OVER!");
						System.out.println("You lost");
						break;
					}
					if(counter >= (size*size)) {
						System.out.println("GAME OVER!");
						System.out.println("It's a tie!");
						break;
					}
			}
		}
		scanner.close();
	}
	public static void main(String[] args) {
		//The play method is basically the main method 
		play();		
	}
}


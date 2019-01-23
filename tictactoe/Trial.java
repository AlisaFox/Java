import java.util.Arrays;
import java.util.Scanner;

public class Trial {
	public static char[][] createBoard(int n){
		char[][] board = new char [n][n];
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				board [i][j]= (char)' ';
			}
		}
		return board;
	}
	public static void displayBoard(char[][] board) {
		int n = board.length;
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print("+-");
				}
			System.out.print("+"+"\n");
			for (int k = 0; k<n; k++) {
				System.out.print("|"+board[i][k]);
				}
			System.out.print("|"+"\n");
		}
		for (int j=0; j<n; j++) {
			System.out.print("+-");
		}
		System.out.print("+"+"\n");
	}
	public static void writeOnBoard(char [][] board, char c, int x, int y) {
		int n = board.length;
		if (x>=n || x<0 || y >=n || y<0) {
			throw new IllegalArgumentException("There is no such space on the board. Please make sure that you are counting from zero");		
		} else if(board[x][y]!=' '){
			throw new IllegalArgumentException("This spot is already taken, please pick another");
		}else{
			board[x][y]= c;
		}
		displayBoard(board);
	}	
	public static void getUserMove(char [][] board) {
		Scanner user = new Scanner(System.in);
		int x;
		int y;
		System.out.println("Please enter your move:");
		x= user.nextInt();
		y= user.nextInt();
		try {
			writeOnBoard(board, 'x', x, y);
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid move. Please enter a different move");
			x= user.nextInt();
			y= user.nextInt();
			writeOnBoard(board, 'x', x, y);
		}
		user.close();
	}
	public static boolean checkForObviousMove(char [][] board) {
		int [] bad = {-1, -1};
		if(Arrays.equals(checkRows(board, 'x'), bad) && Arrays.equals(checkCollumns(board, 'x'), bad) &&Arrays.equals(checkDiagonals(board, 'x'), bad) && Arrays.equals(checkRows(board, 'o'), bad) && Arrays.equals(checkCollumns(board, 'o'), bad) &&Arrays.equals(checkDiagonals(board, 'o'), bad)) {
			return false;
		}else {
			if(!Arrays.equals(checkDiagonals(board, 'x'), bad)) {
				int r = checkDiagonals(board, 'x')[0];
				int c = checkDiagonals(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				return true;
			}
			if(!Arrays.equals(checkCollumns(board, 'x'), bad)) {
				int r = checkCollumns(board, 'x')[0];
				int c = checkCollumns(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				return true;
			}
			if(!Arrays.equals(checkRows(board, 'x'), bad)) {
				int r = checkRows(board, 'x')[0];
				int c = checkRows(board, 'x')[1];
				writeOnBoard(board, 'o', r, c);
				return true;
			}
			if(!Arrays.equals(checkDiagonals(board, 'o'), bad)){
					int r = checkDiagonals(board, 'o')[0];
					int c = checkDiagonals(board, 'o')[1];
					writeOnBoard(board, 'o', r, c);
					return true;
				}
			if(!Arrays.equals(checkCollumns(board, 'o'), bad)){			
					int r = checkCollumns(board, 'o')[0];
					int c = checkCollumns(board, 'o')[1];
					writeOnBoard(board, 'o', r, c);
					return true;
				}
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
			int counter = 0;
			int opponent = 0;
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j< board.length; j++) {
					if (board[i][j]==' '){
						counter++;
						r = i;
						c = j;
					} if (board[i][j]==against){
						opponent++;
					}if (counter > 1 || opponent > 0) {
						continue;				
					} if (counter == 1 && j == board.length-1 && opponent == 0) {
						int[] spot = {r, c};
						return spot;			
					}
				}
			counter = 0;
			opponent = 0;
			}
			r = -1;
			c = -1;
			int[] spot = {r, c};
			return spot;
		}
		public static int[] checkCollumns(char [][] board, char against) {
			int counter = 0;
			int opponent = 0;
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
				for(int j=0; j< board.length; j++) {
					if (board[j][i]==' '){
						counter++;
						r = j;
						c = i;
					} if (board[j][i]==against){
						opponent++;
					}if (counter > 1 || opponent > 0) {
						continue;				
					} if (counter == 1 && j == board.length-1 && opponent == 0) {
						int[] spot = {r, c};
						return spot;
					}
				}
			counter = 0;
			opponent = 0;
			}
			r = -1;
			c = -1;
			int[] spot = {r, c};
			return spot;
		}
		public static int[] checkDiagonals(char [][] board, char against) {
			int counter = 0;
			int opponent = 0;
			int r = 0;
			int c = 0;
			for(int i=0; i<board.length; i++) {
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
			counter = 0;
			opponent = 0;
			for(int j=0; j<board.length; j++) {
				if (board[j][board.length-1-j]==' '){
					counter++;
					r = j;
					c = board.length-1-j;
				} if (board[j][board.length-1-j]==against){
					opponent++;
				}if (counter > 1 || opponent > 0) {
					continue;		
				} if (counter == 1 && j == board.length-1 && opponent == 0) {
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
		if(checkForObviousMove(board)==false) {
			int r = 0;
			int c = 0;
			while (board[r][c]!=' ') {
				r = (int) (Math.random()*board.length);
				c = (int) (Math.random()*board.length);
			}
			writeOnBoard(board, 'o', r, c);
		}
	}	
	public static char checkForWinner(char [][] board) {
		int counterx = 0;
		int countero = 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j< board.length; j++) {
				if(board[i][j]=='x') {
					counterx++;
					if(counterx == board.length) {
						return 'x';	
					}				
				}
				if(board[i][j]=='o') {
					countero++;
					if(countero == board.length) {
						return 'o';					
					}
				}
			}
		counterx = 0;
		countero = 0;
		}
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j< board.length; j++) {
				if (board[j][i]=='x'){
					counterx++;
					if(counterx == board.length) {
						return 'x';	
					}				
				}
				if(board[j][i]=='o') {
					countero++;
					if(countero == board.length) {
						return 'o';					
					}
				}
			}
		counterx = 0;
		countero = 0;
		}
		
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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your name");
			String name = scanner.next();
			System.out.println("Welcome, "+name+ "! Are you ready to play?");
		System.out.println("Please choose the dimensions of your board");
			int size = 0;
			String input = "a";
			boolean notAnInt = true;
			while(notAnInt == true){
			     input = scanner.next();
			     try{
			         int copy = Integer.parseInt(input);
			         if (copy < 1) {
			        	 System.out.println("Really? A POSITIVE integer please");
			        	 continue;
			         }
			         notAnInt = false;
			         size = copy;
			     }catch(Exception e){
			         System.out.println("Please input a proper positive integer");
			     }
			}
			char[][] board = createBoard(size);
		int n = (int) (Math.random()*2);
		if(n==0) {
			System.out.println("The result of the coin toss is 0");
			System.out.println("You have the first move");
			int counter = 0;
			while (checkForWinner(board)== ' ') {
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
		}else {
			System.out.println("The result of the coin toss is 1");
			System.out.println("The AI has the first move");
			int counter = 0;
			while (checkForWinner(board)== ' '){
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
		play();		
	}
}


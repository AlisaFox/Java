package assignment1;

public class Message {
	
	public String message;
	public int lengthOfMessage;

	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}
	
	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}
	
	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){
		//INSERT YOUR CODE HERE
		
		//I will be using the ascii table to represent the characters in the message
		int ascii = 0;
		//I will also have a counter to count all the non-letters to avoid going out of bounds
		int counter = 0;
		//a character array to store the new and improved message
		char [] newm = new char [lengthOfMessage]; 
		
		for(int i = 0; i< lengthOfMessage; i++) {
			ascii = (int) message.charAt(i);
			//if it's a lower case letter, just store it
			if(ascii >= 97 && ascii <= 122) {
				char c = (char) ascii;
				newm [i-counter] = c;
			}
			//for a capital letter, convert it to lower case, store it
			  else if (ascii >= 65 && ascii <= 90) {
					ascii += 32;
					char c = (char) ascii;
					newm [i-counter] = c;
					}
				//otherwise, don't add to the array and just skip
				else {
					counter++;
					continue;					
					}
		}
		
		//now that we have the final count of unneeded blanks, resize the array to get rid of black spaces 
		char [] newm1 = new char [newm.length-counter];
		for (int j = 0; j < newm1.length; j++) {
			newm1[j]=newm[j];
		}
		//convert back into a String, make it the message
		String m = new String(newm1);
		this.message = m;
		this.lengthOfMessage = m.length();
	}
	
	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}
	
	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}
	
	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
	
		//I will again use the ascii table to change the characters
		int ascii = 0;
		//cut down too big keys to stay within the alphabet
		int keySmall = key%26;
		//again, make a character array to store the shifted message
		char [] shiftm = new char[lengthOfMessage];
		
		for (int i =0; i< lengthOfMessage; i++) {
			if (message.charAt(i)+keySmall <=122 && message.charAt(i)+keySmall >=97) {
				ascii = message.charAt(i)+keySmall;
			}
			//to avoid going past 'z', cycle back and start at a
				else if (key>0) {
					//the dif is to go all the way to the z, instead of starting at a right away
					int dif = 123 - message.charAt(i);
					ascii = 97 + keySmall - dif;
				} 
					//if the key is negative, use the exact same strategy to avoid going before 'a'
					 else if (key<0){
						int dif = message.charAt(i) - 97;
						ascii = 123 +keySmall + dif;						
					}
			//fill the array with the shifted char
			shiftm[i] = (char) ascii;
			}
		
		String m = new String (shiftm);
		this.message = m;
		this.lengthOfMessage = m.length();
	}
	
	public void caesarDecipher(int key){
		this.caesarCipher(- key);
	}
	
	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * - computes how often each letter appear in the message
	 * - computes a shift (key) such that the letter that happens the most was originally an 'e'
	 * - deciphers the message using the key you have just computed
	 */
	public void caesarAnalysis(){
		// INSERT YOUR CODE HERE
		//Fist, compare letters to each other. Need some variables outside of the loop to keep track 
		int counter= 0;
		int record = 0;
		char recordHolder = ' ';
		
		//Basically, take each character and see how often it appears in the code. If it appears the most, it is now the recordHolder 
		for (int i = 0; i < lengthOfMessage; i++) {
			counter = 0;
			char c = message.charAt(i);
			for (int j = 0; j < lengthOfMessage; j++){
				if (message.charAt(j)==c){
					counter++;
				}
			}
			if (counter> record) {
				record = counter;
				recordHolder=c;
			}
		}
		
		//moving onto finding the key, by looking for the distance between the letter and e
		int key;
		int location = (int) recordHolder;
		//ascii of e is 101
		if(location> 101) {
			key = location - 101;
		}else if (location < 101) {
			key = 101 + 97 - location;
		}else {
			key = 0;
		}
		
		//and finally decipher the message, yay!
		caesarDecipher(key);		
	}
	
	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
		
		int ascii = 0;
		char[] newm = new char[lengthOfMessage];
		
		for (int i = 0; i <lengthOfMessage; i++) {
			//to avoid issues with too small keys, use modulo to avoid going out of bounds 
			int keyLocation = i % key.length;			
			//and again, make sure that the key is not bigger than 26 (smaller than -26)
			int keySmall= key[keyLocation]%26;
			
			//exact same shifting as with caesarCypher, just with individual keys this time
			if (message.charAt(i)+keySmall <=122 && message.charAt(i)+keySmall >=97) {
				ascii = message.charAt(i)+keySmall;
				}else if (key[i]>0) {
					int dif = 123 - message.charAt(i);
					ascii = 97 + keySmall - dif;
					} else if (key[i]<0){
						int dif = message.charAt(i) - 97;
						ascii = 123 +keySmall + dif;				
			}
			newm[i] = (char) ascii;
		}	
		String m = new String (newm);
		this.message = m;
		this.lengthOfMessage = m.length();
	}

	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		
		//since this is an array of ints, can't just say "vigenereCipher(-key)", have to make the values negative manually
		int [] newKey = new int [key.length];
		for (int i = 0; i < key.length; i++) {
			newKey[i] = - key[i];
		}
		vigenereCipher(newKey);
	}
	
	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	public void transpositionCipher (int key){
		// INSERT YOUR CODE HERE
		//first find array size (aka the amount of rows, key being amount of columns)
		int size = 0;
		if (lengthOfMessage % key == 0) {
			size = lengthOfMessage / key;
		}
		else {
			size = (lengthOfMessage / key)+1;
		}
		
		//and make the 2d array
		char [][] table = new char [size][key];
		
		//fill the table
		int counter = 0;
		for(int i = 0; i<size; i++) {
			for(int j = 0; j < key; j++) {
				//at the end of the array, if there is still space left, put '*' instead of leaving it blank and avoid increasing the counter
				if (counter>=lengthOfMessage) {
					table[i][j] = '*';
					continue;
				}
				table [i][j]= message.charAt(counter);
				counter++;
			}
		}
		
		//now cipher it. Make a new table, then right away put values into the new message single array
		char [][] shiftm = new char [key][size];
		char [] newm = new char [key*size];
		int spot = 0;
		
		for(int i = 0; i<key; i++) {
			for(int j = 0; j < size; j++) {
				//If something was in the location [2,3] (2nd row, 3rd column), it will now be in location [3,2] (3rd row, 2nd column)
				//You would then read it as a normal table (row by row, instead of column by column)
				shiftm [i][j] = table [j][i];
				newm [spot] = shiftm [i][j];
				spot++;
			}
		}
		
		String m = new String(newm);	
		this.message = m;
		this.lengthOfMessage = m.length();
	}
	
	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		// INSERT YOUR CODE HERE
		//reverse the order of the operations
		int size = lengthOfMessage / key;
		char [][] table = new char [key][size];
		int counter = 0;
		//since lengthOfMessage is not actually the length of the original message, need to shorten it later 
		int starCounter = 0;
		
		//fill the table and count the '*'s
		for(int i = 0; i<key; i++) {
			for(int j = 0; j < size; j++) {
				table [i][j]= message.charAt(counter);
				if (message.charAt(counter)=='*') {
					starCounter++;
				}
				counter++;
			}
		}
		
		//now decipher it, and right away put away the message into a 1d character array		
		char [][] shiftm = new char [size][key];
		char [] newm = new char [lengthOfMessage - starCounter];
		int spot = 0;
		
		for(int i = 0; i<size; i++) {
			for(int j = 0; j < key; j++) {				
				shiftm [i][j] = table [j][i];
				if(table[j][i]=='*') {
					continue;
				}	
				//do not include '*'s into the new message			
				newm [spot] = shiftm [i][j];
				spot++;
			}
		}
		
		String m = new String(newm);	
		this.message = m;
		this.lengthOfMessage = m.length();
	}	
}

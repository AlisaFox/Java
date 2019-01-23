import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Tweet {
	
	//attributes
	private String userAccount;
	private String date;
	private String time;
	private String message;
	public static HashSet<String> stopWords;
	
	
	//methods
	public Tweet(String user, String date, String time, String msg) {
		//this is the constructor
		this.userAccount = user;
		this.date = date;
		this.time = time;
		this.message = msg;	
	}
	
	public boolean checkMessage() {
		//make an counter to keep track of all words
		//splitHelper is a helper method below, it returns a string array of words 
		int allWords = (splitHelper(this.message)).length;
		//precaution for if the stopWords file was not loaded (method below not activated)
		if (stopWords==null) {
			throw new NullPointerException("Error checking the stopWords database: The file of stopWords has not been loaded yet");			
		}
		//find out how many of the words in the message are stopWords, using a helper method below
		int withoutWords = stopWordFinder(stopWords);
		//minus the amount of stopWords from all words, gives all the non stop words
		int words = allWords - withoutWords;
		
		//now the gist of the method, if the amount of words passes these parameters, it is good
		if(words < 16 && words >0) {
			return true;
		}else {
			return false;
		}		
	}
		private String [] splitHelper(String msg) {
			//helper that splits messages by spaces
			String[] words = msg.split(" ");
			return words;
		}
		private int stopWordFinder(HashSet<String> stopWords) {
			//helper that compares words with stop words and counts how many there are
			String[] words = splitHelper(this.message);
			//get the words and set up the counter
			int counter = 0;
			for(String s:stopWords) {
				//cycle through all the stop words
				for(int i = 0; i < words.length; i++) {
					//cycle through all the msg words
					if(s.equalsIgnoreCase(words[i]) || (s+",").equalsIgnoreCase(words[i]) || (s+".").equalsIgnoreCase(words[i]) || (s+";").equalsIgnoreCase(words[i])|| (s+":").equalsIgnoreCase(words[i])) {
						//also a stop word if it has an attachment like ,
						counter++;
						//if the if method is true, counter increases
					}
				}
			}
			//after all the combinations have been compared, return counter
			return counter;
		}
	
	//self-explanatory get methods
	public String getDate() {
		return this.date;
	}
	public String getTime() {
		return this.time;
	}
	public String getMessage() {
		return this.message;
	}
	public String getUserAccount() {
		return this.userAccount;
	}
	
	//to string method, with a tab between each attribute
	public String toString() {
		String all = this.userAccount + "\t" + this.date + "\t" + this.time + "\t" + this.message;
		return all;
	}
	
	//here it gets fun, welcome to the comparator method (aka what is earlier)
	public boolean isBefore(Tweet t) {
		//I made a helper method before, which returns an int
		// -1 for earlier, 0 for same, +1 for later
		int val = helperisEarlier(t.getDate(), t.getTime());
		//if this date/time is earlier, boolean is true
		if(val == -1) {
			return true;
		}else {
			return false;
		}		
	}
		private int helperisEarlier(String inputDate, String inputTime) {
			// -1 for earlier, 0 for same, +1 for later
			//use the split method to split up the numbers
			String[] tdates = this.date.split("-");
			String[] idates = inputDate.split("-");
			String[] ttimes = this.time.split(":");
			String[] itimes = inputTime.split(":");
			
			int val;
			//compare one by one using helperComparer below
			//also turn strings into an integer from a string
			//compare year, then month, then, day, then hours, then minutes, then seconds
			//if any one of these is earlier or later, return -1 or 1
			val = helperComparer(Integer.parseInt(tdates[0]), Integer.parseInt(idates[0]));
			if(val!=0) {
				return val;
			}else {
				val = helperComparer(Integer.parseInt(tdates[1]), Integer.parseInt(idates[1]));
				if(val!=0) {
					return val;
				}else {
					val = helperComparer(Integer.parseInt(tdates[2]), Integer.parseInt(idates[2]));
					if(val!=0) {
						return val;
					}else {
						val = helperComparer(Integer.parseInt(ttimes[0]), Integer.parseInt(itimes[0]));
						if(val!=0) {
							return val;
						}else {
							val = helperComparer(Integer.parseInt(ttimes[1]), Integer.parseInt(itimes[1]));
							if(val!=0) {
								return val;
							}else {
								val = helperComparer(Integer.parseInt(ttimes[2]), Integer.parseInt(itimes[2]));
								return val;
							}
						}
					}
				}
			}
		}
		
		private int helperComparer(int num1, int num2) {
			//get two numbers, return -1, 0, or 1 depending on which one is smaller
			if (num1 >num2) {
				//bigger than means later
				return 1;
			}else {
				if(num1< num2) {
					//smaller than means earlier
					return -1;
				}else {
					//equals means move onto the next value, or all the values are exactly the same
					return 0;
				}
			}
		}
		
	public static void loadStopWords(String name){
		//load stopWords file
		FileReader fr;
		BufferedReader br;
		String currentLine;
		//initialize hashset
		stopWords = new HashSet<String>();
		try {
			fr = new FileReader (name);
			br = new BufferedReader(fr);
			currentLine = br.readLine();
			while(currentLine != null) {
				stopWords.add(currentLine);
				currentLine = br.readLine();				
			}
			br.close();
			fr.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}	
}

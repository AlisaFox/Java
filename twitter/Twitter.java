import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Twitter {
	//attribute
	private ArrayList <Tweet> tweets;
	
	public Twitter() {
		//constructor, initialize the arraylist
		tweets = new ArrayList<Tweet>();
	}
	public void loadDB(String fileName) {
		//load tweets file
		FileReader fr;
		BufferedReader br;
		String currentLine;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			currentLine = br.readLine();
			while(currentLine != null) {		
				//each line of the file is turned into an array
				String[] values = currentLine.split("	");
				//make a tweet using these values
				Tweet a = new Tweet(values[0], values[1], values[2], values[3]);
				if (a.checkMessage() == true) {
					//if it counts as a message, add it to the big arraylist
					tweets.add(new Tweet(values[0], values[1], values[2], values[3]));
				}
				currentLine = br.readLine();				
			}
			br.close();
			fr.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		//sort Twitter by using method below
		sortTwitter();
	}
	
	public void sortTwitter() {
		//bubble sort
		//for every tweet
		for(int i=1; i<tweets.size(); i++) {
			//compare to other tweets
			for(int j = 1; j< tweets.size(); j++) {
				//set j up as 1 to avoid arrayoutofbounds exception
				Tweet temp= null;
				if(tweets.get(j-1).isBefore(tweets.get(j)) == false) {
					//if tweet is later, switch them around
					temp = tweets.get(j-1);
					tweets.set(j-1, tweets.get(j));
					tweets.set(j, temp);
				}
			}
			
		}
	}
				
	public int getSizeTwitter() {
		int x = tweets.size();
		return x;
	}
	public Tweet getTweet(int index) {
		//get a tweet at the given index
		Tweet x = tweets.get(index);
		return x;
	}
	public String printDB() {
		String every = "";
		//make a big string
		for(int i = 0; i < tweets.size(); i++) {
			//go through tweets, make each tweet a string
			String x = tweets.get(i).toString();
			//add it, and have the next one begin on a new line
			every += x +"\n";
		}
		return every;
	}
	
	public ArrayList <Tweet> rangeTweets(Tweet t1, Tweet t2) {
		//here is another complicated-ish method
		//first, check which tweet is earlier
		boolean which = t1.isBefore(t2);
		//make a new ArrayList
		ArrayList <Tweet> rangedTweets = new ArrayList<Tweet>();
		if (which == true) {
			//when t1 is earlier than t2
			//get the index of the later tweet for the range
			int j = tweets.indexOf(t2);
			for (int i = tweets.indexOf(t1); i <= j; i++) {	
				//set up range, and start adding to the new arraylist
				rangedTweets.add(tweets.get(i));
			}
			return rangedTweets;
		}else { 
			//same thing, but for t2 being earlier than t1
			int j = tweets.indexOf(t1);
			for (int i = tweets.indexOf(t2); i <= j; i++) {
				rangedTweets.add(tweets.get(i));
			}
			return rangedTweets;
		}
	}
	public void saveDB(String fileName) {
		//make a file using printDB
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(printDB());
			bw.close();
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public String trendingTopic() {	
		//use a helper method to get all the messages, since the rest of the attributes of tweets are not needed
		String [] msgs = helperMsg();
		//set up a count, this is for how often a given word appears
		int count = 0;
		//and a string to hold the most appearing word atm
		String biggestWord = null;
		//go through one sentence
		for (int i = 0; i < msgs.length; i++) {
			//helper method below, moved from Tweet.java since we can't make helpers public
			String[] words = splitHelper(msgs[i]);
			//choose a word
			for(int j = 0; j< words.length; j++) {
				if (helperIsStopWord(words[j]) == true) {
					//check that it is not a stop word using a helper method below
					//if it is, skip this iteration
					continue;
				}
				//set up values for the current word
				String currentWord = words[j];
				int value = 0;
				//compare with other msgs
				for(int k = 0; k< msgs.length; k++) {
					if(helperContainsWord(msgs[k], currentWord) == true) {
						//used helper contains word to see if the sentence has it (only has to appear once)
						value ++;
						
					}
				}
				//after cycle, if it occurs more than the previous word, update counters
				if (value > count) {
					count = value;
					biggestWord = currentWord;
				}
			}
		}
		return biggestWord;
	}	
		private boolean helperIsStopWord(String word) {
			//is the given word a stopWord?
			boolean isIt = false;
			for(String s:Tweet.stopWords) {
				if(s.equalsIgnoreCase(word) || (s+",").equalsIgnoreCase(word) || (s+".").equalsIgnoreCase(word) || (s+";").equalsIgnoreCase(word)|| (s+":").equalsIgnoreCase(word)) {	
					isIt = true;
					break;
				}
			}
			return isIt;
			//if isIt is true, the word is a stopword
		}	
		private boolean helperContainsWord(String msg, String word) {
			String[] words = splitHelper(msg);
			//is the given word in the sentence?
			boolean contain = false;
			for(int i = 0; i<words.length; i++) {
				if(words[i].equals(word)) {
					contain = true;
					break;
				}else {
					contain = false;
					continue;
				}
			}
			//true if it is, false if it isnt
			return contain;
		}
		private String [] splitHelper(String msg) {
			//splitter brought over from Tweet.java
			String[] words = msg.split(" ");
			return words;
		}
		private String [] helperMsg () {
			//helper that takes tweets and returns only an array of messages
			String [] msgs = new String [tweets.size()];
			for(int i =0; i< tweets.size(); i++) {
				msgs[i] = tweets.get(i).getMessage();
			}
			return msgs;
		}
	
	

	public static void main(String [] args) {
		//Alisa Gagina 260770497
		Twitter example = new Twitter();
		Tweet.loadStopWords("stopWords.txt");
		example.loadDB("tweets.txt");
	}
}


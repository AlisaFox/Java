# Twitter

## What:
This is a fake "twitter". It takes a file of messages, and can do multiple things. It can bubblesort according to the time of the message. 
Or it can find the most used word, or the hottopic. Or it can check if a message exists at all in a tweet.

## What's happening?
We have `Tweet.java` with attributes
- userAccount;
- date;
- time;
- message;
And then we have  `Twitter.java`, which is an arrayList of tweets. 

There are numerous other functions described clearly in the pdf.

One interesting one includes finding trending topics. We pass in a file of stopwords, which are very common words that we can subtract from the twitter messages. We can use this to find the most occuring unusual word. 



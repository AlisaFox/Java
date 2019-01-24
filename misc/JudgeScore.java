
public class JudgeScore {
	public static void main(String[] args) {
	
	//Declaring the variables for storing the judges scores.
	int judge1, judge2, judge3, judge4;
	judge1 = Integer.valueOf(args[0]);
	judge2 = Integer.valueOf(args[1]);
	judge3 = Integer.valueOf(args[2]);
	judge4 = Integer.valueOf(args[3]);
	
	//First up, I'll find the max (biggest score) and min (lowest score) values by using if-else statements
	int max, min;
	// max first
	if (judge1>=judge2 && judge1>=judge3 && judge1>=judge4) {
		max = judge1;
	}else {
		if (judge2>=judge1 && judge2>=judge3 && judge2>=judge4) {
			max = judge2;
		}else {
			if (judge3>=judge1 && judge3>=judge2 && judge3>=judge4) {
				max = judge3;
			}else {
				max = judge4;
			}
		}
	}
	//Let's find the lowest score next
	if (judge1<=judge2 && judge1<=judge3 && judge1<=judge4) {
		min = judge1;
	}else {
		if (judge2<=judge1 && judge2<=judge3 && judge2<=judge4) {
			min = judge2;
		}else {
			if (judge3<=judge1 && judge3<=judge2 && judge3<=judge4) {
				min = judge3;
			}else {
				min = judge4;
			}
		}
	}
	//Next step is to find the average of the scores without the min and max values
	double score = (((double)judge1+(double)judge2+(double)judge3+(double)judge4-(double)max-(double)min)/2);
	// I used casting to make sure that I would not get an integer result
	//And print out the result
	System.out.println(score);
	
	
}
}




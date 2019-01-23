package assignment2;

public class Warehouse{

	protected Shelf[] storage;
	protected int nbShelves;
	public Box toShip;
	public UrgentBox toShipUrgently;
	static String problem = "problem encountered while performing the operation";
	static String noProblem = "operation was successfully carried out";
	
	public Warehouse(int n, int[] heights, int[] lengths){
		this.nbShelves = n;
		this.storage = new Shelf[n];
		for (int i = 0; i < n; i++){
			this.storage[i]= new Shelf(heights[i], lengths[i]);
		}
		this.toShip = null;
		this.toShipUrgently = null;
	}
	
	public String printShipping(){
		Box b = toShip;
		String result = "not urgent : ";
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n" + "should be already gone : ";
		b = toShipUrgently;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
 	public String print(){
 		String result = "";
		for (int i = 0; i < nbShelves; i++){
			result += i + "-th shelf " + storage[i].print();
		}
		return result;
	}
	
 	public void clear(){
 		toShip = null;
 		toShipUrgently = null;
 		for (int i = 0; i < nbShelves ; i++){
 			storage[i].clear();
 		}
 	}
 	
 	/**
 	 * initiate the merge sort algorithm
 	 */
	public void sort(){
		mergeSort(0, nbShelves -1);
	}
	
	/**
	 * performs the induction step of the merge sort algorithm
	 * @param start
	 * @param end
	 */
	protected void mergeSort(int start, int end){
		 if (start < end){
	            // Find the middle point
	            int m = (start+end)/2;
	            // Sort first and second halves
	            mergeSort(start, m);
	            mergeSort(m+1, end);
	 	        // Merge the sorted halves
	            merge(start, m, end);
	        }
	}
	
	/**
	 * performs the merge part of the merge sort algorithm
	 * @param start
	 * @param mid
	 * @param end
	 */
	protected void merge(int start, int mid, int end){
		//prepare for split into two arrays
		int n1 = mid-start+1;
		int n2 = end - mid;
		Shelf [] L = new Shelf[n1];
		Shelf [] R = new Shelf[n2];
		
		//split
		for (int i = 0; i<n1; i++) {
			L[i]= storage[start+i];
		}
		for (int i = 0; i<n2;i++) {
			R[i]=storage[mid+i+1];
		}
		
		//copy array contents
		int i =0;
		int j= 0;
		int k = start;
	        while (i < n1 && j < n2){
	            if (L[i].height<=R[j].height){
	                storage[k] = L[i];
	                i++;
	            }
	            else{
	                storage[k] = R[j];
	                j++;
	            }
	            k++;
	        }
	        // Copy remaining elements of L[] if any
	        while (i < n1){
	            storage[k] = L[i];
	            i++;
	            k++;
	        }	 
	        // Copy remaining elements of R[] if any 
	        while (j < n2)	        {
	            storage[k] = R[j];
	            j++;
	            k++;
	        }
	}
	
	/**
	 * Adds a box is the smallest possible shelf where there is room available.
	 * Here we assume that there is at least one shelf (i.e. nbShelves >0)
	 * @param b
	 * @return problem or noProblem
	 */
	public String addBox (Box b){
		//ADD YOUR CODE HERE
		//a box can be added as long as it fits in height and there is enough space on the shelf
		for(int i=0; i<nbShelves; i++) {
			if(storage[i].height >= b.height && storage[i].availableLength >= b.length) {
				System.out.println(storage[i].availableLength);
				System.out.println(b.length);
				storage[i].addBox(b);
				return noProblem;
			}			
		}
		return problem;
	}
	
	/**
	 * Adds a box to its corresponding shipping list and updates all the fields
	 * @param b
	 * @return problem or noProblem
	 */
	public String addToShip (Box b){
		//ADD YOUR CODE HERE
		//for urgent boxes
		if (b instanceof UrgentBox) {			
			if (toShipUrgently == null) {
				//in case of it being the only one on the list
				toShipUrgently = (UrgentBox) b;
				b.next=null;
				b.next = null;
			}		
			else {
				//otherwise add it in front and update nodes
				Box temp = toShipUrgently; 
				temp.previous=b;
				b.next = temp;
				toShipUrgently = (UrgentBox)b;
			}
			return noProblem;
		}
		
		//for normal boxes, using the exact same logic
		if (b instanceof Box) {
			if (toShip == null) {
				toShip = b;
				b.next=null;
				b.previous = null;
			}		
			else {
				Box temp = toShip; 
				temp.previous=b;
				b.next = temp;
				toShip = b;
			}
			return noProblem;
		}
		//if it was added to neither of the lists, there was a problem
		return problem;
	}
	
	/**
	 * Find a box with the identifier (if it exists)
	 * Remove the box from its corresponding shelf
	 * Add it to its corresponding shipping list
	 * @param identifier
	 * @return problem or noProblem
	 */
	public String shipBox (String identifier){
		//ADD YOUR CODE HERE		
		//look through all shelves
		for (int i = 0; i<nbShelves ; i++) {
			Box b = storage[i].firstBox;
			//look through boxes on shelf
			while(b != null){
				if (b.id.equals(identifier)) {
					//if it was found, remove it and add to the list
					storage[i].removeBox(identifier);
					addToShip(b);
					return noProblem;
				}
				b = b.next;
			}
		}
		return problem;
	}
	
	/**
	 * if there is a better shelf for the box, moves the box to the optimal shelf.
	 * If there are none, do not do anything
	 * @param b
	 * @param position
	 */
	public void moveOneBox (Box b, int position){
		//ADD YOUR CODE HERE
		//only really have to cycle through until the position, because the shelves are already sorted
		for (int i = 0; i<position; i++) {
			//a box can be moved as long as it fits the height, there is enough space on the smaller shelf, and the shelf's height is smaller than its current one
			if (b.height <= storage[i].height && storage[i].height < storage[position].height && b.length <= storage[i].availableLength) {
				storage[position].removeBox(b.id);
				storage[i].addBox(b);
				break;
			}
		}
	}
	
	/**
	 * reorganize the entire warehouse : start with smaller shelves and first box on each shelf.
	 */
	public void reorganize (){
		//ADD YOUR CODE HERE
		
		//Now onto the fun part, first find out the max amount of boxes possible
		int record = 0;
		for (int i=0; i<nbShelves; i++) {
			int counter = 0;
			Box b= storage[i].firstBox;
			while(b!=null) {
				counter++;
				b = b.next;
			}
			if (counter > record) {
				record = counter;
			}
		}
		
		//then use a couple of counters. These will be used to ensure that we always start with the first box on each shelf, instead of all boxes on one shelf
		int count = 0;
		int county = 0;
		//Loop will cycle through until we check that all the boxes on a shelf were checked for their placements and haven't been moved
		//cycle through until record is reached, as well one more time for insurance
		while (count != record+1) {
			if(county == nbShelves) {
				//county will be used to check box we are looking at (first, 2nd, end?)
				count++;
			}
			county = 0;
			for(int i = 0; i < nbShelves; i++) {
				//this loop is the # of shelf
				Box b = storage[i].firstBox;	
				for(int z = 0; z < count; z++) {
					//this loop is the # of box on shelf
					if(b !=null) {
						b=b.next;						
					}
				}				
				//if there is a box in this spot, check if it exists
				if(b!=null) {
					String temp = print();
					moveOneBox(b, i);
					//try to move it, if it was moved, next round we will start by checking the same # of box again if any can be moved
					if (!temp.equals(print())) {
						county--;
					}				
				}
				county++;
			}
		}
		//and done, we have reorganized!
	}
}
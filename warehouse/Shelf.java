package assignment2;

public class Shelf {
	
	protected int height;
	protected int availableLength;
	protected int totalLength;
	protected Box firstBox;
	protected Box lastBox;

	public Shelf(int height, int totalLength){
		this.height = height;
		this.availableLength = totalLength;
		this.totalLength = totalLength;
		this.firstBox = null;
		this.lastBox = null;
	}
	
	protected void clear(){
		availableLength = totalLength;
		firstBox = null;
		lastBox = null;
	}
	
	public String print(){
		String result = "( " + height + " - " + availableLength + " ) : ";
		Box b = firstBox;
		while(b != null){
			result += b.id + ", ";
			b = b.next;
		}
		result += "\n";
		return result;
	}
	
	/**
	 * Adds a box on the shelf. Here we assume that the box fits in height and length on the shelf.
	 * @param b
	 */
	public void addBox(Box b){
		//ADD YOUR CODE HERE
		
		//if this is the first box, it will have nothing to link to
		if (firstBox == null) {
			this.firstBox=b;
			this.lastBox=b;
		}		
		else {
			//otherwise, add a box to the end, and link the last two boxes together
			lastBox.next=b;
			Box temp = lastBox;
			lastBox=b;
			lastBox.previous= temp;
		}
		//adjust shelf space
		availableLength-=b.length;
	}
	
	/**
	 * If the box with the identifier is on the shelf, remove the box from the shelf and return that box.
	 * If not, do not do anything to the Shelf and return null.
	 * @param identifier
	 * @return
	 */
	public Box removeBox(String identifier){
		//ADD YOUR CODE HERE
		//if shelf is empty, there is nothing to remove
		Box b = firstBox;
		if (firstBox==null) { 
			return null;
		}
		//checks first box
		if (firstBox.id.equals(identifier)) {
			//adjust free space on shelf
			availableLength+=firstBox.length;
			//move up boxes
			firstBox=firstBox.next;	
			//and make sure the links are also adjusted
			if (firstBox==null) {
				lastBox=null;
			}else {
				firstBox.previous=null;
			}
			//make sure that the removed box has no more connections to the shelf it was moved from
			b.previous=null;
			b.next=null;
			return b;
		}
		
		//cycle through the rest of the boxes 
		while (b.next !=null &&	!b.next.id.equals(identifier)) {
			b = b.next;
		}
		Box temp = b.next;
		//once we've exited the loop, it either means that box isn't on the shelf
		if (b.next==null) {
			return null;
		}else {
			//or it is, in which case adjust the parameters
			availableLength+=temp.length;
			b.next = b.next.next; 
			if (b.next==null) {
				lastBox=b;
				}
			if (temp.previous!=null) {
				b.previous=temp.previous;
				}			
			}
		temp.previous=null;
		temp.next=null;
		return temp;
		}
	
}


public class Room {
	
	//the three private attributes
	private String type;
	private double price;
	private boolean availability;
	
	//and the methods
	//constructor first
	public Room(String type){
		this.type = type;
		//first set up type, the other two attributes depend on what it is
		if (type.equals("double")) {
			//stats for doubles
			this.price = 90.0;
			this.availability = true;
		}else {
			//queen
			if (type.equals("queen")) {
				this.price = 110.0;
				this.availability = true;
			}else {
				//king
				if (type.equals("king")){
				this.price = 150.0;
				this.availability = true;
				}else {
					//if something different was entered, throw a message
					throw new IllegalArgumentException("There exists no such room type");	
					}
			}
		}
	}
	
	public String getType() {
		//allows access to the type of the room from different methods
		return this.type;
	}
	public double getPrice() {
		//and again, for price
		return this.price;
	}
	public boolean getAvailability() {
		//and again
		return this.availability;
	}
	public void changeAvailability() {
		//make a temporary variable
		boolean copy = this.availability;
		if (copy == true) {
			//change the original to the opposite
			this.availability = false;
		}else{
			//if it's not true, it must be false, so change to true
			this.availability = true;
		}		
	}
	public String findAvailableRoom(Room [] rooms, String type) {
		//make a loop to look through the array
		for(int i = 0; i< rooms.length; i++) {
			//must be the same type, and available
			if(rooms[i].type.equals(type)&& rooms[i].availability == true) {
				return type;
			}	
		}
		//if no such room exists, return a null string
		return null;
	}
}

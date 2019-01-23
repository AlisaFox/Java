
public class Reservation {
	//attributes
	private String name;
	private Room roomReserved;
	
	//constructor and get methods, exactly the same as in Room.java
	public Reservation (Room rr, String n) {
		this.roomReserved = rr;
		this.name = n; 
	}
	public String getName() {
		return this.name;
	}
	public Room getRoom() {
		return this.roomReserved;
	}
}

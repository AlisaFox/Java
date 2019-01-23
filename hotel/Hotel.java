import java.util.NoSuchElementException;

public class Hotel {
	
	private String name;
	private Room [] rooms;
	private Reservation [] reservedr;
	//I needed an integer outside a method, that would not be reset everytime I called it (see addReservation)
	private int reservationCounter = 0;
	
	
	//constructor
	public Hotel (String hotelName, Room [] roomsi) {
		//roomsi stands for initial rooms
		//rooms of the hotel are a copy
		this.rooms = new Room[roomsi.length];
		for (int i = 0; i < roomsi.length; i++) {
			//fill up the copy array
			this.rooms[i] = roomsi[i];
		}
		this.name = hotelName;
		this.reservedr = new Reservation[roomsi.length];
		//reservedr (stands for reserved rooms) is going to be null most of the time, so I set up a bunch of catch blocks further in the code
	}
	
	private void addReservation(Reservation rr) {
		//this is why I needed to have that counter that would not reset every time I called upon this code, so it would act like an index counter
		//basically, I assign rooms to new indexes everytime, since they are initially null
		this.reservedr[reservationCounter] = rr;
		reservationCounter++;
	}
	
	private void removeReservation(String n, String type) {
		//make a boolean that tracks if such a reservation exists, by default it doesn't
		boolean exists = false;		
		//a loop to go through the array
		for(int i = 0; i <reservedr.length; i++) {
			try {
				if(this.reservedr[i].getRoom().getType().equals(type) && this.reservedr[i].getName().equals(n)) {	
					//that was a long annoying piece of code, which makes sure that a room of the requested type was reserved under the requested name
					//if it does, there is not longer such a reservation (it is removed)
					this.reservedr[i]= null;
					//the availability has to change back to true
					//since findAvailableRoom did not return an index, and changeAvailability did not ask for an index, I made a helper method (see below)
					//it returns the index of the reserved room of the requested type
					int x = availabilityHelper(rooms, type, false);
					if(x>-1) {
						//i set index to be -1 only if such a reservation doesn't exists, otherwise it should go through this if statement 
						//technically, this is if statement is just to be safe
						rooms[x].changeAvailability();
					}
					//if the first if condition was fulfilled, change the boolean to be true
					exists = true;
					//can break the loop since we found what we were looking for
					break;
				}
			}catch(java.lang.NullPointerException e) {
				//this is just so all the null reservations in the reservedr array are ignored and the code is not interrupted
				continue;
			}
		}
		//if a reservation was not found after going through the loop, throw this comment. The boolean was made for this occasion
		if(exists == false) {
			throw new NoSuchElementException("No such reservation has been made");		
		}
	}
		//said helper method that returns the index
		private int availabilityHelper(Room[] rooms, String type, boolean changeFrom) {
			//sorts through the rooms array
			for(int i = 0; i< rooms.length; i++ ) {
				try {
					//if something was found, it returns the index
					if(rooms[i].getType().equals(type) && rooms[i].getAvailability()==changeFrom) {
						return i;
					}
				}catch(java.lang.NullPointerException e) {
					//if it's null, ignore, no need to stop the whole program
					return -1;
				}
			}
			//if nothing was found after the loop, return a nonexistent index
			return -1;
		}
		
	public void createReservation(String n, String type) {
		//here, I will be using the helper method again to help change the availability
		int x = availabilityHelper(rooms, type, true);
		//this time we are looking for an available room (availability should be true)
		if(x>-1 && rooms[x].findAvailableRoom(this.rooms, type).equals(type)) {
			//if such a room was found, make a new reservation
			Reservation a = new Reservation (rooms[x], n);		
			//change availability
			rooms[x].changeAvailability();
			//add a reservation an empty spot in the reservedr array
			addReservation(a);
			//and print out a "yay" message
			System.out.print("You have successfully reserved a " + type + " room under the name of " + n+ ".");
		}else {
			//or, if the room was not available, print out a "nay" message
			System.out.println("Sorry "+ n+ ", we have no available rooms of the desired type.");
		}
	}

	public void cancelReservation(String n, String type) {
		try {
			//most of the code for this to work has already been written in removeReservation
			removeReservation(n, type);
			//if it worked, here is the "yay" message
			System.out.println(n + ", your reservation for a " + type+ " room has been successfully cancelled." );
		} catch (NoSuchElementException e){
			//if it didn't, there was no such reservation
			System.out.println("There's no reservation for a " + type+ " room under the name of " + n +"." );
		}		
	}
	
	public void printInvoice(String n) {
		//this is very similar to a toString method
		//set up a counter
		double invoice = 0;
		for(int i = 0; i< reservedr.length; i++) {
			//go through the reservations 
			try {				
				//sort by name
				if((reservedr[i].getName()).equals(n)) {
					//and add to the counter bit by bit
					invoice += reservedr[i].getRoom().getPrice();
				}
			}catch(java.lang.NullPointerException e) {
				//again, if there is a null index ignore it until the loop ends
				continue;
			}
		}
		//if the counter is 0, that means nothing in the loop counted
		if (invoice == 0) {
			System.out.println("No reservations have been made under this name");
		}else {
			//if invoice was changed, it means that somebody reserved a room, and the menu worked 
			System.out.println(n + "'s invoice is of $" + invoice);
		}
	}
	
	public String toString() {
		//make a null string
		String info = "";
		info += "Here is the hotel info" + "\n" + "Hotel name: " + this.name + "\n";
		//set up counters for everything
		int doubles = 0;
		int queens = 0;
		int kings = 0;
		//go through all the rooms, and if they are available, increase the respective counter
		for(int i = 0; i<rooms.length; i++) {
			if(rooms[i].getType().equals("double")&& rooms[i].getAvailability()==true) {
				doubles++;
			}else {
				if(rooms[i].getType().equals("queen")&& rooms[i].getAvailability()==true) {
					queens++;
				}else {
					if(rooms[i].getType().equals("king")&& rooms[i].getAvailability()==true)
					kings++;
					}
				}
			}
		//add to the info string
		info += "Available Rooms: " + doubles + " double, " + queens + " queen, " + kings + " king.";
		return info;
	}
}

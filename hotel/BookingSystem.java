import java.util.Scanner;
import java.util.Random;

public class BookingSystem {

    private static String[] typeOfRooms = {"double","queen","king"};
    private static Random r = new Random(123);
    
    //returns a random String from the above array. 
    private static String getRandomType(){
        int index = r.nextInt(typeOfRooms.length);
        return typeOfRooms[index];
    }
    //returns a random number of rooms between 5 and 50.
    private static int getRandomNumberOfRooms(){
        return r.nextInt(50)+1;
    }
    //End of provided code. 
    
    public static void main(String[] args){
        //Student Name: Alisa Gagina
        //Student Number: 260770497
        //Your code goes here. 
    	
    	
    	//set up the scanner
    	Scanner scanner = new Scanner(System.in);
    		System.out.println("Welcome to the COMP 202 booking system. \nPlease enter the name of the hotel you'd like to book");
    		//get the hotel name
    		String hotelName = scanner.next();  	
    		//make an array of rooms using the provided methods
	    	int numberOfRooms = getRandomNumberOfRooms();
	    	Room[] rooms = new Room [numberOfRooms];
	    	for(int i = 0; i< numberOfRooms; i++) {
	    		rooms[i]= new Room(getRandomType());
	    	}
	    	//and initialize a hotel using the hotel name and the rooms array
	    	Hotel hotel = new Hotel (hotelName, rooms);
    	//make a loop for the menu
	    //it should run forever until it is broken (scanner input 5), so I am just going to give an unchanging value as the condition
    	while (numberOfRooms > 0) {
    		//print the menu    		
    		System.out.println("\n*******************************");
    		System.out.println("Welcome to " + hotelName + ". Choose one of the following options:");
    		System.out.println("1) Make a reservation \n2) Cancel a reservation \n3) See an invoice \n4) See hotel info \n5) Exit the Booking System");
    		System.out.println("*******************************");
    		//and get the input
    		int input = scanner.nextInt();
    		if(input!= 1 && input!= 2 && input!= 3 && input!= 4 && input!= 5) {
    			//the input can not be anything other than 1-5, so reset the loop if it isn't
    			System.out.println("There is no such option, please try again");
    			continue;
    		}
    		//input 1 makes a reservation
    		if(input == 1) {
    			//get the two variables needed for creating a reservation
    			System.out.println("Please enter your name:");
    			String n = scanner.next();
    			System.out.println("What type of room would you like to reserve?");
    			String type = scanner.next();
    			//use the hotel class to make a reservation
    			hotel.createReservation(n, type);
    			System.out.print(" We look forward to having you at " + hotelName +"!\n");
    			//skip to the next iteration of the loop, so the menu shows up again
    			continue;
    		}
    		//input 2 cancels a reservation
    		if(input ==2) {
    			//very similar to making a reservation, all the details are in the hotel class
    			System.out.println("Please enter the name you used to make the reservation");
    			String n = scanner.next();
    			System.out.println("What type of room did you reserve?");
    			String type = scanner.next();
    			hotel.cancelReservation(n, type);
    			continue;
    		}
    		//get the invoice
    		if(input ==3) {
    			//make sure you get the name of the person who you need the invoice for
    			System.out.println("Please enter your name");
    			String n = scanner.next();
    			hotel.printInvoice(n);
    			continue;
    		}
    		//show the information of the hotel, including an updating available rooms list
    		if(input ==4) {
    			System.out.println(hotel.toString());
    			continue;
    		}
    		//this is the option that takes you out of the loop
    		if (input == 5) {
    			System.out.println("It was a pleasure doing business with you!");
    			break;
    		}
    	}
    	//close the scanner so that there are no information leak warnings
    	scanner.close();	
    }
}


# Hotel

## What
This is a hotel. Nice and simple. You have a menu with several options: make a reservation, delete a reservation,
view hotel info, and view your bill.

## What is happening: the closer look

This is an object oriented program. We have `Room.java` with attributes
  - type;
    - double, queen, king
	- price;
	- availability;
  
  `Reservation.java` with attributes
  - name (ie. the name of the person who reserved)
	- roomReserved 
  
  `BookingSystem.java` uses a scanner to ask the user inputs. This is the frontend part, that shows up as a menu with options 
  1. Make a reservation 
  2. Cancel a reservation 
  3. See an invoice 
  4. See hotel info 
  5. Exit the Booking System
  
  Finally `Hotel.java` is the backend. You have a constructor for the rooms, you can add / remove reservations, and you can print the invoice for a
  certain name.

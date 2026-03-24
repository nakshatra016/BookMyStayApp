# BookMyStayApp
This project focuses on the design and implementation of a Hotel Booking Management System to demonstrate how Core Java and fundamental data structures are applied to solve real-world software engineering challenges.

## Use Case 1 – Application Entry & Welcome Message

This use case represents the starting point of the Hotel Booking Management System.

- Starts the application using the `main()` method
- Displays a welcome message to the user
- Confirms that the system has initialized successfully

## Use Case 2 – Basic Room Types & Static Availability

This use case initializes different room types in the hotel system.

- Creates basic room types: Single Room, Double Room, and Suite Room
- Assigns predefined attributes such as number of beds, room size, and price per night
- Displays room details for each room type
- Shows static availability for each room type

## Use Case 3 – Centralized Room Inventory Management

This use case manages room availability using a centralized inventory system.

- Stores available room counts using a map-based inventory
- Initializes default availability for Single, Double, and Suite rooms
- Retrieves room pricing and characteristics from room objects
- Displays room details along with current availability

## Use Case 4 – Room Search & Availability Check

This use case allows guests to view available rooms in the hotel.

- Reads room availability from the centralized inventory
- Retrieves room details such as beds, size, and price from room objects
- Displays only rooms that currently have availability
- Performs read-only access without modifying inventory data

## Use Case 5 – Booking Request Queue (FIFO)

This use case demonstrates how booking requests are handled using a queue.

- Creates booking requests with guest name and room type
- Stores requests in a FIFO queue
- Processes booking requests in the order they were received
- Ensures fair request handling without modifying room inventory
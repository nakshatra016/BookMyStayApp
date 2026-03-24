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

## Use Case 6 – Reservation Confirmation & Room Allocation

This use case confirms booking requests and assigns rooms.

- Processes booking requests from the queue in FIFO order
- Generates unique room IDs for each confirmed booking
- Updates room availability immediately after allocation
- Ensures that no room is double booked

## Use Case 7 – Add-On Service Selection

This use case allows optional services to be attached to a confirmed reservation.

- Creates optional services such as breakfast or spa
- Associates services with a reservation ID
- Supports attaching multiple services to a reservation
- Calculates the total cost of selected add-on services

## Use Case 8 – Booking History & Reporting

This use case stores confirmed reservations and generates booking reports.

- Maintains a list of confirmed reservations
- Stores booking history in ordered form
- Generates a report of all confirmed bookings
- Separates reporting logic from data storage

## Use Case 9 – Error Handling & Validation

This use case validates booking requests before they are processed.

- Accepts user input for guest name and room type
- Validates booking details using centralized validation logic
- Uses a custom exception for domain-specific errors
- Handles invalid booking scenarios gracefully.

## Use Case 10 – Booking Cancellation & Inventory Rollback

This use case handles booking cancellations and restores room availability.

- Registers confirmed bookings for tracking
- Cancels bookings safely using reservation IDs
- Restores room inventory when a booking is cancelled
- Maintains rollback history using a stack
- Displays recently released reservation IDs

## Use Case 11 – Concurrent Booking Simulation

This use case simulates multiple users booking rooms simultaneously.

- Uses multiple threads to process booking requests
- Synchronizes access to shared resources like booking queue and inventory
- Prevents race conditions during room allocation
- Demonstrates thread-safe booking processing

## Use Case 12 – Data Persistence & System Recovery

This use case demonstrates saving and restoring system inventory using a file.

- Saves room inventory to a text file
- Loads inventory data during system startup
- Restores previous system state if data exists
- Uses simple file-based persistence without databases
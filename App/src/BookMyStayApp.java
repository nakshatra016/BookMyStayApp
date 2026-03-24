import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.*;
import java.util.Scanner;

class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}
class AddOnServiceManager {

    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<Service> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (Service s : services) {
            total += s.getCost();
        }

        return total;
    }
}
class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType + " Room") > 0) {

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType
                    .computeIfAbsent(roomType, k -> new HashSet<>())
                    .add(roomId);

            inventory.updateAvailability(
                    roomType + " Room",
                    availability.get(roomType + " Room") - 1
            );

            System.out.println(
                    "Booking confirmed for Guest: "
                            + reservation.getGuestName()
                            + ", Room ID: "
                            + roomId
            );
        }
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        return roomType + "-" + count;
    }
}
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}
class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!roomType.equals("Single") &&
                !roomType.equals("Double") &&
                !roomType.equals("Suite")) {

            throw new InvalidBookingException("Invalid room type selected.");
        }

        Integer available = inventory
                .getRoomAvailability()
                .get(roomType + " Room");

        if (available == null || available <= 0) {
            throw new InvalidBookingException("Selected room type not available.");
        }
    }
}
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single Room") > 0) {
            System.out.println("Single Room:");
            singleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single Room"));
            System.out.println();
        }

        if (availability.get("Double Room") > 0) {
            System.out.println("Double Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double Room"));
            System.out.println();
        }

        if (availability.get("Suite Room") > 0) {
            System.out.println("Suite Room:");
            suiteRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Suite Room"));
        }
    }
}
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}
class Service {

    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            Reservation reservation = new Reservation(guestName, roomType);
            bookingQueue.addRequest(reservation);

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}
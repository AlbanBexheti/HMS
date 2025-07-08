import java.time.LocalDate;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("HOTEL MANAGEMENT SYSTEM\n");

        //Initializing Hotel
        Hotel hotel = new Hotel("Hotel | Park Plaza Belvedere");
        System.out.println("Hotel created: " + hotel.getHotelName() + "\n");

        //Add rooms with BigDecimal rates
        hotel.addRoom(new Room(100, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(101, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(102, "Deluxe", new BigDecimal("180.00")));
        hotel.addRoom(new Room(103, "Suite", new BigDecimal("350.00")));
        hotel.addRoom(new Room(104, "Suite", new BigDecimal("350.00")));
        hotel.addRoom(new Room(105, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(106, "Standard", new BigDecimal("120.00")));
        hotel.addRoom(new Room(107, "Deluxe", new BigDecimal("180.00")));
        hotel.addRoom(new Room(108, "Suite", new BigDecimal("350.00")));
        hotel.addRoom(new Room(109, "Suite", new BigDecimal("350.00")));
        System.out.println();

        //Register guests
        Guest guest1 = new Guest(1, "Alban", "Bexheti", "bexhetialban@gmail.com");
        Guest guest2 = new Guest(2, "Sarah", "Johnson", "sarah.j@email.com");
        Guest guest3 = new Guest(3, "Mike", "Brown", "mike.brown@icloud.com");
        Guest guest4 = new Guest(4, "Emma", "Davis", "emma.davis@email.com");
        Guest guest5 = new Guest(5, "Albin", "Shabani", "shabanialbin@gmail.com");
        Guest guest6 = new Guest(6, "Bardhi", "Bajrami", "bbardhi@hotmail.com");
        Guest guest7 = new Guest(7, "Admir", "Rufati", "adorufati@yahoo.com");
        Guest guest8 = new Guest(8, "Selman", "Ademi", "selman1221@yahoo.com");

        System.out.println("Guests registered:");
        System.out.println(guest1);
        System.out.println(guest2);
        System.out.println(guest3);
        System.out.println(guest4);
        System.out.println(guest5);
        System.out.println(guest6);
        System.out.println(guest7);
        System.out.println(guest8);
        System.out.println();

        //Display room status
        hotel.displayAllRooms();

        //Check availability for specific dates
        LocalDate searchCheckIn = LocalDate.of(2025, 8, 1);
        LocalDate searchCheckOut = LocalDate.of(2025, 8, 5);
        hotel.displayAvailableRooms(searchCheckIn, searchCheckOut);

        //Execute booking operations
        System.out.println("BOOKING OPERATIONS");

        //Successful bookings
        hotel.makeBooking(guest1, 100, LocalDate.of(2025, 8, 1), LocalDate.of(2025, 8, 5));
        hotel.makeBooking(guest2, 101, LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 7));
        hotel.makeBooking(guest3, 102, LocalDate.of(2025, 8, 10), LocalDate.of(2025, 8, 15));

        //Test overlapping bookings (should fail)
        System.out.println("\nAttempting overlapping bookings:");
        hotel.makeBooking(guest4, 103, LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 6));
        hotel.makeBooking(guest5, 204, LocalDate.of(2025, 8, 6), LocalDate.of(2025, 8, 8));

        //Test non-overlapping bookings (should succeed)
        System.out.println("\nAttempting non-overlapping bookings:");
        hotel.makeBooking(guest4, 101, LocalDate.of(2025, 8, 6), LocalDate.of(2025, 8, 8));
        hotel.makeBooking(guest5, 201, LocalDate.of(2025, 8, 8), LocalDate.of(2025, 8, 10));

        //Failed booking attempts
        System.out.println("\nAttempting other failed bookings:");
        hotel.makeBooking(guest6, 105, LocalDate.of(2025, 8, 20), LocalDate.of(2025, 8, 22));
        hotel.makeBooking(guest7, 106, LocalDate.of(2025, 8, 25), LocalDate.of(2025, 8, 23));
        hotel.makeBooking(guest8, 107, LocalDate.of(2025, 8, 25), LocalDate.of(2025, 8, 25));

        System.out.println();

        //Display current status after bookings
        hotel.displayAllRooms();
        hotel.displayAllBookings();

        //Check availability again for the same dates
        hotel.displayAvailableRooms(searchCheckIn, searchCheckOut);

        System.out.println("CANCELLATION OPERATIONS");
        hotel.cancelBooking(108);
        hotel.cancelBooking(109);
        System.out.println();

        hotel.displayAvailableRooms(LocalDate.of(2025, 8, 3), LocalDate.of(2025, 8, 16));

        System.out.println("BOOKING IN PREVIOUSLY CANCELLED ROOM");
        hotel.makeBooking(guest6, 109, LocalDate.of(2025, 8, 20), LocalDate.of(2025, 8, 30));
        System.out.println();

        System.out.println("FINAL SYSTEM STATUS");
        hotel.displayAllRooms();
        hotel.displayAllBookings();

        System.out.println("Hotel Summary: " + hotel);
        System.out.println("\nHotel Management System!");
    }
}
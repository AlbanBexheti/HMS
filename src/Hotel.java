import java.util.ArrayList;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Hotel {
    private String hotelName;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;
    private int nextBookingId;

    //Constructor
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.nextBookingId = 1;
    }

    //Add room to Hotel
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Room " + room.getRoomId() + " added to " + hotelName);
    }

    //Find room by ID
    private Room findRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    //Check if room is available for specific dates
    private boolean isRoomAvailableForDates(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                //Check for date overlap
                if (!(checkOutDate.isBefore(booking.getCheckInDate()) ||
                        checkInDate.isAfter(booking.getCheckOutDate()) ||
                        checkInDate.isEqual(booking.getCheckOutDate()) ||
                        checkOutDate.isEqual(booking.getCheckInDate()))) {
                    return false; // Room is not available
                }
            }
        }
        return true; // Room is available
    }

    //Get list of available rooms for specific dates
    public ArrayList<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    //Make a booking
    public boolean makeBooking(Guest guest, int roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        //Validate dates check-out must be after check-in
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            System.out.println("Error: Check-out date must be after check-in date");
            return false;
        }

        //Find the room
        Room room = findRoomById(roomId);
        if (room == null) {
            System.out.println("Error: Room " + roomId + " not found");
            return false;
        }

        //Check if room is available for the requested dates
        if (!isRoomAvailableForDates(room, checkInDate, checkOutDate)) {
            System.out.println("Error: Room " + roomId + " is not available for the requested dates");
            return false;
        }

        //Create booking
        Booking newBooking = new Booking(nextBookingId++, room, guest, checkInDate, checkOutDate);
        bookings.add(newBooking);

        System.out.println("Booking successful! Booking ID: " + newBooking.getBookingId() +
                " | Total Cost: $" + newBooking.calculateTotalCost());
        return true;
    }

    //Cancel a booking
    public boolean cancelBooking(int bookingId) {
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.getBookingId() == bookingId) {
                // Remove booking
                bookings.remove(i);
                System.out.println("Booking " + bookingId + " cancelled successfully");
                return true;
            }
        }
        System.out.println("Error: Booking " + bookingId + " not found");
        return false;
    }

    //Display all rooms
    public void displayAllRooms() {
        System.out.println("\nALL ROOMS IN " + hotelName.toUpperCase());
        if (rooms.isEmpty()) {
            System.out.println("No rooms available");
        } else {
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    //Display all bookings
    public void displayAllBookings() {
        System.out.println("\nALL BOOKINGS IN " + hotelName.toUpperCase());
        if (bookings.isEmpty()) {
            System.out.println("No bookings found");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
        System.out.println();
    }

    //Display available rooms for specific dates
    public void displayAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        System.out.println("\n AVAILABLE ROOMS FROM " + checkInDate + " TO " + checkOutDate);
        ArrayList<Room> availableRooms = getAvailableRooms(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for the requested dates");
        } else {
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
        System.out.println();
    }

    //Get hotel name
    public String getHotelName() {
        return hotelName;
    }

    //Get total revenue from all bookings
    public BigDecimal getTotalRevenue() {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (Booking booking : bookings) {
            totalRevenue = totalRevenue.add(booking.calculateTotalCost());
        }
        return totalRevenue;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "Name='" + hotelName + '\'' +
                ", Rooms=" + rooms.size() +
                ", Bookings=" + bookings.size() +
                ", Total Revenue=$" + getTotalRevenue() +
                '}';
    }
}
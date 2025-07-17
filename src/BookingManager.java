import java.time.LocalDate;
import java.util.*;

public class BookingManager {
    private ArrayList<Booking> bookings;
    private ArrayList<Room> rooms;

    public BookingManager() {
        this.bookings = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void printBookedRoomUsers() {
        for(Booking booking : this.bookings) {
            System.out.println("Room " + booking.getRoom().getRoomId() + " booked by " + booking.getGuest().getFullName());
        }
    }

    public Room findFirstUnbookedRoom(Room[] roomsToCheck) {
        if (roomsToCheck == null) {
            return null;
        }

        for(Room room : roomsToCheck) {
            if (room.isAvailable()) {
                return room;
            }
        }

        System.out.println("All rooms are occupied");
        return null;
    }

    public void validateBookingDates(String[] checkInDates, String[] checkOutDates) throws InvalidBookingDatesExceptions {
        if (checkInDates == null || checkOutDates == null || checkInDates.length != checkOutDates.length) {
            throw new IllegalArgumentException("Input arrays must be non-null and of equal length");
        }

        for(int i = 0; i < checkInDates.length; i++) {
            LocalDate checkIn = LocalDate.parse(checkInDates[i]);
            LocalDate checkOut = LocalDate.parse(checkOutDates[i]);
            if (!checkOut.isAfter(checkIn)) {
                throw new InvalidBookingDatesExceptions("Invalid dates at index " + i + ": Check-out must be after check-in");
            }
        }
    }

    public Room getEarliestUnbookedRoom(LocalDate startDate) {
        for(Room room : this.rooms) {
            if (room.isAvailable(startDate, startDate.plusDays(1))) {
                return room;
            }
        }
        return null;
    }
}
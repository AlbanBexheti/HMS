import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;

public class Booking {
    private int bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    //Parametrized Constructor
    public Booking(int bookingId, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    //Getter Methods
    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    //Calculate total cost of the booking
    public BigDecimal calculateTotalCost() {
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal nights = BigDecimal.valueOf(numberOfNights);
        return room.getNightlyRate().multiply(nights);
    }

    //Get number of nights
    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    @Override
    public String toString() {
        return "Booking{ " + " ID: " + bookingId
                + " Room: " + room.getRoomId()
                + " Guest: " + guest.getFullName()
                + " Check-In: " + checkInDate
                + " Check-Out: " + checkOutDate
                + " Nights: " + getNumberOfNights()
                + " Total Cost: $" + calculateTotalCost() + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Booking booking = (Booking) object;
        return bookingId == booking.bookingId;
    }
}
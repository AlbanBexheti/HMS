import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Chargeable {
    private int bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<HotelService> services; // Add services to booking


    public Booking(int bookingId, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.services = new ArrayList<>();
    }

    public void addService(HotelService service) {
        services.add(service);
    }

    public boolean removeService(String serviceId) {
        return services.removeIf(service -> service.getServiceId().equals(serviceId));
    }

    public List<HotelService> getServices() {
        return new ArrayList<>(services);
    }

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

    public BigDecimal calculateTotalCost() {
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal nights = BigDecimal.valueOf(numberOfNights);
        BigDecimal roomCost = room.getNightlyRate().multiply(nights);

        BigDecimal serviceCost = BigDecimal.ZERO;
        for (HotelService service : services) {
            serviceCost = serviceCost.add(BigDecimal.valueOf(service.calculateFinalCost()));
        }

        return roomCost.add(serviceCost);
    }

    @Override
    public BigDecimal getCost() {
        return calculateTotalCost();
    }

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
                + " Services: " + services.size()
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
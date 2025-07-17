import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Chargeable {
    private int bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<HotelService> services;

    public Booking(int bookingId, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.services = new ArrayList<>();
    }

    public Booking() {
        this.services = new ArrayList<>();
    }

    public void addService(HotelService service) {
        this.services.add(service);
    }

    public boolean removeService(String serviceId) {
        return this.services.removeIf(service -> service.getServiceId().equals(serviceId));
    }

    public List<HotelService> getServices() {
        return new ArrayList<>(this.services);
    }

    public int getBookingId() {
        return this.bookingId;
    }

    public Room getRoom() {
        return this.room;
    }

    public Guest getGuest() {
        return this.guest;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public BigDecimal calculateTotalCost() {
        long numberOfNights = ChronoUnit.DAYS.between(this.checkInDate, this.checkOutDate);
        BigDecimal nights = BigDecimal.valueOf(numberOfNights);
        BigDecimal roomCost = this.room.getNightlyRate().multiply(nights);
        BigDecimal serviceCost = BigDecimal.ZERO;

        for(HotelService service : this.services) {
            serviceCost = serviceCost.add(BigDecimal.valueOf(service.calculateFinalCost()));
        }

        return roomCost.add(serviceCost);
    }

    public BigDecimal getCost() {
        return this.calculateTotalCost();
    }

    public long getNumberOfNights() {
        return ChronoUnit.DAYS.between(this.checkInDate, this.checkOutDate);
    }

    public String toString() {
        return "Booking{  ID: " + this.bookingId + " Room: " + this.room.getRoomId() +
                " Guest: " + this.guest.getFullName() + " Check-In: " + this.checkInDate +
                " Check-Out: " + this.checkOutDate + " Nights: " + this.getNumberOfNights() +
                " Services: " + this.services.size() + " Total Cost: $" + this.calculateTotalCost() + "}";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Booking booking = (Booking) object;
        return this.bookingId == booking.bookingId;
    }
}
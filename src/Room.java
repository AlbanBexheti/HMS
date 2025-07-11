import java.math.BigDecimal;
import java.time.LocalDate;

public class Room implements Bookable {
    private int roomId;
    private String roomType;
    private BigDecimal nightlyRate;
    private boolean isAvailable;

    private LocalDate bookedStartDate;
    private LocalDate bookedEndDate;


    public Room() {
        this.roomId = 0;
        this.roomType = "Standard";
        this.nightlyRate = new BigDecimal("100.00");
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
    }

    public Room(int roomId, String roomType, BigDecimal nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = nightlyRate;
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
    }

    public Room(int roomId, String roomType, double nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = BigDecimal.valueOf(nightlyRate);
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
    }

    @Override
    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        if (bookedStartDate == null || bookedEndDate == null) {
            return true;
        }
        return endDate.isBefore(bookedStartDate) || startDate.isAfter(bookedEndDate) ||
                startDate.isEqual(bookedEndDate) || endDate.isEqual(bookedStartDate);
    }

    @Override
    public void markAsBooked(LocalDate startDate, LocalDate endDate) {
        this.bookedStartDate = startDate;
        this.bookedEndDate = endDate;
        this.isAvailable = false;
    }

    @Override
    public void cancelBooking(LocalDate startDate, LocalDate endDate) {
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.isAvailable = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public BigDecimal getNightlyRate() {
        return nightlyRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" + " ID: "
                + roomId +
                ", Type: " + roomType +
                ", NightlyRate: $" + nightlyRate +
                ", isAvailable: " + isAvailable + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Room room = (Room) object;
        return roomId == room.roomId;
    }
}
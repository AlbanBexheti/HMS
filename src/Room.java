import java.math.BigDecimal;

public class Room {

    private int roomId;
    private String roomType;
    private BigDecimal nightlyRate;
    private boolean isAvailable;

    //Default Constructor
    public Room(){
        this.roomId = 0;
        this.roomType = "Standard";
        this.nightlyRate = new BigDecimal("100.00");
        this.isAvailable = true;
    }

    //Parametrized Constructor
    public Room(int roomId, String roomType, BigDecimal nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = nightlyRate;
        this.isAvailable = true;
    }

    //Convenience constructor for double values (converts to BigDecimal)
    public Room(int roomId, String roomType, double nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = BigDecimal.valueOf(nightlyRate);
        this.isAvailable = true;
    }

    //Getter method
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

    //Setter method for the availability
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
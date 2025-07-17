import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Bookable {
    private int roomId;
    private String roomType;
    private BigDecimal nightlyRate;
    private boolean isAvailable;
    private LocalDate bookedStartDate;
    private LocalDate bookedEndDate;
    private Map<LocalDate, Boolean> dailyOccupancy;

    public Room() {
        this.roomId = 0;
        this.roomType = "Standard";
        this.nightlyRate = new BigDecimal("100.00");
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.dailyOccupancy = new HashMap<>();
    }

    public Room(int roomId, String roomType, BigDecimal nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = nightlyRate;
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.dailyOccupancy = new HashMap<>();
    }

    public Room(int roomId, String roomType, double nightlyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.nightlyRate = BigDecimal.valueOf(nightlyRate);
        this.isAvailable = true;
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.dailyOccupancy = new HashMap<>();
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (this.dailyOccupancy.getOrDefault(date, false)) {
                return false;
            }
        }
        return true;
    }

    public void markAsBooked(LocalDate startDate, LocalDate endDate) {
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            this.dailyOccupancy.put(date, true);
        }
        this.bookedStartDate = startDate;
        this.bookedEndDate = endDate;
        this.isAvailable = false;
    }

    public void cancelBooking(LocalDate startDate, LocalDate endDate) {
        for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            this.dailyOccupancy.remove(date);
        }
        this.bookedStartDate = null;
        this.bookedEndDate = null;
        this.isAvailable = true;
    }

    public static List<Room> getRoomsAboveRate(List<Room> rooms, Double minRate) {
        List<Room> result = new ArrayList<>();
        for(Room room : rooms) {
            if (room.getNightlyRate().doubleValue() > minRate) {
                result.add(room);
            }
        }
        return result;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public BigDecimal getNightlyRate() {
        return this.nightlyRate;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String toString() {
        return "Room{ ID: " + this.roomId + ", Type: " + this.roomType +
                ", NightlyRate: $" + this.nightlyRate + ", isAvailable: " + this.isAvailable + "}";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Room room = (Room) object;
        return this.roomId == room.roomId;
    }

    public boolean wasVacantForThreeConsecutiveDays(boolean[] weeklyOccupancy) {
        if (weeklyOccupancy == null || weeklyOccupancy.length != 7) {
            throw new IllegalArgumentException("Array must contain 7 days of occupancy data");
        }

        int consecutiveVacantDays = 0;
        for(boolean occupied : weeklyOccupancy) {
            if (!occupied) {
                consecutiveVacantDays++;
                if (consecutiveVacantDays >= 3) {
                    return true;
                }
            } else {
                consecutiveVacantDays = 0;
            }
        }
        return false;
    }

    public boolean checkBedCapacityViolation(int[] nightlyOccupants) {
        if (nightlyOccupants == null) {
            return false;
        }

        int maxCapacity = 2;
        if (this.roomType.equals("Deluxe")) {
            maxCapacity = 4;
        } else if (this.roomType.equals("Suite")) {
            maxCapacity = 6;
        }

        for(int occupants : nightlyOccupants) {
            if (occupants > maxCapacity) {
                return true;
            }
        }
        return false;
    }

    public void applyDiscountCodes(char[] discountCodes) {
        if (discountCodes != null) {
            for(char code : discountCodes) {
                switch (code) {
                    case 'A':
                        this.nightlyRate = this.nightlyRate.multiply(new BigDecimal("0.9"));
                        break;
                    case 'B':
                        this.nightlyRate = this.nightlyRate.multiply(new BigDecimal("0.85"));
                        break;
                    case 'C':
                        this.nightlyRate = this.nightlyRate.multiply(new BigDecimal("0.8"));
                        break;
                    default:
                        System.out.println("Unknown discount code: " + code);
                }
            }
        }
    }
}
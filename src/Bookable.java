import java.time.LocalDate;

public interface Bookable {
    boolean isAvailable(LocalDate startDate, LocalDate endDate);
    void markAsBooked(LocalDate startDate, LocalDate endDate);
    void cancelBooking(LocalDate startDate, LocalDate endDate);
}


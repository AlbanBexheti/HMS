import java.time.LocalDate;

public interface Bookable {
    boolean isAvailable(LocalDate var1, LocalDate var2);

    void markAsBooked(LocalDate var1, LocalDate var2);

    void cancelBooking(LocalDate var1, LocalDate var2);
}
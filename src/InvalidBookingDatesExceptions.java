public class InvalidBookingDatesExceptions extends Exception {
    public InvalidBookingDatesExceptions() {
        super("Check-out date must be after check-in date");
    }

    public InvalidBookingDatesExceptions(String message) {
        super(message);
    }
}
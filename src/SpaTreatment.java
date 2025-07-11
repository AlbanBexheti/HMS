import java.time.LocalDate;

public class SpaTreatment extends HotelService implements Bookable {
    private int durationMinutes;
    private boolean isPremium;
    private LocalDate bookedDate;
    private boolean isBooked;

    public SpaTreatment(String serviceId, String description, double baseCost, int durationMinutes, boolean isPremium) {
        super(serviceId, description, baseCost);
        this.durationMinutes = durationMinutes;
        this.isPremium = isPremium;
        this.isBooked = false;
        this.bookedDate = null;
    }

    @Override
    public double calculateFinalCost() {
        double cost = getBaseCost() * (durationMinutes / 60.0);
        return isPremium ? cost * 1.3 : cost;
    }

    @Override
    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        return !isBooked || !startDate.equals(bookedDate);
    }

    @Override
    public void markAsBooked(LocalDate startDate, LocalDate endDate) {
        this.isBooked = true;
        this.bookedDate = startDate;
    }

    @Override
    public void cancelBooking(LocalDate startDate, LocalDate endDate) {
        this.isBooked = false;
        this.bookedDate = null;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isBooked() {
        return isBooked;
    }

    @Override
    public String toString() {
        return String.format("Spa Treatment [ID: %s, Description: %s, Duration: %d min, Premium: %s, Booked: %s, Final Cost: $%.2f]",
                serviceId, description, durationMinutes, isPremium, isBooked, calculateFinalCost());
    }
}
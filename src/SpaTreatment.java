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

    public double calculateFinalCost() {
        double cost = this.getBaseCost() * ((double)this.durationMinutes / (double)60.0F);
        return this.isPremium ? cost * 1.3 : cost;
    }

    public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
        return !this.isBooked || !startDate.equals(this.bookedDate);
    }

    public void markAsBooked(LocalDate startDate, LocalDate endDate) {
        this.isBooked = true;
        this.bookedDate = startDate;
    }

    public void cancelBooking(LocalDate startDate, LocalDate endDate) {
        this.isBooked = false;
        this.bookedDate = null;
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    public boolean isPremium() {
        return this.isPremium;
    }

    public boolean isBooked() {
        return this.isBooked;
    }

    public String toString() {
        return String.format("Spa Treatment [ID: %s, Description: %s, Duration: %d min, Premium: %s, Booked: %s, Final Cost: $%.2f]", this.serviceId, this.description, this.durationMinutes, this.isPremium, this.isBooked, this.calculateFinalCost());
    }
}
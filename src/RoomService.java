public class RoomService extends HotelService {
    private int numberOfItems;
    private static final double ITEM_MULTIPLIER = (double)1.5F;

    public RoomService(String serviceId, String description, double baseCost, int numberOfItems) {
        super(serviceId, description, baseCost);
        this.numberOfItems = numberOfItems;
    }

    public double calculateFinalCost() {
        return this.getBaseCost() * (double)this.numberOfItems * (double)1.5F;
    }

    public int getNumberOfItems() {
        return this.numberOfItems;
    }

    public String toString() {
        return String.format("Room Service [ID: %s, Description: %s, Items: %d, Final Cost: $%.2f]", this.serviceId, this.description, this.numberOfItems, this.calculateFinalCost());
    }

    public void markStepsCompleted(char[] steps) {
        if (steps != null) {
            for(int i = 0; i < steps.length; ++i) {
                if (steps[i] == '-' || steps[i] == 'P' || steps[i] == 'C') {
                    steps[i] = 'X';
                }
            }

        }
    }
}
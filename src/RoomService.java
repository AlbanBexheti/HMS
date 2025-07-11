public class RoomService extends HotelService {
    private int numberOfItems;
    private static final double ITEM_MULTIPLIER = 1.5;

    public RoomService(String serviceId, String description, double baseCost, int numberOfItems) {
        super(serviceId, description, baseCost);
        this.numberOfItems = numberOfItems;
    }

    @Override
    public double calculateFinalCost() {
        return getBaseCost() * numberOfItems * ITEM_MULTIPLIER;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    @Override
    public String toString() {
        return String.format("Room Service [ID: %s, Description: %s, Items: %d, Final Cost: $%.2f]",
                serviceId, description, numberOfItems, calculateFinalCost());
    }
}
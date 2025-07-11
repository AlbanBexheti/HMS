public class LaundryService extends HotelService {
    private int numberOfGarments;
    private boolean expressService;

    public LaundryService(String serviceId, String description, double baseCost,
                          int numberOfGarments, boolean expressService) {
        super(serviceId, description, baseCost);
        this.numberOfGarments = numberOfGarments;
        this.expressService = expressService;
    }

    @Override
    public double calculateFinalCost() {
        double cost = getBaseCost() * numberOfGarments;
        return expressService ? cost + 15.0 : cost; // $15 express fee
    }

    public int getNumberOfGarments() {
        return numberOfGarments;
    }

    public boolean isExpressService() {
        return expressService;
    }

    @Override
    public String toString() {
        return String.format("Laundry Service [ID: %s, Description: %s, Garments: %d, Express: %s, Final Cost: $%.2f]",
                serviceId, description, numberOfGarments, expressService, calculateFinalCost());
    }
}
public class LaundryService extends HotelService {
    private int numberOfGarments;
    private boolean expressService;

    public LaundryService(String serviceId, String description, double baseCost, int numberOfGarments, boolean expressService) {
        super(serviceId, description, baseCost);
        this.numberOfGarments = numberOfGarments;
        this.expressService = expressService;
    }

    public double calculateFinalCost() {
        double cost = this.getBaseCost() * (double)this.numberOfGarments;
        return this.expressService ? cost + (double)15.0F : cost;
    }

    public int getNumberOfGarments() {
        return this.numberOfGarments;
    }

    public boolean isExpressService() {
        return this.expressService;
    }

    public String toString() {
        return String.format("Laundry Service [ID: %s, Description: %s, Garments: %d, Express: %s, Final Cost: $%.2f]", this.serviceId, this.description, this.numberOfGarments, this.expressService, this.calculateFinalCost());
    }

    public void checkWeightLimit(double[] itemWeights) {
        if (itemWeights != null) {
            double totalWeight = (double)0.0F;

            for(double weight : itemWeights) {
                totalWeight += weight;
            }

            if (totalWeight > (double)20.0F) {
                System.out.println("Warning: Total weight " + totalWeight + "kg exceeds 20kg limit");
            }

        }
    }
}
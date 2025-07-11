public abstract class HotelService implements Chargeable {
    protected String serviceId;
    protected String description;
    protected double baseCost;

    public HotelService(String serviceId, String description, double baseCost) {
        this.serviceId = serviceId;
        this.description = description;
        this.baseCost = baseCost;
    }

    public abstract double calculateFinalCost();

    @Override
    public java.math.BigDecimal getCost() {
        return java.math.BigDecimal.valueOf(calculateFinalCost());
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getDescription() {
        return description;
    }

    protected double getBaseCost() {
        return baseCost;
    }

    @Override
    public abstract String toString();
}

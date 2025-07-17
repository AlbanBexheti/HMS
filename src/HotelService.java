import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class HotelService implements Chargeable {
    protected String serviceId;
    protected String description;
    protected double baseCost;
    private Map<String, Double> discountCodes;

    public HotelService(String serviceId, String description, double baseCost) {
        this.serviceId = serviceId;
        this.description = description;
        this.baseCost = baseCost;
        this.discountCodes = new HashMap<>();
        this.discountCodes.put("SUMMER20", 0.20);
        this.discountCodes.put("WINTER15", 0.15);
        this.discountCodes.put("VIP10", 0.10);
    }

    public abstract double calculateFinalCost();

    public BigDecimal getCost() {
        return BigDecimal.valueOf(this.calculateFinalCost());
    }

    public void applyDiscount(String discountCode) {
        if (this.discountCodes.containsKey(discountCode)) {
            double discount = this.discountCodes.get(discountCode);
            this.baseCost = this.baseCost * (1 - discount);
            System.out.println("Applied " + discountCode + " discount: " + (discount * 100) + "%");
        } else {
            System.out.println("Invalid discount code: " + discountCode);
        }
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public String getDescription() {
        return this.description;
    }

    protected double getBaseCost() {
        return this.baseCost;
    }

    public abstract String toString();

    public void validateDiscountCodes(char[] discountCodes) {
        if (discountCodes != null) {
            for(char code : discountCodes) {
                if (code < 'A' || code > 'Z') {
                    System.out.println("Invalid discount code: " + code + " (must be uppercase A-Z)");
                }
            }
        }
    }

    public static void applyTieredDiscounts(double[] costs, char[] tiers) {
        if (costs == null || tiers == null || costs.length != tiers.length) {
            throw new IllegalArgumentException("Arrays must be non-null and of equal length");
        }

        for(int i = 0; i < costs.length; i++) {
            double originalCost = costs[i];
            double discountedCost = originalCost;

            switch (tiers[i]) {
                case 'A':
                    discountedCost = originalCost * 0.9;
                    break;
                case 'B':
                    discountedCost = originalCost * 0.8;
                    break;
                case 'C':
                    discountedCost = originalCost * 0.7;
                    break;
                default:
                    System.out.println("Unrecognized tier: " + tiers[i]);
            }

            System.out.printf("Item %d: Original $%.2f, Discounted $%.2f%n", i + 1, originalCost, discountedCost);
        }
    }
}
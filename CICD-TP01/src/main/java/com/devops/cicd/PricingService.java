package com.devops.cicd;

public final class PricingService {

    private static final double VIP_DISCOUNT_RATE = 0.10;
    private static final double SHIPPING_COST = 4.99;

    private final PricingConfig config;

    public PricingService(PricingConfig config) {
        this.config = config;
    }

    /** HT -> TTC */
    public double applyVat(double amountExclVat) {
        return amountExclVat * (1 + config.getVatRate() / 100);
    }

    public double applyVipDiscount(double amount, boolean vip) {
        if (vip) {
            return amount * (1 - VIP_DISCOUNT_RATE);
        }
        return amount;
    }

    public double shippingCost(double amount) {
        if (amount >= config.getFreeShippingThreshold()) {
            return 0.0;
        }
        return SHIPPING_COST;
    }

    /**
     * Ordre :
     * 1. TVA
     * 2. Remise VIP
     * 3. Frais de livraison
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        double ttc = applyVat(amountExclVat);
        double discounted = applyVipDiscount(ttc, vip);
        double shipping = shippingCost(discounted);
        return discounted + shipping;
    }
}

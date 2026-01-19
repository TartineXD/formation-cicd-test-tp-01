package com.devops.cicd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    private final PricingConfig fakeConfig = new PricingConfig(20.0, 50.0);
    private final PricingService service = new PricingService(fakeConfig);

    @Test
    void applyVat_shouldAdd20Percent() {
        double result = service.applyVat(100.0);
        assertEquals(120.0, result);
    }

    @Test
    void applyVipDiscount_shouldApply10PercentForVip() {
        double result = service.applyVipDiscount(100.0, true);
        assertEquals(90.0, result);
    }

    @Test
    void applyVipDiscount_shouldNotApplyDiscountForNonVip() {
        double result = service.applyVipDiscount(100.0, false);
        assertEquals(100.0, result);
    }

    @Test
    void shippingCost_shouldBeFreeWhenThresholdReached() {
        double result = service.shippingCost(50.0);
        assertEquals(0.0, result);
    }

    @Test
    void shippingCost_shouldCost499BelowThreshold() {
        double result = service.shippingCost(40.0);
        assertEquals(4.99, result);
    }

    @Test
    void finalTotal_shouldApplyAllRulesInCorrectOrder_forVip() {
        /**
         * HT = 100
         * TTC = 120
         * VIP -10% → 108
         * Livraison gratuite (>= 50)
         */
        double result = service.finalTotal(100.0, true);
        assertEquals(108.0, result);
    }

    @Test
    void finalTotal_shouldApplyShippingForNonVipSmallAmount() {
        /**
         * HT = 30
         * TTC = 36
         * Pas de remise
         * Livraison = 4.99
         */
        double result = service.finalTotal(30.0, false);
        assertEquals(40.99, result);
    }
}

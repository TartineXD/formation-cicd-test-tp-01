package com.devops.cicd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingIntegrationTest {

    @Test
    void fullPricingFlow_withRealConfigFile() {

        PricingConfigLoader loader = new PricingConfigLoader();
        PricingConfig config = loader.load();
        PricingService service = new PricingService(config);

        double amountExclVat = 100.0;
        boolean vip = true;

        double result = service.finalTotal(amountExclVat, vip);

        /**
         * Calcul attendu avec app.properties :
         * vat.rate = 20%
         * free.shipping.threshold = 50
         *
         * 100 HT
         * → 120 TTC
         * → VIP -10% = 108
         * → Livraison gratuite
         */
        assertEquals(108.0, result, 0.001);
    }
}

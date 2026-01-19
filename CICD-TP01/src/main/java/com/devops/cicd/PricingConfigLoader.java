package com.devops.cicd;

import java.io.InputStream;
import java.util.Properties;

public class PricingConfigLoader {

    public PricingConfig load() {
        Properties props = new Properties();

        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("app.properties")) {

            if (is == null) {
                throw new IllegalStateException("app.properties not found in classpath");
            }

            props.load(is);

            double vatRate = Double.parseDouble(
                    required(props, "vatRate")
            );

            double freeShippingThreshold = Double.parseDouble(
                    required(props, "freeShippingThreshold")
            );

            return new PricingConfig(vatRate, freeShippingThreshold);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to load pricing configuration", e);
        }
    }

    private String required(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing required property: " + key
            );
        }
        return value;
    }
}

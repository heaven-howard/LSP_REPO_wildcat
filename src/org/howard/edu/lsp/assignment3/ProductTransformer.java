package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles the transformation logic for products according to business rules.
 * This class encapsulates all transformation rules and provides methods
 * to apply them to Product objects.
 */
public class ProductTransformer {
    
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.90");
    private static final BigDecimal PREMIUM_THRESHOLD = new BigDecimal("500.00");
    private static final String ELECTRONICS_CATEGORY = "Electronics";
    private static final String PREMIUM_ELECTRONICS_CATEGORY = "Premium Electronics";

    /**
     * Transforms a product according to the business rules:
     * 1. Uppercase the name
     * 2. Apply 10% discount for Electronics category
     * 3. Recategorize Electronics to Premium Electronics if price > 500 after discount
     * 4. Calculate price range based on final price
     *
     * @param product the product to transform
     * @return the transformed product, or null if transformation fails
     */
    public Product transform(Product product) {
        if (product == null || !product.isValid()) {
            return null;
        }

        try {
            // Create a copy to avoid modifying the original
            Product transformedProduct = new Product(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getCategory()
            );

            // 1. Uppercase the name
            transformedProduct.setName(product.getName().toUpperCase());

            // 2. Apply discount for Electronics
            BigDecimal finalPrice = applyElectronicsDiscount(transformedProduct);

            // 3. Recategorize if needed
            recategorizeIfNeeded(transformedProduct, finalPrice);

            // 4. Calculate price range
            String priceRange = calculatePriceRange(finalPrice);
            transformedProduct.setPriceRange(priceRange);

            return transformedProduct;
        } catch (Exception e) {
            return null; // Skip malformed products
        }
    }

    /**
     * Applies the 10% discount for Electronics category and rounds to 2 decimal places.
     *
     * @param product the product to apply discount to
     * @return the final price after discount and rounding
     */
    private BigDecimal applyElectronicsDiscount(Product product) {
        BigDecimal originalPrice = product.getPrice();
        BigDecimal finalPrice = originalPrice;

        if (ELECTRONICS_CATEGORY.equals(product.getCategory())) {
            finalPrice = originalPrice.multiply(DISCOUNT_RATE);
        }

        // Round HALF_UP to 2 decimals
        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
        product.setPrice(finalPrice);

        return finalPrice;
    }

    /**
     * Recategorizes Electronics to Premium Electronics if price exceeds threshold.
     *
     * @param product the product to potentially recategorize
     * @param finalPrice the final price after discount
     */
    private void recategorizeIfNeeded(Product product, BigDecimal finalPrice) {
        if (ELECTRONICS_CATEGORY.equals(product.getCategory()) && 
            finalPrice.compareTo(PREMIUM_THRESHOLD) > 0) {
            product.setCategory(PREMIUM_ELECTRONICS_CATEGORY);
        }
    }

    /**
     * Calculates the price range based on the final price.
     * Ranges:
     * - 0.00–10.00 → Low
     * - 10.01–100.00 → Medium
     * - 100.01–500.00 → High
     * - 500.01+ → Premium
     *
     * @param finalPrice the final price to categorize
     * @return the price range category
     */
    private String calculatePriceRange(BigDecimal finalPrice) {
        if (finalPrice.compareTo(new BigDecimal("0.00")) >= 0 && 
            finalPrice.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (finalPrice.compareTo(new BigDecimal("10.00")) > 0 && 
                   finalPrice.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (finalPrice.compareTo(new BigDecimal("100.00")) > 0 && 
                   finalPrice.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}

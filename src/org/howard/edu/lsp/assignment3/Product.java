package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product with its basic attributes and transformation capabilities.
 * This class encapsulates product data and provides methods for data validation
 * and basic transformations.
 */
public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;

    /**
     * Constructs a Product with the specified attributes.
     *
     * @param productId the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category of the product
     */
    public Product(String productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = null; // Will be set during transformation
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the new product price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the product category.
     *
     * @return the product category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     *
     * @param category the new product category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the price range.
     *
     * @return the price range
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets the price range.
     *
     * @param priceRange the new price range
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Validates that the product has all required fields.
     *
     * @return true if the product is valid, false otherwise
     */
    public boolean isValid() {
        return productId != null && !productId.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               price != null && category != null && !category.trim().isEmpty();
    }

    /**
     * Returns a string representation of the product in CSV format.
     *
     * @return the product as a CSV string
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", 
            productId, name, price.toPlainString(), category, priceRange);
    }
}

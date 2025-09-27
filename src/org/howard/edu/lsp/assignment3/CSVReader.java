package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading CSV files and converting them to Product objects.
 * This class encapsulates all CSV reading logic and provides methods
 * to parse CSV data into Product objects.
 */
public class CSVReader {
    
    private static final int EXPECTED_COLUMNS = 4;

    /**
     * Reads a CSV file and converts it to a list of Product objects.
     * Skips the header row and malformed rows.
     *
     * @param inputPath the path to the input CSV file
     * @return a list of valid Product objects
     * @throws IOException if there's an error reading the file
     */
    public List<Product> readProducts(Path inputPath) throws IOException {
        List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // Skip header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                Product product = parseProductLine(line);
                if (product != null) {
                    products.add(product);
                }
            }
        }
        
        return products;
    }

    /**
     * Parses a single CSV line into a Product object.
     * Returns null if the line is malformed or invalid.
     *
     * @param line the CSV line to parse
     * @return a Product object or null if parsing fails
     */
    private Product parseProductLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",", -1);
        if (parts.length < EXPECTED_COLUMNS) {
            return null; // Skip malformed rows
        }

        // Trim all parts
        String[] trimmedParts = trimAll(parts);
        
        String productId = trimmedParts[0];
        String name = trimmedParts[1];
        String priceStr = trimmedParts[2];
        String category = trimmedParts[3];

        // Validate required fields
        if (productId.isEmpty() || name.isEmpty() || priceStr.isEmpty() || category.isEmpty()) {
            return null;
        }

        // Parse price
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            return null; // Skip malformed price
        }

        return new Product(productId, name, price, category);
    }

    /**
     * Trims all elements in a string array.
     *
     * @param parts the array to trim
     * @return a new array with trimmed elements
     */
    private String[] trimAll(String[] parts) {
        String[] trimmed = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            trimmed[i] = parts[i] == null ? "" : parts[i].trim();
        }
        return trimmed;
    }
}

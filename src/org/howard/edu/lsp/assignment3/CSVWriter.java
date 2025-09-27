package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles writing Product objects to CSV files.
 * This class encapsulates all CSV writing logic and provides methods
 * to write Product objects to CSV format.
 */
public class CSVWriter {
    
    private static final String CSV_HEADER = "ProductID,Name,Price,Category,PriceRange";

    /**
     * Writes a list of products to a CSV file.
     * Creates the output directory if it doesn't exist.
     *
     * @param outputPath the path to the output CSV file
     * @param products the list of products to write
     * @throws IOException if there's an error writing the file
     */
    public void writeProducts(Path outputPath, List<Product> products) throws IOException {
        // Ensure parent directory exists
        Path parent = outputPath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            // Write header
            writer.write(CSV_HEADER);
            writer.newLine();
            
            // Write products
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Writes only the CSV header to a file.
     * Used when there are no products to write.
     *
     * @param outputPath the path to the output CSV file
     * @throws IOException if there's an error writing the file
     */
    public void writeHeaderOnly(Path outputPath) throws IOException {
        writeProducts(outputPath, List.of());
    }
}

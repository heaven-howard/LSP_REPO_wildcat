package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Main ETL pipeline class that orchestrates the Extract-Transform-Load process.
 * This class coordinates the CSVReader, ProductTransformer, and CSVWriter
 * to process product data according to business rules.
 */
public class ETLPipeline {
    
    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    
    private final CSVReader csvReader;
    private final ProductTransformer productTransformer;
    private final CSVWriter csvWriter;

    /**
     * Constructs an ETLPipeline with default components.
     */
    public ETLPipeline() {
        this.csvReader = new CSVReader();
        this.productTransformer = new ProductTransformer();
        this.csvWriter = new CSVWriter();
    }

    /**
     * Constructs an ETLPipeline with custom components.
     * This constructor allows for dependency injection and testing.
     *
     * @param csvReader the CSV reader component
     * @param productTransformer the product transformer component
     * @param csvWriter the CSV writer component
     */
    public ETLPipeline(CSVReader csvReader, ProductTransformer productTransformer, CSVWriter csvWriter) {
        this.csvReader = csvReader;
        this.productTransformer = productTransformer;
        this.csvWriter = csvWriter;
    }

    /**
     * Main method to run the ETL pipeline.
     * This method handles file validation, error reporting, and result display.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Path inputPath = Paths.get(INPUT_FILE);
        Path outputPath = Paths.get(OUTPUT_FILE);

        if (!Files.exists(inputPath)) {
            System.err.println("ERROR: Input file not found: " + inputPath.toString());
            System.err.println("Ensure you run from the project root and that the data/ folder exists.");
            return;
        }

        try {
            ETLPipeline pipeline = new ETLPipeline();
            ETLResult result = pipeline.runPipeline(inputPath, outputPath);
            
            System.out.println("Summary:");
            System.out.println("- Rows read: " + result.getRowsRead());
            System.out.println("- Rows transformed: " + result.getRowsTransformed());
            System.out.println("- Rows skipped: " + result.getRowsSkipped());
            System.out.println("- Output written to: " + outputPath.toString());
        } catch (IOException e) {
            System.err.println("ERROR during ETL: " + e.getMessage());
        }
    }

    /**
     * Runs the complete ETL pipeline process.
     * This method orchestrates the extract, transform, and load phases.
     *
     * @param inputPath the path to the input CSV file
     * @param outputPath the path to the output CSV file
     * @return an ETLResult containing process statistics
     * @throws IOException if there's an error during file operations
     */
    public ETLResult runPipeline(Path inputPath, Path outputPath) throws IOException {
        // Extract phase
        List<Product> products = csvReader.readProducts(inputPath);
        
        if (products.isEmpty()) {
            // No data to process, write header only
            csvWriter.writeHeaderOnly(outputPath);
            return new ETLResult(0, 0, 0);
        }

        int rowsRead = products.size();
        List<Product> transformedProducts = new ArrayList<>();
        int skipped = 0;

        // Transform phase
        for (Product product : products) {
            Product transformed = productTransformer.transform(product);
            if (transformed == null) {
                skipped++;
            } else {
                transformedProducts.add(transformed);
            }
        }

        // Load phase
        csvWriter.writeProducts(outputPath, transformedProducts);
        
        return new ETLResult(rowsRead, transformedProducts.size(), skipped);
    }
}

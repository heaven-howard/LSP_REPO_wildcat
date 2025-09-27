package org.howard.edu.lsp.assignment3;

/**
 * Encapsulates the result of an ETL pipeline execution.
 * This class holds statistics about the ETL process including
 * the number of rows read, transformed, and skipped.
 */
public class ETLResult {
    private final int rowsRead;
    private final int rowsTransformed;
    private final int rowsSkipped;

    /**
     * Constructs an ETLResult with the specified statistics.
     *
     * @param rowsRead the number of rows read from input
     * @param rowsTransformed the number of rows successfully transformed
     * @param rowsSkipped the number of rows skipped due to errors
     */
    public ETLResult(int rowsRead, int rowsTransformed, int rowsSkipped) {
        this.rowsRead = rowsRead;
        this.rowsTransformed = rowsTransformed;
        this.rowsSkipped = rowsSkipped;
    }

    /**
     * Gets the number of rows read from input.
     *
     * @return the number of rows read
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Gets the number of rows successfully transformed.
     *
     * @return the number of rows transformed
     */
    public int getRowsTransformed() {
        return rowsTransformed;
    }

    /**
     * Gets the number of rows skipped due to errors.
     *
     * @return the number of rows skipped
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }

    /**
     * Returns a string representation of the ETL result.
     *
     * @return a formatted string with the ETL statistics
     */
    @Override
    public String toString() {
        return String.format("ETLResult{rowsRead=%d, rowsTransformed=%d, rowsSkipped=%d}", 
            rowsRead, rowsTransformed, rowsSkipped);
    }
}

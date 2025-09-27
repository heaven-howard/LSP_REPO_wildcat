# Assignment 3 Reflection: Object-Oriented Redesign

## Overview
This reflection compares the Assignment 2 (A2) and Assignment 3 (A3) implementations of the ETL pipeline, focusing on the object-oriented improvements made in A3.

## Design Differences

### Assignment 2: Procedural Approach
- **Single Class**: All functionality was contained in one `ETLPipeline` class
- **Static Methods**: All methods were static, making the code procedural rather than object-oriented
- **Monolithic Structure**: The entire ETL process was handled by a single class with multiple responsibilities
- **Tight Coupling**: All components were tightly coupled within the single class

### Assignment 3: Object-Oriented Approach
- **Multiple Classes**: Separated concerns into 6 distinct classes
- **Instance Methods**: Used instance methods and dependency injection
- **Single Responsibility**: Each class has one clear responsibility
- **Loose Coupling**: Classes are loosely coupled and can be tested independently

## Object-Oriented Improvements

### 1. Encapsulation
**A2**: Data and methods were mixed together in static methods
**A3**: Each class encapsulates its own data and behavior:
- `Product` encapsulates product attributes and validation
- `ETLResult` encapsulates pipeline execution statistics
- Each class has private fields with public getters/setters

### 2. Class Design
**A2**: One monolithic class
**A3**: Six focused classes:
- `Product`: Represents a single product with validation
- `ProductTransformer`: Handles all transformation logic
- `CSVReader`: Manages CSV file reading operations
- `CSVWriter`: Manages CSV file writing operations
- `ETLResult`: Encapsulates pipeline results
- `ETLPipeline`: Orchestrates the entire process

### 3. Single Responsibility Principle
**A2**: The `ETLPipeline` class handled:
- File I/O operations
- Data transformation logic
- Business rule application
- Result reporting

**A3**: Responsibilities are clearly separated:
- `CSVReader`: Only handles reading CSV files
- `CSVWriter`: Only handles writing CSV files
- `ProductTransformer`: Only handles transformation logic
- `Product`: Only handles product data and validation
- `ETLPipeline`: Only orchestrates the process

### 4. Dependency Injection
**A2**: All dependencies were hardcoded
**A3**: `ETLPipeline` constructor accepts dependencies, enabling:
- Easier testing with mock objects
- Better flexibility in component selection
- Reduced coupling between classes

### 5. Data Validation
**A2**: Validation was scattered throughout the transformation process
**A3**: `Product` class centralizes validation with `isValid()` method, making validation consistent and reusable

## Object-Oriented Concepts Used

### 1. Objects
- `Product` objects represent real-world entities
- `ETLResult` objects encapsulate process outcomes
- Each class instance represents a specific component of the system

### 2. Classes
- Each class defines a blueprint for creating objects
- Classes encapsulate both data (fields) and behavior (methods)
- Clear class hierarchies and relationships

### 3. Encapsulation
- Private fields with controlled access through public methods
- Data hiding prevents direct access to internal state
- Methods provide controlled interfaces to class functionality

### 4. Inheritance
- While not extensively used, the design supports inheritance
- `ETLResult` could be extended for different result types
- `Product` could be extended for different product types

### 5. Polymorphism
- The design supports polymorphism through interfaces
- `ProductTransformer` could be replaced with different transformer implementations
- `CSVReader` and `CSVWriter` could be replaced with different I/O implementations

## Testing Verification

### Functional Testing
Both implementations were tested with identical scenarios:

1. **Normal Operation**: 
   - Input: `data/products.csv` with 6 products
   - Output: Identical `data/transformed_products.csv`
   - Both produce same transformation results

2. **Missing File**:
   - Both display identical error messages
   - Both exit gracefully without crashing

3. **Empty File**:
   - Both create output file with header only
   - Both report 0 rows processed

### Output Verification
The final output files are byte-for-byte identical, confirming that A3 maintains the exact same functionality as A2 while providing a much cleaner, more maintainable object-oriented design.

## Benefits of Object-Oriented Design

1. **Maintainability**: Each class has a single responsibility, making changes easier
2. **Testability**: Individual components can be unit tested in isolation
3. **Reusability**: Classes like `Product` and `ProductTransformer` can be reused
4. **Extensibility**: New transformation rules can be added without modifying existing code
5. **Readability**: Code is more self-documenting with clear class and method names
6. **Flexibility**: Dependencies can be injected, allowing for different implementations

## Conclusion

The object-oriented redesign successfully transforms a procedural ETL pipeline into a well-structured, maintainable system while preserving identical functionality. The separation of concerns, encapsulation, and single responsibility principle make the code more professional and suitable for larger-scale applications.

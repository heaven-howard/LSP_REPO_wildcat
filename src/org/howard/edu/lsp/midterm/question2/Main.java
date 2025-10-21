package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator overloaded methods.
 * 
 * Method overloading is the better design choice here because it provides a clean,
 * intuitive API where users can call the same method name "area" for different shapes
 * without having to remember different method names. This follows the principle of
 * polymorphism and makes the code more readable and maintainable.
 */
public class Main {
    
    public static void main(String[] args) {
        // Demonstrate all overloaded area methods
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Demonstrate exception handling
        try {
            AreaCalculator.area(-1.0); // This will throw an IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

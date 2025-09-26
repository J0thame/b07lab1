import java.io.*;

public class TestPolynomialFromFile {
    public static void main(String[] args) {
        try {
            File f = new File("test.txt"); // file contains: +5.0+11.0x1+17.0x2+3.0x3
            Polynomial p = new Polynomial(f);

            // Print polynomial
            System.out.print("Polynomial read from file: ");
            p.printPoly();

            // Evaluate at x=2
            double val = p.evaluate(2);
            System.out.println("Polynomial evaluated at x=2: " + val);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}




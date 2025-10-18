import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Product {
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," +
                supplierName + "," + quantity + "," + price;
    }
    public String getSearchKey() {
        return productID;
    }
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true))
        {
            writer.write(lineRepresentation() + "\n");
            System.out.println("Product saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving product: " + e.getMessage());
        }
    }
import java.io.FileWriter;

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
    public String getSearchKey() {
        return productID;
    }
    public float getPrice() {
        return this.price;
    }



    }


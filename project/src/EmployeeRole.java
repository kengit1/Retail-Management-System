import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {
    private final ProductDatabase productsDatabase;
    private final CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID,String productName,String manufacturerName,String supplierName,int quantity){
        float placeholderPrice = 1.0f;

        Product newProduct=new Product(productID,productName,manufacturerName,supplierName,quantity,placeholderPrice);
        productsDatabase.insertRecord(newProduct);
    }

    public Product[] getListOfProducts(){
        int size=productsDatabase.returnAllRecords().size();

        return productsDatabase.returnAllRecords().toArray(new Product[size]);
    }


    public CustomerProduct[] getListOfPurchasingOperations(){
        int size = customerProductDatabase.returnAllRecords().size();
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[size]);
    }

    public boolean purchaseProduct(String customerSSN,String productID,LocalDate purchaseDate) {
        Product product = productsDatabase.getRecord(productID);

        if (product==null || product.getQuantity()<= 0) {
            return false;
        }
        product.setQuantity(product.getQuantity() - 1);
        CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(newPurchase);

        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            return -1.0;
        }

        Product product = productsDatabase.getRecord(productID);
        if (product == null) {
            return -1.0;
        }


        String key = customerSSN + "," + productID + "," + purchaseDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        CustomerProduct purchaseRecord=customerProductDatabase.getRecord(key);

        if (purchaseRecord == null) {
            return -1.0;
        }
        if (ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14) {
            return -1.0;
        }
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);
        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        boolean updated = false;

        for (CustomerProduct record : customerProductDatabase.returnAllRecords()) {
            if (record.getCustomerSSN().equals(customerSSN) &&
                    record.getPurchaseDate().isEqual(purchaseDate) &&
                    !record.isPaid())
            {
                record.setPaid(true);
                updated=true;

            }
        }
        return updated;
    }
    public void removeProduct(String key){
        productsDatabase.deleteRecord(key);
    }


    public void logout() {
        System.out.println("\nLogging out and saving all changes...");
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record {
    private final String customerSSN,productID;
    private final LocalDate purchaseDate;
    private boolean paid = false;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String getCustomerSSN(){
        return customerSSN;
    }
    public String getProductID(){
        return productID;
    }
    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }
    @Override
    public String toString(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + dateformatter.format(purchaseDate) + "," + paid;
    }
    @Override
    public String lineRepresentation(){
        return this.toString();
    }
    public boolean isPaid(){
        return paid;
    }
    public void setPaid(boolean paid){
        this.paid = paid;
    }

    @Override
     public String getSearchKey(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + dateformatter.format(purchaseDate);
    }

    public static void main(String[] args){
        LocalDate today = LocalDate.of(2022,2,4);
        CustomerProduct product1 = new CustomerProduct("7845345678","P2568",today);
        CustomerProductDatabase database1 = new CustomerProductDatabase("CustomersProducts.txt");
        database1.readFromFile();
        database1.insertRecord(product1);
        database1.saveToFile();
        /*System.out.println(database1.contains("12345433,1253,18-10-2025"));
        System.out.println(database1.getRecord("123456789,1234,18-10-2025"));*/

        System.out.println(database1.returnAllRecords());
    }

}

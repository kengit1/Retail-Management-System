import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record {
    private final String customerSSN,productID;
    private final LocalDate purchaseDate;
    private boolean paid;

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
    public String lineRepresentation(){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN + "," + productID + "," + dateformatter.format(purchaseDate) + "," + paid;
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

}

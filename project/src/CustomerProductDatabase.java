import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CustomerProductDatabase extends AbstractDatabase<CustomerProduct>{
    public CustomerProductDatabase(String filename){
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
            System.out.println("Invalid record in the CustomerProductDatabase");
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(parts[2], formatter);
        CustomerProduct Product = new CustomerProduct(parts[0].trim(), parts[1].trim(), date);
        boolean paid;
        paid = !parts[3].equals("false");
        Product.setPaid(paid);
        return Product;
    }
    @Override
    public boolean contains(String key){
        return returnAllRecords().stream().anyMatch(record -> key.equals(record.getSearchKey()));
    }
    @Override
    public CustomerProduct getRecord(String key){
        return returnAllRecords().stream().filter(record -> key.equals(record.getSearchKey())).findFirst().orElse(null);
    }
}

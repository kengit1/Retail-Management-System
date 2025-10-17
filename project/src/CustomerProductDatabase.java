import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CustomerProductDatabase extends AbstractDatabase<CustomerProduct>{
    public CustomerProductDatabase(String filename){
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 3) {
            System.out.println("Invalid record in the CustomerProductDatabase");
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(parts[2], formatter);
        return new CustomerProduct(
                parts[0].trim(),
                parts[1].trim(),
                date
                );
    }
    @Override
    public boolean contains(String key){
        return records.stream().anyMatch(record -> key.equals(record.getSearchKey()));
    }
    @Override
    public CustomerProduct getRecord(String key){
        return records.stream().filter(record -> key.equals(record.getSearchKey())).findFirst().orElse(null);
    }
}

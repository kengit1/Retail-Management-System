import java.io.*;
import java.util.ArrayList;

public class ProductDatabase {
    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Product product = createRecordFrom(line);
                    if (product != null) {
                        records.add(product);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public Product createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                String manufacturer = parts[2].trim();
                String supplier = parts[3].trim();
                int quantity = Integer.parseInt(parts[4].trim());
                float price = Float.parseFloat(parts[5].trim());
                return new Product(id, name, manufacturer, supplier, quantity, price);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in line: " + line);
        }
        return null;
    }

    public ArrayList<Product> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Product getRecord(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return product;
            }
        }
        return null;
    }

    public void insertRecord(Product record) {
        if (record != null && !contains(record.getSearchKey())) {
            records.add(record);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product with ID '" + record.getSearchKey() + "' already exists.");
        }
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                System.out.println("Product with ID '" + key + "' deleted successfully.");
                return;
            }
        }
        System.out.println("Product with ID '" + key + "' not found.");
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, false))) {
            for (Product product : records) {
                pw.println(product.lineRepresentation());
            }
            System.out.println("All data saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

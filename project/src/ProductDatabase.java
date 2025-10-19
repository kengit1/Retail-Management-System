public class ProductDatabase extends AbstractDatabase<Product>
{
    ProductDatabase(String fileName)
    {
        super(fileName);
    }

    @Override
    public Product createRecordFrom(String lineRecord) {
        String[] parts = lineRecord.split(",",-1) ;
        if(parts.length != 6){
            System.out.println("Invalid record in the EmployeeDatabase");
            return null ; // not a safe practice
        }
        return new Product(
                parts[0].trim()
                , parts[1].trim()
                , parts[2].trim()
                , parts[3].trim()
                , Integer.parseInt(parts[4].trim())
                , Float.parseFloat(parts[5].trim())
        );

    }

    @Override
    public boolean contains(String Key) {
        return returnAllRecords().stream().anyMatch(record -> Key.equals(record.getSearchKey())) ;
    }

    @Override
    public Product getRecord(String Key) {
        return returnAllRecords().stream().filter(
                        record -> Key.equals(record.getSearchKey()))
                .findFirst().orElse(null) ;
    }
}
public class EmployeeUserDatabase extends AbstractDatabase<EmployeeUser>{
    EmployeeUserDatabase(String fileName)
    {
        super(fileName);
    }

    @Override
    public EmployeeUser createRecordFrom(String lineRecord) {
        String[] parts = lineRecord.split(",",-1) ;
        if(parts.length != 5){
            System.out.println("Invalid record in the EmployeeDatabase");
            return null ; // not a safe practice
        }
        return new EmployeeUser(
                  parts[0].trim()
                , parts[1].trim()
                , parts[2].trim()
                , parts[3].trim()
                , parts[4].trim()
                );
    }
    @Override
    public boolean contains(String Key)
    {
        return records.stream().anyMatch(record -> Key.equals(record.getSearchKey())) ;
        // better to avoid the null from the records
    }
    @Override
    public EmployeeUser getRecord(String Key)
    {
        return records.stream().filter(
                record -> Key.equals(record.getSearchKey()))
                .findFirst().orElse(null) ;
        /* converted the Collection of ArrayList into a Stream of data which
        *  Java will do searching on it instead of the OG For loop ❤️
        *  */
    }

}

public class EmployeeUser implements Record {
    private final String employeeId,name ;
    private String email,address,phoneNumber ;

    EmployeeUser(String employeeId , String name , String email, String address , String phoneNumber)
    {
        this.employeeId = employeeId ;
        this.name = name ;
        this.email = email ;
        this.address =address;
        this.phoneNumber = phoneNumber ;
    }
    // setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
/*
    public static void main(String[] args) //test main
    {
        EmployeeUser user = new EmployeeUser("Emp101","AkrmSaad","akrSad@gmail.com",
                "15 street street","01090132139") ;
        EmployeeUserDatabase database = new EmployeeUserDatabase("EmployeeUserDatabase.txt") ;
        database.readFromFile();
        database.insertRecord(user);
        database.saveToFile();

        System.out.println(database.returnAllRecords());
    }
*/
    @Override
    public String toString()
    {
        return employeeId+","+name+","+email+","+address+","+phoneNumber ;
    }
    @Override
    public String lineRepresentation() {
        return this.toString() ;
    }

    @Override
    public String getSearchKey() {
        return employeeId;
    }
}

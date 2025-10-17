public class AdminRole {
    private final EmployeeUserDatabase dataBase;

    AdminRole() {
        dataBase = new EmployeeUserDatabase("EmployeeUserDatabase.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser user = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        dataBase.readFromFile();
        dataBase.insertRecord(user);
    }

    public EmployeeUser[] getListOfEmployees() {
        dataBase.readFromFile();
        int x = dataBase.returnAllRecords().size();
        return dataBase.returnAllRecords().toArray(new EmployeeUser[x]);
    }


    public void removeEmployee(String key) {
        dataBase.readFromFile();
        dataBase.deleteRecord(key);
    }

    public void logout() {
        dataBase.saveToFile();
    }
}
//this is Omar-_ Hesham//
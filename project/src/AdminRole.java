public class AdminRole {
    private final EmployeeUserDatabase dataBase;

    AdminRole() {
        dataBase = new EmployeeUserDatabase("EmployeeUserDatabase.txt");
        dataBase.readFromFile();
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser user = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        dataBase.insertRecord(user);
    }

    public EmployeeUser[] getListOfEmployees() {
        int x = dataBase.returnAllRecords().size();
        return dataBase.returnAllRecords().toArray(new EmployeeUser[x]);
    }


    public void removeEmployee(String key) {
        dataBase.deleteRecord(key);
    }

    public void logout() {
        dataBase.saveToFile();
    }
}
//this is Omar-_ Hesham
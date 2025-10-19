import java.util.*;
import java.time.LocalDate;
import java.io.*;
import java.time.format.DateTimeFormatter;



public class Menu {

    // ANSI Escape Codes for Text Color and Reset
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // ANSI Background Colors (Optional)
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // Special Styling (Optional)
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BOLD = "\u001B[1m";

    private static final Scanner s=new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public static void main(String[] args) {


        boolean flag=true;
        while(flag) {
            System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "===================================================================" + ANSI_RESET);
            System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "||" + ANSI_BOLD + "               INVENTORY MANAGEMENT SYSTEM                       " + ANSI_RESET + ANSI_CYAN_BACKGROUND + ANSI_BLACK + "||" + ANSI_RESET);
            System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "===================================================================" + ANSI_RESET);
            System.out.println("\n" + ANSI_GREEN + "--- MAIN ACCESS MENU --------------------------------------------" + ANSI_RESET);
            System.out.println("1. Login as " + ANSI_BOLD + "Administrator" + ANSI_RESET);
            System.out.println("2. Login as " + ANSI_BOLD + "Retail Employee" + ANSI_RESET);
            System.out.println("3. " + ANSI_RED + "Exit System" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.print(ANSI_BRIGHT_YELLOW + "Enter your choice: " + ANSI_RESET);
            int choice;
            do {

                choice =s.nextInt();
                s.nextLine();
                if (choice < 1 || choice > 3)
                {
                    System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "===================================================================" + ANSI_RESET);
                    System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "||" + ANSI_BOLD + "               INVENTORY MANAGEMENT SYSTEM                       " + ANSI_RESET + ANSI_CYAN_BACKGROUND + ANSI_BLACK + "||" + ANSI_RESET);
                    System.out.println(ANSI_CYAN_BACKGROUND + ANSI_BLACK + "===================================================================" + ANSI_RESET);
                    System.out.println("\n" + ANSI_GREEN + "--- MAIN ACCESS MENU --------------------------------------------" + ANSI_RESET);
                    System.out.println("1. Login as " + ANSI_BOLD + "Administrator" + ANSI_RESET);
                    System.out.println("2. Login as " + ANSI_BOLD + "Retail Employee" + ANSI_RESET);
                    System.out.println("3. " + ANSI_RED + "Exit System" + ANSI_RESET);
                    System.out.println(ANSI_GREEN + "-----------------------------------------------------------------" + ANSI_RESET);
                    System.out.print(ANSI_RED + "Enter A Choice From (1-3): " + ANSI_RESET); // Changed color for error prompt
                }
            } while (choice < 1 || choice > 3);
            switch (choice) {
                case 1:
                    String password;
                    int k = 0;
                    do {
                        k++;
                        System.out.print("Enter Password: ");
                        password = s.nextLine();
                    } while (!Objects.equals(password, "1234") && k < 3); // not clean
                    if (k >= 3) {
                        System.out.println(ANSI_RED + "Wrong password entered please try later " + ANSI_RESET);
                        return;
                    }
                    adminLogin();
                    break;
                case 2:
                    EmployeeUserDatabase database = new EmployeeUserDatabase("EmployeeUserDatabase.txt");
                    database.readFromFile();
                    String id;
                    int l=0;
                    do {
                        l++;
                        System.out.print("Enter your Employee Id: ");
                        id = s.nextLine();

                    } while (!database.contains(id)&& l<3);
                    if (l >= 3) {
                        System.out.println(ANSI_RED + "Wrong Id entered please try later " + ANSI_RESET);
                        return;
                    }
                    employeeLogin();
                    break;
                case 3:
                    flag = false;
                    System.out.println(ANSI_RED + "\n[EXIT] System is shutting down. Goodbye!" + ANSI_RESET);
                    break;
                default:

            }

        }

    }

    private static void adminLogin() {
        AdminRole admin = new AdminRole();
        while (true) {


            System.out.println("\n" + ANSI_PURPLE + "--- ADMINISTRATOR MENU ------------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.println("1. Add New Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. " + ANSI_RED + "Remove Employee" + ANSI_RESET);
            System.out.println("4. " + ANSI_BOLD + "Logout and Save Changes" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.print(ANSI_BRIGHT_YELLOW + "Enter your choice (1-4): " + ANSI_RESET);

            int choice = s.nextInt();


            switch (choice) {
                case 1:
                    Random random = new Random();
                    int randomInt = random.nextInt(10000);
                    String str = String.format("%04d", randomInt);
                    EmployeeUserDatabase database1 = new EmployeeUserDatabase("EmployeeUserDatabase.txt");
                    database1.readFromFile();
                    while(database1.contains(str)){
                        randomInt = (randomInt + 1) % 10000;
                        str = String.format("%04d", randomInt);
                    }
                    //random id generation.

                    System.out.println("\n" + ANSI_BLUE + "--- ADD NEW EMPLOYEE --------------------------------------------" + ANSI_RESET);
                    s.nextLine();
                    System.out.print("Enter Name: ");
                    String name = s.nextLine();
                    System.out.print("Enter Email: ");
                    String email = s.nextLine();
                    System.out.print("Enter Address: ");
                    String address = s.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = s.nextLine();
                    admin.addEmployee(str, name, email, address, phone);
                    System.out.println(ANSI_GREEN + "\n[SUCCESS] Employee added with ID: " + str + ANSI_RESET);
                    break;
                case 2:
                    System.out.println("\n" + ANSI_BLUE + "--- LIST OF EMPLOYEES -------------------------------------------" + ANSI_RESET);
                    EmployeeUser[] employees = admin.getListOfEmployees();

                    if (employees.length == 0) {
                        System.out.println(ANSI_YELLOW + "[INFO] No employee records found." + ANSI_RESET);
                        // Removed 'return' here to stay in the admin menu
                        break;
                    }

                    System.out.printf(ANSI_BOLD + ANSI_BLUE + "%-10s %-20s %-25s %-20s %-15s%n" + ANSI_RESET, "ID", "Name", "Email", "Address", "Phone");
                    System.out.println(ANSI_BLUE + "---------------------------------------------------------------------------------------------------" + ANSI_RESET);
                    for (EmployeeUser emp : employees) {
                        String[] parts = emp.lineRepresentation().split(",");

                        System.out.printf("%-10s %-20s %-25s %-20s %-15s%n",
                                parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());

                    }
                    System.out.println(ANSI_BLUE + "---------------------------------------------------------------------------------------------------" + ANSI_RESET);
                    break;
                case 3:
                    s.nextLine();
                    System.out.print(ANSI_RED + "Enter Employee's ID to remove: " + ANSI_RESET);
                    String Key = s.nextLine();
                    admin.removeEmployee(Key);
                    break;
                case 4:
                    admin.logout();
                    System.out.println(ANSI_CYAN + "\n[LOGOUT] Administrator session ended. Employee data saved." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_RED + "\n[INVALID INPUT] Please enter a number from 1 to 4." + ANSI_RESET);
            }
        }
    }

    private static void employeeLogin() {
        EmployeeRole employee = new EmployeeRole();
        while (true) {
            System.out.println("\n" + ANSI_PURPLE + "--- EMPLOYEE OPERATIONS MENU ------------------------------------" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.println("1. Add New Product to Inventory");
            System.out.println("2. " + ANSI_RED + "Remove a Product" + ANSI_RESET);
            System.out.println("3. View All Products");
            System.out.println("4. View All Purchasing Operations");
            System.out.println("5. " + ANSI_GREEN + "Process New Product Purchase (Sale)" + ANSI_RESET);
            System.out.println("6. Process Product Return");
            System.out.println("7. Apply Payment for Purchase");
            System.out.println("8. " + ANSI_BOLD + "Logout and Save Changes" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------" + ANSI_RESET);
            System.out.print(ANSI_BRIGHT_YELLOW + "Enter your choice (1-8): " + ANSI_RESET);
            int num  ;
            num=s.nextInt();

            switch(num)  {
                case 1:
                    System.out.println("\n" + ANSI_BLUE + "--- ADD NEW PRODUCT ---------------------------------------------" + ANSI_RESET);
                    s.nextLine();
                    System.out.print("Enter Product ID: ");
                    String id=s.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name=s.nextLine();
                    System.out.print("Enter Manufacturer Name: ");
                    String manufacturer=s.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String supplier=s.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity=s.nextInt();
                    employee.addProduct(id,name,manufacturer,supplier,quantity);
                    System.out.println(ANSI_GREEN + "\n[SUCCESS] Product added successfully. Changes will be saved on logout." + ANSI_RESET);
                    break;
                case 2:
                    System.out.println("\n" + ANSI_BLUE + "--- REMOVE PRODUCT ----------------------------------------------" + ANSI_RESET);
                    System.out.print(ANSI_RED + "Enter the Product ID of the item to remove: " + ANSI_RESET);
                    s.nextLine() ;
                    String productIdToRemove = s.nextLine();

                    employee.removeProduct(productIdToRemove);

                    System.out.println(ANSI_YELLOW + "[INFO] Removal command for Product ID '" + productIdToRemove + "' has been executed." + ANSI_RESET);
                    break;
                case 3:
                    System.out.println("\n" + ANSI_BLUE + "--- LIST OF ALL PRODUCTS IN INVENTORY ---------------------------" + ANSI_RESET);
                    Product[] products = employee.getListOfProducts();
                    if (products.length == 0) {
                        System.out.println(ANSI_YELLOW + "[INFO] No product records found." + ANSI_RESET);
                        // Removed 'return' here to stay in the employee menu
                        break;
                    }
                    // Header
                    System.out.printf(ANSI_BOLD + ANSI_BLUE + "%-10s %-20s %-15s %-15s %-8s %-8s%n" + ANSI_RESET, "ID", "Name", "Manufacturer", "Supplier", "Qty", "Price");
                    System.out.println(ANSI_BLUE + "--------------------------------------------------------------------------------" + ANSI_RESET);

                    for (Product prod : products) {
                        String[] parts = prod.lineRepresentation().split(","); // Split the data

                        System.out.printf("%-10s %-20s %-15s %-15s %-8s %-8s%n",
                                parts[0].trim(),
                                parts[1].trim(),
                                parts[2].trim(),
                                parts[3].trim(),
                                parts[4].trim(),
                                parts[5].trim());
                    }
                    System.out.println(ANSI_BLUE + "--------------------------------------------------------------------------------" + ANSI_RESET);

                    break;
                case 4:
                    System.out.println("\n" + ANSI_BLUE + "--- LIST OF PURCHASING OPERATIONS (SALES) -----------------------" + ANSI_RESET);
                    CustomerProduct[] purchases = employee.getListOfPurchasingOperations();

                    if (purchases.length == 0) {
                        System.out.println(ANSI_YELLOW + "[INFO] No purchasing operations found." + ANSI_RESET);
                        // Removed 'return' here to stay in the employee menu
                        break;
                    }

                    System.out.printf(ANSI_BOLD + ANSI_BLUE + "%-15s %-10s %-15s %-6s%n" + ANSI_RESET, "Customer SSN", "Product ID", "Purchase Date", "Paid");
                    System.out.println(ANSI_BLUE + "-------------------------------------------------------" + ANSI_RESET);
                    for (CustomerProduct purchase : purchases) {
                        String purchaseString = purchase.toString();
                        if (purchaseString.endsWith("PAID")) {
                            System.out.println(ANSI_GREEN + purchaseString + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + purchaseString + ANSI_RESET);
                        }
                    }
                    System.out.println(ANSI_BLUE + "-------------------------------------------------------" + ANSI_RESET);

                    break;
                case 5:
                    System.out.println("\n" + ANSI_GREEN + "--- PROCESS PRODUCT PURCHASE  -----------------------------" + ANSI_RESET);
                    s.nextLine();
                    System.out.print("Enter Customer SSN: ");
                    String ssn = s.nextLine();
                    System.out.print("Enter Product ID: ");
                    String productId = s.nextLine();

                    LocalDate purchaseDate=LocalDate.now();

                    boolean success = employee.purchaseProduct(ssn, productId, purchaseDate);

                    if (success) {
                        System.out.println(ANSI_GREEN + "\n[SUCCESS] Purchase recorded. Inventory quantity decreased by 1." + ANSI_RESET);
                        System.out.println("Changes will be saved on logout.");
                    } else {
                        System.out.printf(ANSI_RED + "\n[FAILURE] Purchase failed. Product ID '%s' not found or out of stock.%n" + ANSI_RESET, productId);
                    }
                    break;
                case 6:
                    System.out.println("\n" + ANSI_BLUE + "--- PROCESS PRODUCT RETURN --------------------------------------" + ANSI_RESET);
                    s.nextLine();
                    System.out.print("Enter Customer SSN of the purchase: ");
                    String ssn2 = s.nextLine();
                    System.out.print("Enter Product ID of the returned item: ");
                    String productId2 = s.nextLine();
                    System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                    String date2 = s.nextLine();
                    LocalDate purchaseDate2= LocalDate.parse(date2,DATE_FORMATTER);
                    LocalDate returnDate = LocalDate.now();

                    double price = employee.returnProduct(ssn2, productId2, purchaseDate2, returnDate);

                    if (price != -1) {
                        System.out.printf(ANSI_GREEN + "\n[SUCCESS] Return processed. Product quantity incremented. Refund amount: $%.2f%n" + ANSI_RESET, price);
                        System.out.println("The purchase record was deleted. Changes will be saved on logout.");
                    } else {
                        System.out.println(ANSI_RED + "\n[FAILURE] Return failed. Reasons may include:" + ANSI_RESET);
                        System.out.println(ANSI_RED + "  - Return date is before purchase date." + ANSI_RESET);
                        System.out.println(ANSI_RED + "  - Purchase record was not found or already deleted." + ANSI_RESET);
                        System.out.println(ANSI_RED + "  - More than 14 days passed between purchase and return." + ANSI_RESET);
                    }
                    break;
                case 7:
                    System.out.println("\n" + ANSI_BLUE + "--- APPLY PAYMENT FOR PURCHASE ----------------------------------" + ANSI_RESET);
                    s.nextLine();
                    System.out.print("Enter Customer SSN for payment: ");
                    String ssn3 = s.nextLine();

                    System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                    String date3 = s.nextLine();
                    LocalDate purchaseDate3= LocalDate.parse(date3,DATE_FORMATTER);

                    boolean paid = employee.applyPayment(ssn3,purchaseDate3);

                    if (paid) {
                        System.out.println(ANSI_GREEN + "\n[SUCCESS] Payment successfully applied. Status set to PAID." + ANSI_RESET);
                        System.out.println("Changes will be saved on logout.");
                    } else {
                        System.out.println(ANSI_RED + "\n[FAILURE] Payment failed. Purchase record not found, or it was already paid." + ANSI_RESET);
                    }
                    break;
                case 8:
                    employee.logout();
                    System.out.println(ANSI_CYAN + "\n[LOGOUT] Employee session ended. Employee data saved." + ANSI_RESET);
                    return;
                default:
                    System.out.println(ANSI_RED + "*Invalid Choice" + ANSI_RESET);
            }
        }
    }

}
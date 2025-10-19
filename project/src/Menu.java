import java.util.*;
import java.time.LocalDate;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class Menu {
    private static Scanner s=new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public static void main(String[] args) {


        boolean flag=true;
        while(flag) {
            System.out.println("===================================================================");
            System.out.println("||               INVENTORY MANAGEMENT SYSTEM                       ||");
            System.out.println("===================================================================");
            System.out.println("\n--- MAIN ACCESS MENU --------------------------------------------");
            System.out.println("1. Login as Administrator");
            System.out.println("2. Login as Retail Employee");
            System.out.println("3. Exit System");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice;
            do {

                choice =s.nextInt();
                s.nextLine();
                if (choice < 1 || choice > 3)
                {
                    System.out.println("===================================================================");
                    System.out.println("||               INVENTORY MANAGEMENT SYSTEM                       ||");
                    System.out.println("===================================================================");
                    System.out.println("\n--- MAIN ACCESS MENU --------------------------------------------");
                    System.out.println("1. Login as Administrator");
                    System.out.println("2. Login as Retail Employee");
                    System.out.println("3. Exit System");
                    System.out.println("-----------------------------------------------------------------");
                    System.out.print("Enter A Choice From (1-3): ");
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
                        System.out.println("Wrong password entered please try later ");
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
                        System.out.println("Wrong Id entered please try later ");
                        return;
                    }
                    employeeLogin();
                    break;
                case 3:
                    flag = false;
                default:

            }

        }

    }

private static void adminLogin() {
    AdminRole admin = new AdminRole();
        while (true) {


        System.out.println("\n--- ADMINISTRATOR MENU ------------------------------------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Remove Employee");
        System.out.println("4. Logout and Save Changes");
        System.out.println("-----------------------------------------------------------------");
        System.out.print("Enter your choice (1-4): ");

        int choice = s.nextInt();


        switch (choice) {
            case 1:

                System.out.println("\n--- ADD NEW EMPLOYEE --------------------------------------------");
                s.nextLine();
                System.out.println("Enter ID : "); // added this to try
                // replace it with the id gen.
                String id = s.nextLine() ;
                System.out.print("Enter Name: ");
                String name = s.nextLine();
                System.out.print("Enter Email: ");
                String email = s.nextLine();
                System.out.print("Enter Address: ");
                String address = s.nextLine();
                System.out.print("Enter Phone Number: ");
                String phone = s.nextLine();
                admin.addEmployee(id, name, email, address, phone);
                break;
            case 2:
                System.out.println("\n--- LIST OF EMPLOYEES -------------------------------------------");
                EmployeeUser[] employees = admin.getListOfEmployees();

                if (employees.length == 0) {
                    System.out.println("[INFO] No employee records found.");
                    return;
                }

                System.out.printf("%-10s %-20s %-25s %-20s %-15s%n", "ID", "Name", "Email", "Address", "Phone");
                System.out.println("---------------------------------------------------------------------------------------------------");
                for (EmployeeUser emp : employees) {
                    String[] parts = emp.lineRepresentation().split(",");

                        System.out.printf("%-10s %-20s %-25s %-20s %-15s%n",
                                parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());

                }
                System.out.println("---------------------------------------------------------------------------------------------------");
                break;
            case 3:
                s.nextLine();
                System.out.print("Enter Employee's ID: ");
                String Key = s.nextLine();
                admin.removeEmployee(Key);
                break;
            case 4:
                admin.logout();
                System.out.println("\n[LOGOUT] Administrator session ended. Employee data saved.");
                return;
            default:
                System.out.println("\n[INVALID INPUT] Please enter a number from 1 to 4.");
        }
    }
}

    private static void employeeLogin() {
        EmployeeRole employee = new EmployeeRole();
        while (true) {
            System.out.println("\n--- EMPLOYEE OPERATIONS MENU ------------------------------------");
            System.out.println("-----------------------------------------------------------------");
            System.out.println("1. Add New Product to Inventory");
            System.out.println("2. Remove a Product");
            System.out.println("3. View All Products");
            System.out.println("4. View All Purchasing Operations");
            System.out.println("5. Process New Product Purchase (Sale)");
            System.out.println("6. Process Product Return");
            System.out.println("7. Apply Payment for Purchase");
            System.out.println("8. Logout and Save Changes");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Enter your choice (1-8): ");
            int num  ;
            num=s.nextInt();

                switch(num)  {
                    case 1:
                        System.out.println("\n--- ADD NEW PRODUCT ---------------------------------------------");
                        s.nextLine();
                        System.out.print("Enter Product ID: ");
                        String id=s.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name=s.nextLine();
                        System.out.print("Enter Manufacturer Name: ");
                        String manufacturer=s.nextLine();
                        System.out.print("Enter Supplier Name: ");
                        String supplier=s.nextLine();
                        System.out.println("Enter Quantity");
                        int quantity=s.nextInt();
                        employee.addProduct(id,name,manufacturer,supplier,quantity);
                        System.out.println("\n[SUCCESS] Product added successfully. Changes will be saved on logout.");
                        employee.logout();
                        break;
                    case 2:
                        System.out.println("\n--- REMOVE PRODUCT ----------------------------------------------");
                        System.out.print("Enter the Product ID of the item to remove: ");
                        s.nextLine() ;
                        String productIdToRemove = s.nextLine();

                        employee.removeProduct(productIdToRemove);

                        System.out.println("[INFO] Removal command for Product ID '" + productIdToRemove + "' has been executed.");
                        break;
                    case 3:
                        System.out.println("\n--- LIST OF ALL PRODUCTS IN INVENTORY ---------------------------");
                        Product[] products = employee.getListOfProducts();
                        if (products.length == 0) {
                            System.out.println("[INFO] No product records found.");
                            return;
                        }
                        System.out.printf("%-10s %-20s %-15s %-15s %-8s %-8s%n", "ID", "Name", "Manufacturer", "Supplier", "Qty", "Price");
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.printf("%-10s %-20s %-15s %-15s %-8s %-8s%n", "ID", "Name", "Manufacturer", "Supplier", "Qty", "Price");
                        System.out.println("--------------------------------------------------------------------------------");
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
                        System.out.println("--------------------------------------------------------------------------------");

                        break;
                    case 4:
                        System.out.println("\n--- LIST OF PURCHASING OPERATIONS (SALES) -----------------------");
                        CustomerProduct[] purchases = employee.getListOfPurchasingOperations();

                        if (purchases.length == 0) {
                            System.out.println("[INFO] No purchasing operations found.");
                            return;
                        }

                        System.out.printf("%-15s %-10s %-15s %-6s%n", "Customer SSN", "Product ID", "Purchase Date", "Paid");
                        System.out.println("-------------------------------------------------------");
                        for (CustomerProduct purchase : purchases) {
                            System.out.println(purchase.toString());
                        }
                        System.out.println("-------------------------------------------------------");

                        break;
                    case 5:
                        System.out.println("\n--- PROCESS PRODUCT PURCHASE  -----------------------------");
                        s.nextLine();
                        System.out.print("Enter Customer SSN: ");
                        String ssn = s.nextLine();
                        System.out.print("Enter Product ID: ");
                        String productId = s.nextLine();

                        LocalDate purchaseDate=LocalDate.now();

                        boolean success = employee.purchaseProduct(ssn, productId, purchaseDate);

                        if (success) {
                            System.out.println("\n[SUCCESS] Purchase recorded. Inventory quantity decreased by 1.");
                            System.out.println("Changes will be saved on logout.");
                        } else {
                            System.out.printf("\n[FAILURE] Purchase failed. Product ID '%s' not found or out of stock.%n", productId);
                        }
                        break;
                    case 6:
                        System.out.println("\n--- PROCESS PRODUCT RETURN --------------------------------------");
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
                            System.out.printf("\n[SUCCESS] Return processed. Product quantity incremented. Refund amount: $%.2f%n", price);
                            System.out.println("The purchase record was deleted. Changes will be saved on logout.");
                        } else {
                            System.out.println("\n[FAILURE] Return failed. Reasons may include:");
                            System.out.println("  - Return date is before purchase date.");
                            System.out.println("  - Purchase record was not found or already deleted.");
                            System.out.println("  - More than 14 days passed between purchase and return.");
                        }
                        break;
                    case 7:
                        System.out.println("\n--- APPLY PAYMENT FOR PURCHASE ----------------------------------");
                        s.nextLine();
                        System.out.print("Enter Customer SSN for payment: ");
                        String ssn3 = s.nextLine();

                        System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                        String date3 = s.nextLine();
                        LocalDate purchaseDate3= LocalDate.parse(date3,DATE_FORMATTER);

                        boolean paid = employee.applyPayment(ssn3,purchaseDate3);

                        if (paid) {
                            System.out.println("\n[SUCCESS] Payment successfully applied. Status set to PAID.");
                            System.out.println("Changes will be saved on logout.");
                        } else {
                            System.out.println("\n[FAILURE] Payment failed. Purchase record not found, or it was already paid.");
                        }
                        break;
                    case 8:
                        employee.logout();
                        System.out.println("\n[LOGOUT] Employee session ended. Employee data saved.");
                        return;
                    default:
                        System.out.println("*Invalid Choice");
                }
            }
        }

    }
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        Admin Ceo = new Admin("beso","beso",10,10,2002,"Ceo","9-12");
        Database.admins.add(Ceo);

        while (!exit) {
            System.out.println("Welcome To StackShop!");
            System.out.println("Choose Your User Type: ");
            System.out.println("1-Customer");
            System.out.println("2-Admin");
            System.out.println("3-Supplier");
            System.out.println("(-1)-Exit");
            int userType = input.nextInt();
            int action=0;
            if(userType == -1)
                break;
            if(userType !=2) {
                System.out.println("Would you like to login(2) or signup(1)");
                action = input.nextInt();}else{
                action=2;
            }

            Object currentUser = handleUser(userType, action);
            boolean loggedIn = true;

            while (loggedIn) {
                if (userType == 1) {
                    System.out.println("1-Add interests");
                    System.out.println("2-View by category");
                    System.out.println("3-View All products");
                    System.out.println("4-View all suppliers");
                    System.out.println("5-View my details");
                    System.out.println("6-View my orders");
                    System.out.println("7-Logout");
                    System.out.println("8-Exit");
                    int choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            Category.viewCategories();
                            boolean addInterest = true;
                            while (addInterest) {
                                System.out.println("Enter the name of the category you want to add");
                                String category = input.next();
                                ((Customer) currentUser).addIntrests(category);
                                System.out.println("Do u want to add another interest yes(1) no(2)?");
                                if (input.nextInt() == 2)
                                    addInterest = false;
                            }
                            break;

                        case 2:
                            Category.viewCategories();
                            String Category_choice=input.next();
                            ((Customer) currentUser).viewByCategory(Category_choice);
                            String backChoice = "1";
                            while (!backChoice.equals("0")) {
                                System.out.println("Choose a product id to add to your cart");
                                System.out.println("0-back to menu");
                                backChoice = input.next();
                                if (backChoice.equals("0"))
                                    break;
                                else {
                                    ((Customer) currentUser).add_to_cart(((Customer) currentUser).select(backChoice));
                                }
                            }
                            break;

                        case 3:
                            ((Customer) currentUser).viewAllProducts();
                            String backChoice2 = "1";
                            while (!backChoice2.equals("-1")) {
                                System.out.println("Choose a product id to add to your cart");
                                System.out.println("(-1)-back to menu");
                                backChoice2 = input.next();
                                if (backChoice2.equals("-1"))
                                    break;
                                else {
                                    ((Customer) currentUser).add_to_cart(((Customer) currentUser).select(backChoice2));
                                }
                            }
                            break;

                        case 4:
                            ((Customer) currentUser).viewAllSuppliers();
                            String backChoice3 = "-2";
                            while (!backChoice3.equals("-1")) {
                                System.out.println("Choose a Supplier to view their products: ");
                                System.out.println("(-1) back to menu");
                                backChoice3 = input.next();
                                if (backChoice3.equals("-1"))
                                    break;
                                else {
                                    Supplier supplier = Database.suppliers.get(Integer.parseInt(backChoice3));
                                    supplier.viewAllProducts();
                                    String backChoice4 = "1";
                                    while (!backChoice4.equals("-1")) {
                                        System.out.println("Choose a product id to add to your cart");
                                        System.out.println("(-1)-back to menu");
                                        backChoice4 = input.next();
                                        if (backChoice4.equals("-1"))
                                            break;
                                        else {
                                            ((Customer) currentUser).add_to_cart(((Customer) currentUser).select(backChoice4));
                                        }
                                    }
                                }
                            }
                            break;
                        case 5:
                            ((Customer) currentUser).viewDetails();
                            String backChoice4 = "-2";
                            while(!backChoice4.equals("-1")){
                                System.out.println("Edit username (1)");
                                System.out.println("Edit password (2)");
                                System.out.println("Add Balance (3)");
                                System.out.println("back to menu (-1)");
                                backChoice4 = input.next();
                                if(backChoice4.equals("1")){
                                    System.out.println("Enter new username");
                                    String newUsername = input.next();
                                    ((Customer) currentUser).setUsername(newUsername);
                                }
                                else if(backChoice4.equals("2")){
                                    System.out.println("Enter new passwrord");
                                    String newPassword = input.next();
                                    ((Customer) currentUser).setPassword(newPassword);
                                }
                                else if(backChoice4.equals("3")){
                                    System.out.println("Enter new balance");
                                    double newBalance = input.nextFloat();
                                    ((Customer) currentUser).setBalance(newBalance);
                                }

                            }
                            break;
                        case 6:
                            int back = 1;
                            String username = ((Customer) currentUser).getUsername();
                            for(Order order : Database.orders){
                                if(order.getCustomerName().equals(username)){
                                    order.viewOrder();
                                }
                            }
                            System.out.println("Go back enter any value");
                            back = input.nextInt();
                            break;
                        case 7:
                            loggedIn = false;
                            break;

                        case 8:
                            loggedIn = false;
                            exit = true;
                            break;
                    }
                }else if(userType==2){
                    System.out.println("Welcome back "+ ((Person)Ceo).getUsername()+"!");
                    System.out.println("1-Add a supllier");
                    System.out.println("2-View All products");
                    System.out.println("3-View All orders");
                    System.out.println("4-View All customers");
                    System.out.println("5-View All admins");
                    System.out.println("6-logout");
                    System.out.println("7-Exit");
                    int choice = input.nextInt();
                    switch(choice) {
                        case 1:

                            break;

                        case 2:
                            ((Admin) currentUser).view_products();
                            System.out.println("Go back enter any value");
                            String go_back2 = input.next();
                            break;

                        case 3:
                            for (int i = 0; i < Database.orders.size(); i++) {
                                Database.orders.get(i).viewOrder();
                            }
                            System.out.println("Go back enter any value");
                            String go_back3 = input.next();
                            break;

                        case 4:
                            for (int i = 0; i < Database.users.size(); i++) {
                                Database.users.get(i).viewDetails();
                            }
                            System.out.println("Go back enter any value");
                            String go_back4 = input.next();
                            break;

                        case 5:
                            for (int i = 0; i < Database.admins.size(); i++) {
                                Database.admins.get(i).viewDetails();
                            }
                            System.out.println("Go back enter any value");
                            String go_back5 = input.next();
                            break;

                        case 6:
                            loggedIn = false;
                            break;

                        case 7:
                            loggedIn = false;
                            exit = true;
                            break;
                    }
                }
                else if(userType==3){
                    System.out.println(((Supplier)currentUser).getcompName());
                    System.out.println("1-View by category");
                    System.out.println("2-View All products");
                    System.out.println("3-Add Product");
                    System.out.println("4-Logout");
                    System.out.println("5-Exit");
                    int choice = input.nextInt();
                    switch(choice){
                        case 1:
                            Category.viewCategories();
                            System.out.println("Enter a category");
                            String cat= input.next();
                            ((Supplier)currentUser).viewByCategory(cat);
                            System.out.println("Go back enter any value");
                            String go_back1 = input.next();
                            break;
                        case 2:
                            ((Supplier)currentUser).viewAllProducts();
                            System.out.println("Go back enter any value");
                            String go_back2 = input.next();
                            break;
                        case 3:
                            System.out.println("Enter Product name:");
                            String name = input.next();
                            System.out.println("Enter Product Price:");
                            double Price = input.nextDouble();
                            System.out.println("Enter Category:");
                            String category = input.next();
                            System.out.println("Enter Stock you Can Provide Now:");
                            int stock=input.nextInt();
                            SupplierProduct newprod=new SupplierProduct(name,Price,category,stock,(Supplier)currentUser);
                            ((Supplier)currentUser).add_product(newprod);
                            break;
                        case 4:
                            loggedIn = false;
                            break;
                        case 5:
                            loggedIn = false;
                            exit = true;
                            break;



                    }

                }
            }
        }
        input.close();
    }

    public static Object handleUser(int userType, int action) {
        Scanner input = new Scanner(System.in);

        if (userType == 1) {
            if (action == 1) {
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                System.out.println("Enter your date of birth");
                int day = input.nextInt();
                int month = input.nextInt();
                int year = input.nextInt();
                System.out.println("Enter your address");
                String address = input.next();
                System.out.println("What is ur gender");
                String gender = input.next();
                Customer newCustomer = new Customer(username, password, day, month, year, gender, address);
                Database.users.add(newCustomer);
                return newCustomer;
            } else if (action == 2) {
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                return (Customer) validLogin(username, password, "Customer");
            }
        } else if (userType == 2) {
            System.out.println("Enter your account data");
            System.out.println("Username");
            String username = input.next();
            System.out.println("Password");
            String password = input.next();
            return (Admin) validLogin(username,password,"Admin");
        } else if (userType == 3) {
            if (action == 1) {
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                System.out.println("Commercial Name");
                String compName = input.next();
                Supplier newSupplier = new Supplier(username, password, compName);
                Database.suppliers.add(newSupplier);
                return newSupplier;
            } else if (action == 2) {
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                return SupplierValidateLogin(username, password);
            }
        }
        return null;
    }

    public static Person validLogin(String username, String password, String type) {
        if (type.equals("Customer")) {
            for (Customer customer : Database.users) {
                if (customer.username.equals(username)  && customer.password.equals(password))
                    return customer;
            }
            return null;
        }
        if (type.equals("Admin") ) {
            for (Admin admin : Database.admins) {
                if (admin.username.equals(username) && admin.password.equals(password) )
                    return admin;
            }
            return null;
        }
        return null;
    }

    public static Supplier SupplierValidateLogin(String username, String password) {
        for (Supplier supplier : Database.suppliers) {
            if (supplier.getUsername().equals(username) && supplier.getPassword().equals(password))
                return supplier;
        }
        return null;
    }
}

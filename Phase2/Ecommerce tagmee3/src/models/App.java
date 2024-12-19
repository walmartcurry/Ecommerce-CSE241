package models;

import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        Admin Ceo = new Admin("beso","beso",10,10,2002,"Ceo","9-12");
        Database.admins.add(Ceo);
        Supplier Nada = new Supplier("NADA102", "NADA008", "NADA");
        SupplierProduct protienmilk = new SupplierProduct("Protein shake", 700, "dairy",0,Nada);
        SupplierProduct greek = new SupplierProduct("Greek yogurt", 300, "dairy",102,Nada);
        Nada.add_product(protienmilk);
        Nada.add_product(greek);
        Customer ana = new Customer("amir", "bigAmir",2,7,2005,"male","El rehab");
        Customer enta = new Customer("adham", "adhamthearray",2,7,2005,"male","El rehab");
        Database.users.add(enta);
        Database.users.add(ana);
        Database.suppliers.add(Nada);
        while (!exit) {
            System.out.println("Welcome To StackShop!");
            System.out.println("Choose Your User Type: ");
            System.out.println("1-Customer");
            System.out.println("2-Admin");
            System.out.println("3-Supplier");
            System.out.println("(-1)-Exit");
            int userType = getValidIntInput(input,"Choose your account type");
            int action=0;
            if(userType == -1)
                break;
            if(userType !=2) {

                action = getValidIntInput(input, "Would you like to login(2) or signup(1)");
            }
            else{
                action=2;
            }

            Object currentUser = handleUser(userType, action);
            boolean loggedIn = true;
            if(currentUser instanceof Supplier && Database.supplierRequests.contains(currentUser)){
                loggedIn = false;
                System.out.println("Your request is pending");
            }
            if(currentUser == null){
                loggedIn = false;
                System.out.println("The username or password u entered is incorrect");

            }

            while (loggedIn) {
                if (userType == 1) {
                    System.out.println("1-Add interests");
                    System.out.println("2-View by category");
                    System.out.println("3-View All products");
                    System.out.println("4-View all suppliers");
                    System.out.println("5-View my details");
                    System.out.println("6-View my previous orders");
                    System.out.println("7-View my cart");
                    System.out.println("8- View by intrests");
                    System.out.println("9-Logout");
                    System.out.println("0-Exit");
                    int choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            Category.viewCategories();
                            boolean addInterest = true;
                            while (addInterest) {
                                System.out.println("Enter the name of the category you want to add");
                                int category = getValidIntInput(input, null);
                                ((Customer) currentUser).addIntrests(Category.categories[category-1]);
                                int choice2 = getValidIntInput(input,"Do u want to add another interest yes(1) no(2)?");
                                if ( choice2 == 2)
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
                            String back = " ";
                            String username = ((Customer) currentUser).getUsername();
                            for(Order order : Database.orders){
                                if(order.getCustomerName().equals(username)){
                                    order.viewOrder();
                                }
                            }

                            System.out.println("Go back enter any value");
                            back = input.next();
                            break;
                        case 7 :
                            String back2 = "1";
                            ((Customer) currentUser).viewMyCart();
                            while(!((Customer)currentUser).getOrder().getMyCart().cartItems.isEmpty()){
                                System.out.println("Select item you want to edit");
                                System.out.println("Go back to menu (-1)");
                                System.out.println("Checkout (c)");
                                back2 = input.next();
                                if(back2.toLowerCase().equals("c")){
                                    System.out.println("Enter payment method");
                                    System.out.println("0-cash");
                                    System.out.println("1-balance");
                                    int paymentMethod = getValidIntInput(input, "");
                                    if(paymentMethod == 0){
                                        ((Customer) currentUser).finaliseOrder("cash");
                                        break;
                                    }

                                    else if (paymentMethod == 1){
                                        ((Customer) currentUser).finaliseOrder("balance");
                                        break;
                                    }
                                    else{
                                        System.out.println("INVALID YA 7AYAWAN");
                                        break;
                                    }
                                }
                                if(!back2.equals("-1")){
                                    CustomerProduct edited = ((Customer)currentUser).getOrder().getMyCart().cartItems.get(Integer.parseInt(back2));
                                    while(!back2.equals("-1")){
                                        System.out.println("Remove (1)");
                                        System.out.println("Set quantity(2)");
                                        System.out.println("Go back (-1)");
                                        back2 = input.next();
                                        if(back2.equals("1")){
                                            ((Customer)currentUser).remove_from_cart(edited);
                                            break;
                                        }
                                            
                                        else if (back2.equals("2")){

                                            int quant = getValidIntInput(input, "Enter the quantity you want");
                                            edited.setQuantity(quant);
                                        }
                                    }}
                                else if (back2.equals("-1")){
                                    break;
                                }
                            }
                            break;
                        case 8:
                        if( ((Customer) currentUser).getIntrests().isEmpty()){
                            System.out.println("No intrests");
                            break;
                        }
                        ((Customer) currentUser).viewByIntrests();
                            String backChoice6 = "1";
                            while (!backChoice6.equals("-1")) {
                                System.out.println("Choose a product id to add to your cart");
                                System.out.println("(-1)-back to menu");
                                backChoice6 = input.next();
                                if (backChoice6.equals("-1"))
                                    break;
                                else {
                                    ((Customer) currentUser).add_to_cart(((Customer) currentUser).select(backChoice6));
                                }
                            }
                            break;

                        case 9:
                            loggedIn = false;
                            break;

                        case 0:
                            loggedIn = false;
                            exit = true;
                            break;
                    }
                }else if(userType==2){
                    System.out.println("Welcome back "+ ((Person)Ceo).getUsername()+"!");
                    System.out.println("1-View requests");
                    System.out.println("2-View All products");
                    System.out.println("3-View All orders");
                    System.out.println("4-View All customers");
                    System.out.println("5-View All admins");
                    System.out.println("6-logout");
                    System.out.println("7-Exit");
                    int choice = getValidIntInput(input, "");
                    switch(choice) {
                        case 1:
                            if (!((Admin)currentUser).getRole().equals("HR") && !((Admin)currentUser).getRole().equals("Ceo")){
                                System.out.println("Acces denied");
                                break;
                            }
                            else{
                                ((Admin)currentUser).viewRequests();
                                System.out.println("Enter the index of the supplier you want to edit");
                                System.out.println("Exit(-1)");
                                int ha3mel_eh=getValidIntInput(input, "");
                                if(ha3mel_eh== -1)
                                    break;
                                try{

                                    ((Admin)currentUser).handle_supplier(ha3mel_eh);
                                }
                                catch(IndexOutOfBoundsException e ){
                                    System.out.println("Invalid index");
                                }
                            }

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
                    int choice = getValidIntInput(input, "");
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
                            ((Supplier) currentUser).viewAllProducts();
                            for(SupplierProduct product : ((Supplier) currentUser).getProducts()){
                                int count = 0;
                                System.out.println("Product "+count +" Stock: "+product.getStock());
                                count++;


                            }
                            String backChoice2 = "1";
                            while (!backChoice2.equals("-1")) {
                                System.out.println("Choose a product id to edit");
                                System.out.println("(-1)-back to menu");
                                backChoice2 = input.next();
                                if (backChoice2.equals("-1"))
                                    break;
                                double choice4 = -19;
                                while(choice4 !=-1){
                                    String [] split = backChoice2.split("\\.");

                                    int prodIndex = Integer.parseInt(split[1]);

                                    System.out.println("Edit price (0)");
                                    System.out.println("Increase stock (1)");
                                    System.out.println("Back (-1)");
                                    choice4 = getValidIntInput(input, "");
                                    if(choice4 == 0){
                                        int new_price=getValidIntInput(input, "Enter new price");
                                        ((Supplier) currentUser).getProducts().get(prodIndex).setPrice(new_price);
                                    }
                                    else if (choice4 == 1){
                                        System.out.println("how many pieces do u want to add");
                                        int h= getValidIntInput(input, "");
                                        ((Supplier) currentUser).getProducts().get(prodIndex).Increase_Stock(h);
                                    }
                                }
                            }
                            break;

                        case 3:
                            System.out.println("Enter Product name:");
                            String name = input.next();
                            System.out.println("Enter Product Price:");
                            double Price = input.nextDouble();
                            System.out.println("Enter Category:");
                            String category = input.next();
                            System.out.println("Enter Stock you Can Provide Now:");
                            int stock=getValidIntInput(input, "");
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
                while(!isValidUsername(username)){
                    System.out.println("Invalid username try again ");
                    username = input.next();
                }
                System.out.println("Password");
                String password = input.next();
                while(!isValidPassword(password)){
                    System.out.println("Invalid password try again ");
                    input.nextLine();
                    password = input.nextLine();
                }
                System.out.println("Enter your date of birth");
                int day = getValidIntInput(input, "Day:");
                int month = getValidIntInput(input, "Month:");
                int year = getValidIntInput(input, "Year:");
                while(!isValidDateOfBirth(day, month, year)){
                    System.out.println("Invalid DOB");
                     day = getValidIntInput(input, "Day:");
                     month = getValidIntInput(input, "Month:");
                     year = getValidIntInput(input, "Year:");

                }
                System.out.println("Enter your address");
                String address = input.next();
                while(!isValidAddress(address)){
                    System.out.println("Invalid address try again ");
                    address = input.next();
                }
                System.out.println("What is ur gender");
                String gender = input.next();
                while(!isValidGender(gender)){
                    System.out.println("Invalid gender try again ");
                    gender = input.next();
                }
              
                
                isValidGender(gender);
                isValidPassword(password);
                isValidUsername(username);
                Customer newCustomer = new Customer(username, password, day, month, year, gender, address);
                int back = 1;
                Category.viewCategories();
                System.out.println("enter the index of ur intrests");
                System.out.println("enter -1 if ur done");
                while(back!=-1){
                    back = input.nextInt();
                    if(back == -1)
                        break;
                    newCustomer.addIntrests(Category.categories[back-1]);
                }
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
                while(!isValidUsername(username)){
                    System.out.println("Invalid username try again ");
                    username = input.next();
                }
                System.out.println("Password");
                String password = input.next();
                while(!isValidPassword(password)){
                    System.out.println("Invalid password try again ");
                    password = input.next();
                }
                System.out.println("Commercial Name");
                String compName = input.next();
                Supplier newSupplier = new Supplier(username, password, compName);
                Database.supplierRequests.add(newSupplier);
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
    private static int getValidIntInput(Scanner input, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine(); // Clear the invalid input
            }
        }
    }
    private static boolean isValidUsername(String username) {
        // Example rules: 3-20 characters, alphanumeric
        return username.matches("^[a-zA-Z0-9]{3,20}$");
    }
    private static boolean isValidPassword(String password) {
        // Example rules: 8+ characters, at least one uppercase, one lowercase, one digit
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    }
    private static boolean isValidAddress(String address) {
        // Prevent empty or extremely short addresses
        return address != null && address.trim().length() >= 3;
    }
    private static boolean isValidGender(String gender) {
    // Restrict to predefined values
    return Arrays.asList("male", "female", "other").contains(gender.toLowerCase());
}
private static boolean isValidDateOfBirth(int day, int month, int year) {
    if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) return false;
    if (month < 1 || month > 12) return false;
    
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
        daysInMonth[1] = 29; // Leap year
    }
    
    return day >= 1 && day <= daysInMonth[month - 1];
}
}


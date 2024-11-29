import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        
        
    }
    public static Object handleUser(String userType , String action){
         Scanner input = new Scanner(System.in);
        if(userType == "customer"){
            if(action == "signup"){
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
                Customer newCustomer = new Customer(username,password,day,month,year,gender,address);
                Database.users.add(newCustomer);
                return newCustomer;

            }
            else if (action == "login"){
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                return (Customer)validLogin(username,password,"Customer");

            }
        }
        else if (userType == "admin"){
            System.out.println("Enter your account data");
            System.out.println("Username");
            String username = input.next();
            System.out.println("Password");
            String password = input.next();
            return (Admin)validLogin(username,password,"Admin");//Returns either found Customer or NULL 

        }
        else if (userType == "supplier"){
            if(action == "signup"){
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                System.out.println("Commercial Name");
                String compName = input.next();
                Supplier newSupplier =  new Supplier(username,password,compName);
                Database.suppliers.add( newSupplier);
                return newSupplier;

            }
            else if (action == "login"){
                System.out.println("Enter your account data");
                System.out.println("Username");
                String username = input.next();
                System.out.println("Password");
                String password = input.next();
                return SupplierValidateLogin(username,password);//Returns either found Customer or NULL handeled in main

        //VALIDATIONS

            }
        }
        return null;
    }
    public static Person validLogin(String username , String password,String type){
        if(type == "Customer"){
            for(Customer customer : Database.users){
                if(customer.username == username && customer.password== password)
                    return customer;
            }
            return null;
        }
        if(type == "Admin"){
            for(Admin admin : Database.admins){
                if(admin.username == username && admin.password== password)
                    return admin;
            }
            return null;
        }
        return null;

    }
    public static Supplier SupplierValidateLogin(String username , String password){
         for(Supplier supplier : Database.suppliers){
             if(supplier.getUsername() == username && supplier.getPassword()== password)
                  return supplier;
            }
            return null;

    }

}

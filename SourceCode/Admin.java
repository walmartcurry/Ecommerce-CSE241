import java.util.Scanner;
import java.util.Date;
public class Admin extends Person
{
    Scanner input = new Scanner(System.in);
    String[] role = {"ceo","call"};
    private String Role;
    private String working_hours;

    public Admin(){
        Person();
        Role="null";
        working_hours="null";

    }
    public Admin(String user, String pass, int year, int month, int day,String role,String WH){
        Person(user,pass,year,month,day);
        this.Role=role;
        this.working_hours=WH;

    }
    public String getRole() {
        return Role;
    }

    public void setRole(String r) {
        r=r.toLowerCase();
        for(int i=0;i<role.length;i++)
            if(r.equals(role[i])) {
                this.Role=r;
                break;
            }
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }
    public void add_supplier(String username,String password,String compName){
        Supplier new_sup=new Supplier( username, password,compName);
        Database.suppliers.add(new_sup);
        System.out.println(new_sup.getcompName() +"  has been added :)");
    }
    public void view_products(){
        System.out.println("ALL PRODUCTS:");
        System.out.println(" ");



        for(int i=0;i<Database.suppliers.size();i++)
        {
            Supplier supplier = Database.suppliers.get(i);
            System.out.println("*"+supplier.getcompName()+": ");
            if ( supplier.getProducts().size() > 0) {
                supplier.viewAllProducts();
            } else {
                System.out.println("No products available");
            }
        }


    }
    public void view_suppliers(){
        if (Database.suppliers.isEmpty()) {
            System.out.println("No suppliers available");
            return;
        }
        System.out.println("ALL SUPPLIERS:");
        System.out.println(" ");
        for(int i=0;i<Database.suppliers.size();i++)
        {
            Supplier supplier = Database.suppliers.get(i);
            int count=i+1;
            System.out.println(count + "-"+ supplier.getcompName());

        }
    }
    public void view_orders(){
        System.out.println("ALL ORDERS:");
        System.out.println(" ");



        for(int i=0;i<Database.users.size();i++)
        {
            Customer customer = Database.users.get(i);
            System.out.println("*"+customer.username+": ");
            if ( customer.orders.size() > 0) {
                for (int j = 0; j < customer.orders.size(); j++) {

                    Order order=customer.orders.get(j);
                    int count=j+1;
                    System.out.print("Order " + count+" :");
                    order.viewOrder();
                    System.out.println("___________________________");
                    System.out.println(" ");


                }
            } else {
                System.out.println("No Orders Done");
            }
        }
    }
    public void view_customers(){
        if (Database.users.isEmpty()) {
            System.out.println("No Customers available");
            return;
        }
        System.out.println("ALL Customers:");
        System.out.println(" ");

        for(int i=0;i<Database.users.size();i++)
        {
            Customer customer = Database.users.get(i);

            int count=i+1;
            System.out.println(count + "-"+ customer.username);

        }
    }

}

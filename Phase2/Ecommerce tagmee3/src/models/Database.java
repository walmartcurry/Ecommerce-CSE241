package models;
import java.util.ArrayList;
public abstract class Database {
    public static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    public static ArrayList<Customer> users = new ArrayList<Customer>();
    public static ArrayList<Order> orders = new ArrayList<Order>();
    public static ArrayList<Admin> admins = new ArrayList<Admin>();
    public static ArrayList<Supplier> supplierRequests = new ArrayList<Supplier>();
}

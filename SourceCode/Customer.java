
import java.util.ArrayList;


public class Customer extends Person implements View{
    public enum Gender {
        male, female;
    }

    private int balance;
    private String address;
    private ArrayList<Category> intrests;
    Gender gender;
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Category> getIntrests() {
        return intrests;
    }

    public void addIntrests(Category c) {
       
    }



    public Gender getGender() {
        return gender;
    }

    public void add_to_cart(Product ADD){}
    public void remove_from_cart(){}
    public void view_by_category(){}
    public void view_all_products(){}
    public void view_by_supp(){}
    public void finaliseOrder(String paymentmethod){}

}

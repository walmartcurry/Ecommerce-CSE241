import java.util.ArrayList;
import java.util.HashMap;
public class Customer extends Person implements View{
    public enum Gender {
        male, female , NULL;
    }
    private int balance;
    private String address;
    private ArrayList<Category> intrests;
    Gender gender;
    Order order;
    Customer(String username , String password , int year , int month , int day ,String gender , String address)
    {
        super(username,password,year,month,day);
        this.address = address;
        this.gender = Gender.valueOf(gender.toLowerCase());
        order = new Order();
    }
    Customer(){
        super();
        this.address = "NULL";
        gender = Gender.valueOf("NULL");
        order = new Order();

    }
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

    public void addIntrests(String category){
        if(Category.checkValidity(category)){
            for (Category cat : intrests) {
                if(category.toLowerCase() == cat.gettype().toLowerCase()){
                    System.out.println("This Category is already added to your intrests");
                    return;
                }
                intrests.add(new Category(category));
                System.out.println(category +" Added to intrests");
            }
        }
        else
        System.out.println("Category you entered does not exist in our store");
    }

    public Gender getGender() {
        return gender;
    }

    public void add_to_cart(SupplierProduct Add){
        for(CustomerProduct product : (order.getMyCart()).cartItems){
            if(Add.getName().equals(product.getName()) ){
                product.setQuantity(product.getQuantity()+1);
                return;
            }
        }
        order.getMyCart().AddToCart(new CustomerProduct(Add));
        System.out.println("Item added to cart");
    }
    public void remove_from_cart(Product Remove){
        for(CustomerProduct product : (order.getMyCart()).cartItems){
            if(Remove.getName().equals(product.getName())){
                order.getMyCart().removeFromCart(Remove);
                System.out.println("Item has been removed");
            }
            System.out.println("Item not in cart");
            return;
        }
    }
    public void viewByCategory(Category category){

        for(Supplier supplier : Database.suppliers){
            supplier.viewByCategory(category);
        }

    }
    public void view_all_products(){
        for(Supplier supplier : Database.suppliers){
            supplier.viewAllProducts();
        }

    }
    public void view_by_supp(Supplier enteredSupplier){
        for(Supplier supplier : Database.suppliers){
            if(supplier == enteredSupplier){
            supplier.viewAllProducts();
            return;
            }
        }
    }
    public void finaliseOrder(String paymentmethod){
        if(paymentmethod.toLowerCase() == "cash"){
            order.payByCash();
        }
        else if(paymentmethod.toLowerCase() == "balance"){
            order.payByBalance(balance);
        }
        else{
            System.out.println("Invalid payment method");
            return;
        }
        order.finalizeOrder();
    }
    public void selectProduct(int index){

    }
    

}




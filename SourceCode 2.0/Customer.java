import java.util.ArrayList;
public class Customer extends Person implements View{
    public enum Gender {
        male, female , NULL;
    }
    private double balance;
    private String address;
    private ArrayList<Category> intrests=new ArrayList<>();
    private Gender gender;
    Order order;  
    Customer(String username , String password , int year , int month , int day ,String gender , String address)
    {
        super(username,password,year,month,day);
        this.address = address;
        this.gender = Gender.valueOf(gender.toLowerCase());
    }
    Customer(){
        super();
        this.address = "NULL";
        gender = Gender.valueOf("NULL");
        order = new Order(username,address);
        intrests = new ArrayList<Category>();
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Order getOrder() {
        return order;
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
                if(category.toLowerCase().equals(cat.gettype().toLowerCase())){
                    System.out.println("This Category is already added to your intrests");
                    return;
                }
            }
            intrests.add(new Category(category));
            System.out.println(category +" Added to intrests");
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
    public void remove_from_cart(CustomerProduct Remove){
        for(CustomerProduct product : (order.getMyCart()).cartItems){
            if(Remove.getName().equals(product.getName())){
                order.getMyCart().removeFromCart(Remove);
                System.out.println("Item has been removed");
            }
            System.out.println("Item not in cart");
            return;
        }
    }
    public void viewByCategory(String category1) {
        if (Category.checkValidity(category1)) {
            for (Supplier supp : Database.suppliers)
                supp.viewByCategory(category1);
        } else {
            System.out.println("no existo");
        }
    }

    
    public void viewAllProducts(){
        for(Supplier supplier : Database.suppliers){
            supplier.viewAllProducts();
        }

    }
    public void viewAllSuppliers(){
        for(Supplier supplier : Database.suppliers){
            System.out.println(supplier.getcompName()); 
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
        if(paymentmethod.toLowerCase().equals("cash") ){
            order.payByCash();
        }
        else if(paymentmethod.toLowerCase().equals("balance") ){
            order.payByBalance(balance);
        }
        else{
            System.out.println("Invalid payment method");
            return;
        }
        order.finalizeOrder();
    }
    public SupplierProduct select(String sel){
        String [] split = sel.split("\\.");
        int suppIndex = Integer.parseInt(split[0]);
        int prodIndex = Integer.parseInt(split[1]);
        return Database.suppliers.get(suppIndex).getProducts().get(prodIndex);
    }
    public void viewDetails(){
        System.out.println("Username : " + username);
    }
    

}




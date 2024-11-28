import java.util.ArrayList;
public class Supplier implements View {
    private String username;
    private String password;
    private String COMM_NAME;
    ArrayList<SupplierProduct> products;
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void  viewByCategory(Category category){
        for(SupplierProduct product : products){
            if(product.getMycat().toLowerCase() == category.gettype().toLowerCase()){
                product.viewProduct();
            }

        }
    }
    public  void viewAllProducts(){
        for(SupplierProduct product : products){
                product.viewProduct();
        }
    }
    public void add_product(SupplierProduct ADD){
        products.add(ADD);
    }
   
}

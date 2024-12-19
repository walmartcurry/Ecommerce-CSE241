package models;
import java.util.ArrayList;
public class Supplier implements View {
    private String username;
    private String password;
    private String compName;
    private ArrayList<SupplierProduct> products=new ArrayList<>();
    public Supplier(String username , String password , String compName){
        this.username = username;
        this.password = password;
        this.compName = compName;

    }

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
    public ArrayList<SupplierProduct> getProducts() {
        return products;
    }
    public String getcompName() {
        return compName;
    }
    public void  viewByCategory(String category){
        if(Category.checkValidity(category))
        {
            for (SupplierProduct product : products) {
                if (product.getMycat().toLowerCase().equals(category.toLowerCase())) {
                    product.viewProduct();
                }

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
   


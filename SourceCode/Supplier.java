public class Supplier implements View {
    protected String username;
    protected String password;
    Product[] SUPP_PROD;

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
    public void  view_by_category(){}
    public  void view_all_products(){}
    public void add_product(Product ADD){}
}

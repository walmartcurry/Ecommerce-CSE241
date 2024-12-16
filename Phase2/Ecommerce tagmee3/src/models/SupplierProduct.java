package models;
public class SupplierProduct extends Product {
    private int stock = 0 ;
    private Supplier supplier;
    SupplierProduct(){
        super();

    }
    SupplierProduct(String name , double price , String category,int stock,Supplier supplier){
        super(name,price,category);
        this.stock = stock;
        this.supplier = supplier;
    }
    public void setStock(int stock) {
        this.stock=stock;
    }
    public int getStock() {
        return stock;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void Increase_Stock(int num_to_add){
        this.stock+=num_to_add;
        System.out.println("New Stock Of " +this.stock +" is "+"this.stock");

    } 
    @Override
    public void viewProduct(){
        if(stock == 0){
            return;
        }
        System.out.println("Product id : " +Database.suppliers.indexOf(supplier)+"."+Database.suppliers.get(Database.suppliers.indexOf(supplier)).getProducts().indexOf(this));
        System.out.println("Product Name :" + name);
        System.out.println("Price :" + price);
        System.out.println("Category :" + mycat.gettype());
        if(stock<5){
            System.out.println("Only "+stock + "pieces left");
        }
        System.out.println("--------------------------------");

    }

}

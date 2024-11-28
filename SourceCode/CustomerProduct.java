public class CustomerProduct extends Product {
    private int quantity = 1;
    private Supplier supplier;
    CustomerProduct(String name , double price , String category , Supplier supplier){
        super(name,price,category);
        this.supplier = supplier;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    @Override
    public void viewProduct(){
        System.out.println("Product Name :" + name);
        System.out.println("Price :" + price);
        System.out.println("Category :" + mycat.gettype());
        System.out.println("Quantity : "+quantity);


    }


    
}


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


    
}


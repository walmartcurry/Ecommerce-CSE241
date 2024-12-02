public class CustomerProduct extends Product {
    private int quantity = 1;
    private SupplierProduct supplierProd;
    CustomerProduct(SupplierProduct newProd){
        super(newProd.name,newProd.price,newProd.getMycat());
        this.supplierProd = newProd;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
   public SupplierProduct getSupplierProd() {
       return supplierProd;
   }
    @Override
    public void viewProduct(){
        System.out.println("Product Name :" + name);
        System.out.println("Price :" + price);
        System.out.println("Category :" + mycat.gettype());
        System.out.println("Quantity : "+quantity);


    }


    
}


import java.util.ArrayList;

public class Cart{
    ArrayList<CustomerProduct> cartItems;
    private double totalPrice;

    Cart(){
        cartItems = new ArrayList<CustomerProduct>();
    }

    public void calcTotalPrice(){
        for (CustomerProduct cartItem : cartItems) {
            this.totalPrice += cartItem.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void viewcart(){
        if(!cartItems.isEmpty()){
            calcTotalPrice();
        int count = 0;
        for (CustomerProduct cartItem : cartItems) {
            System.out.println("Product : "+ count++);
            cartItem.viewProduct();
            System.out.println("--------------------");
        }
        System.out.println("total price : " + totalPrice);
    }
        else{
            System.out.println("Your cart is empty");
        }

    };

    public void AddToCart(CustomerProduct ADD){
        cartItems.add(ADD);
    };
    public void removeFromCart(CustomerProduct Remove){
        cartItems.remove(Remove);
    };
    public void IncreaseProd(CustomerProduct inc){
        for (CustomerProduct cartItem : cartItems) {
            if (cartItem==inc && cartItem.getQuantity()<cartItem.getSupplierProd().getStock()){
                cartItem.setQuantity(cartItem.getQuantity()+1);
            }
        }
    }; // Increases quantity of product using Product.increaseQuantity
    public void DecreaseProd(CustomerProduct dec){
        for (CustomerProduct cartItem : cartItems) {
            if (cartItem==dec && cartItem.getQuantity()!=0){
                cartItem.setQuantity(cartItem.getQuantity()-1);
            }

        }
    }; // Decreases quantity of product using Product.decreaseQuantity







}

import java.util.ArrayList;

public class Cart{

    ArrayList<CustomerProduct> cartItems;
    private double totalPrice;

    Cart(){
        totalPrice = 0;
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
        for (CustomerProduct cartItem : cartItems) {
            cartItem.viewProduct();
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

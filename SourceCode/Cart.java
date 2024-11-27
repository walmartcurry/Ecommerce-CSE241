import java.util.ArrayList;

public class Cart{

    ArrayList<Product> cartItems;
    private double totalPrice;

    Cart(){
        totalPrice = 0;
        cartItems = new ArrayList<Product>();
    }

    public void calcTotalPrice(){
        for (Product cartItem : cartItems) {
            this.totalPrice += cartItem.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void viewcart(){
        for (Product cartItem : cartItems) {
            System.out.println(cartItem.getName());
        }
    };

    public void AddToCart(Product ADD){
        cartItems.add(ADD);
    };
    public void removeFromCart(Product Remove){
        cartItems.remove(Remove);
    };
    public void IncreaseProd(Product inc){
        for (Product cartItem : cartItems) {
            if (cartItem==inc){
                cartItem.setQuantity(cartItem.getQuantity()+1);
            }
        }
    }; // Increases quantity of product using Product.increaseQuantity
    public void DecreaseProd(Product dec){
        for (Product cartItem : cartItems) {
            if (cartItem==dec){
                cartItem.setQuantity(cartItem.getQuantity()-1);
            }
        }
    }; // Decreases quantity of product using Product.decreaseQuantity
}

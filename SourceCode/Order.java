
import java.util.HashMap;
import java.util.Map;

public class Order {
    enum PaymentMethods {
        Balance,
        Cash,

    }

    enum OrderStatus {
        PENDING,
        PAID,
        SHIPPED,
        CANCELED,
    }
    private static Map<String, PromoCode> promoCodeRepo = new HashMap<>();
    static {
        promoCodeRepo.put("SAVE10", new PromoCode("SAVE10", 10.0, false, LocalDate.of(2024, 12, 31), 50.0));
        promoCodeRepo.put("DISCOUNT20", new PromoCode("DISCOUNT20", 20.0, true, LocalDate.of(2024, 12, 31), 100.0));
    }

    private PaymentMethods paymentMethod;
    private Cart myCart;
    private double totalPrice;
    private double discount;
    private OrderStatus orderStatus;
    private String customerName;
    private String shippingAddress;

    public Order(String customerName, String shippingAddress) {
        this.myCart = new Cart();
        this.orderStatus = OrderStatus.PENDING;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
    }

    public void viewOrder() {
        System.out.println("Customer: " + customerName);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Cart Details: ");
        myCart.viewcart();
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Discount Applied: $" + discount);
         System.out.println("Final Price: $" + (totalPrice - discount));
        System.out.println("Payment Method: " + paymentMethod.toString());
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void payByBalance(double myBalance) {
        if (myBalance >= totalPrice) {
            System.out.println("Payment successful by balance.");
            orderStatus = OrderStatus.PAID;
        } else {
            System.out.println("Insufficient balance to complete the payment.");
        }
    }

    public void payByCash() {
        System.out.println("Payment successful by cash.");
        orderStatus = OrderStatus.PAID;
    }



    public void ChangeAddress(String newAddress) {
        this.shippingAddress = newAddress;
        System.out.println("Shipping address updated to: " + newAddress);
    }

    public void finalizeOrder() {
        if (orderStatus == OrderStatus.PAID) {
            orderStatus = OrderStatus.SHIPPED;
            System.out.println("Order has been finalized and shipped.");
        }
    }

    public void cancelOrder() {
        if (orderStatus == OrderStatus.SHIPPED) {
            orderStatus = OrderStatus.CANCELED;
            System.out.println("Order has been canceled.");
        } else {
            System.out.println("Cannot cancel the order at this stage.");
        }
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void addItemToCart(CustomerProduct product) {
        myCart.AddToCart(product);
    }

    public void removeItemFromCart(CustomerProduct product) {
        myCart.removeFromCart(product);
    }
    public void applyPromoCode(String promoCode) {
        PromoCode code = promoCodeRepo.get(promoCode);
        if (code == null) {
            System.out.println("Invalid promo code.");
            return;
        }

        if (!code.isValid(totalPrice)) {
            System.out.println("Promo code is invalid or does not meet the minimum order value.");
            return;
        }

        discount = code.calculateDiscount(totalPrice);
        System.out.println("Promo code applied! Discount of $" + discount + " applied.");
    }


    public Cart getMyCart() {
        return myCart;
    }
}











   

 

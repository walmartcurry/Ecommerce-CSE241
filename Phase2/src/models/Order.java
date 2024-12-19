package models;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    public enum PaymentMethods {
        Balance,
        Cash,

    }

    enum OrderStatus {
        PENDING,
        PAID,
        SHIPPED,
        CANCELED,
    }
    // enum Promocodes {
    //     save5(5.0),
    //     save10(10.0),
    //     save20(20.0),
    //     save30(30.0),
    //     save40(40.0),
    //     save50(50.0);
    //     private final double discount;

    //     void PromoCode(double discount) {
    //         this.discount = discount;
    //     }

    //     public double getDiscount() {
    //         return discount;
    //     }
    // }
    private PaymentMethods paymentMethod;
    private Cart myCart;
    // private double discount;
    private OrderStatus orderStatus;
    private String customerName;
    private String shippingAddress;
    
    private double discount;
    private static Map<String, PromoCode> promoCodeRepo = new HashMap<>();
    static {
        promoCodeRepo.put("SAVE10",new PromoCode("SAVE10", 10, LocalDate.of(2024, 12, 25) , 100.0));
        promoCodeRepo.put("SAVE20", new PromoCode("SAVE20", 20.0,LocalDate.of(2024, 12, 31), 100.0));
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getPaymentMethod() {
        return paymentMethod.name();
    }
public String getShippingAddress() {
    return shippingAddress;
}
    public Order(String customerName, String shippingAddress) {
        this.myCart = new Cart();
        this.orderStatus = OrderStatus.PENDING;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = PaymentMethods.Cash;
    }

    public void viewOrder() {
        System.out.println("Customer: " + customerName);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Cart Details: ");
        myCart.viewcart();
        System.out.println("Discount Applied: $" + discount);
         System.out.println("Final Price: $" + (myCart.getTotalPrice() - discount));
        System.out.println("Payment Method: " + paymentMethod.toString());
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void payByBalance(double myBalance) {
        setPaymentMethod(PaymentMethods.Balance);
        if (myBalance >= myCart.getTotalPrice()) {
            System.out.println("Payment successful by balance.");
            orderStatus = OrderStatus.PAID;
        } else {
            System.out.println("Insufficient balance to complete the payment.");
        }
    }

    public void payByCash() {
        setPaymentMethod(PaymentMethods.Cash);
        System.out.println("Payment successful by cash.");
        orderStatus = OrderStatus.PAID;
    }

    // public void applyDiscount(double discountAmount) {

    //     if (discountAmount > 0 && discountAmount <= totalPrice) {
    //         this.discount = discountAmount;
    //         System.out.println("Discount of $" + discountAmount + " applied.");
    //     } else {
    //         System.out.println("Invalid discount amount.");
    //     }
    // }

    public void ChangeAddress(String newAddress) {
        this.shippingAddress = newAddress;
        System.out.println("Shipping address updated to: " + newAddress);
    }

    public void finalizeOrder() {
        if (orderStatus == OrderStatus.PAID) {
            for( CustomerProduct product : myCart.cartItems){
                product.getSupplierProd().setStock((product.getSupplierProd().getStock()) - (product.getQuantity()));
            }
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

        if (!code.isValid(myCart.getTotalPrice())) {
            System.out.println(myCart.getTotalPrice());
            System.out.println("Promo code is invalid or does not meet the minimum order value.");
            return;
        }

        discount = code.calculateDiscount(myCart.getTotalPrice());
        System.out.println("Promo code applied! Discount of $" + discount + " applied.");
        myCart.afterDiscount(discount);
    }


    public Cart getMyCart() {
        return myCart;
    }


}







   





   

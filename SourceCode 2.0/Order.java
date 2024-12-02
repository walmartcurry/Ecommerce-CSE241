
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
    private double totalPrice;
    // private double discount;
    private OrderStatus orderStatus;
    private String customerName;
    private String shippingAddress;
    public String getCustomerName() {
        return customerName;
    }

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
        // System.out.println("Discount Applied: $" + discount);
        // System.out.println("Final Price: $" + (totalPrice - discount));
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

    // public void apllypromocode(String promocode) {
    //     try {
    //         Promocode code = promocode.valueOf(promocode.toUpperCase());
    //         double promodis = code.getDiscount;
    //         if (promoDiscount <= totalPrice) {
    //             this.discount = promoDiscount;
    //             System.out.println("Promo code applied! Discount of $" + promoDiscount + " applied.");

    //         } else {
    //             System.out.println("Promo code discount exceeds total price. Cannot apply this promo code.");

    //         }
    //     } catch (IllegalArgumentException e) {
    //         System.out.println("Invalid promo code.");

    //     }

    // }
    public Cart getMyCart() {
        return myCart;
    }
}







   

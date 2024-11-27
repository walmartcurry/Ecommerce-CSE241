import java.util.ArrayList;
import java.util.List;

public class Order
{


    enum PaymentMethods {
        Balance,
        Cash,

    }


    enum OrderStatus
    {
        PENDING,
        PAID,
        SHIPPED,
        CANCELED,
    }
    private paymentMethods paymentMethod;
    private Cart myCart;
    private double totalPrice;
    private double discount;
    private OrderStatus orderStatus;
    private String customerName;
    private String shippingAddress;

    public Order(String customerName, String shippingAddress)
    {
    this.paymentMethod=PaymentMethods.Balance;
    this.myCart=new cart();
    this.totalPrice=0.0;
    this.discount=0.0;
    this.orderStatus=OrderStatus.PENDING;
    this.customerName=customerName;
    this.shippingAddress=shippingAddress;

    }
    public void viewOrder() {
        System.out.println("Customer: " + customerName);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Cart Details: " + myCart);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Discount Applied: $" + discount);
        System.out.println("Final Price: $" + (totalPrice - discount));
        System.out.println("Payment Method: " + paymentMethod);
    }
    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void payByBalance(double myBalance) {
        if (myBalance >= (totalPrice - discount)) {
            System.out.println("Payment successful by balance.");
            totalPrice = 0;
            orderStatus = OrderStatus.PAID;
        } else {
            System.out.println("Insufficient balance to complete the payment.");
        }
    }
    public void payByCash() {
        System.out.println("Payment successful by cash.");
        totalPrice = 0;
        orderStatus = OrderStatus.PAID;
    }
    public void applyDiscount(double discountAmount) {
        if (discountAmount > 0 && discountAmount <= totalPrice) {
            this.discount = discountAmount;
            System.out.println("Discount of $" + discountAmount + " applied.");
        } else {
            System.out.println("Invalid discount amount.");
        }
    }
    public void ChangeAddress(String newAddress) {
        this.shippingAddress = newAddress;
        System.out.println("Shipping address updated to: " + newAddress);
    }
    public void finalizeOrder() {
        if (orderStatus == OrderStatus.PAID) {
            orderStatus = OrderStatus.SHIPPED;
            System.out.println("Order has been finalized and shipped.");
        } else {
            System.out.println("Order cannot be finalized until payment is made.");
        }
    }
        public void cancelOrder() {
            if (orderStatus == OrderStatus.PENDING) {
                orderStatus = OrderStatus.CANCELED;
                System.out.println("Order has been canceled.");
            } else {
                System.out.println("Cannot cancel the order at this stage.");
            }
        }
            public OrderStatus getOrderStatus () {
                return orderStatus;
            }
    public void addItemToCart(Product product) {
        myCart.AddToCart(product);
        totalPrice += product.getPrice();
    }
    public void removeItemFromCart(Product product) {
        if (myCart.removeFromCart(product)) {
            totalPrice -= product.getPrice();
            System.out.println(product + " has been removed from the cart.");
        } else {
            System.out.println(product + " was not found in the cart.");
        }
    }
    }

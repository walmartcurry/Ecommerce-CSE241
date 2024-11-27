public class Order {
    enum paymentMethods{
        Balance,
        Cash
    }
    paymentMethods paymentMethod;
    private Cart myCart;
    Order(){} // initialize price to zero , create an object Cart
    public void viewOrder(){};
    public void setPaymentMethod(){}
    public void finalizeOrder(){};

}

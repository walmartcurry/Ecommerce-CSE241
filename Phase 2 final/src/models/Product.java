package models;
public abstract class Product {
    protected String name;
    protected Category mycat;
    protected double price;
    Product(){
        this.name="null";
        this.price=0.0;
        mycat=new Category("other");

    } 
    Product(String name , double price, String category){
        this.name=name;
        this.price=price;
        mycat=new Category(category);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public String getMycat() {
        return mycat.gettype();
    }
    public abstract void viewProduct();
}



    


public class Product {
        private String name;
        private int quantity=0;
        private int stock=0;
        private Category mycat;
        private double price;
        Product(){
            this.name="null";
            this.stock=0;
            this.price=0.0;
            mycat=new Category();

        } // Set defualt to "NULL" and 0.0 and "Other"
        Product(String name , double price, String category,int stock){
            this.name=name;
            this.price=price;
            this.stock=stock;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public void increaseStock(int new_stock_quantity){
            this.stock=this.stock+new_stock_quantity;
            System.out.println("Now,There are "+this.stock +" items of "+this.name+" available for Sale!");
        };
        public void increaseQuantity(){
            quantity++;
        }; // Increases quantity while it's less than stock


    }


package models;
public class Category{
    public static final String[] categories = {
        "Electronics & Devices",
        "Clothing & Fashion", 
        "Books & Audible",
        "Sports & Outdoors",
        "Home & Kitchen",
        "Beauty & Personal Care",
        "Grocery & Gourmet",
        "Toys & Games",
        "Pet Supplies",
        "Automotive & Industrial"
    };
    
    private String type;
    public String gettype()
    {
        return type;
    }

    public Category(String category)
    {
        if(checkValidity(category.toLowerCase()))
        {
            this.type=category;
        }
        else {
            this.type="others";
        }
    }
    public static boolean checkValidity(String category)
    {
        for(int i=0;i< categories.length;i++)
        {
            if(category.equals(categories[i].toLowerCase()))
            { return true;
            }
        }
        return false;
    }
    public static void viewCategories(){
        for(int i = 0 ; i < categories.length ; i++){
            System.out.println((i+1)+" : "+categories[i]);
        }
    }
}

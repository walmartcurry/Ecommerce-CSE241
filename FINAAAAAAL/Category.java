
public class Category{
    public static final String[] categories={"electronics", "bakery", "clothes", "laptops", "others", 
        "furniture", "toys", "books", "groceries", "sports","dairy"};
    private String type;
    public Category(){
        this.type="others";
    }
    public String gettype()
    {
        return type;
    }

    public Category(String category)
    {
        category=category.toLowerCase();
        if(checkValidity(category))
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
            if(category.equals(categories[i]))
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

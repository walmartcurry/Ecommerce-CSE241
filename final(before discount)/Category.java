public class Category{
    private static final String[] categories={"electronics", "bakery", "clothes", "laptops", "others", 
        "furniture", "toys", "books", "groceries", "sports"};
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
            {
                return true;
            }
        }
        return false;
    }
}
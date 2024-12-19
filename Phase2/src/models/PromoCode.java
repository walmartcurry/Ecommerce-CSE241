package models;
import java.time.LocalDate;
public class PromoCode
{
    private String code;
    private double discount;
    private LocalDate expiryDate;
    private double minOrderValue;

    public PromoCode(String code,double discount,LocalDate expiryDate,double minOrderValue)
    {
        this.code=code;
        this.discount=discount;
        this.expiryDate = expiryDate;
        this.minOrderValue = minOrderValue;

    }

    public String getCode()
    {
        return code;
    }
    public boolean isValid(double orderValue)
    {
        return LocalDate.now().isBefore(expiryDate) && orderValue >= minOrderValue;
    }
    public double calculateDiscount(double orderValue)
    {
        
            return (discount / 100) * orderValue;
        
        
    }

}

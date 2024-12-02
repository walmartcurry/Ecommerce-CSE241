
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
public class PromoCode
{
    private String code;
    private double discount;
    private boolean isPercentage ;
    private LocalDate expiryDate;
    private double minOrderValue;

    public PromoCode(String code,double discount,boolean isPercentage,LocalDate expiryDate,double minOrderValue)
    {
        this.code=code;
        this.discount=discount;
        this.isPercentage = isPercentage;
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
        if (isPercentage) {
            return (discount / 100) * orderValue;
        }
        return discount;
    }

}

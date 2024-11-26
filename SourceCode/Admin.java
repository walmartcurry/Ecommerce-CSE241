public class Admin extends Person
{
    String[] role = {"ceo","call"};
    private String Role;
    private String working_hours;

    public String getRole() {
        return Role;
    }

    public void setRole(String r) {
        r=r.toLowerCase();
        for(int i=0;i<role.length;i++)
            if(r.equals(role[i])) {
                this.Role=r;
                break;
            }
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }
    public void add_supplier(Supplier new_sup){}
    public void view_products(){}
    public void view_suppliers(){}
    public void view_orders(){}
    public void view_customers(){}

}

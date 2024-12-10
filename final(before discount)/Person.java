import java.time.LocalDate;
public abstract class Person {
   protected String username;
    protected String password;
    protected LocalDate DOB;

Person(){
   username="NULL";
   password="NULL";
}

Person(String user, String pass, int year, int month, int day){
   this.username=user;
   this.password=pass;
   DOB = LocalDate.of(year, month, month);
}
   
}
import java.util.Date;

public abstract class Person {
   protected String username;
    protected String password;
    protected Date DOB;

Person(){
   username=NULL;
   password=NULL;
}

Person(String user, String pass, int year, int month, int day){
   this.username=user;
   this.password=pass;
   DOB = new Date(year,month,day);
}
   
}

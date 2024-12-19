package models;
import java.time.LocalDate;
public abstract class Person {
    protected String username;
    protected String password;
    protected LocalDate DOB;

    Person(){
        username="NULL";
        password="NULL";
    }
    public String getPassword() {
        return password;
    }

    Person(String user, String pass, int day, int month, int year){
        this.username=user;
        this.password=pass;
        DOB =
                LocalDate.of(year,month,day);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}


import java.util.ArrayList;
import java.util.List;

public class Engineer {
    private String firstname,lastname,email;
    private int id;

    public Engineer(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getSecondName(){
        return lastname;
    }

    public String getEmail(){
        return email;
    }

    public String getFirstName() {
        return firstname;
    }
}

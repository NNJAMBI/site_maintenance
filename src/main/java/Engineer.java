import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object otherEngineer){
        if (!(otherEngineer instanceof Engineer)) {
            return false;
        } else {
            Engineer newEngineer = (Engineer) otherEngineer;
            return this.getFirstName().equals(newEngineer.getFirstName()) &&
                    this.getSecondName().equals(newEngineer.getSecondName()) &&
                    this.getEmail().equals(newEngineer.getEmail());
        }
    }
}

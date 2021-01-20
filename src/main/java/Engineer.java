import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Engineer {
    private String firstname;
    private String lastname;
    private String email;
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

    public void save() {
        try(Connection con = DB.sql2o.open())  {
            String sql = "INSERT INTO engineer (firstname, lastname, email) VALUES (:firstname, :lastname, :email)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("firstname", this.firstname)
                    .addParameter("lastname",this.lastname)
                    .addParameter("email",this.email)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Engineer> all() {
        String sql = "SELECT * FROM engineer";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Engineer.class);
        }
    }

    public int getId() {
        return id;
    }
}

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Engineer {
    private String firstName;
    private String lastName;
    private String email;
    private int id;

    public Engineer(String firstname, String lastname, String email) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
    }

    public  List<Site> getSites() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sites where engineerId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Site.class);
        }
    }

    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }


    public static List<Engineer> all() {
        String sql = "SELECT * FROM engineer";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Engineer.class);
        }
    }


    public  void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO engineer (firstname, lastname, email) VALUES (:firstname, :lastname, :email)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("firstname", this.firstName)
                    .addParameter("lastname", this.lastName)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }


    public static Engineer find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM engineer where id=:id";
            Engineer engineer = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Engineer.class);
            return engineer;
        }
    }


    @Override
    public boolean equals(Object otherEngineer) {
        if (!(otherEngineer instanceof Engineer)) {
            return false;
        } else {
            Engineer newEngineer = (Engineer) otherEngineer;
            return this.getFirstName().equals(newEngineer.getFirstName()) &&
                    this.getSecondName().equals(newEngineer.getSecondName()) &&
                    this.getEmail().equals(newEngineer.getEmail());
        }
    }

    public void update(String firstname, String lastname, String email) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE engineer SET firstname = :firstname,lastname = :lastname,email = :email";
            con.createQuery(sql)
                    .addParameter("firstname", firstname)
                    .addParameter("lastname", lastname)
                    .addParameter("email", email)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM engineer WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EngineerDetails {

    public String firstName;
    public String lastName;
    public String email;
    public String site_name;
    public String county;

    public EngineerDetails(String firstName, String lastName, String email, String site_name, String county) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.site_name = site_name;
        this.county = county;
    }

    public void delete() {
    }
}

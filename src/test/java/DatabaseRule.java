import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/site_maintenance_test", "postgres", "admin");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteEngineerQuery = "DELETE FROM engineer";
            String deleteSiteQuery = "DELETE FROM sites";
            con.createQuery(deleteEngineerQuery).executeUpdate();
            con.createQuery(deleteSiteQuery).executeUpdate();
        }
    }

}

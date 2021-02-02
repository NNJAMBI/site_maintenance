import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import org.sql2o.Connection;


public class Site{
    private int id;
    private String site_name;
    private String  county;
    private int engineerId;
    private Timestamp createdAt;

    public Site(int id, String site_name, String county, int engineerId, Timestamp createdAt) {
        this.id = id;
        this.site_name = site_name;
        this.county = county;
        this.engineerId = engineerId;
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(int engineerId) {
        this.engineerId = engineerId;
    }

    public void save(){
        String sql = "INSERT INTO sites (site_name, county, engineerId) VALUES (:site_name, :county, :engineerId)";
        try(Connection con = DB.sql2o.open()){
             con.createQuery(sql, true)
                    .addParameter("site_name", this.site_name)
                    .addParameter("county", this.county)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Site> all() {
        String sql = "SELECT * FROM sites ORDER BY id DESC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Site.class);
        }
    }

    public static List<Site> findByEngineer(int engineerId) {
        String sql = "SELECT * FROM sites WHERE engineerId=:engineerId ORDER BY id DESC";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("engineerId", engineerId)
                    .executeAndFetch(Site.class);
        }
    }

    public static Site find(int id) {
        String sql = "SELECT * FROM Sites where id=:id";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Site.class);
        }
    }



    public void delete(){
        String sql = "DELETE FROM Sites WHERE id =:id;";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }


    public static void deleteAll(){
        String sql = "DELETE FROM sites";
        try(Connection con = DB.sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return getId() == site.getId() &&
                getSite_name().equals(site.getSite_name()) &&
                getCounty().equals(site.getCounty());
    }

    private String getCounty() {
        return county;
    }


    public void update(String site_name, String county){
        try(Connection con = DB.sql2o.open()){
            String query = "UPDATE Sites SET site_name = :site_name, county=:county  WHERE id = :id";
            con.createQuery(query)
                    .addParameter("site_name", site_name)
                    .addParameter("county", county)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSite_name(), getCounty());
    }

    }











    
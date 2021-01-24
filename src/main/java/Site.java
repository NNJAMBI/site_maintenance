import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Site{
    private int id;
    private String site_name;
    private int engineerId;
    private boolean completed;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object otherSite){
        if(!(otherSite instanceof Site)){
            return false;
        }else{
            Site newSite = (Site) otherSite;
            return this.getsite_name().equals(newSite.getsite_name()) &&
                    this.getId() == newSite.getId() &&
                    this.getengineerId() == newSite.getengineerId();
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO Sites (site_name, engineerId) VALUES (:site_name, :engineerId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("site_name", this.site_name)
                    .addParameter("engineerId", this.engineerId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void update(String site_name){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE Sites SET site_name = :site_name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("site_name", site_name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM Sites WHERE id =:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public Site(String site_name, int engineerId) {
        this.site_name = site_name;
        completed = false;
        createdAt = LocalDateTime.now();
        this.engineerId = engineerId;
    }

    public int getengineerId(){
        return engineerId;
    }

    public String getsite_name() {
        return site_name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static List<Site> all() {
        String sql = "SELECT id, site_name, engineerId FROM Sites";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Site.class);
        }
    }

    public int getId() {
        return id;
    }

    public static Site find(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM Sites where id=:id";
            Site Site = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Site.class);
            return Site;
        }
    }

    public String getSiteName() {
        return site_name;
    }
}
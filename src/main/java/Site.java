import java.time.LocalDateTime;
import java.util.List;
import org.sql2o.*;

public class Site {
    private String site_name;
    private LocalDateTime CreatedAt;
    private int id;
    private int engineerid;

    public Site(String site_name, int engineerid) {
        this.site_name = site_name;
        CreatedAt = LocalDateTime.now();
        this.engineerid = engineerid;
    }

    public static List<Site> all() {
        String sql = "SELECT id, site_name, engineerid FROM sites";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Site.class);
        }
    }

    @Override
    public boolean equals(Object otherSite){
        if ( !(otherSite instanceof Site)){
            return false;
        } else {
            Site newSite = (Site) otherSite;
            return this.getSiteName().equals(newSite.getSiteName()) &&
                    this.getId() == newSite.getId()&&
                    this.getEngineerId() == newSite.getEngineerId();
        }
    }

    public int getEngineerId() {
        return engineerid;
    }

    public String getSiteName() {
        return site_name;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }


    public void save() {
    }

    public int getId() {
        return id;
    }
}


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

    public String getSiteName() {
        return site_name;
    }
}


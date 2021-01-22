import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;


public class SiteTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Site_instantiatesCorrectly_true() {
        Site mySite = new Site("Thika Servers",  1);
        assertEquals(true, mySite instanceof Site);
    }

    @Test
    public void Site_instantiatesWithName_String() {
        Site mySite = new Site("Thika Servers", 1);
        assertEquals("Thika Servers", mySite.getSiteName());
    }


}


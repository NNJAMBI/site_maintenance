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

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_Today() {
        Site mySite = new Site("QOA DR", 1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), mySite.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void all_returnsAllInstancesOfSite_True() {
        Site firstSite = new Site("HQ DR", 1);
        firstSite.save();
        Site secondSite = new Site("Garissa DR", 1);
        secondSite.save();
        assertEquals(true, Site.all().get(0).equals(firstSite));
        assertEquals(true, Site.all().get(1).equals(secondSite));

    }

    @Test
    public void getId_sitesInstantiatesWithAnId_1() {
        Site mySite = new Site("MSR Power Station", 1);
        mySite.save();
        assertEquals(1, mySite.getId()>0 );
    }

    @Test
    public void equals_returnsTrueIfSite_nameAreTheSame() {
        Site firstSite = new Site("HQ3 DR", 1);
        Site secondSite = new Site("HQ3 DR", 1);
        assertTrue(firstSite.equals(secondSite));
    }

    @Test
    public void save_returnsTrueIfSite_namesAreTheSame() {
        Site mySite = new Site("HQ3 DR", 1);
        mySite.save();
        assertTrue(Site.all().get(0).equals(mySite));
    }
}


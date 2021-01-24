import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class SiteTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void update_updatesSitesName_true() {
        Site mySite = new Site("Thika Servers", 1);
        mySite.save();
        mySite.update("Syokimau MSR");
        assertEquals("Syokimau MSR", Site.find(mySite.getId()).getSiteName());
    }

    @Test
    public void Site_instantiatesCorrectly_true() {
        Site mySite = new Site("QOA DR", 1);
        assertEquals(true, mySite instanceof Site);
    }

    @Test
    public void Site_instantiatesWithDescription_String() {
        Site mySite = new Site("QOA DR", 1);
        assertEquals("QOA DR", mySite.getSiteName());
    }

    @Test
    public void isCompleted_isFalseAfterInstantiation_false() {
        Site mySite = new Site("QOA DR", 1);
        assertEquals(false, mySite.isCompleted());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Site mySite = new Site("QOA DR", 1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), mySite.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void all_returnsAllInstancesOfSite_true() {
        Site firstSite = new Site("HQ DR", 1);
        firstSite.save();
        Site secondSite = new Site("Mombasa MSR", 1);
        secondSite.save();
        assertEquals(true, Site.all().get(0).equals(firstSite));
        assertEquals(true, Site.all().get(1).equals(secondSite));
    }

    @Test
    public void getId_SitesInstantiateWithAnID_1() {
        Site mySite = new Site("GARISSA MSR", 1);
        mySite.save();
        assertTrue(mySite.getId()>0);
    }

    @Test
    public void find_returnsSiteWithSameId_secondSite() {
        Site firstSite = new Site("HQ DR", 1);
        firstSite.save();
        Site secondSite = new Site("Mombasa MSR", 1);
        secondSite.save();
        assertEquals(Site.find(secondSite.getId()), secondSite);
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Site firstSite = new Site("Kisumu DR", 1);
        Site secondSite = new Site("Kisumu DR", 1);
        assertTrue(firstSite.equals(secondSite));
    }

    @Test
    public void save_returnsTrueIfNamesAretheSame() {
        Site mySite = new Site("Kisumu DR", 1);
        mySite.save();
        assertTrue(Site.all().get(0).equals(mySite));
    }

    @Test
    public void save_assignsIdToObjects(){
        Site mySite = new Site("Kisumu DR", 1);
        mySite.save();
        Site savedSite = Site.all().get(0);
        assertEquals(mySite.getId(), savedSite.getId());
    }

    @Test
    public void delete_deletesSite_true(){
        Site mySite = new Site("Nairobi DR", 1);
        mySite.save();
        int mySiteId = mySite.getId();
        mySite.delete();
        assertEquals(null, Site.find(mySiteId));
    }
}
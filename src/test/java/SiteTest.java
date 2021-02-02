import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SiteTest{

    @Rule
    public DatabaseRule database = new DatabaseRule();
    private String site_name;

    @Test
    public void update_updatesSitesName_true() {
        Site mySite = new Site(site_name, "Thika Servers", "1");
        mySite.save();
        mySite.update("Syokimau MSR", "Machakos");
        assertEquals("Syokimau MSR", Site.find(mySite.getId()).getSite_name());
    }

    @Test
    public void Site_instantiatesCorrectly_true() {
        Site mySite = new Site(site_name, "QOA DR", "1");
        assertEquals(true, mySite instanceof Site);
    }

    @Test
    public void Site_instantiatesWithDescription_String() {
        Site mySite = new Site(site_name, "QOA DR", "1");
        assertEquals("QOA DR", mySite.getSite_name());
    }

  //  @Test
   // public void isCompleted_isFalseAfterInstantiation_false() {
       // Site mySite = new Site("QOA DR", 1);
       // assertEquals(false, mySite.isCompleted());
  //  }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Site mySite = new Site("site_name", "QOA DR", "1");
        assertEquals(LocalDateTime.now().getDayOfWeek(), mySite.getCreatedAt().toLocalDateTime());
    }

    @Test
    public void all_returnsAllInstancesOfSite_true() {
        Site firstSite = new Site(site_name, "HQ DR", "Nairobi");
        firstSite.save();
        Site secondSite = new Site(site_name, "Mombasa MSR", "Mombasa");
        secondSite.save();
        assertEquals(true, Site.all().get(0).equals(firstSite));
        assertEquals(true, Site.all().get(1).equals(secondSite));
    }

    @Test
    public void getId_SitesInstantiateWithAnID_1() {
        Site mySite = new Site(site_name, "GARISSA MSR", "Garissa");
        mySite.save();
        assertTrue(mySite.getId()>0);
    }

    @Test
    public void find_returnsSiteWithSameId_secondSite() {
        Site firstSite = new Site(site_name, "HQ DR", "Nairobi");
        firstSite.save();
        Site secondSite = new Site(site_name, "Mombasa MSR", "Mombasa");
        secondSite.save();
        assertEquals(Site.find(secondSite.getId()), secondSite);
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Site firstSite = new Site(site_name, "Kisumu DR", "Kisumu");
        Site secondSite = new Site(site_name, "Kisumu DR", "Kisumu");
        assertTrue(firstSite.equals(secondSite));
    }

    @Test
    public void save_returnsTrueIfNamesAretheSame() {
        Site mySite = new Site(site_name, "Kisumu DR","Kisumu" );
        mySite.save();
        assertTrue(Site.all().get(0).equals(mySite));
    }

    @Test
    public void save_assignsIdToObjects(){
        Site mySite = new Site(site_name, "Kisumu DR", "Kisumu");
        mySite.save();
        Site savedSite = Site.all().get(0);
        assertEquals(mySite.getId(), savedSite.getId());
    }

    @Test
    public void delete_deletesSite_true(){
        Site mySite = new Site(site_name, "Nairobi DR", "Nairobi");
        mySite.save();
        int mySiteId = mySite.getId();
        mySite.delete();
        assertEquals(null, Site.find(mySiteId));
    }
}
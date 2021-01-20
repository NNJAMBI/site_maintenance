import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.sql2o.*;

public class EngineerTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void newEngineer_instatiatesCorrectly() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "[email protected]");
        assertTrue(engineer instanceof Engineer);
    }

    @Test
    public void newEngineer_getsFirstName_Nancy() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "[email protected]");
        assertEquals("Nancy", engineer.getFirstName());
    }

    @Test
    public void newEngineer_getsSecondName_Nancy() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "[email protected]");
        assertEquals("Karanja", engineer.getSecondName());
    }

    @Test
    public void newEngineer_getsEmail_emailprotected() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "[email protected]");
        assertEquals("[email protected]", engineer.getEmail());
    }

    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        Engineer anotherEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        assertTrue(firstEngineer.equals(anotherEngineer));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Engineer() {
        Engineer testEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        testEngineer.save();
        assertTrue(Engineer.all().get(0).equals(testEngineer));
    }

    @Test
    public void all_returnsAllInstancesOfEngineer_true() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        firstEngineer.save();
        Engineer secondEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        secondEngineer.save();

        assertEquals(true, Engineer.all().get(0).equals(firstEngineer));
        assertEquals(true, Engineer.all().get(1).equals(secondEngineer));
    }

    @Test
    public void save_assignsIdToObject() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        firstEngineer.save();
        Engineer savedEngineer = Engineer.all().get(0);
        assertEquals(firstEngineer.getId(), savedEngineer.getId());
    }

    @Test
    public void getId_engineerInstantiateWithAnID() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        firstEngineer.save();
        assertTrue(firstEngineer.getId() > 0);
    }

    @Test
    public void find_returnsEngineerWithSameId_secondEngineer() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        firstEngineer.save();
        Engineer secondEngineer = new Engineer("Brian", "Masai","[email protected]");
        secondEngineer.save();
        assertEquals(Engineer.find(secondEngineer.getId()), secondEngineer);
    }

    @Test
    public void update_updatesEngineer_true() {
        Engineer firstEngineer = new Engineer("Nancy", "Karanja","[email protected]");
        firstEngineer.save();
        firstEngineer.update("Brian", "Masai","[email protected]");
        assertEquals("Brian", Engineer.find(firstEngineer.getId()).getFirstName());
    }
}


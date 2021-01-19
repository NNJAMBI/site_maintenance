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
}


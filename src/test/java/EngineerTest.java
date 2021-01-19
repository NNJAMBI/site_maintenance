import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EngineerTest {

    @Test
    public void newEngineer_instatiatesCorrectly() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "nkaranja7@gmail.com");
        assertTrue(engineer instanceof Engineer);
    }

    @Test
    public void newEngineer_getsFirstName_Nancy() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "nkaranja7@gmail.com");
        assertEquals("Nancy", engineer.getFirstName());
    }

    @Test
    public void newEngineer_getsSecondName_Nancy() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "nkaranja7@gmail.com");
        assertEquals("Karanja", engineer.getSecondName());
    }

    @Test
    public void newEngineer_getsEmail_nkaranja7() {
        Engineer engineer = new Engineer("Nancy", "Karanja", "nkaranja7@gmail.com");
        assertEquals("nkaranja7@gmail.com", engineer.getEmail());

    }
}


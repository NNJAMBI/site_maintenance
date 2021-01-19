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
}


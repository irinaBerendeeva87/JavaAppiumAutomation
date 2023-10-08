import org.example.MainClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassNumber() {
        boolean result = mainClass.getClassNumber() > 45;
        assertTrue("Test failed. Expected value greater than 45", result);
    }

}
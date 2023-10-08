import org.example.MainClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        int actual = mainClass.getLocalNumber();
        int expected = 14;
        assertEquals("The number is not equal to 14", expected, actual);
    }

}

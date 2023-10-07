import org.example.MainClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber(){
        int actual = super.getLocalNumber();
        int expected = 14;
        assertEquals("The number is not equal to 14", expected, actual);
    }
}

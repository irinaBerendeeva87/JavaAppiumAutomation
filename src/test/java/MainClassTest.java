import org.example.MainClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainClassTest {
    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassString() {
        String str = mainClass.getClassString();
        boolean containsSubstrings = str.contains("hello") || str.contains("Hello");
        assertTrue("Expect: String contains 'hello' or 'Hello'!, actual :" + str, containsSubstrings);
    }
}

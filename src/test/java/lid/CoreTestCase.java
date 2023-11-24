package lid;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import junit.framework.TestCase;

import java.net.URL;

public class CoreTestCase extends TestCase  {
    protected AppiumDriver driver;
    private static final String URL = "http://0.0.0.0:4723/";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Android Emulator")
                .setPlatformVersion("8.1")
                .setUdid("emulator-5556")
                .setAutomationName("UiAutomator2")
                .setAppPackage("org.wikipedia")
                .setAppActivity(".main.MainActivity")
                .setApp(System.getProperty("user.dir") + "/apks/org.wikipedia_new.apk");
        driver = new AndroidDriver(new URL(URL), options);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if (driver != null) {
            driver.quit();
        }
    }
}

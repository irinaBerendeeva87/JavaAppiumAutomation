package lid;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    public static AppiumDriver driver;
    private static final String URL = "http://0.0.0.0:4723/";
@Before
    public void android_setUp() throws MalformedURLException {
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

    protected void iOS_setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName("wiki13")
                .setPlatformVersion("15.5")
                .setAutomationName("XCUITest")
                .setApp(System.getProperty("user.dir") + "/apks/Wikipedia.app");
        driver = new IOSDriver(new URL(URL), options);
    }
@After
    public void tearDown()  {
        if (null!= driver) {
            driver.quit();
        }
    }

    public void rotateScreenPortrait(){
        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);

    }

    public void rotateScreenLandscape(){
        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

    }
}

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import static org.junit.Assert.assertTrue;

public class AndroidFirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "nightwatch-android-11");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/irina/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchFieldContainsText() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                5);

        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Search input field does not contain the expected text.'",
                5);
    }

    @Test
    public void cancelSearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia'",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search')]"),"Netherlands",
                "Can't find search input",
                5 );
        waitForElementPresent(By.className("androidx.recyclerview.widget.RecyclerView"),
                "Search_results_list is empty",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Can't find X on search field",
                5);
        boolean isElementPresent =  waitForElementNotPresent(By.id("org.wikipedia:id/search_results_list"),
                "Results are still present on the page",
                5);
        assertTrue("Results are still present on the page",isElementPresent);
    }

    private WebElement waitForElementAndClick(By locator, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By locator, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void assertElementHasText(By locator,String expectedText, String error_message, long timeoutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSecond);
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(error_message);
        }
    }

    private WebElement waitForElementAndSendKeys(By locator, String value, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(locator, error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By locator, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}


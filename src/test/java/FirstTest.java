import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;


public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidEmulator");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("udid", "emulator-5556");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/irina/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_new.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"),capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void swipeArticleToElement() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                ofSeconds(5));
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia'",
                ofSeconds(5));
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Can't find Appium in search",
                ofSeconds(10));
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),
                "Can't find 'Appium' topic searching by Java",
                ofSeconds(10));
        waitForElementPresent(By.xpath("//*[contains(@content-desc, 'Appium')]"),
                "Can't find the title",
                ofSeconds(15));
        swipeToFindElement(By.xpath(
                        "//*[contains(@content-desc, 'View article in browser')]"),
                "Can't find the end of the article",
                20);
    }

    @Test
    public void amountOfNotEmptySearch(){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                ofSeconds(5));
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia'",
                ofSeconds(5));
        String search_Line = "ABBA discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_Line,
                "Can't find Appium in search",
                ofSeconds(10));
        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@class = 'android.view.ViewGroup']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Can't find anything by the request "+search_Line,
                ofSeconds(15));
        int amountOfSearchResults = getAmountOfElements(
                By.xpath(searchResultLocator)
        );
        assertTrue("We found too few results",amountOfSearchResults >0 );
    }



    private WebElement waitForElementAndClick(By locator, String error_message, Duration duration) {
        WebElement element = waitForElementPresent(locator, error_message,duration);
        element.click();
        return element;
    }

    private WebElement waitForElementPresent(By locator, String error_message, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitForElementAndSendKeys(By locator, String value, String error_message, Duration duration) {
        WebElement element = waitForElementPresent(locator, error_message,duration);
        element.sendKeys(value);
        return element;
    }

    protected void swipeUp() {
        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;
        int endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(scroll));
    }

    protected void swipeQuick() {
        swipeUp();
    }

    protected void swipeToFindElement(By by, String error_message, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Can't find element by swipe up. \n", ofSeconds(5));
                return;
            }
            swipeQuick();
            alreadySwiped++;
        }
    }

    private int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }
}


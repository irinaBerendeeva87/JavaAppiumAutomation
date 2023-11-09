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

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertEquals;

public class AndroidAdvancedTest {
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
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void saveTwoArticlesToMyList() {
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
                "Java",
                "Can't find Java in search",
                ofSeconds(5));
        String locatorJava = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Java (programming language)']";
        waitForElementAndClick(
                By.xpath(locatorJava),
                "Can't find 'Java (programming language)' topic searching by Java",
                ofSeconds(15));
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Can't find 'Save' button",
                ofSeconds(5));
        String addToListBtn = "//*[@resource-id = 'org.wikipedia:id/snackbar_action'][@text = 'Add to list']";
        waitForElementAndClick(
                By.xpath(addToListBtn),
                "Can't find button " +addToListBtn,
                ofSeconds(10));
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "My list",
                "Can't find 'My list' in the field",
                ofSeconds(5));
        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can't find 'Ok' button",
                ofSeconds(5));
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_search"),
                "Can't find 'Search Wikipedia' button",
                ofSeconds(5));
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Can't find Appium in search",
                ofSeconds(5));
        String locatorAppium = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']";
        waitForElementAndClick(
                By.xpath(locatorAppium),
                "Can't find 'Appium' topic",
                ofSeconds(10));
        String saveBtn = "org.wikipedia:id/page_save";
        waitForElementAndClick(
                By.id(saveBtn),
                "Can't find 'Save' button",
                ofSeconds(5));
        waitForElementAndClick(
                By.xpath(addToListBtn),
                "Can't find button " + addToListBtn,
                ofSeconds(10));
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Can't find 'My list' button",
                ofSeconds(10));
        String viewListBtn = "org.wikipedia:id/snackbar_action";
        waitForElementAndClick(
                By.id(viewListBtn),
                "Can't find button "+ viewListBtn,
                ofSeconds(5));
        swipeLeft(
                By.xpath(locatorAppium),
                "Can't find saved article");
        waitForElementNotPresent(
                By.xpath(locatorAppium),
                "Can't delete saved article",
                ofSeconds(5));
        waitForElementAndClick(
                By.xpath(locatorJava),
                "Can't find 'Java (programming language)' topic searching by Java",
                ofSeconds(15));
        WebElement elementTitle = waitForElementPresent(
                By.xpath("//*[@content-desc= 'Java (programming language)']"),
                "Can't find the title",
                ofSeconds(10));
        String articleTitle = elementTitle.getAttribute("content-desc");
        assertEquals("We see unexpected title",articleTitle, "Java (programming language)");
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

    private boolean waitForElementNotPresent(By locator, String error_message, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver,duration);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void swipeLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, ofSeconds(10));
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeLeft = new Sequence(finger, 0);
        int left_x = element.getRect().x + (element.getSize().width * 3/4);
        int upper_y = element.getRect().y + (element.getSize().height / 2);
        int right_x = element.getRect().x + (element.getSize().width / 4);
        int lower_y = element.getRect().y + (element.getSize().height / 2);
        swipeLeft.addAction(
                finger.createPointerMove(Duration.ofSeconds(0),
                        PointerInput.Origin.viewport(), left_x, upper_y));
        swipeLeft.addAction(
                finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeLeft.addAction(
                finger.createPointerMove(Duration.ofMillis(700),
                        PointerInput.Origin.viewport(), right_x, lower_y));
        swipeLeft.addAction(
                finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipeLeft));
    }

}

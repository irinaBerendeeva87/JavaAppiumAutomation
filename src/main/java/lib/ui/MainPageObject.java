package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementAndClick(By by, String error_message, Duration duration) {
        WebElement element = waitForElementPresent(by, error_message, duration);
        element.click();
        return element;
    }

    public WebElement waitForElementPresent(By by, String error_message, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndSendKeys(By locator, String value, String error_message, Duration duration) {
        WebElement element = waitForElementPresent(locator, error_message, duration);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By locator, String error_message, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void assertElementHasText(By locator,String expectedText, String error_message, Duration duration) {
        WebElement element = waitForElementPresent(locator, error_message, duration);
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(error_message);
        }

    }

    public int getAmountOfElements(By by){
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }
    public void swipeUp() {
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
    public void swipeQuick() {
        swipeUp();
    }

    public void swipeToFindElement(By by, String error_message, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).isEmpty()) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Can't find element by swipe up. \n", ofSeconds(5));
                return;
            }
            swipeQuick();
            alreadySwiped++;
        }
    }

    public void swipeLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, ofSeconds(10));
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipeLeft = new Sequence(finger, 0);
        int left_x = element.getRect().x + (element.getSize().width * 3 / 4);
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
    public String waitForElementGetAttribute(By by,String attribute, String error_message,Duration duration ){
        WebElement element = waitForElementPresent(by, error_message, duration);
        return element.getAttribute(attribute);
    }
    //method without wait
    public void assertElementPresent(By by, String error_message) {
        WebElement element = driver.findElement(by);
        String articleTitle = element.getAttribute("content-desc");
        if (articleTitle.isEmpty()) {
            String defaultError = "An element " + by.toString() + " supposed to be present." ;
            throw new AssertionError(defaultError + error_message);
        }

    }
//    public void rotation(){
//    }
    //method with wait
//    public void assertElementPresent(By by, String error_message) {
//        WebElement element = waitForElementPresent((by), "testError", ofSeconds(15));
//        String articleTitle = element.getAttribute("content-desc");
//        if (articleTitle.isEmpty()) {
//            String defaultError = "An element " + by.toString() + " supposed to be present." ;
//            throw new AssertionError(defaultError + error_message);
//        }
//    }
}

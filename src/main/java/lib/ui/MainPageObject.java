package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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
import static org.openqa.selenium.support.PageFactory.initElements;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }


    public void clickElement(WebElement element, Duration duration) {
        waitForVisibility(element , duration);
        element.click();

    }

    public WebElement waitForVisibility(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void waitForElementAndSendKeys(WebElement element, String value,Duration duration) {
        waitForVisibility(element, duration);
        element.sendKeys(value);
    }

    public boolean waitForElementNotPresent(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        return wait.until(
                ExpectedConditions.invisibilityOf(element));
    }

    public void assertElementHasText(WebElement element, String expectedText, String errorMessage, Duration duration) {
        waitForVisibility(element, duration);
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(errorMessage);
        }
    }

    public int getAmountOfElements(By element) {
        List<WebElement> elements = driver.findElements(element);
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

    public void swipeToFindElement(WebElement locator, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements((By) locator).isEmpty()) {
            if (alreadySwiped > maxSwipes) {
                waitForVisibility(locator, ofSeconds(5));
                return;
            }
            swipeQuick();
            alreadySwiped++;
        }
    }

    public void swipeLeft(WebElement element) {
        waitForVisibility(element, ofSeconds(10));
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

    public String waitForElementGetAttribute(By locator, String attribute) {
       return driver.findElement(locator).getAttribute(attribute);
    }

    //method with wait
    public void assertElementPresentWithWait(WebElement locator, String errorMessage) {
        WebElement element = waitForVisibility(locator, ofSeconds(15));
        String articleTitle = element.getAttribute("content-desc");
        if (articleTitle.isEmpty()) {
            String defaultError = "An element " + locator.toString() + " supposed to be present." ;
            throw new AssertionError(defaultError + errorMessage);
        }
    }
    //method without wait
    public void assertElementPresent(WebElement element,String attribute) {
        String articleTitle = element.getAttribute(attribute);
        if (articleTitle.isEmpty()) {
            String defaultError = "An element " + element.toString() + " supposed to be present.";
            throw new AssertionError( defaultError);
        }
    }
}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofSeconds;

public class WelcomePageObject extends MainPageObject {
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']")
    WebElement learnMoreAboutLink;

    @iOSXCUITFindBy(accessibility = "New ways to explore")
    WebElement newWayToExploreText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Next']")
    WebElement nextLink;

    @iOSXCUITFindBy(accessibility = "Search in over 300 languages")
    WebElement search300LangText;

    @iOSXCUITFindBy(accessibility = "Help make the app better")
    WebElement helpMakeAppBetterLink;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Get started']")
    WebElement getStartedButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Skip']")
    WebElement skipLink;


    public void waitForNewWaysToExploreText() {
        waitForVisibility(newWayToExploreText,  ofSeconds(10));
    }

    public void waitForAddOrEditPreferredLangText() {
        waitForVisibility(search300LangText,  ofSeconds(10));
    }

    public void waitForHelpMakeAppText() {
        waitForVisibility(helpMakeAppBetterLink,  ofSeconds(10));
    }

    public void clickNextButton() {
        clickElement(nextLink, ofSeconds(10));
    }
    public void clickSkipButton() {
        clickElement(skipLink,  ofSeconds(10));
    }

    public void clickGetStartedButton() {
        clickElement(getStartedButton,  ofSeconds(10));
    }

}

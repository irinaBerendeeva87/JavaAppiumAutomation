package lib.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofSeconds;

public class MyListPageObject extends MainPageObject {
    private static final String
            ARTICLE_BY_TITLE_TPL = "//*[contains(@text, '{TITLE}')]";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getSavedArticleByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }
    /*TEMPLATES METHODS */

    public void waitForArticleToAppearedByTitle(String articleTitle) {
        String articleTitleXpath = getSavedArticleByTitle(articleTitle);
        driver.findElement(By.xpath(articleTitleXpath)).isDisplayed();
    }

    public void waitForArticleToDisappearedByTitle(String articleTitle) {
        String articleXpath = getSavedArticleByTitle(articleTitle);
        WebElement articleEl = driver.findElement(By.xpath(articleXpath));
        waitForElementNotPresent(articleEl, ofSeconds(5));
    }

    public void swipeByArticleToDelete(String articleTitle) {
        waitForArticleToAppearedByTitle(articleTitle);
        String articleXpath = getSavedArticleByTitle(articleTitle);
        WebElement articleToDeleteSwipe = driver.findElement(AppiumBy.xpath(articleXpath));
        swipeLeft(articleToDeleteSwipe);
        waitForArticleToDisappearedByTitle(articleTitle);
    }

    public void openArticleByTitle(String articleTitle) {
        String articleXpath = getSavedArticleByTitle(articleTitle);
        WebElement articleEl = driver.findElement(By.xpath(articleXpath));
        clickElement(articleEl, ofSeconds(5));
    }
}

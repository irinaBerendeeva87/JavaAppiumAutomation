package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            TITLE = "//*[contains(@content-desc, 'Appium')]",
            FOOTER_ELEMENT = "//*[contains(@content-desc, 'View article in browser')]",
            ARTICLE_SAVE_BOTTOM = "org.wikipedia:id/page_save",
            OPTION_ADD_TO_LIST_BOTTOM = "//*[@resource-id = 'org.wikipedia:id/snackbar_action'][@text = 'Add to list']",
            OPTION_OK_BOTTOM = "android:id/button1",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            VIEW_LIST_BOTTOM = "org.wikipedia:id/snackbar_action",
            MY_LIST_FOLDER = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text = 'My list']";


    public WebElement waitForTitleElement() {
        return waitForElementPresent(By.xpath(TITLE),
                "Can't find the title",
                Duration.ofSeconds(5));
    }

    public String getTitleElement() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("content-desc");
    }

    public void swipeToFooter() {
        swipeToFindElement(By.xpath(FOOTER_ELEMENT), "Can't find the end of the article", 20);
    }


    public void addFirstArticleToMyList(String nameOfFolder) {
        waitForElementAndClick(By.id(ARTICLE_SAVE_BOTTOM), "Can't find the Save bottom", ofSeconds(5));
        waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_LIST_BOTTOM),
                "Can't find and click 'Add to list bottom' ",
                ofSeconds(10));
        waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                nameOfFolder,
                "Can't find 'My list' in the field",
                ofSeconds(5));
        waitForElementAndClick(
                By.id(OPTION_OK_BOTTOM),
                "Can't find 'Ok' button",
                ofSeconds(5));
    }

    public void addSecondArticleToMyList() {
        waitForElementAndClick(By.id(ARTICLE_SAVE_BOTTOM), "Can't find the Save bottom", ofSeconds(5));
        waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_LIST_BOTTOM),
                "Can't find and click 'Add to list' bottom ",
                ofSeconds(10));
        waitForElementAndClick(
                By.xpath(MY_LIST_FOLDER),
                "Can't find 'My list' bottom",
                ofSeconds(10));
    }

    public void openMyListViaViewList() {
        waitForElementAndClick(By.id(VIEW_LIST_BOTTOM), "Can't find 'VIEW_LIST' bottom ", ofSeconds(5));
    }
    public void openArticleByTitle(String articleTitle){
        waitForElementAndClick(
                By.xpath(articleTitle),
                "Can't find 'Java (programming language)' topic searching by Java",
                ofSeconds(15));
    }
}
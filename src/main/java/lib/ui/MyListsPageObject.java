package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static java.time.Duration.ofSeconds;

public class MyListsPageObject extends MainPageObject {
    private static final String
            ARTICLE_BY_TITLE_TPL = "//*[@text = '{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getSavedArticle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);

    }

    public void waitForArticleToAppearedByTitle(String articleTitle) {
        String articleXpath = getSavedArticle(articleTitle);
        waitForElementPresent(By.xpath(articleXpath),
                "Can't find saved article by title" + articleTitle,
                ofSeconds(15));
    }

    public void waitForArticleToDisappearedByTitle(String articleTitle) {
        String articleXpath = getSavedArticle(articleTitle);
        waitForElementNotPresent(By.xpath(articleXpath), "Can't delete saved article", ofSeconds(5));
    }

    public void swipeByArticleToDelete(String articleTitle) {
        waitForArticleToAppearedByTitle(articleTitle);
        String articleXpath = getSavedArticle(articleTitle);
        swipeLeft(By.xpath(articleXpath), "Can't find saved article");
        waitForArticleToDisappearedByTitle(articleTitle);
    }


}

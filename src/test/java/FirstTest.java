import lib.ui.ArticlePageObject;

import lib.ui.SearchPageObject;
import lid.TestBase;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FirstTest extends TestBase {
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    @Test
    public void testSearch() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
    }

    @Test
    public void testAmountOfEmptySearch() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("sqdfwgfw");
    }

    @Test
    public void testCompareArticle() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        assertEquals("We see unexpected title", "Appium", articleTitle);

    }

    @Test
    public void testSwipeArticleToElement() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testAmountOfNotEmptySearch() throws MalformedURLException {
        android_setUp();
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("ABBA discography");
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results", amountOfSearchResults > 0);

    }
}
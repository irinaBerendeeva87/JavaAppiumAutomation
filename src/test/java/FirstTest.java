import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lid.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

import static java.time.Duration.ofSeconds;


public class FirstTest extends CoreTestCase {
    private MainPageObject mainPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.waitForSearchResult("Appium");
    }

    @Test
    public void testAmountOfEmptySearch(){
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("sqdfwgfw");
    }

    @Test
    public void testCompareArticle() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getTitleElement();
        assertEquals("We see unexpected title", "Appium", articleTitle);

    }

    @Test
    public void testSwipeArticleToElement() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("ABBA discography");
        searchPageObject.clickByArticleWithSubstring("ABBA discography");


//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Can't find the Skip bottom",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Can't find 'Search Wikipedia'",
//                ofSeconds(5));
//        String search_Line = "ABBA discography";
//        mainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                search_Line,
//                "Can't find 'ABBA discography' in search",
//                ofSeconds(10));
        String searchResultLocator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@class = 'android.view.ViewGroup']";
        mainPageObject.waitForElementPresent(By.xpath(searchResultLocator), "Can't find anything by the request ", ofSeconds(15));
        int amountOfSearchResults = mainPageObject.getAmountOfElements(
                By.xpath(searchResultLocator)
        );
        assertTrue("We found too few results", amountOfSearchResults > 0);
    }
}


import io.appium.java_client.android.AndroidDriver;
import lib.ui.*;
import lid.CoreTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;


import static java.time.Duration.ofSeconds;

public class AndroidAdvancedTest extends CoreTestCase {
    private MainPageObject mainPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private MyListPageObject myListPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        myListPageObject = new MyListPageObject(driver);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String nameOfFolder = "My list";
        articlePageObject.addFirstArticleToMyList(nameOfFolder);
        searchPageObject.clickToolbarSearch();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.addSecondArticleToMyList();
        articlePageObject.openMyListViaViewList();
        myListPageObject.swipeByArticleToDelete("Appium");
        myListPageObject.openArticleByTitle("Java (programming language)");
        String article_Title = articlePageObject.getTitleElement();
        assertEquals("We see unexpected title", "Java (programming language)", article_Title);

    }

    @Test
    public void testAssertTitle() {
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        articlePageObject.assertTittleWithoutWaiting();
    }
    @Test
    public void testRotationArticle(){
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                ofSeconds(5));
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia'",
                ofSeconds(5));
        String searchLine = "Java";
        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                searchLine,
                "Can't find Java in search",
                ofSeconds(5));
        String locatorJava = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']";
        mainPageObject.waitForElementAndClick(
                By.xpath(locatorJava),
                "Can't find 'Java (programming language)' topic searching by Java",
                ofSeconds(15));
        String elementLocator = "//*[@content-desc= 'Java (programming language)']";
        String titleBeforeRotation = mainPageObject.waitForElementGetAttribute(
                By.xpath(elementLocator),
                "content-desc",
                "Can't find title of article",
                ofSeconds(15));
        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = mainPageObject.waitForElementGetAttribute(
                By.xpath(elementLocator),
                "content-desc",
                "Can't find title of article",
                ofSeconds(15));
        assertEquals("titles are not equal",titleBeforeRotation,titleAfterRotation);
    }

}

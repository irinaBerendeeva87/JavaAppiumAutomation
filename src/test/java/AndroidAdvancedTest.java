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
    private MyListsPageObject myListsPageObject;
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipBottom();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        articlePageObject = new ArticlePageObject(driver);
        String nameOfFolder = "My list";
        articlePageObject.addFirstArticleToMyList(nameOfFolder);
        searchPageObject.clickToolbarSearch();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.addSecondArticleToMyList();
        articlePageObject.openMyListViaViewList();
        myListsPageObject = new MyListsPageObject(driver);
//        String articleTitle = articlePageObject.getTitleElement();
        myListsPageObject.swipeByArticleToDelete("Appium");
        articlePageObject.openArticleByTitle("Java (programming language)");
//        WebElement elementTitle = articlePageObject.waitForElementPresent(
//                By.xpath("//*[@content-desc= 'Java (programming language)']"),
//                "Can't find the title",
//                ofSeconds(10));
//        String article_Title = elementTitle.getAttribute("content-desc");
//        assertEquals("We see unexpected title", article_Title, "Java (programming language)");


//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Can't find the Skip bottom",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Can't find 'Search Wikipedia'",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Java",
//                "Can't find Java in search",
//                ofSeconds(5));
//        String locatorJava = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Java (programming language)']";
//        mainPageObject.waitForElementAndClick(
//                By.xpath(locatorJava),
//                "Can't find 'Java (programming language)' topic searching by Java",
//                ofSeconds(15));
//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_save"),
//                "Can't find 'Save' button",
//                ofSeconds(5));
//        String addToListBtn = "//*[@resource-id = 'org.wikipedia:id/snackbar_action'][@text = 'Add to list']";
//        mainPageObject.waitForElementAndClick(
//                By.xpath(addToListBtn),
//                "Can't find button " + addToListBtn,
//                ofSeconds(10));
//        mainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                "My list",
//                "Can't find 'My list' in the field",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndClick(
//                By.id("android:id/button1"),
//                "Can't find 'Ok' button",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_toolbar_button_search"),
//                "Can't find 'Search Wikipedia' button",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Appium",
//                "Can't find Appium in search",
//                ofSeconds(5));
//        String locatorAppium = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text = 'Appium']";
//        mainPageObject.waitForElementAndClick(
//                By.xpath(locatorAppium),
//                "Can't find 'Appium' topic",
//                ofSeconds(10));
//        String saveBtn = "org.wikipedia:id/page_save";
//        mainPageObject.waitForElementAndClick(
//                By.id(saveBtn),
//                "Can't find 'Save' button",
//                ofSeconds(5));
//        mainPageObject.waitForElementAndClick(
//                By.xpath(addToListBtn),
//                "Can't find button " + addToListBtn,
//                ofSeconds(10));
//        mainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/item_title_container"),
//                "Can't find 'My list' button",
//                ofSeconds(10));
//        String viewListBtn = "org.wikipedia:id/snackbar_action";
//        mainPageObject.waitForElementAndClick(
//                By.id(viewListBtn),
//                "Can't find bottom " + viewListBtn,
//                ofSeconds(5));
//        mainPageObject.swipeLeft(
//                By.xpath(locatorAppium),
//                "Can't find saved article");
//        mainPageObject.waitForElementNotPresent(
//                By.xpath(locatorAppium),
//                "Can't delete saved article",
//                ofSeconds(5));


//        mainPageObject.waitForElementAndClick(
//                By.xpath(locatorJava),
//                "Can't find 'Java (programming language)' topic searching by Java",
//                ofSeconds(15));
//        WebElement elementTitle = mainPageObject.waitForElementPresent(
//                By.xpath("//*[@content-desc= 'Java (programming language)']"),
//                "Can't find the title",
//                ofSeconds(10));
//        String article_Title = elementTitle.getAttribute("content-desc");
//        assertEquals("We see unexpected title", article_Title, "Java (programming language)");
    }

    @Test
    public void testAssertTitle() {
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Can't find the Skip bottom",
                ofSeconds(5));
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia'",
                ofSeconds(5));
        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't find Java in search",
                ofSeconds(5));
        String locatorJava = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']";
        mainPageObject.waitForElementAndClick(
                By.xpath(locatorJava),
                "Can't find 'Java (programming language)' topic searching by Java",
                ofSeconds(15));
        String elementLocator = "//*[@content-desc= 'Java (programming language)']";
        mainPageObject.assertElementPresent(
                By.xpath(elementLocator),
                "We not found any results by request without  ");
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

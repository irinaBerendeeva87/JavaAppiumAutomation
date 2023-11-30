import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lid.TestBase;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class AndroidAdvancedTest extends TestBase {

    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    @Test
    public void testSaveTwoArticlesToMyList() throws MalformedURLException {
        android_setUp();
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String nameOfFolder = "My list";
        articlePageObject.addFirstArticleToMyList(nameOfFolder);
        searchPageObject.clickToolbarSearch();
        searchPageObject.typeSearchLine("appium automation for mobile");
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");
        articlePageObject.addSecondArticleToMyList();
        articlePageObject.openMyListViaViewList();
        myListPageObject.swipeByArticleToDelete("Appium");
        myListPageObject.openArticleByTitle("Java (programming language)");
        String article_Title = articlePageObject.getArticleTitle();
        assertEquals("We see unexpected title", "Java (programming language)", article_Title);
        tearDown();
    }

    @Test
    public void testAssertTitle() {
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        articlePageObject.assertTittleWithoutWaiting();
        tearDown();
    }

    @Test
    public void testRotationArticle() throws MalformedURLException {
        android_setUp();
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        rotateScreenPortrait();
        assertEquals("Titles are not equal", titleBeforeRotation, titleAfterRotation);
        tearDown();
    }

}
import lib.ui.SearchPageObject;
import lid.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class AndroidFirstTest extends TestBase {
    private SearchPageObject searchPageObject;


    @Test
    public void testSearchFieldContainsText() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.assertPlaceholderText();
        tearDown();
    }

    @Test
    public void testCancelSearch() {
        searchPageObject = new SearchPageObject(driver);
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Netherlands");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        boolean isElementPresent = searchPageObject.SearchResultAfterClickCANCEL_BUTTON();
        assertTrue("Results are still present on the page", isElementPresent);
    }
}
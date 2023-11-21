import lib.ui.SearchPageObject;
import lid.CoreTestCase;
import org.junit.Test;

public class AndroidFirstTest extends CoreTestCase {
    private SearchPageObject searchPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        searchPageObject = new SearchPageObject(driver);
    }

    @Test
    public void testSearchFieldContainsText() {
        searchPageObject.clickSkipButton();
        searchPageObject.assertPlaceholderText();
    }

    @Test
    public void testCancelSearch() {
        searchPageObject.clickSkipButton();
        searchPageObject.clickSearchInput();
        searchPageObject.typeSearchLine("Netherlands");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        boolean isElementPresent = searchPageObject.SearchResultAfterClickCANCEL_BUTTON();
        assertTrue("Results are still present on the page", isElementPresent);
    }
}
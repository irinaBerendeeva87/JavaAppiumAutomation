package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofSeconds;

public class SearchPageObject extends MainPageObject {

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    WebElement skipButton;

    @AndroidFindBy(id = "org.wikipedia:id/search_container")
    WebElement searchLineEl;

    @AndroidFindBy(xpath = "//*[contains(@text, 'Search Wikipedia')]")
    WebElement searchLinePlaceholder;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/search_close_btn")
    WebElement searchCancelButton;

    @AndroidFindBy(id = "org.wikipedia:id/search_results_list")
    WebElement searchResultList;

    @AndroidFindBy(id = "org.wikipedia:id/page_toolbar_button_search")
    WebElement searchLineToolbar;

    @AndroidFindBy(xpath = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@class = 'android.view.ViewGroup']")
    WebElement searchResultElement;

    private static final String
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_RESULT_EL = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@class = 'android.view.ViewGroup']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS */

    public void clickSkipButton() {
        clickElement(skipButton, ofSeconds(5));
    }

    public void clickSearchInput() {
        clickElement(searchLineEl, ofSeconds(10));
    }

    public void typeSearchLine(String searchLine) {
        waitForElementAndSendKeys(searchInput, searchLine, ofSeconds(15));
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        WebElement searchResultElement = driver.findElement(By.xpath(searchResultXpath));
        waitForVisibility(searchResultElement, ofSeconds(5));
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        WebElement searchResultElement = driver.findElement(By.xpath(searchResultXpath));
        searchResultElement.click();
    }

    public void waitForCancelButtonToAppear() {
        waitForVisibility(searchCancelButton, ofSeconds(5));
    }

    public void waitForCancelButtonToDisappear() {
        waitForElementNotPresent(searchCancelButton, ofSeconds(5));
    }

    public void clickCancelSearch() {
        this.clickElement(searchCancelButton, ofSeconds(5));
    }

    public boolean SearchResultAfterClickCANCEL_BUTTON() {
        return waitForElementNotPresent(searchResultList, ofSeconds(5));
    }

    public void assertPlaceholderText() {
        WebElement element = waitForVisibility(searchLinePlaceholder, ofSeconds(5));
        String expectedText = element.getAttribute("text");
        assertElementHasText(searchLinePlaceholder, expectedText, "Can't find 'Search Wikipedia'", ofSeconds(5));
    }

    public void clickToolbarSearch() {
        clickElement(searchLineToolbar, ofSeconds(5));
    }

    public int getAmountOfFoundArticles() {
        waitForVisibility(searchResultElement, ofSeconds(15));
        return getAmountOfElements(By.xpath(SEARCH_RESULT_EL));


    }

}
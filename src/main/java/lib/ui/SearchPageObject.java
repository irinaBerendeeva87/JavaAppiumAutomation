package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofSeconds;

public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_BOTTOM = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_LINE_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_LINE_PLACEHOLDER = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS */

    public void clickSkipButton() {
        this.waitForElementAndClick(By.id(SKIP_BOTTOM), "Can't find the Skip bottom", ofSeconds(5));
    }

    public void clickSearchInput() {
        this.waitForElementAndClick(By.id(SEARCH_LINE_ELEMENT),
                "Can't find and click search line element", ofSeconds(10));
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), searchLine,
                "Can't find and type into search input", ofSeconds(15));
    }

    public void waitForSearchResult(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent (By.xpath(searchResultXpath),
                "Can't find search result with substring " +substring,
                ofSeconds(5));
    }

    public void waitForCancelButtonToAppear(){
        waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Can't find search cancel button", ofSeconds(5));
    }

    public void waitForCancelButtonToDisappear(){
        waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present", ofSeconds(5));
    }
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),
                "Can't find  and click the Cancel button",
                ofSeconds(5));
    }

    public boolean SearchResultAfterClickCANCEL_BUTTON(){
        return waitForElementNotPresent(By.id(SEARCH_RESULT_LIST),
                "Results are still present on the page", ofSeconds(5));
    }

    public void assertPlaceholderText(){
        WebElement element = waitForElementPresent(By.id(SEARCH_LINE_ELEMENT),"Can't find element",ofSeconds(5));
        String expectedText = element.getAttribute("text");
        assertElementHasText(By.xpath(SEARCH_LINE_PLACEHOLDER),expectedText,"Can't find 'Search Wikipedia'", ofSeconds(5));
    }

    public void clickByArticleWithSubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        waitForElementAndClick(By.xpath(searchResultXpath),
                "Can't find  and click search result with substring " +substring,
                ofSeconds(5));
    }

}
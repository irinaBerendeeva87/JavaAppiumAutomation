package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static java.time.Duration.ofSeconds;

public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_BOTTOM = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_LINE_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_LINE_PLACEHOLDER = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text, '{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS */

    public void clickSkipBottom() {
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

    public void waitForSearchResult(String subs
package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static java.time.Duration.ofSeconds;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@content-desc = 'Java (programming language)']")
    WebElement articleTitle;

    @AndroidFindBy(xpath = "//*[contains(@content-desc, 'View article in browser')]")
    WebElement footerElement;

    @AndroidFindBy(accessibility = "Save")
    WebElement articleSaveBtn;

    @AndroidFindBy(xpath = "//*[@resource-id = 'org.wikipedia:id/snackbar_action'][@text = 'Add to list']")
    WebElement addToListBtn;

    @AndroidFindBy(id = "android:id/button1")
    WebElement okBtn;

    @AndroidFindBy(id = "org.wikipedia:id/text_input")
    WebElement myListNameInput;

    @AndroidFindBy(id = "org.wikipedia:id/snackbar_action")
    WebElement viewListBtn;

    @AndroidFindBy(xpath = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text = 'My list']")
    WebElement myListFolder;

    public WebElement waitForTitleElement() {
       return waitForVisibility(articleTitle, ofSeconds(5));

    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("content-desc");
    }

    public void swipeToFooter() {
        swipeToFindElement(footerElement, "Can't find the end of the article",
                20);
    }


    public void addFirstArticleToMyList(String nameOfFolder) {
        clickElement(articleSaveBtn, ofSeconds(5));
        clickElement(addToListBtn, ofSeconds(10));
        waitForElementAndSendKeys(myListNameInput, nameOfFolder, ofSeconds(5));
        clickElement(okBtn, ofSeconds(5));
    }

    public void addSecondArticleToMyList() {
        clickElement(articleSaveBtn, ofSeconds(5));
        clickElement(addToListBtn, ofSeconds(10));
        clickElement(myListFolder, ofSeconds(10));
    }

    public void openMyListViaViewList() {
        clickElement(viewListBtn, ofSeconds(5));
    }

    public void assertTittleWithoutWaiting(){
        assertElementPresent(articleTitle,
                "We not found any results by request without waiting");
    }

}
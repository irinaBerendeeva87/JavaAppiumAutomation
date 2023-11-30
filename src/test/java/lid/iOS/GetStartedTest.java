package lid.iOS;

import lib.ui.WelcomePageObject;
import lid.TestBase;
import org.junit.Test;

import java.net.MalformedURLException;


public class GetStartedTest extends TestBase {

    @Test
    public void testPassThroughWelcome() throws MalformedURLException {
        iOS_setUp();
        WelcomePageObject welcomePageObj = new WelcomePageObject(driver);
        welcomePageObj.clickNextButton();

        welcomePageObj.waitForNewWaysToExploreText();
        welcomePageObj.clickNextButton();

        welcomePageObj.waitForAddOrEditPreferredLangText();
        welcomePageObj.clickNextButton();

        welcomePageObj.waitForHelpMakeAppText();
        welcomePageObj.clickGetStartedButton();
        tearDown();
    }

}

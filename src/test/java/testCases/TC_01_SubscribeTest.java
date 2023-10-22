package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import java.util.ResourceBundle;

public class TC_01_SubscribeTest extends BaseTestClass {
    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void subscribe() {
        try {
            driver.get(ResourceBundle.getBundle("config").getString("appURL"));
            HomePage homePage = new HomePage(driver);
            homePage.inputSearch("security+").clickSignIn();

            SignInPage signInPage = new SignInPage(driver);
            String email = "fuckerforlife";
            signInPage.inputEmail(email).clickSubscribeButton();
            var status = signInPage.getSubscribeStatus();

            Assert.assertTrue(status);
            System.out.println(signInPage.getSubscribeStatusText());

        } catch (NoSuchElementException | TimeoutException | AssertionError e ) {
            logger.fatal(e.getMessage());
            logger.info(System.getProperty("os.name"));
            Assert.fail(e.getMessage());

        }

    }
}

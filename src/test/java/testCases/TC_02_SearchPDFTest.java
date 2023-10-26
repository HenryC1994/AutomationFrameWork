package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseTestClass;

import java.util.ResourceBundle;

public class TC_02_SearchPDFTest extends BaseTestClass {

    @Test
    public void searchItem() {
        try {
            driver.get(ResourceBundle.getBundle("config").getString("appURL"));
            HomePage homePage = new HomePage(driver);
            homePage.inputSearch("css").clickSearchButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.pdfdrive.com/css-books.html");

        } catch (Exception e) {

        }
    }
}

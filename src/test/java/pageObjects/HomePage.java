package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Link = https://www.pdfdrive.com/

    //WebElements
    @FindBy(linkText = "Sign in")
    WebElement signInButton;

    @FindBy(xpath = "//input[@id='q']")
    WebElement searchInput;


    // Action Methods
    public HomePage clickSignIn() {
        wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
        return this;
    }

    public HomePage inputSearch(String inputSearchItem) {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).sendKeys("inputSearchItem");
        return this;
    }

}

package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends HomePage {

    // link = https://www.pdfdrive.com/auth/login?redirURL=%2F

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='email-cats']")
    WebElement emailInputBox;

    @FindBy(xpath = "//body/div[3]/div[1]/div[2]/div[5]/form[1]/div[1]/div[1]/span[1]/button[1]")
    WebElement subscribeButton;

    @FindBy(css = "#subscribebox")
    WebElement subscribeStatus;


    public SignInPage inputEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInputBox)).sendKeys(email);
        return this;
    }

    public SignInPage clickSubscribeButton() {
        wait.until(ExpectedConditions.visibilityOf(subscribeButton)).click();
        return this;
    }

    public boolean getSubscribeStatus() {
        try{
            return wait.until(ExpectedConditions.visibilityOf(subscribeStatus)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getSubscribeStatusText() {

            return wait.until(ExpectedConditions.visibilityOf(subscribeStatus)).getText();

    }



}

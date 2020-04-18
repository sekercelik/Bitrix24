package com.automation.pages;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage extends AbstractBasePage {

    @FindBy(className = "log-popup-header")
    private WebElement authorizationLogo;

    @FindBy(name = "USER_LOGIN")
    private WebElement username; //+

    @FindBy(name="USER_PASSWORD") //+
    private WebElement password;

    @FindBy(className = "login-btn") //+
    private WebElement login;

    @FindBy(className = "login-link-forgot-pass")
    private WebElement forgotPassword;

    @FindBy (className = "errortext") //+
    private WebElement warningMessage;

    @FindBy(id = "USER_REMEMBER")
    private WebElement remember;

    @FindBy(className = "log-popup-header")
    private WebElement forgotGetPasswordLogo;

    @FindBy(name = "USER_LOGIN")
    private WebElement forgotLogin;

    @FindBy(name = "USER_EMAIL")
    private WebElement forgotEmail;

    @FindBy(xpath = "//a[.='Authorization']")
    private  WebElement forgotAuthorizationBtn;

    @FindBy(className = "login-btn")
    private WebElement resetPasswordBtn;

    @FindBy(className = "errortext")
    private WebElement forgotErrorMessage;





    public LoginPage() {

        PageFactory.initElements(Driver.getDriver(), this);

    }
    public String getWarningMessageText(){
        return warningMessage.getText();
    }

    public void login(String usernameValue, String passwordValue){
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
        BrowserUtils.wait(3);
    }

    public void login(){
        BrowserUtils.wait(3);
        username.sendKeys(ConfigurationReader.getProperty("username"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }
    public void loginAs(String userType){
        username.sendKeys(ConfigurationReader.getProperty(userType));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        login.click();
    }





}

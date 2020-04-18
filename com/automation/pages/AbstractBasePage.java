package com.automation.pages;
import com.automation.utilities.Driver;
import org.openqa.selenium.WebDriver;
import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {
    protected WebDriver driver= Driver.getDriver();
    protected WebDriverWait wait=new WebDriverWait(driver,20);
    @FindBy(id="user-block")
    protected WebElement currentUser;
    @FindBy(className = "time-hours")
    protected WebElement clockHour;
    @FindBy(className = "time-minutes")
    protected WebElement clockMin;
    @FindBy(className = "time-am-pm")
    protected WebElement clockAmPm;
    @FindBy(id="timeman-container")
    protected WebElement clockBlock;
    @FindBy(id="search-textbox-input")
    protected WebElement searchBox;
    @FindBy(id = "sitemap-menu")
    protected  WebElement siteMapBtn;


    public AbstractBasePage(){
        PageFactory.initElements(driver,this);
    }

    /**
     * This method returns Current User Name
     * @return user name as String value
     */


    public String getCurrentUserName(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }
    /**
     * This method will return time in different format which user specified
     * @param format accepts format as
     *               HH:MM for full time
     *               or HH for only hour
     *               or MM for only minutes
     *               or PERIOD only for day period (AM or PM)
     * @return time as String value
     */
    public String getCurrentTime(String format){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(clockBlock));
        format=format.toUpperCase();
        switch (format){
            case "HH:MM":
                return clockHour.getText()+":"+clockMin.getText()+" "+clockAmPm.getText();
            case "HH":
                return clockHour.getText();
            case "MM":
                return clockMin.getText();
            case "PERIOD":
                return clockAmPm.getText();
            default:
                return "invalid time format selection";
        }
    }
    /**
     * This method will run search function based on provided String value
     * @param value accepts String value
     */
    public void search_value(String value){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(value, Keys.ENTER);
    }
    /**
     * This method will navigate to user specified module
     * @param module user will enter module name. Case Sensitive!!
     */
    public void topNavigation(String module){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(siteMapBtn));
        siteMapBtn.click();
        String path="//a[@class='sitemap-section-title' and contains(text(),'"+module+"')]";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path)))).click();
    }
    /**
     *This method will navigate to user specified Module and Sub-Module
     * @param module user will enter main module name. Case Sensitive!!
     * @param subModule user will enter sub-module name. Case Sensitive!!
     */
    public void topNavigation(String module, String subModule){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(siteMapBtn));
        siteMapBtn.click();
        String path="//*[@class='sitemap-section-title' and contains(text(),'"+module+"')] /..//a[@class='sitemap-section-item' and contains(text(),'"+subModule+"')]";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path)))).click();
    }
    /**
     * This method will navigate to user specified main Modules on left menu
     * @param module user will enter a main module name. Case Sensitive!!
     */
    public void leftNavigation(String module){
        BrowserUtils.waitForPageToLoad(10);
        WebElement leftmenu=driver.findElement(By.xpath("//*[@title='"+module+"']"));
        wait.until(ExpectedConditions.visibilityOf(leftmenu)).click();
    }
}

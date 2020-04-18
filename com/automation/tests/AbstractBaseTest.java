package com.automation.tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public abstract class AbstractBaseTest {

    protected WebDriverWait wait;
    protected Actions actions;

    protected ExtentReports extentReports;
    protected ExtentHtmlReporter extentHtmlReporter;
    protected ExtentTest extentTest;


    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName) {
        System.out.println("Report name: " + reportName);
        reportName = reportName == null ? "report.html" : reportName+".html";

        extentReports = new ExtentReports();

        String reportPath = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }
        extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setReportName("NextBase CRM Test Automation Results");
    }

    @AfterTest
    public void afterTest() {
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() {
        String URL = ConfigurationReader.getProperty("environment");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(), 25);
        actions = new Actions(Driver.getDriver());
    }


    @AfterMethod
    public void teardown(ITestResult iTestResult) throws IOException {

        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
            extentTest.fail(iTestResult.getName());
            BrowserUtils.wait(2);
            extentTest.addScreenCaptureFromPath(screenshotPath, "Failed");
            extentTest.fail(iTestResult.getThrowable());
        }
        BrowserUtils.wait(2);
        Driver.closeDriver();
    }

}
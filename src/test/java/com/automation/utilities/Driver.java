package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

public class Driver {
    //same for everyone
    private static WebDriver driver;

    //so no one can create object of Driver class
    //everyone should call static getter method instead
    private Driver() {

    }

    public static WebDriver getDriver() {
        //if webdriver object doesn't exist
        //create it
        if (driver == null) {
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().version("79").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    public int removeElement0(int[] nums, int val) {
        int remove=nums.length;
        int n=0;
        for (int i = 0; i <nums.length ; i++) {

            if(nums[i]==val){
                remove--;
            }else {
                nums[n]=nums[i];
                i++;
            }

        }
        return remove;
    }
    public static int removeElement(int[] nums, int val) {
        int length=nums.length;
        int j=length-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==val){
                length--;
                if(j>i){
                    while(j>0&&nums[j]==val)j--;
                    nums[i]=nums[j];
                    j--;
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,2,2};
        int s=removeElement(nums,2);
        System.out.println(Arrays.toString(nums));
        System.out.println(s);
    }



}

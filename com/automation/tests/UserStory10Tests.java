package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pages.UserStory10;
import com.automation.utilities.BrowserUtils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserStory10Tests extends AbstractBaseTest {

    @Test(description = "User should be able to make a comment")
    public void test1(){
        extentTest = extentReports.createTest("verify user can comment " );
        LoginPage loginPage=new LoginPage();
        loginPage.loginAs("marketing");
        UserStory10 userStory10=new UserStory10();
        userStory10.addComment("adding comments...");
        BrowserUtils.wait(3);
        Assert.assertEquals(userStory10.getCommentText(),"adding comments...");

    }
    @Test (description = "User can like others posts")
    public void test2(){
        extentTest = extentReports.createTest("verify user can like a post" );
        LoginPage loginPage=new LoginPage();
        loginPage.loginAs("marketing");
        UserStory10 userStory10=new UserStory10();
        userStory10.clickLike();
        Assert.assertTrue(userStory10.getLikeIcon().isDisplayed());

    }
    @Test(description = "User can use emojis to like other's posts")
    public void test3(){
        extentTest = extentReports.createTest("verify user can use emoji's" );
        LoginPage loginPage=new LoginPage();
        loginPage.loginAs("marketing");
        UserStory10 userStory10=new UserStory10();
        actions.moveToElement(userStory10.getLikeIcon2()).perform();
        BrowserUtils.wait(2);
        userStory10.icons("laugh").click();
        Assert.assertEquals(userStory10.getEmojiIconText(),"Laugh");

    }
    @Test(description = "User should be able to click on reviewers' name and visit their profiles")
    public void test4(){
        extentTest = extentReports.createTest("verify user can visit reviewers' profile" );
        LoginPage loginPage=new LoginPage();
        loginPage.loginAs("marketing");
        UserStory10 userStory10=new UserStory10();
        String reviewer=userStory10.getReviewer().getText();
        userStory10.getReviewer().click();
        Assert.assertEquals(userStory10.getReviewersProfileText(),reviewer);

    }
    @Test(description = "User should be able to add others' posts to favorite by clicking on the Star icon.")
    public void test5(){
        extentTest = extentReports.createTest("verify user can add to favorite other's post" );
        LoginPage loginPage=new LoginPage();
        loginPage.loginAs("marketing");
        UserStory10 userStory10=new UserStory10();
        userStory10.getFavorite().click();
        BrowserUtils.wait(3);
        Assert.assertEquals(userStory10.getFavorite().getAttribute("title"),"Remove from favorites");
    }



}

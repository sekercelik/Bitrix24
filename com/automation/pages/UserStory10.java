package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.util.List;

public class UserStory10 extends AbstractBasePage {
    @FindBy(css = "[contenteditable='true']")
    protected WebElement commentBox;
    @FindBy(css="[id^='lhe_button_submit_blogCommentForm']")
    protected WebElement sendButton;
    @FindBy(xpath="(//a[.='Comment'])[1]")
    protected WebElement comment;
    @FindBy(id="record-BLOG_408-93-text")
    protected WebElement commentText;
    @FindBy(xpath="(//a[contains(@id,'bp')])[1]")
    protected WebElement reviewer;
    @FindBy(xpath = "(//span[contains(@id,'bx-ilike-button-BLOG_POST')])[1]")
    protected WebElement like;
    @FindBy(xpath = "//span[@title='Like']")
    protected WebElement likeIcon;
    @FindBy(id="pagetitle")
    protected WebElement reviewersProfile;
    @FindBy(xpath = "(//div[contains(@id,'log_entry_favorites')])[1]")
    protected WebElement favorite;
    @FindBy(xpath = "(//div[contains(@id,'bx-ilike-result-reaction-laugh-BLOG_POST')])[1]")
    protected WebElement emojiIcon;


    @FindBy(xpath = "(//span[contains(@id,'bx-ilike-button-BLOG_POST')])[1]")
    protected WebElement likeIcon2;

    public WebElement getLikeIcon2(){
        return likeIcon2;
    }

    public String getEmojiIconText(){
        return emojiIcon.getAttribute("title");
    }

    //kiss,laugh,wonder,cry,angry
    public WebElement icons(String feeling){
        return driver.findElement(By.cssSelector("[data-reaction='"+feeling+"']"));
    }

    public WebElement getFavorite(){
        return favorite;
    }

    public String getReviewersProfileText(){
        return reviewersProfile.getText();
    }

    public WebElement getLikeIcon(){
        return likeIcon;
    }

    public void clickLike(){
        like.click();
    }

    public WebElement getReviewer(){
         return reviewer;
    }

    public String getCommentText(){
        return commentText.getText();
    }

    public void addComment(String comments){
        comment.click();
        driver.switchTo().frame(driver.findElement(By.className("bx-editor-iframe")));
        commentBox.sendKeys(comments);
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();

    }





}

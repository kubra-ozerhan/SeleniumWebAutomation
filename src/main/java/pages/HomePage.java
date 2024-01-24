package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  extends BasePage{
    // Locators
    By rejectAllCookiesBtn = By.id("onetrust-reject-all-handler");
    By closeIconLocator = By.xpath("//button[@class='o-modal__closeButton bwi-close']");
    By campaignCardLocator = By.xpath("//div[@class='o-campaignSlider']");


    SearchBox searchBox;

    // constructor
    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
    }

    // Getter method
    public SearchBox searchBox(){
        return this.searchBox;
    }

    public Boolean isOnHomePage() throws InterruptedException {
        Thread.sleep(100);
        // check if campaign cart is shown to verify being in the home page and return a boolean value
        return isDisplayed(campaignCardLocator);
    }

    public void rejectCookies() throws InterruptedException {
        Thread.sleep(1500);
        // if cookies option box appears
        if (isDisplayed(rejectAllCookiesBtn)){
            Thread.sleep(2);
            // select rejecting the cookies button
            click(rejectAllCookiesBtn);
        }
    }

    public void closeSelectingGenderBox() throws InterruptedException {
        // if close icon appears on the selecting gender box
        if (isDisplayed(closeIconLocator)){
            Thread.sleep(10);
            // click close icon
            click(closeIconLocator);
        }
    }


}

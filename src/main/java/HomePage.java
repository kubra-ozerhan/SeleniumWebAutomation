import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage  extends BasePage{

    By rejectAllCookiesBtn = By.id("onetrust-reject-all-handler");
    By closeIconLocator = By.xpath("//button[@class='o-modal__closeButton bwi-close']");
    By campaignCardctaButtonLocator = new By.ByCssSelector("a.m-campaignCard__ctaButton");

    SearchBox searchBox;

    By cartCountIncreasedLocator = By.id("icon icon-cart icon-cart-active");  // actually not id


    public HomePage(WebDriver driver) {
        super(driver);
        searchBox = new SearchBox(driver);
    }

    public SearchBox searchBox(){
        return this.searchBox;
    }
    public Boolean isOnHomePage(){
        return isDisplayed(campaignCardctaButtonLocator);
    }
    public Boolean wasProductAdded(){
        return isDisplayed(cartCountIncreasedLocator);
    }

    public void goToCart(){
        click(cartCountIncreasedLocator);
    }

    public void rejectCookies() throws InterruptedException {
        Thread.sleep(1500);
        if (isDisplayed(rejectAllCookiesBtn)){
            Thread.sleep(2);
            click(rejectAllCookiesBtn);
        }
    }

    public void closeSelectingGenderBox() throws InterruptedException {
        if (isDisplayed(closeIconLocator)){
            Thread.sleep(8);
            click(closeIconLocator);
        }
    }



}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    // To find the element with given locator
    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    // To find the all elements with given locator
    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    // To click on the element with given locator
    public void click(By locator){
        find(locator).click();
    }

    // To fill in an element with given locator and text information
    public void type(By locator , String text){
        find(locator).sendKeys(text);
    }

    // To get a boolean value if the web element with given locator has displayed or not
    public Boolean isDisplayed(By locator){
        return find(locator).isDisplayed();
    }

}

package pages;

import org.openqa.selenium.*;

import java.util.List;

public class ProductDetailPage  extends BasePage{
    // Locators
    By sizeVariationsLocator = By.xpath("//span[starts-with(@class,'m-variation__item')]");
    By addToCartBtn = By.xpath("//button[@class='m-addBasketFavorite__basket btn']");

    // constructor
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isOnProductDetailPage() throws InterruptedException {
        Thread.sleep(2);
        // check if add to cart button is shown to verify being in the product detail page and return a boolean value
        return isDisplayed(addToCartBtn);
    }
    public void addSelectedProductIntoCart() throws InterruptedException {
        // Get all existing sizes of a selected product from detail page
        List<WebElement> sizeVariations = getAllProductSizes();

        // Get the count of total sizes for the selected product
        int totalCount = sizeVariations.size();
        int i = 0;
        // Out of stock sizes can not be selected so with the help following loop the latest selectable size will be clicked
        while(i < totalCount){
            // select size
            selectSize(i);
            i++;
        }
        // add the product with selected size into basket
        click(addToCartBtn);
        Thread.sleep(100);
    }

    public void selectSize(int i){
        // Get all size variations for selected product
        List<WebElement> sizeVariations = getAllProductSizes();
        // Out of stock sizes are not clickable, so clicking to sizes handled like below
        try{
            sizeVariations.get(i).click();
        } catch(NoSuchElementException e){
            // print the exception details
            e.printStackTrace();
        }

    }

    private List<WebElement> getAllProductSizes(){
        // Get all size variations for selected product
        List<WebElement> sizeVariations = findAll(sizeVariationsLocator);
        // return it as a list
        return sizeVariations;
    }

}

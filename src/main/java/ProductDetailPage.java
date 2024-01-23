import org.openqa.selenium.*;

import java.util.List;

public class ProductDetailPage  extends BasePage{

    By sizeVariationsLocator = By.xpath("//span[starts-with(@class,'m-variation__item')]");
    By addToCartBtn = By.xpath("//button[@class='m-addBasketFavorite__basket btn']");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isOnProductDetailPage() throws InterruptedException {
        Thread.sleep(2);
        return isDisplayed(addToCartBtn);
    }
    public void addSelectedProductIntoCart() throws InterruptedException {
        List<WebElement> sizeVariations = getAllProductSizes();

        int totalCount = sizeVariations.size();
        int i = 0;
        while(i < totalCount){
            selectSize(i);
            i++;
        }
        click(addToCartBtn);
        Thread.sleep(50);
    }

    public void selectSize(int i){
        List<WebElement> sizeVariations = getAllProductSizes();
        try{
            sizeVariations.get(i).click();
        } catch(NoSuchElementException e){}

    }

    private List<WebElement> getAllProductSizes(){
        List<WebElement> sizeVariations = findAll(sizeVariationsLocator);
        return sizeVariations;
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductsPage  extends BasePage{
    // Locators
    By productsListTitleLocator = By.id("productListTitle");
    By productDescriptionLocator = By.xpath("//span[@class='m-productCard__desc']");
    By productPriceLocator = By.xpath("//span[@class='m-productCard__newPrice']");

    // constructor
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isOnProductsPage() throws InterruptedException {
        Thread.sleep(5000);
        // check if product list title is shown to verify being in the product list page and return a boolean value
        return isDisplayed(productsListTitleLocator);
    }
    public void clickOnProduct(int i) throws InterruptedException {
        // Get all product descriptions
        List<WebElement> ProductDescriptions = getAllProductDescriptions();
        // Get the given element from all product descriptions list
        WebElement ProductDescription = ProductDescriptions.get(i);

        // scroll a bit to be able to click the web element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");

        Thread.sleep(5);
        // click to product description element to navigate to product detail page
        ProductDescription.click();
    }

    public void writeDescriptionAndPriceInformationOfAProductIntoAFile(int i) throws InterruptedException {
        // Get all product descriptions
        List<WebElement> ProductDescriptions = getAllProductDescriptions();
        // Get the given element from all product descriptions list
        WebElement ProductDescription = ProductDescriptions.get(i);

        // Get all product prices
        List<WebElement> ProductPrices = getAllProductPrices();
        // Get the given element from all product prices list
        WebElement ProductPrice = ProductPrices.get(i);

        // the txt file path
        String txtFilePath = "src/ProductDescriptionAndPriceInformation.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilePath))) {
            // write the description and price information of the given indexed product into the txt file
            writer.write(ProductPrice.getText() + "\n" + ProductDescription.getText());
        } catch (IOException e) {
            // print the exception details
            e.printStackTrace();
        }

    }

    public List<WebElement> getAllProductDescriptions() throws InterruptedException {
        Thread.sleep(5);
        // Get descriptions of all Product elements
        List<WebElement> descriptionData = findAll(productDescriptionLocator);
        return descriptionData;
    }
    private List<WebElement> getAllProductPrices() throws InterruptedException {
        Thread.sleep(5);
        // Get price values of all Product elements
        List<WebElement> priceData = findAll(productPriceLocator);
        return priceData;
    }
}

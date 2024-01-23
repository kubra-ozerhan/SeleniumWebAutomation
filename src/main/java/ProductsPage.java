import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductsPage  extends BasePage{

    By productsListTitleLocator = By.id("productListTitle");
    By productDescriptionLocator = By.xpath("//span[@class='m-productCard__desc']");
    By productPriceLocator = By.xpath("//span[@class='m-productCard__newPrice']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isOnProductsPage() throws InterruptedException {
        Thread.sleep(5000);
        return isDisplayed(productsListTitleLocator);
    }
    public void clickOnProduct(int i) throws InterruptedException {
        List<WebElement> ProductDescriptions = getAllProductDescriptions();
        WebElement ProductDescription = ProductDescriptions.get(i);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
        Thread.sleep(5);
        ProductDescription.click();
    }

    public void writeDescriptionAndPriceInformationOfAProductIntoAFile(int i) throws InterruptedException, IOException {
        List<WebElement> ProductDescriptions = getAllProductDescriptions();
        WebElement ProductDescription = ProductDescriptions.get(i);

        List<WebElement> ProductPrices = getAllProductPrices();
        WebElement ProductPrice = ProductPrices.get(i);

        String txtFilePath = "src/ProductDescriptionAndPriceInformation.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilePath))) {
            writer.write(ProductPrice.getText() + "\n" + ProductDescription.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<WebElement> getAllProductDescriptions() throws InterruptedException {
        Thread.sleep(5);
        List<WebElement> descriptionData = findAll(productDescriptionLocator);
        return descriptionData;
    }
    private List<WebElement> getAllProductPrices() throws InterruptedException {
        Thread.sleep(5);
        List<WebElement> priceData = findAll(productPriceLocator);
        return priceData;
    }
}

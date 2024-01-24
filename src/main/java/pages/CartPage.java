package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage{
    // Locators
    By cartIconLocator = By.xpath("//a[@href='/cart']");
    By basketHeaderLocator = By.xpath("//h3[@class='m-basket__header--title']");
    By productInfoNameLocator = By.xpath("//span[@class='m-basket__productInfoName']");
    By salePriceLocator = By.xpath("//span[@class='m-productPrice__salePrice']");
    By selectQuantityLocator = By.xpath("//select[@id='quantitySelect0-key-0']");
    By quantityOptionLocator = By.xpath("//option[@value='2']");
    By notifySuccessLocator = By.id("notifysuccess");
    By removeCartItemLocator = By.id("removeCartItemBtn0-key-0");
    By emptyCartMessageLocator = By.xpath("//strong[@class='m-empty__messageTitle']");


    // constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToCartPage() throws InterruptedException {
        Thread.sleep(5000);
        // if something added into cart and web element became visible
        if (isDisplayed(cartIconLocator)){
            Thread.sleep(5);
            // click on cart icon to navigate cart page
            click(cartIconLocator);
            Thread.sleep(5000);
        }
    }
    
    public Boolean isOnCartPage() throws InterruptedException {
        Thread.sleep(2);
        // check if basket header is shown to verify being in the cart page and return a boolean value
        return isDisplayed(basketHeaderLocator);
    }

    public Boolean isDescriptionAndPriceAreCorrectOnCartPage() {
        // Get all the content from txt file and put them into a list array
        List<String> fileContent = readDescriptionAndPriceInformationOfAProductIntoAFile();

        // get the first line which has the product price value
        String priceOnTheFile = fileContent.get(0);
        // get the second line which has the product description
        String productInfoOnTheFile = fileContent.get(1);

        // get the product price value from Cart page
        String priceOnTheCartPage = find(salePriceLocator).getText();
        // get the product description from Cart page
        String productInfoOnTheCartPage = find(productInfoNameLocator).getText();

        // to remove fraction => ",00" from price on the cart page before comparing the prices
        String newPriceOnTheCartPage = removeFractionFromPriceValueOnTheCartPage(priceOnTheCartPage);

        // Print the price values on the cart page and product page
        System.out.println("priceOnTheFile is " + priceOnTheFile + "priceOnTheCartPage is " + priceOnTheCartPage + "***************");

        // return a boolean value according to given comparisons
        return priceOnTheFile.equals(newPriceOnTheCartPage) && productInfoOnTheFile.equals(productInfoOnTheCartPage);
    }

    private static String removeFractionFromPriceValueOnTheCartPage(String priceOnTheCartPage) {
        // as an example product price in the list and detail pages are "1.190 TL"
        // but in cart page price seems like "1.190,00 TL"
        // so the following 3 lines were added to remove ",00" from price on the cart page before comparing the prices
        int startIndex = priceOnTheCartPage.indexOf(",");
        int endIndex = priceOnTheCartPage.indexOf(" ", startIndex);
        String newPriceOnTheCartPage = priceOnTheCartPage.substring(0, startIndex) + " " + priceOnTheCartPage.substring(endIndex + 1);

        // return the new cleaned price from the method
        return newPriceOnTheCartPage;
    }

    public List<String> readDescriptionAndPriceInformationOfAProductIntoAFile() {
        // the txt file path
        String txtFilePath = "src/ProductDescriptionAndPriceInformation.txt";
        // create a list array to store the content of the txt file
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // get the line content of the given txt file and add it into fileContent list array
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return the file content list array
        return fileContent;
    }

    public void increaseTheCountOfProductTo2() throws InterruptedException {
        // click on increasing the product quantity button
        if (isDisplayed(selectQuantityLocator)){
            Thread.sleep(5);
            click(selectQuantityLocator);
            Thread.sleep(5000);
        }
        // select quantity 2 from the opened option list
        if (isDisplayed(quantityOptionLocator)){
            Thread.sleep(5);
            click(quantityOptionLocator);
            Thread.sleep(5000);
        }
    }

    public boolean isNewQuantityEqualsTo(String s){
        // get the quantity information from web element
        String productQuantity = find(quantityOptionLocator).getText();
        // return a boolean value if conditions are met or not
        return productQuantity.equals(s) && (isDisplayed(notifySuccessLocator));
    }

    public void removeCartItem() throws InterruptedException {
        Thread.sleep(5000);
        // find the web element to clean the cart
        if (isDisplayed(removeCartItemLocator)){
            Thread.sleep(5);
            // click on the element to clean the cart
            click(removeCartItemLocator);
            Thread.sleep(5000);
        }
    }

    public Boolean isCartEmpty() throws InterruptedException {
        Thread.sleep(100);
        // check if cart has no product to verify the cleaned basket and return a boolean value
        return isDisplayed(emptyCartMessageLocator);
    }

}

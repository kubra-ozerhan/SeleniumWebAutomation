import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

import java.io.IOException;

public class TestAddProductToCart extends BaseTest{

    HomePage homePage = new HomePage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    CartPage cartPage = new CartPage(driver);

    @Test
    @Order(1)
    public void navigate_to_Beymen_home_page_and_verify_that_campaigns_are_shown() throws InterruptedException {
        // After closing the appeared pop-up and rejecting the cookies, verify that navigated to home page
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        Assertions.assertTrue(homePage.isOnHomePage(), "Not on home page");
    }

    @Test
    @Order(2)
    public void fill_in_the_search_bar_with_the_products_from_excel_file () throws InterruptedException, IOException {
        // Fill the search bar with the first product from excel file then remove it without searching
        closeInitialPopUps();
        homePage.searchBox().fillInTheSearchBarWithTheFirstProductFromExcelFile();
        homePage.searchBox().deleteTheProductFromTheSearchBar();

        // Fill the search bar with the second product from excel file and search for it. Then verify that products are listed.
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        Assertions.assertTrue(productsPage.isOnProductsPage(), "Not on products page");
    }

    @Test
    @Order(3)
    public void click_on_a_product_and_verify_that_user_navigated_to_product_detail_page () throws InterruptedException, IOException {
        closeInitialPopUps();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.clickOnProduct(0);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(), "Not on product detail page");
    }

    @Test
    @Order(4)
    public void add_a_product_into_cart_and_check_its_description_and_price_on_the_cart () throws InterruptedException, IOException {
        closeInitialPopUps();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.writeDescriptionAndPriceInformationOfAProductIntoAFile(0);
        productsPage.clickOnProduct(0);
        productDetailPage.addSelectedProductIntoCart();
        cartPage.goToCartPage();
        Assertions.assertTrue(cartPage.isOnCartPage(), "Not on cart page");
        Assertions.assertTrue(cartPage.isDescriptionAndPriceAreCorrectOnCartPage(), "Product price and description on the Cart page are not same with product page");

    }

    @Test
    @Order(5)
    public void increase_the_number_of_product_and_verify_that_product_count_is_2 () throws InterruptedException, IOException {
        closeInitialPopUps();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.writeDescriptionAndPriceInformationOfAProductIntoAFile(0);
        productsPage.clickOnProduct(0);
        productDetailPage.addSelectedProductIntoCart();
        cartPage.goToCartPage();
        cartPage.increaseTheCountOfProductTo2();
        Assertions.assertTrue(cartPage.isNewQuantityEqualsTo("2 adet"), "New quantity is not 2 adet");

    }

    @Test
    @Order(6)
    public void remove_the_product_from_cart_and_verify_that_cart_is_empty () throws InterruptedException, IOException {
        closeInitialPopUps();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.writeDescriptionAndPriceInformationOfAProductIntoAFile(0);
        productsPage.clickOnProduct(0);
        productDetailPage.addSelectedProductIntoCart();
        cartPage.goToCartPage();
        cartPage.removeCartItem();
        Assertions.assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }

    @BeforeEach
    private void closeInitialPopUps() throws InterruptedException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
    }


}

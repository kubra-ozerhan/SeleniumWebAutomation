import org.junit.jupiter.api.*;
import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

import java.io.IOException;


public class TestAddProductToCart extends BaseTest{

    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    @BeforeAll
    public void testSetUp() {
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
    }

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
    public void fill_in_the_search_bar_with_the_first_product_from_excel_file_then_clean_without_searching () throws InterruptedException, IOException {
        // Fill the search bar with the first product from excel file then remove it without searching
        homePage.searchBox().fillInTheSearchBarWithTheFirstProductFromExcelFile();
        homePage.searchBox().deleteTheProductFromTheSearchBar();
        Assertions.assertTrue(productsPage.isSearchSuggestionsVisible(), "Search suggestions are not visible");
    }

    @Test
    @Order(3)
    public void search_for_the_second_product_from_excel_file_and_verify_products_list () throws InterruptedException, IOException {
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        Assertions.assertTrue(productsPage.isOnProductsPage(), "Not on products page");
    }

    @Test
    @Order(4)
    public void click_on_a_product_and_verify_that_user_navigated_to_product_detail_page () throws InterruptedException, IOException {
        productsPage.clickOnProduct(0);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(), "Not on product detail page");
    }

    @Test
    @Order(5)
    public void add_a_product_into_cart_and_check_its_description_and_price_on_the_cart () throws InterruptedException, IOException {
        productDetailPage.addSelectedProductIntoCart();
        cartPage.goToCartPage();
        Assertions.assertTrue(cartPage.isOnCartPage(), "Not on cart page");
        Assertions.assertTrue(cartPage.isDescriptionAndPriceAreCorrectOnCartPage(), "Product price and description on the Cart page are not same with product page");
    }

    @Test
    @Order(6)
    public void increase_the_number_of_product_and_verify_that_product_count_is_2 () throws InterruptedException, IOException {
        cartPage.increaseTheCountOfProductTo2();
        Assertions.assertTrue(cartPage.isNewQuantityEqualsTo("2 adet"), "New quantity is not 2 adet");
    }

    @Test
    @Order(7)
    public void remove_the_product_from_cart_and_verify_that_cart_is_empty () throws InterruptedException, IOException {
        cartPage.removeCartItem();
        Assertions.assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }

    private void closeInitialPopUps() throws InterruptedException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
    }


}

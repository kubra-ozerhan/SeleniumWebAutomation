import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestAddProductToCart extends BaseTest{

    HomePage homePage = new HomePage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    CartPage cartPage = new CartPage(driver);

    @Test
    public void navigate_to_Beymen_home_page_and_verify_that_campaigns_are_shown() throws InterruptedException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        homePage.isOnHomePage();
    }

    @Test
    public void fill_in_the_search_bar_with_first_product_from_excel_file_then_remove_it () throws InterruptedException, IOException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        homePage.searchBox().fillInTheSearchBarWithTheFirstProductFromExcelFile();
        homePage.searchBox().deleteTheProductFromTheSearchBar();
    }

    @Test
    public void fill_in_the_search_bar_with_second_product_from_excel_file_then_search_for_it ()  throws InterruptedException, IOException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        Assertions.assertTrue(productsPage.isOnProductsPage(), "Not on products page");
    }
    @Test
    public void click_on_a_product_and_verify_that_user_navigated_to_product_detail_page () throws InterruptedException, IOException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.clickOnProduct(0);
        Assertions.assertTrue(productDetailPage.isOnProductDetailPage(), "Not on product detail page");
    }

    @Test
    public void add_a_product_into_cart_and_check_its_description_and_price_on_the_cart () throws InterruptedException, IOException {
        homePage.closeSelectingGenderBox();
        homePage.rejectCookies();
        homePage.searchBox().searchTheSecondProductFromExcelFile();
        productsPage.writeDescriptionAndPriceInformationOfAProductIntoAFile(0);
        productsPage.clickOnProduct(0);
        productDetailPage.addSelectedProductIntoCart();
    }


    @Test
    public void increase_the_number_of_product_and_verify_that_product_count_is_2 () {
        System.out.println("geldiiim");
    }


    @Test
    public void remove_the_product_from_cart_and_verify_that_cart_is_empty () {
        System.out.println("geldiiim");
    }


}

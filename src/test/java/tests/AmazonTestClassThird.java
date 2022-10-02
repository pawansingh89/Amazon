package tests;

import init.InitClass;
import org.testng.annotations.Test;

public class AmazonTestClassThird extends InitClass {

    @Test
    public void testThird() throws InterruptedException {
        homePage.checkShippingPop();
        homePage.selectCategory("Electronics");
        homePage.searchForText("iPhone 14");
        searchResultsPage.selectPriceRange();
        searchResultsPage.selectRandomProduct();
        productDisplayPage.changeCountry();
        productDisplayPage.addToCart();
        productDisplayPage.proceedCheckout();
        signInPage.validatePageTitle();
    }
}

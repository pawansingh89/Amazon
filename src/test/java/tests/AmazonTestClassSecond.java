package tests;

import init.InitClass;
import org.testng.annotations.Test;

public class AmazonTestClassSecond extends InitClass {

    @Test
    public void testSecond() {
        homePage.checkShippingPop();
        homePage.selectCategory("Electronics");
        homePage.searchForText("iPhone 14");
        searchResultsPage.selectPriceRange();
        searchResultsPage.sortByOption();
        searchResultsPage.validateSorting();
    }
}
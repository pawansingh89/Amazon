package tests;

import init.InitClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;

import java.util.concurrent.TimeUnit;

public class AmazonTestClassFirst extends InitClass {

    @Test
    public void testOne() {
        homePage.checkShippingPop();
        homePage.selectCategory("Electronics");
        homePage.searchForText("iPhone 14");
        searchResultsPage.selectFeatureBrand();
        searchResultsPage.enterPriceRange("100", "2000");
        searchResultsPage.validatePriceRange();
    }


}

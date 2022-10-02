package init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageObjects.HomePage;
import pageObjects.ProductDisplayPage;
import pageObjects.SearchResultsPage;
import pageObjects.SignInPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InitClass {

    public static WebDriver driver;
    public static HomePage homePage;
    public static SearchResultsPage searchResultsPage;
    public static ProductDisplayPage productDisplayPage;
    public static SignInPage signInPage;

    @BeforeClass
    public void beforeclass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedrivermac");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        productDisplayPage = new ProductDisplayPage(driver);
        signInPage = new SignInPage(driver);
    }

    @BeforeMethod
    public void beforemethod() {
        driver.get("https://www.amazon.com/");
    }

    @AfterClass
    public void afterclass() {
        driver.quit();
    }


}

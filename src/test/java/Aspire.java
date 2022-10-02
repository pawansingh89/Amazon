import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Aspire {

    public WebDriver driver;

    By shippingPopup = By.xpath("//input[@data-action-type='DISMISS']");

    //By searchDropdownCategory=By.cssSelector("#nav-search-label-id");
    By searchDropdownCategory = By.xpath("//select[@id='searchDropdownBox']");
    By searchTextBox = By.cssSelector("#twotabsearchtextbox");

    By searchButton = By.cssSelector("#nav-search-submit-button");

    By featuredBrandBelkin = By.xpath("//span[text()='Featured Brands']/following::ul[1]/li[@aria-label='Belkin']//input[@type='checkbox']/following::i[1]");

    By minPrice = By.xpath("//li[contains(@id,'price-range')]//input[@name='low-price']");

    By maxPrice = By.xpath("//li[contains(@id,'price-range')]//input[@name='high-price']");

    By priceRangeGoButton = By.xpath("//li[contains(@id,'price-range')]//following::span[@class='a-button-inner']/input");

    By searchResultsList = By.xpath("//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]");

    By itemPriceOnSearchResultsPage = By.xpath("//span[@data-a-color='base']/span[@class='a-offscreen' and not(contains(aria-hidden,'true'))]");

    By selectPriceRange = By.xpath("//span[text()='Price']//following::li//span[text()='$100 to $200']");

    By sortByDropDown = By.xpath("//span[text()='Sort by:']");
    By selectSortValue = By.xpath("//a[text()='Price: High to Low']");
    By addToCart=By.xpath("//input[@id='add-to-cart-button']");
    By checkoutButton=By.xpath("//input[@value='Proceed to checkout']");
    By undeliverable=By.xpath("//span[@id='exportsUndeliverable-cart-announce']");
    By shippingError=By.xpath("(//span[@class='a-color-error'])[1]");
    By deliverIndia=By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']");
    By changeCountryDropDown=By.xpath("//span[@role='radiogroup']/span");
    By selectCountry=By.xpath("(//a[text()='Australia'])[1]");
    By countryChangedDoneButton=By.xpath("//button[text()='Done']");


    //(//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//span[@class='a-offscreen'])[1]

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedrivermac");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void firtTestCase() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        try {
            if (driver.findElement(shippingPopup).isDisplayed()) {
                System.out.println("Shipping Popup found");
                driver.findElement(shippingPopup).click();
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getLocalizedMessage());
        }
        selectCategory("Electronics");
        searchForText("iPhone 14");
        selectFeatureBrand("Belkin");

        enterPriceRange("100", "2000");
        Thread.sleep(3000);


        List<WebElement> priceListValues = driver.findElements(itemPriceOnSearchResultsPage);
        List<String> priceList = new ArrayList<String>();
        Iterator<WebElement> iterator = priceListValues.iterator();
        while (iterator.hasNext()) {
            priceList.add(iterator.next().getAttribute("innerText"));
        }
        for (String price : priceList) {
            System.out.println(price.substring(1));
            if (Float.parseFloat(price.substring(1)) >= 100 && Float.parseFloat(price.substring(1)) <= 2000) {
                System.out.println("Price is in given range");
            } else
                System.out.println("Price is not in given range");

        }
    }

    @Test
    public void secondTestCase() {
        driver.get("https://www.amazon.com/");
        try {
            if (driver.findElement(shippingPopup).isDisplayed()) {
                System.out.println("Shipping Popup found");
                driver.findElement(shippingPopup).click();
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getLocalizedMessage());
        }
        selectCategory("Electronics");
        searchForText("iPhone 14");
        driver.findElement(selectPriceRange).click();
        driver.findElement(sortByDropDown).click();
        driver.findElement(selectSortValue).click();

        List<WebElement> itemsPrice = driver.findElements(itemPriceOnSearchResultsPage);
        List<String> priceList = new ArrayList<>();
        Iterator<WebElement> iterator = itemsPrice.iterator();
        while (iterator.hasNext()) {
            priceList.add(iterator.next().getAttribute("innerText"));
        }

        Assert.assertTrue(priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(priceList),
                " Items are not sorted as per selection ");

    }

    @Test
    public void thirdTestCase() throws InterruptedException {

        driver.get("https://www.amazon.com/");
        try {
            if (driver.findElement(shippingPopup).isDisplayed()) {
                System.out.println("Shipping Popup found");
                driver.findElement(shippingPopup).click();
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getLocalizedMessage());
        }
        selectCategory("Electronics");
        searchForText("iPhone 14");
        driver.findElement(selectPriceRange).click();
        selectRandomProduct(driver.findElements(searchResultsList));
        //some items are not shipped to India

        changeCountry();
        driver.findElement(addToCart).click();

        driver.findElement(checkoutButton).click();
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Sign-In"));


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    public void selectCategory(String categoryName) {

        try {
            driver.findElement(searchDropdownCategory).click();
        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
        }
        Select select = new Select(driver.findElement(searchDropdownCategory));
        select.selectByVisibleText(categoryName);
    }

    public void searchForText(String text) {
        driver.findElement(searchTextBox).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public void selectFeatureBrand(String featureBrand) {
        driver.findElement(featuredBrandBelkin).click();
    }

    public void enterPriceRange(String min, String max) {
        driver.findElement(minPrice).sendKeys(min);
        driver.findElement(maxPrice).sendKeys(max);
        driver.findElement(priceRangeGoButton).click();

    }
    public void selectRandomProduct(List<WebElement> list){
        Random random=new Random();
        list.get(random.nextInt(list.size())).click();

    }
    public void changeCountry() throws InterruptedException {
        driver.findElement(deliverIndia).click();
        Thread.sleep(2000);
        driver.findElement(changeCountryDropDown).click();
        driver.findElement(selectCountry).click();
        Thread.sleep(2000);
        driver.findElement(countryChangedDoneButton).click();
    }


}

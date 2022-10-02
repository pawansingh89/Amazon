package pageObjects;

import init.InitClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends InitClass {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@data-action-type='DISMISS']")
    private WebElement shippingPopup;

    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    private WebElement searchDropdownCategory;

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchTextBox;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    public void checkShippingPop() {
        try {
            if (shippingPopup.isDisplayed()) {
                System.out.println("Shipping Popup found");
                shippingPopup.click();
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getLocalizedMessage());
        }
    }

    public void selectCategory(String categoryName) {

        try {
            searchDropdownCategory.click();
        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
        }
        Select select = new Select(searchDropdownCategory);
        select.selectByVisibleText(categoryName);
    }

    public void searchForText(String text) {
        searchTextBox.sendKeys(text);
        searchButton.click();
    }

    public void selectFeatureBrand() {
        featuredBrandBelkin.click();
    }

    public void enterPriceRange(String min, String max) {
        minPrice.sendKeys(min);
        minPrice.sendKeys(max);
        priceRangeGoButton.click();

    }


    @FindBy(xpath = "//span[text()='Featured Brands']/following::ul[1]/li[@aria-label='Belkin']//input[@type='checkbox']")
    private WebElement featuredBrandBelkin;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//input[@name='low-price']")
    private WebElement minPrice;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//input[@name='high-price']")
    private WebElement maxPrice;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//following::span[@class='a-button-inner']/input")
    private WebElement priceRangeGoButton;

    @FindBy(xpath = "//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]")
    private List<WebElement> searchResultsList;


}

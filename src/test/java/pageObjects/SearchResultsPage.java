package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class SearchResultsPage {

    public SearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//span[text()='Featured Brands']/following::ul[1]/li[@aria-label='Belkin']//input[@type='checkbox']/following::i[1]")
    private WebElement featuredBrandBelkin;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//input[@name='low-price']")
    private WebElement minPrice;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//input[@name='high-price']")
    private WebElement maxPrice;

    @FindBy(xpath = "//li[contains(@id,'price-range')]//following::span[@class='a-button-inner']/input")
    private WebElement priceRangeGoButton;

    @FindBy(xpath = "//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//span[@data-a-color='base']/span[@class='a-offscreen' and not(contains(aria-hidden,'true'))]")
    private List<WebElement> itemPriceOnSearchResultsPage;

    @FindBy(xpath = "//span[text()='Price']//following::li//span[text()='$100 to $200']")
    private WebElement selectPriceRange;

    @FindBy(xpath = "//span[text()='Sort by:']")
    private WebElement sortByDropDown;

    @FindBy(xpath = "//a[text()='Price: High to Low']")
    private WebElement selectSortValue;


    public void selectFeatureBrand() {
        featuredBrandBelkin.click();
    }

    public void enterPriceRange(String min, String max) {
        minPrice.sendKeys(min);
        maxPrice.sendKeys(max);
        priceRangeGoButton.click();

    }

    public void validatePriceRange() {
        List<String> priceList = new ArrayList<String>();
        Iterator<WebElement> iterator = itemPriceOnSearchResultsPage.iterator();
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

    public void selectPriceRange() {
        selectPriceRange.click();
    }

    public void sortByOption() {
        sortByDropDown.click();
        selectSortValue.click();
    }

    public void validateSorting() {
        List<String> priceList = new ArrayList<>();
        Iterator<WebElement> iterator = itemPriceOnSearchResultsPage.iterator();
        while (iterator.hasNext()) {
            priceList.add(iterator.next().getAttribute("innerText"));
        }

        Assert.assertTrue(priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(priceList),
                " Items are not sorted as per selection ");
    }

    public void selectRandomProduct() {
        Random random = new Random();
        searchResultsList.get(random.nextInt(searchResultsList.size())).click();

    }

}

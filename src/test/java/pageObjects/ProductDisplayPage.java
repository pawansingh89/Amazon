package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductDisplayPage {

    public ProductDisplayPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private WebElement addToCart;

    @FindBy(xpath = "//input[@value='Proceed to checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@id='contextualIngressPtLabel_deliveryShortLine']")
    private WebElement deliverIndia;

    @FindBy(xpath = "//span[@role='radiogroup']/span")
    private WebElement changeCountryDropDown;

    @FindBy(xpath = "(//a[text()='Australia'])[1]")
    private WebElement selectCountry;

    @FindBy(xpath = "//button[text()='Done']")
    private WebElement countryChangedDoneButton;

    public void changeCountry() throws InterruptedException {
        deliverIndia.click();
        Thread.sleep(2000);
        changeCountryDropDown.click();
        selectCountry.click();
        Thread.sleep(2000);
        countryChangedDoneButton.click();
    }

    public void addToCart() {
        addToCart.click();
    }

    public void proceedCheckout(){
        checkoutButton.click();
    }

}

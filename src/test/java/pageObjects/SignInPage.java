package pageObjects;

import init.InitClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignInPage extends InitClass {

    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void validatePageTitle() {
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Sign-In"));

    }

}

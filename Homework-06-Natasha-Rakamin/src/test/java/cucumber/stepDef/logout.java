package cucumber.stepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class logout {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";

    @Given("Page of products in Swag Labs")
    public void page_of_products_in_swag_labs(){
        WebDriverManager.chromedriver().setup(); //setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); //create object to setup option for chrome driver
        opt.setHeadless(false); //set chrome driver to not using headless mode

        driver = new ChromeDriver(opt); //Apply chrome driver setup to driver
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //timeout webdriver to wait element
        driver.manage().window().maximize(); //maximize window
        driver.get(baseurl); //access base url

        // Input username, password, and click login button
        driver.findElement(By.id("user-name")).sendKeys( "standard_user");
        driver.findElement(By.id("password")).sendKeys( "secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Assertion page products
        String pageproducts = driver.findElement(By.xpath("//div[@id='header_container']//span[@class='title']")).getText();
        Assert.assertEquals(pageproducts, "Products");
    }

    @Then("User taps the three-bar button at the upper left corner")
    public void user_taps_the_three_bar_button_at_the_upper_left_corner() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @And("User taps the logout button")
    public void user_taps_the_logout_button() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("user verifies successful log out from the website")
    public void user_verifies_successful_log_out_from_the_website() {
        String swaglabs = driver.findElement(By.xpath("//div[@id='root']//div[@class='login_logo']")).getText();
        Assert.assertEquals(swaglabs, "Swag Labs");
        driver.close();
    }
}

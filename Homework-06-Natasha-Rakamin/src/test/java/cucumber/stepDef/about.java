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

public class about {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";

    @Given("Swag labs product page")
    public void swag_labs_product_page(){
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

    @Then("User taps the three-bar button")
    public void user_taps_the_three_bar_button() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @And("User taps the about button")
    public void user_taps_the_about_button() {
        driver.findElement(By.id("about_sidebar_link")).click();
    }

    @Then("User verifies open about website")
    public void user_verifies_open_about_website() {
        String about = driver.findElement(By.xpath("/html//div[@id='__next']/div[@class='MuiBox-root css-14ifkx6']/div[1]//p")).getText();
        Assert.assertEquals(about, "The world relies on your code. Test on thousands of different device, browser, and OS configurations–anywhere, any time.");
        driver.close();
    }
}

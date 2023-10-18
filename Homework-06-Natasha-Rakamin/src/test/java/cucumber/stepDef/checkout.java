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

public class checkout {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";

    @Given("The page of shipping information in Swag Labs")
    public void the_page_of_shipping_information_in_swag_labs(){
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

        //Add product to cart and checkout product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
        driver.findElement(By.id("checkout")).click();

        //Assertion shipping page
        String shippinginformation = driver.findElement(By.xpath("//div[@id='header_container']//span[@class='title']")).getText();
        Assert.assertEquals(shippinginformation, "Checkout: Your Information");
    }

    @Then("User inputs her first name")
    public void user_inputs_her_first_name() {
        driver.findElement(By.id("first-name")).sendKeys( "Lala");
    }

    @And("User inputs her last name")
    public void user_inputs_her_last_name() {
        driver.findElement(By.id("last-name")).sendKeys( "Maarcella");
    }

    @Then("User inputs postal code area")
    public void user_inputs_postal_code_area() {
        driver.findElement(By.id("postal-code")).sendKeys( "15133");
    }

    @And("User taps the continue & Finish button")
    public void user_taps_the_continue_finish_button() {
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
    }

    @And("User taps the continue button")
    public void user_taps_the_continue_button() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("User verifies successful fill information for shipping")
    public void user_verifies_successful_fill_information_for_shipping() {
        //Assert checkout:complete
        String CoComplete = driver.findElement(By.xpath("//div[@id='checkout_complete_container']/h2[@class='complete-header']")).getText();
        Assert.assertEquals(CoComplete, "Thank you for your order!");
        driver.close();
    }

    @Then("User verifies failed filling information with error message")
    public void user_verifies_failed_filling_information_with_error_message() {
        //Assert failed checkout, with find error a message
        String CoComplete = driver.findElement(By.xpath("/html//div[@id='checkout_info_container']//form//h3")).getText();
        Assert.assertEquals(CoComplete, "Error: Postal Code is required");
        driver.close();
    }
}

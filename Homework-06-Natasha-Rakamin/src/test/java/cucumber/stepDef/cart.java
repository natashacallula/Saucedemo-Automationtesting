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

public class cart {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";
    @Given("The page of products in Swag Labs")
    public void the_page_of_products_in_swag_labs(){
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

    @Then("User adds an item to the cart")
    public void user_adds_an_item_to_the_cart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("User taps the cart button")
    public void user_taps_the_cart_button() {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
    }

    @And("User taps the checkout button")
    public void user_taps_the_checkout_button() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("User verifies the success status")
    public void user_verifies_the_success_status(){
        String shippinginformation = driver.findElement(By.xpath("//div[@id='header_container']//span[@class='title']")).getText();
        Assert.assertEquals(shippinginformation, "Checkout: Your Information");
        driver.close();
    }

    @Then("User taps the empty cart button")
    public void user_taps_the_empty_cart_button() {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
    }

    @Then("User verifies the failed checkout")
    public void user_verifies_the_failed_checkout() {
        String shippinginformation = driver.findElement(By.xpath("//div[@id='header_container']//span[@class='title']")).getText();
        Assert.assertEquals(shippinginformation, "Checkout: Your Information");
        driver.close();
    }
}

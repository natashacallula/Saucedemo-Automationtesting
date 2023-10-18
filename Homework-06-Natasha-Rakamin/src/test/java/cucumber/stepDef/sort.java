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
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class sort {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";
    @Given("The page of products")
    public void the_page_of_products(){
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

    @Then("User taps sort price high to low")
    public void user_taps_sort_price_high_to_low() {
        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByValue("hilo");
    }

    @And("User verifies the successful sorting of products by price")
    public void user_verifies_the_successful_sorting_of_products_by_price() {
        String sortHtoL = driver.findElement(By.xpath("//html//div[@id='inventory_container']/div/div[@id='inventory_container']/div[@class='inventory_list']/div[1]/div[@class='inventory_item_description']/div[@class='pricebar']/div[@class='inventory_item_price']")).getText();
        Assert.assertEquals(sortHtoL, "$49.99");
        driver.close();
    }

}

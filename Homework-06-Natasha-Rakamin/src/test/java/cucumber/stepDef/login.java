package cucumber.stepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;


public class login {
    WebDriver driver;
    String baseurl = "https://www.saucedemo.com/";

    @Given("User launches the web")
    public void user_launches_the_web(){
        WebDriverManager.chromedriver().setup(); //setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); //create object to setup option for chrome driver
        opt.setHeadless(false); //set chrome driver to not using headless mode

        driver = new ChromeDriver(opt); //Apply chrome driver setup to driver
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //timeout webdriver to wait element
        driver.manage().window().maximize(); //maximize window
        driver.get(baseurl); //access base url

        //Assertion
        String swaglabs = driver.findElement(By.xpath("//div[@id='root']//div[@class='login_logo']")).getText();
        Assert.assertEquals(swaglabs, "Swag Labs");

    }

    @Then("User inputs a registered username")
    public void user_inputs_a_registered_username() {
        driver.findElement(By.id("user-name")).sendKeys( "standard_user");
    }

    @And("User inputs a registered password")
    public void user_Inputs_a_registered_password() {
        driver.findElement(By.id("password")).sendKeys( "secret_sauce");
    }

    @And("User taps login button")
    public void user_taps_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User verifies success login")
    public void user_verifies_success_login() {
        String pageproducts = driver.findElement(By.xpath("//div[@id='header_container']//span[@class='title']")).getText();
        Assert.assertEquals(pageproducts, "Products");
        driver.close();
    }

    @Then("User inputs an unregistered username")
    public void user_inputs_an_unregistered_username() {
        driver.findElement(By.id("user-name")).sendKeys( "rakamin_user");
    }

    @Then("User verifies failed logout with error message")
    public void user_verifies_failed_logout_with_error_message() {
        String pageproducts = driver.findElement(By.xpath("//div[@id='login_button_container']//form//h3")).getText();
        Assert.assertEquals(pageproducts, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}

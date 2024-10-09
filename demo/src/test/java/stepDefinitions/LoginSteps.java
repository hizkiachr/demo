package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.Helpers;

public class LoginSteps {
    //21. panggil webdriver
    private WebDriver driver;

    //22. ambil dari pagenya
    private LoginPage loginPage;

    //23. lalu deklarasi step gherkinsnya
    @Given("User open Browser and Navigate to URL")
    //buat method
    public void openBrowser(){
        //karena sudah download, lalu saya set path download drivernya
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fathi\\Documents\\Automation\\Test\\demo\\src\\test\\resource\\drivers\\chromedriver.exe");
        //saya akan menambahakan beberapa options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");

        //langsung saya panggil
        driver = new ChromeDriver(options);
        //lalu menuju urlnya, panggil dari Helpers/Keyword. siapa yang dipanggil? baseUrlnya
        driver.get(Helpers.getProperty("baseUrl"));
        //dan terakhir
        loginPage = new LoginPage(driver);
    }

    //24. setelah givennya, saya membuat whennya. untuk annotationnya dapat diubah dengan {string} karena menandakan dia berupa sebuah inputan atau sebuah data
    @When("User Input username with {string} and password with {string}")
    public void inputCredential(String username, String password){
        //lalu saya panggil yang sudah saya buat objectmodelnya
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
    }

    //25. lalu endnya
    @And("User Click button Login")
    public void clickButtonLogin(){
        loginPage.clickLogin();
    }

    //26. lalu buat expected Positivenya
    @Then("User Successfully Login and direct to HomePage")
    public void directHomePage(){
        loginPage.homePage();
    }

    //27. saya tambahkan untuk validasi failed Login
    @Then("User failed Login and Validate error message")
    public void failedLogin(){
        loginPage.errorMessage();
    }
}

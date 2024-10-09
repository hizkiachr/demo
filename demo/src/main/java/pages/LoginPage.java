package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Helpers;

public class LoginPage {

    //1. kita panggil drivernya
    private WebDriver driver;

    //terus kita isi field field yang kita perlukan dari web yang di testing, disini saya akan memakai syntax By untuk penentuan locator field/element yang digunakan
    
    //2. get By.id
    private By objectUsername = By.id("username");

    //3. get By.id juga
    private By objectPassword = By.id("password");

    //4. get By.xpath
    private By objectButtonLogin = By.xpath("//button[@type='submit']");

    //5. get By.xpath
    private By objectTextValidation = By.xpath("//div//div//div[@id='flash']");
 
    //16. setelah mendeklarasi semua element
    public LoginPage(WebDriver driver){
        this.driver = driver;
        //lalu panggil file Helpers/Keyword yang sudah dibuat
        Helpers.setDriver(driver);
    }

    //17. saya butuh action input, jadi saya akan membuat input username
    public void inputUsername(String username){
        Helpers.takeScreenshot();
        WebElement usernameObject = Helpers.waitForElementVisible(objectUsername, 60);
        //jadi kalau sudah objectnya tampil, baru dapat di input
        usernameObject.sendKeys(username);
    }

     //18. saya juga akan membuat input password
    public void inputPassword(String password){
        driver.findElement(objectPassword).sendKeys(password);
        Helpers.takeScreenshot();
    }

    //19. untuk click button loginnya yaitu
    public void clickLogin(){
        driver.findElement(objectButtonLogin).click();
    }

    //20. langkah terakhir yaitu mem-validasi
    public void homePage(){
        //saya buat sampai element yang ingin saya validasi tampil
        Helpers.waitForElementVisible(objectTextValidation, 60);
        Helpers.takeScreenshot();
        Helpers.isElementPresent(objectTextValidation);
        //saya ambil textnya
        System.out.println(driver.findElement(objectTextValidation).getText());
        driver.quit();
    }

    //28. buat validasi untuk message error
    public void errorMessage(){
        Helpers.waitForElementVisible(objectTextValidation, 60);
        Helpers.takeScreenshot();
        Helpers.isElementPresent(objectTextValidation);
        driver.quit();
    }
}

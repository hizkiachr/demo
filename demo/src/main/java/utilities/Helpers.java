package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {
    //6. disini kita buat webdriver
    private static WebDriver driver;
    
    //9. lalu kita buat
    private static Properties properties;

    //13. lalu kita buat nama foldernya
    private static String folderName;
    
    //7. kita akan menentukan driver dulu jadi hanya 1 kali panggil saja
    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

    //8. baru kita buat methodnya
    public static WebElement waitForElementVisible(By object, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        //karena ini static saya akan return
        return wait.until(ExpectedConditions.visibilityOfElementLocated(object));
    }

    public static boolean isElementPresent(By object){
        try {
            driver.findElement(object);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //10. bisa memakai try catch (inget tulis lengkap catchnya dulu gapake throw)
    static{
        //11. apa yang mau kita baca adalah file config.properties (ambil pathnya)
        try(FileInputStream fileinputStream = new FileInputStream("C:\\Users\\fathi\\Documents\\Automation\\Test\\demo\\src\\test\\resource\\config\\config.properties")){
            //lanjut actionnya
            properties = new Properties();
            //kemudian saya load dari file yang kita mau
            properties.load(fileinputStream);

        } catch(IOException e){
            e.printStackTrace();
            //12. kalau error saya throw. untuk mempermudah kalau error
            throw new RuntimeException("Failed to Read Config Properties");
        }
    }

    //13. sekarang buat methodnya. untuk nanti value apa yang akan diambil
    public static String getProperty(String key){
        //lalu saya return dan parameternya adalah key
        return properties.getProperty(key);
    }

    //14. setelah itu saya buat staticnya
    static {
        //pertama menentukan project directory yang saya inginkan, jadi akan dinamis
        String projectDir = System.getProperty("user.dir");
        //format dari foldernya saya menggunakan timeStamp
        SimpleDateFormat foldeFormatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-SSS");
        //foldername yang sudah dibuat saya panggil disini
        folderName = foldeFormatter.format(new Date());
        //setelah itu saya akan menentukan directory pathnya, tempat folder akan dibuat
        String directoryPath = projectDir + File.separator + "screenshoots" + File.separator + folderName;
        //kemungkinan akan saya buat foldernya jadi biar otomatis, file baru di directoryPath
        File directory = new File(directoryPath);
        //buat condition kalau directory exist
        if (!directory.exists()){
            //maka directory buat folder
            directory.mkdirs();
        }
    }

    //15. nah kalau syntax sudah selesai, saya akan membuat method screenshoot
    public static void takeScreenshot(){
        //saya akan memakai format timeStamp juga pada nama screenshoot
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy--HH-mm-ss-SSS");
        //lalu saya buat namanya
        String fileFormatter = formatter.format(new Date());
        //kemudian saya akan membuat project directory. yang mana hasil screenshoot akan disimpan
        String projectDir = System.getProperty("user.dir");
        //karena sifatnya dinamis saya akan memanggil directoryPathnya
        String directoryPath = projectDir + File.separator + "screenshoots" + File.separator + folderName;

        //saya membuat perintah screenshootnya. outputnya adalah file
        File screenshootFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //lalu namanya adalah. dan saya kasih format nya .png
        String fileName = directoryPath + File.separator + "screenshoots_" + fileFormatter + ".png";
        
        //setelah itu try catch (inget tulis lengkap catchnya dulu)
        try{
            Files.copy(screenshootFile.toPath(), Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
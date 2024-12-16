package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.AllureHelper;

import java.time.Duration;

public abstract class BaseTest {

    // Declare WebDriver as a static class-level field
    protected static WebDriver driver;
    protected static WebDriverWait wait;



    @BeforeSuite
    public void setupAllureResultsDirectory() {
        System.setProperty("allure.results.directory", "target/allure-results");
    }
    @BeforeSuite
    public void setUp() {

        // Initialize WebDriver with ChromeOptions
        ChromeOptions options = new ChromeOptions();
// Ρυθμίσεις για Headless Mode και αποφυγή σφαλμάτων
        options.addArguments("--headless");  // Εκτέλεση χωρίς GUI
        options.addArguments("--no-sandbox"); // Αποφυγή security error
        options.addArguments("--disable-dev-shm-usage"); // Αποφυγή shared memory issues
        options.addArguments("--remote-allow-origins=*"); // Για απομακρυσμένα origins
        options.addArguments("--disable-gpu"); // Απενεργοποίηση GPU acceleration        driver = new ChromeDriver(options); // Instantiate the driver only once
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Maximize browser window and set a base URL if needed
        //driver.manage().window().maximize();
    }
    public void clickButtonWithWait(String cssSelector, WebElement buttonName) {
        try {
            // Περιμένουμε το κουμπί να είναι ορατό και clickable

            buttonName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            buttonName.click();
            // Καταγραφή στο Allure
            AllureHelper.clickButton(String.valueOf(buttonName.getText()));
            System.out.println("Clicked on button: " + buttonName.getText());
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on button: " , e);
        }
    }

    public void sendKeysInput(String cssSelector , WebElement inputName , String value){
        try {
            // Περιμένουμε το κουμπί να είναι ορατό και clickable

            inputName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            inputName.sendKeys(value);
            // Καταγραφή στο Allure
            Allure.step("Setting value '" + value + "' to input field " );
            System.out.println("set the value: " + value + "to input");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys: " , e);
        }
    }




//@AfterTest
//public void tearDown() {
//    // Quit the driver to close browser and free resources
//    if (driver != null) {
//        driver.quit();
//    }
//}
}
package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public abstract class BaseTest {

    // Declare WebDriver as a static class-level field
    protected static WebDriver driver;
    protected static WebDriverWait wait;



    @BeforeSuite
    public void setupAllureResultsDirectory() {
        System.setProperty("allure.results.directory", "target/allure-results");
    }
    @BeforeTest
    public void setUp() {

        // Initialize WebDriver with ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Add any custom options for Chrome here
        driver = new ChromeDriver(options); // Instantiate the driver only once
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Maximize browser window and set a base URL if needed
        //driver.manage().window().maximize();
    }



//@AfterTest
//public void tearDown() {
//    // Quit the driver to close browser and free resources
//    if (driver != null) {
//        driver.quit();
//    }
//}
}
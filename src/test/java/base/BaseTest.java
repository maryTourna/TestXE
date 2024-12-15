package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public abstract class BaseTest {

    // Declare WebDriver as a class-level field
    protected WebDriver driver;



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

        // Maximize browser window and set a base URL if needed
        //driver.manage().window().maximize();
    }



    @AfterTest
    public void tearDown() {
        // Quit the driver to close browser and free resources
        if (driver != null) {
            driver.quit();
        }
    }
}
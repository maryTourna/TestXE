package base;

import io.qameta.allure.Allure;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.AllureHelper;

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
    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless"); // Για CI περιβάλλον
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.manage().window().maximize();
  }

  // click button By.cssSelector
  public void clickButtonWithWait(String cssSelector, WebElement buttonName) {
    try {
      // Περιμένουμε το κουμπί να είναι ορατό και clickable
      buttonName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
      buttonName.click();
      // Καταγραφή στο Allure
      AllureHelper.clickButton(buttonName.getText());
      System.out.println("Clicked on button: " + buttonName.getText());
    } catch (Exception e) {
      throw new RuntimeException("Failed to click on button: ", e);
    }
  }

  // Set value to an input located By cssSelector
  public void sendKeysInput(String cssSelector, WebElement inputName, String value) {
    try {
      // Περιμένουμε το input να είναι ορατό

      inputName =
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
      inputName.sendKeys(value);
      // Καταγραφή στο Allure
      Allure.step("Setting value '" + value + "' to input field ");
      System.out.println("set the value: " + value + "to input");
    } catch (Exception e) {
      throw new RuntimeException("Failed to send keys: ", e);
    }
  }

  // At the bd of test suite quit driver and browser
  @AfterSuite
  public void tearDown() {
    // Quit the driver to close browser and free resources
    if (driver != null) {
      driver.quit();
    }
  }
}

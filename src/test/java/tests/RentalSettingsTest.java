package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.AllureHelper;


public class RentalSettingsTest extends BaseTest {

    @Test
    public void setRentProperties() {
        
        String pageTitle = driver.getTitle();
        AllureHelper.verifyPageTitle(pageTitle);
        Assert.assertEquals(pageTitle, "Ενοικιάσεις κατοικιών στις επιλεγμένες περιοχές");

        setPrice();
        setSquareFootage();
    
    }

   public void setPrice(){
       WebElement priceButton = null;
       clickButtonWithWait("button[data-testid='price-filter-button']",priceButton);

       WebElement minimumPrice = null;
       sendKeysInput("input[data-testid='minimum_price_input']", minimumPrice, "200");

       WebElement maximumPrice = null;
       sendKeysInput("input[data-testid='maximum_price_input']", maximumPrice, "700");

   }

    public void setSquareFootage(){
        WebElement sizeButton = null;
        clickButtonWithWait("button[data-testid='size-filter-button']",sizeButton);

        WebElement minimumSize = null;
        sendKeysInput("input[data-testid='minimum_size_input']",sizeButton, "75");

        WebElement maximumSize = null;
        sendKeysInput("input[data-testid='maximum_size_input']", maximumSize, "150");

    }

    public void clickButtonWithWait(String cssSelector, WebElement buttonName) {
        try {
            // Περιμένουμε το κουμπί να είναι ορατό και clickable

            buttonName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            buttonName.click();
            // Καταγραφή στο Allure
            AllureHelper.clickButton(String.valueOf(buttonName.toString()));
            System.out.println("Clicked on button: " + buttonName.getAttribute("name"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on button: " + buttonName.getAttribute("name"), e);
        }
    }

    public void sendKeysInput(String cssSelector , WebElement inputName , String value){
        try {
            // Περιμένουμε το κουμπί να είναι ορατό και clickable

            inputName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            inputName.sendKeys(value);
            // Καταγραφή στο Allure

            System.out.println("set the value: " + value + "to input");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys: " , e);
        }
    }
}

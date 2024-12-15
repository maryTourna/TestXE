package tests;

import base.BaseTest;
import io.qameta.allure.Step;
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

        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
    
    }
@Step
   public void setPrice(){
       WebElement priceButton = null;
       clickButtonWithWait("button[data-testid='price-filter-button']",priceButton);

       WebElement minimumPrice = null;
       sendKeysInput("input[data-testid='minimum_price_input']", minimumPrice, "200");

       WebElement maximumPrice = null;
       sendKeysInput("input[data-testid='maximum_price_input']", maximumPrice, "700");

   }
@Step
    public void setSquareFootage(){
        WebElement sizeButton = null;
        clickButtonWithWait("button[data-testid='size-filter-button']",sizeButton);

        WebElement minimumSize = null;
        sendKeysInput("input[data-testid='minimum_size_input']",sizeButton, "75");

        WebElement maximumSize = null;
        sendKeysInput("input[data-testid='maximum_size_input']", maximumSize, "150");

    }


}

package tests;

import base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.AllureHelper;

/// Σε αυτο το Τεστ
/// ● Set a price from €200 to €700 and a square footage from 75m2 to 150m2 and sort the results by descending order.
public class RentalSettingsTest extends BaseTest {

    @Test
    public void setRentProperties() {

        //Περιμενουνε τα results
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h1[data-testid='Ενοικιάσεις κατοικιών στην περιοχή Παγκράτι']")));

            System.out.println("The element is visible!");
        } catch (TimeoutException e) {
            System.out.println("The element did not appear within 10 seconds.");
        }

        String pageTitle = driver.getTitle();
        AllureHelper.verifyPageTitle(pageTitle);
        Assert.assertEquals(pageTitle, "Ενοικιάσεις κατοικιών στις επιλεγμένες περιοχές");

        setPrice();
        setSquareFootage();
        sortTheAds();

        WebElement body = driver.findElement(By.tagName("body"));
        body.click();

    }
    //Πειμενουμε το button "Τιμη" να ειναι clicable και στη συνεχεια δινουμε το ευρος τιμων
<<<<<<< HEAD
@Step
   public void setPrice(){
       WebElement priceButton = null;
       clickButtonWithWait("button[data-testid='price-filter-button']",priceButton);
=======
    @Step
    public void setPrice(){
        WebElement priceButton = null;
        clickButtonWithWait("button[data-testid='price-filter-button']",priceButton);
>>>>>>> 4a3ac44246fbbf0cc9c9d6de057eb4a5517f27f1

        WebElement minimumPrice = null;
        sendKeysInput("input[data-testid='minimum_price_input']", minimumPrice, "200");

        WebElement maximumPrice = null;
        sendKeysInput("input[data-testid='maximum_price_input']", maximumPrice, "700");

<<<<<<< HEAD
   }
=======
    }
>>>>>>> 4a3ac44246fbbf0cc9c9d6de057eb4a5517f27f1
    //Πειμενουμε το button "Τετραγωνικά" να ειναι clicable και στη συνεχεια δινουμε το ευρος των τετραγωνικων

    @Step
    public void setSquareFootage(){
        WebElement sizeButton = null;
        clickButtonWithWait("button[data-testid='size-filter-button']",sizeButton);

        WebElement minimumSize = null;
        sendKeysInput("input[data-testid='minimum_size_input']",sizeButton, "75");

        WebElement maximumSize = null;
        sendKeysInput("input[data-testid='maximum_size_input']", maximumSize, "150");

    }

    //Περιμενουμε τη dropdownlist ατη συνέχεια επιλεγουμε "Τιμη(Φθίνουσα)
    @Step
    public void sortTheAds(){
        // Περιμένουμε το κουμπί που ανοίγει το dropdown να είναι clickable
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-testid='open-property-sorting-dropdown']")
        ));
        dropdownButton.click();

        // Περιμένουμε το κουμπί "Τιμη Φθίνουσα"
        WebElement descButton = null;
        clickButtonWithWait("button[data-id='price_desc']",descButton);


        AllureHelper.clickButton("Τιμη (Φθίνουσα)");


        // Επαλήθευση ότι το κουμπί "Τιμη Φθίνουσα" έχει την κλάση 'selected'
        descButton = driver.findElement(By.cssSelector("button[data-id='price_desc']"));
        String descClass = descButton.getAttribute("class");
        Assert.assertTrue(descClass.contains("selected"), "Το κουμπί 'Ενοικίαση' δεν είναι επιλεγμένο!");

        //System.out.println("Επιβεβαιώθηκε ότι το 'Τιμη Φθίνουσα' είναι επιλεγμένο!");
    }



}

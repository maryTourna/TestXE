package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeTest;
import utilities.AllureHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchProperty extends BaseTest {

    @Test
    @Description("This test attempts to get the link of the website . Fails if any error happens.")
    @Epic("Landing Page")
    public void testLandingPage() {

        // Άνοιγμα σελίδας
        String url = "https://www.xe.gr/property/";
        driver.get(url);
        AllureHelper.openPage(url);

        // Επαλήθευση τίτλου σελίδας
        String pageTitle = driver.getTitle();
        AllureHelper.verifyPageTitle(pageTitle);

        // Εύρεση και κλικ στο κουμπί "ΣΥΜΦΩΝΩ"

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='ΣΥΜΦΩΝΩ']")
        ));

        button.click();
        AllureHelper.clickButton("ΣΥΜΦΩΝΩ");

        SelectRegions();
    }
@Step
    public void SelectRegions() {
        WebElement searchField = driver.findElement(By.xpath("//input[@name='geo_place_id']"));
        searchField.sendKeys("παγκρατι");

        // Περίμενε μέχρι να εμφανιστεί η λίστα προτεινόμενων περιοχών

// Wait for the dropdown panel to become visible
    By dropdownList = By.cssSelector("div[data-testid='geo_place_id_dropdown_panel']");
    wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));

        // Καταγραφή στο Allure με assertion
        io.qameta.allure.Allure.step("Η λίστα προτεινόμενων εμφανίζεται", () -> {
            WebElement dropdownElement = driver.findElement(dropdownList);
            assert dropdownElement.isDisplayed() : "Η λίστα προτεινόμενων δεν εμφανίστηκε!";
        });


    // Get all dropdown option buttons
    List<WebElement> options = driver.findElements(By.cssSelector("button.dropdown-panel-option"));

    System.out.println("Total options found: " + options.size());

    // Loop through each option, print and click it
    for (WebElement option : options) {
        String optionText = option.getText();
        System.out.println("Selecting option: " + optionText);

        // Click the option
        option.click();

        searchField.clear();
//        searchField.sendKeys("παγκρατι");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));
    }
    }







    }






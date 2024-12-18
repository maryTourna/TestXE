package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import utilities.AllureHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;


import java.util.List;
/// Σε αυτο το τεστ -> search for rent ads on Παγκράτι and all related areas and check some properties of the search results. When searching for Παγκράτι (or other areas for that matter) a popup may appear that asks you to select one specific area of those proposed - in our case we want to select all areas proposed by autocomplete before proceeding to a search.
public class searchproperty extends BaseTest {

    @Test
    @Description("This test attempts to find all region compatible with pagrati  . Fails if any error happens.")
    @Epic("Landing Page")
    public void testLandingPage() {

        // Άνοιγμα σελίδας
        String url = "https://www.xe.gr/property/";
        driver.get(url);
        AllureHelper.openPage(url);

        // Επαλήθευση τίτλου σελίδας
        String pageTitle = driver.getTitle();
        AllureHelper.verifyPageTitle(pageTitle);

        // Εύρεση και κλικ στο κουμπί "ΣΥΜΦΩΝΩ" (accept coockies)

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='ΣΥΜΦΩΝΩ']")));
            button.click();
            System.out.println("Button 'ΣΥΜΦΩΝΩ' clicked.");
        } catch (TimeoutException e) {
            System.out.println("Button 'ΣΥΜΦΩΝΩ' not found. Skipping click.");
        }
        AllureHelper.clickButton("ΣΥΜΦΩΝΩ");
        verifyRentIsSelected();
        SelectRegions();
    }
    @Step
    public void SelectRegions() {

        // Εντοπισμός του input field και πληκτρολόγηση "παγκρατι"
        By searchFieldLocator = By.cssSelector("input[name='geo_place_id']");
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));
        searchField.sendKeys("παγκρατι");

        // Περιμένουμε να εμφανιστεί το dropdown
        By dropdownList = By.cssSelector("div[data-testid='geo_place_id_dropdown_panel']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));

        // Συλλογή αρχικών επιλογών
        List<WebElement> options = driver.findElements(By.cssSelector("button.dropdown-panel-option"));
        System.out.println("Total options found: " + options.size());

        int totalOptions = options.size(); // Αποθηκεύουμε το συνολικό αριθμό επιλογών

        for (int i = 0; i < totalOptions; i++) {
            // Επαναφορτώνουμε τη λίστα των κουμπιών
            options = driver.findElements(By.cssSelector("button.dropdown-panel-option"));

            // Έλεγχος αν το στοιχείο υπάρχει ακόμα
            if (i >= options.size()) {
                System.out.println("All regions have been clicked!");
                break; // Διακοπή του βρόχου αν δεν υπάρχουν άλλες επιλογές
            }

            // Παίρνουμε το i-οστό κουμπί και το κείμενό του
            WebElement option = options.get(i);
            String optionText = option.getText();
            Allure.step("Clicking on region: " + optionText);
            System.out.println("Selecting option: " + optionText);

            // Κάνουμε κλικ
            option.click();

            // Αναμονή για το input field να επιστρέψει στο DOM
            searchField = wait.until(ExpectedConditions.presenceOfElementLocated(searchFieldLocator));
            //searchField.clear();
            searchField.sendKeys("παγκρατι");

            // Περιμένουμε να φορτωθεί ξανά το dropdown
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));
        }

        // System.out.println("All regions clicked successfully.");

        // Κάνουμε κλικ στο κουμπί Αναζήτηση
        By searchButtonLocator = By.xpath("//input[@data-testid='submit-input']");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
        Allure.step("Clicking on ΑΝΑΖΗΤΗΣΗ button.");
        searchButton.click();
    }
    //Πιτοποιουμε οτι ειναι επιλεγμενο το "ΕΝΟΙΚΙΑΣΗ"
    public void verifyRentIsSelected() {
        // Περιμένουμε το κουμπί που ανοίγει το dropdown να είναι clickable
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='open-property-transaction-dropdown']")
        ));
        dropdownButton.click();

        // Περιμένουμε το κουμπί "Ενοικίαση" να είναι ορατό και clickable
        WebElement rentButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='rent']")
        ));
        rentButton.click();
        AllureHelper.clickButton("ΕΝΟΙΚΙΑΣΗ");

        // Επαλήθευση ότι το hidden input έχει τη σωστή τιμή
        WebElement hiddenInput = driver.findElement(By.cssSelector("input[name='property_type']"));
        String inputValue = hiddenInput.getAttribute("value");
        Assert.assertEquals(inputValue, "rent", "Το hidden input δεν έχει την τιμή 'rent'!");

        // Επαλήθευση ότι το κουμπί "Ενοικίαση" έχει την κλάση 'selected'
        rentButton = driver.findElement(By.cssSelector("button[data-testid='rent']"));
        String rentClass = rentButton.getAttribute("class");
        if (rentClass != null) {
            Assert.assertTrue(rentClass.contains("selected"), "Το κουμπί 'Ενοικίαση' δεν είναι επιλεγμένο!");
        }

    }



}






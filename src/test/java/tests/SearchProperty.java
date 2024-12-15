package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import utilities.AllureHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='ΣΥΜΦΩΝΩ']")
        ));

        button.click();
        AllureHelper.clickButton("ΣΥΜΦΩΝΩ");
    }


}
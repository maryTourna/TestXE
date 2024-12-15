package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class checkAds extends BaseTest {
    // Εντοπίζουμε όλα τα αποτελέσματα της σελίδας
    @Test
    public void checkSearchResults() {

        // Καθορισμένα όρια
        int minPrice = 200;
        int maxPrice = 700;
        int minSize = 75;
        int maxSize = 150;


        //Περιμενουνε τα results
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-testid='property-ad-title']")));
        // Εντοπίζουμε ΜΟΝΟ τις αγγελίες (εξαιρούμε τα widgets)
        List<WebElement> propertyAds = driver.findElements(By.cssSelector("div[class='common-property-ad-title']"));

// Εκτυπώνουμε το πλήθος των αγγελιών που βρήκαμε
        System.out.println("Total property ads found: " + propertyAds.size());



// Εξάγουμε πληροφορίες από κάθε αγγελία
        for (WebElement ad : propertyAds) {
            try {
                // Παίρνουμε τον τίτλο της αγγελίας
                String title = ad.findElement(By.xpath("//div[@class='common-property-ad-title']/h3[@data-testid='property-ad-title']")).getText();
                int size = extractNumber(title); // Μετατροπή σε αριθμό
                Assert.assertTrue(size >= minSize && size <= maxSize, "Size out of range: " + size);

                // Παίρνουμε την τιμή της αγγελίας
                String price = ad.findElement(By.xpath("//span[@data-testid='property-ad-price']")).getText();
                int priceInt = extractNumber(price); // Μετατροπή σε αριθμό
                Assert.assertTrue(priceInt >= minPrice && priceInt <= maxPrice, "Price out of range: " + priceInt);

                // Εκτύπωση για debugging
                System.out.println("Verified ad: Price = " + price + ", Size = " + size);
            }catch (Exception e) {
                System.err.println("Failed to verify ad: " + e.getMessage());
            }

        }
        System.out.println("All property ads have been verified successfully!");


    }
    private int extractNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}

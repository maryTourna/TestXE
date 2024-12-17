package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class checkAds extends BaseTest {
    // Εντοπίζουμε όλα τα αποτελέσματα της σελίδας
    @Test
    public void checkSearchRangeResults() throws InterruptedException {

        // Καθορισμένα όρια
        int minPrice = 200;
        int maxPrice = 700;
        int minSize = 75;
        int maxSize = 150;


        // Περιμένουμε να φορτωθούν τα αποτελέσματα
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid-x >div.lazyload-wrapper")));

// Εντοπίζουμε ΜΟΝΟ τις αγγελίες (εξαιρούμε τα widgets)
        List<WebElement> propertyAds = driver.findElements(By.cssSelector("div.grid-x >div.lazyload-wrapper.medium-4.scroll"));

// Εκτυπώνουμε το πλήθος των αγγελιών που βρήκαμε
        System.out.println("Total property ads found: " + propertyAds.size());

// Βρόχος για να μετρήσουμε τα images σε κάθε αγγελία
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < propertyAds.size(); i++) {
            WebElement ad = propertyAds.get(i);

            // Scroll στο συγκεκριμένο property ad για να ενεργοποιηθεί το lazy loading
            js.executeScript("arguments[0].scrollIntoView(true);", ad);
            Thread.sleep(1000); // Περίμενε για το lazy loading

            // Εντοπίζουμε ΜΟΝΟ εικόνες μέσα σε img tag με div.slick-slide[style*='outline: none']
            List<WebElement> images = propertyAds.get(i).findElements(By.cssSelector("div.slick-slide[style*='outline: none']"));
            int countImages = images.size();
            Assert.assertTrue(countImages <= 30, "number of images: " + countImages);

            //String propname =ad.getText();
            // Εκτυπώνουμε τον αριθμό των εικόνων
            //System.out.println(propname+"  "+"Property ad " + (i + 1) + " has " + images.size() + " images. //////////");
        }



//// Εξάγουμε πληροφορίες από κάθε αγγελία
//        for (WebElement ad : propertyAds) {
//            try {
//                // Παίρνουμε τον τίτλο της αγγελίας
//                String title = ad.findElement(By.xpath("//div[@class='common-property-ad-title']/h3[@data-testid='property-ad-title']")).getText();
//                int size = extractNumber(title); // Μετατροπή σε αριθμό
//                Assert.assertTrue(size >= minSize && size <= maxSize, "Size out of range: " + size);
//
//                // Παίρνουμε την τιμή της αγγελίας
//                String price = ad.findElement(By.xpath("//span[@data-testid='property-ad-price']")).getText();
//                int priceInt = extractNumber(price); // Μετατροπή σε αριθμό
//                Assert.assertTrue(priceInt >= minPrice && priceInt <= maxPrice, "Price out of range: " + priceInt);
//
//                // Εκτύπωση για debugging
//                System.out.println("Verified ad: Price = " + price + ", Size = " + size);
//            }catch (Exception e) {
//                System.err.println("Failed to verify ad: " + e.getMessage());
//            }

       // }
       // System.out.println("All property ads have been verified successfully!");


    }
    public void imageNumber(){

    }

    private int extractNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
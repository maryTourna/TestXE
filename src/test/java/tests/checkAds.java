package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AllureHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class checkAds extends BaseTest {
    // Εντοπίζουμε όλα τα αποτελέσματα της σελίδας

    @Test
    public void checkSearchRangeResults() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid-x >div.lazyload-wrapper")));

        // Εντοπίζουμε ΜΟΝΟ τις αγγελίες (εξαιρούμε τα widgets)
        List<WebElement> propertyAds = driver.findElements(By.cssSelector("div.grid-x >div.lazyload-wrapper.medium-4.scroll"));

        // Εκτυπώνουμε το πλήθος των αγγελιών που βρήκαμε
        System.out.println("Total property ads found: " + propertyAds.size());


        imageNumber();




    }
    @Step
    public void imageNumber() throws InterruptedException {
        int minPrice = 200;
        int maxPrice = 700;
        int minSize = 75;
        int maxSize = 150;
        // Περιμένουμε να φορτωθούν τα αποτελέσματα
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid-x >div.lazyload-wrapper")));

// Εντοπίζουμε ΜΟΝΟ τις αγγελίες (εξαιρούμε τα widgets)
        List<WebElement> propertyAds = driver.findElements(By.cssSelector("div.grid-x >div.lazyload-wrapper.medium-4.scroll"));

// Εκτυπώνουμε το πλήθος των αγγελιών που βρήκαμε
        //System.out.println("Total property ads found: " + propertyAds.size());
        Allure.step("Total property ads found: " + propertyAds.size());

// Βρόχος για να μετρήσουμε τα images σε κάθε αγγελία
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < propertyAds.size(); i++) {
            WebElement ad = propertyAds.get(i);

            // Scroll στο συγκεκριμένο property ad για να ενεργοποιηθεί το lazy loading
            js.executeScript("arguments[0].scrollIntoView(true);", ad);
            Thread.sleep(1000); // Περίμενε για το lazy loading

            // Εντοπίζουμε ΜΟΝΟ εικόνες μέσα στα propertyAds τις εικόνες με div.slick-slide[style*='outline: none']
            List<WebElement> images = propertyAds.get(i).findElements(By.cssSelector("div.slick-slide[style*='outline: none']"));
            int countImages = images.size();
            Assert.assertTrue(countImages <= 30, "number of images: " + countImages);


            //Παίρνουμε τα τετραγωνικα της αγγελίας και ελέγχουμε αν ειναι μεσα στο range
            String title = propertyAds.get(i).findElement(By.cssSelector("div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > div:nth-child(1) > h3:nth-child(1)")).getText();
            int size = extractNumber(title);
            Assert.assertTrue(size >= minSize && size <= maxSize, "Size out of range: " + size);



            //Παίρνουμε την τιμή της αγγελίας και ελέγχουμε αν ειναι μεσα στο range
            String price = propertyAds.get(i).findElement(By.cssSelector("div:nth-child(1) > div:nth-child(2) > a:nth-child(1) > div:nth-child(2)>div>span:nth-child(1) ")).getText();
            int priceInt = extractNumber(price);
            Assert.assertTrue(priceInt >= minPrice && priceInt <= maxPrice, "Price out of range: " + priceInt);


            System.out.println("Property ad " + (i + 1) + " has " + images.size() + " images. "+"has size:" +size +"  ,has price"+priceInt);
        }

        Allure.step("All property ads passed the image count check.");
        Allure.step("All property ads passed the priice and size check.");


    }



    // Μετατροπή σε αριθμό
    private int extractNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
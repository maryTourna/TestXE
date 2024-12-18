# Test xe.gr by Tourna Mary

This Suite has 3 tests:
 - SearchProperty       : Test case for searching rent ads on Παγκράτι and all related areas and check some properties of the search results
 - RentalSettingsTest   : Test case for for setting a price from €200 to €700 and a square footage from 75m2 to 150m2 and sort the ads by descending price
 - checkAds             : Test case for ads verification (square footage and prices are within the range we specified in the search and No ad contains more than 30 pictures)





The test can be executed headless or not with Chromedriver 

You can run the test through git actions manualy or  every Sunday at 10:00 UTC(git action schedulle) and see the results at :

### https://marytourna.github.io/TestXE/#suites

It's selenium Java OPenJDK 21.0.5  Maven project  using Allure reports ,made in macOS 

### The project view :

<img width="554" alt="Screenshot 2024-12-16 at 8 58 00 PM" src="https://github.com/user-attachments/assets/6f6717a8-8d6e-4190-b2e4-fb833b2ddab5" />


##How to Run the Test Suite with Maven:

Execute the following command to run the test suite using Maven and see allure results:

```bash
mvn test 
allure serve target/allure-results











package utilities;

import io.qameta.allure.Step;

public class AllureHelper {

    @Step("Opening the page: {url}")
    public static void openPage(String url) {
        System.out.println("Opening page: " + url);
    }

    @Step("Verifying page title: {title}")
    public static void verifyPageTitle(String title) {
        System.out.println("Verified page title: " + title);
    }

    @Step("Clicking on button: {buttonDescription}")
    public static void clickButton(String buttonDescription) {
        System.out.println("Clicked on button: " + buttonDescription);
    }
}
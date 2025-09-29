package tests.contact;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.user.HomePage;

public class ContactUsTest extends BaseTest {

    @Test(description = "Test Case 1: Register User")
    public void testRegisterUser() {
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickContactUs()
                .verifylGetInTouchMessage();
    }
}

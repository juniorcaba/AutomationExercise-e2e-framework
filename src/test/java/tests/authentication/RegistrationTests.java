package tests.authentication;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegistrationTests extends BaseTest {

    @Test(description = "Test Case 1: Register User")
    public void testRegisterUser(){
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .fillSignupName("Mateo")
                .fillSignupEmail("emaildeprueba@test.com")
                .clickSignupButton()
                .selectTitle("Mr.")
                .fillPassword("123456")
                .selectNewsletterSubscription()
                .selectSpecialOffers();
    }


}

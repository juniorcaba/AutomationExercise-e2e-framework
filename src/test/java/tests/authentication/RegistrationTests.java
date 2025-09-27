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
                .verifylNewUserSignupIsVisible()
                .fillSignupName("Mateo")
                .fillSignupEmail("emaildeprueba@test.com")
                .clickSignupButton()
                .signupAndVerifySuccess()
                .selectTitle("Mr.")
                .fillPassword("123456")
                .fillDateOfBirth("17","11", "2010")
                .selectNewsletterSubscription()
                .selectSpecialOffers()
                .fillFirstName("Mateo")
                .fillLastName("Castillo")
                .fillCompany("SQA")
                .fillAddress1("Santo cura 31")
                .fillAddress2("Santo Domingo, Republica Dominicana")
                .fillCountry("Canada")
                .fillState("QA")
                .fillCity("Santo Domingo")
                .fillZipCode("12000")
                .fillMobilePhone("829-400-0909")
                .clickCreateAccountButton()
                .verifyAccountCreatedPageIsDisplayed()
                .clickContinueAfterAccountCreation()
                .verifyUserIsLoggedIn()
                .clickDeleteAccount()
                .verifySuccessMessage()
                .clickContinueAfterAccountDeletion()
                .verifyUserIsNotLoggedIn();

    }

    //@Test(description = "Create Account for test")
    public void testCreateUserForTest(){
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .verifylNewUserSignupIsVisible()
                .fillSignupName("Sandra")
                .fillSignupEmail("emailparaprueba@test.com")
                .clickSignupButton()
                .signupAndVerifySuccess()
                .selectTitle("Mr.")
                .fillPassword("123456")
                .fillDateOfBirth("17","11", "2010")
                .selectNewsletterSubscription()
                .selectSpecialOffers()
                .fillFirstName("Mateo")
                .fillLastName("Castillo")
                .fillCompany("SQA")
                .fillAddress1("Santo cura 31")
                .fillAddress2("Santo Domingo, Republica Dominicana")
                .fillCountry("Canada")
                .fillState("QA")
                .fillCity("Santo Domingo")
                .fillZipCode("12000")
                .fillMobilePhone("829-400-0909")
                .clickCreateAccountButton()
                .verifyAccountCreatedPageIsDisplayed()
                .clickContinueAfterAccountCreation()
                .verifyUserIsLoggedIn();

    }


}

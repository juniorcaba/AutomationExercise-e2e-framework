package tests.authentication;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.user.HomePage;

public class LoginSignupTests extends BaseTest {

    //@Test(description = "Test Case 2: Login User with correct email and password")
    public void testLoginWithValidCredentials() {
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .verifyloginToYouAccountIsVisible()
                .fillLoginEmail("emailparaprueba@test.com")
                .fillLoginPass("123456")
                .clickLoginButton()
                .loginAndVerifySuccess()
                .verifyUserIsLoggedIn()
                .clickDeleteAccount()
                .verifyAccountDeletePageIsDisplayed();
    }

    //@Test(description = "Test Case 3: Login User with incorrect email and password")
    public void testLoginWithInvalidCredentials() {
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .verifyloginToYouAccountIsVisible()
                .fillLoginEmail("email@test.com")
                .fillLoginPass("1234566555")
                .clickLoginButton()
                .loginAndVerifySuccess()
                .verifyUserIsLoggedIn()
                .clickDeleteAccount()
                .verifyAccountDeletePageIsDisplayed();
    }

    @Test(description = "Test Case 4: Logout User")
    public void testLogoutUser() {
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .verifyloginToYouAccountIsVisible()
                .fillLoginEmail("emailparaprueba@test.com")
                .fillLoginPass("123456")
                .clickLoginButton()
                .loginAndVerifySuccess()
                .verifyUserIsLoggedIn()
                .clickLogout()
                .verifyloginToYouAccountIsVisible();

    }
}

package tests.authentication;

import basetest.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginLogoutTests extends BaseTest {

    @Test(description = "Test Case 2: Login User with correct email and password")
    public void testLoginLogout(){
        new HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
                .verifyloginToYouAccountIsVisible()
                .fillLoginEmail("emailparaprueba@test.com")
                .fillLoginPass("123456")
                .clickLoginButton()
                .loginAndVerifySuccess()
                .verifyUserIsLoggedIn();
                //.clickDeleteAccount();
                //.verifyAccountDeletePageIsDisplayed();
    }
}

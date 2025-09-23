package tests.authentication;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegistrationTests extends BaseTest {

    @Test(priority = 1, description = "Verificar navegación a la página principal")
    public void testNavigateToHomePage() {
        // Crear instancia de HomePage y navegar
        HomePage homePage = new HomePage(getDriver());
        homePage.navigateToHomePage();
    }
}

package pages;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;
import utils.WebHelpers;
import pages.SignupLoginPage;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;


/**
 * The type Home page.
 */
public class HomePage extends BasePage {

    private final By signupLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By labelLoginAccount = By.xpath("//*[contains(text(),'Login to your account')]");
    private final By homePageButton = By.xpath("//a[normalize-space()='Home']");
    private final By productsPageButton = By.xpath("//a[@href='/products']");
    private final By contactUsPageButton = By.xpath("//a[normalize-space()='Contact us']");

    private final By loggedInMessage = By.xpath("//text()[contains(.,'Logged in as')]/parent::*");
    private final By logoutButton = By.xpath("//a[normalize-space()='Logout']");
    private final By deleteAccountButton = By.xpath("//a[normalize-space()='Delete Account']");



    /**
     * The Helpers.
     */
    WebHelpers helpers = new WebHelpers(driver);


    /**
     * Instantiates a new Home page.
     *
     * @param driver the driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }


    /**
     * Navigate to home home page.
     *
     * @return the home page
     */
    public HomePage navigateToHome() {
        String baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupLoginButton));
        BaseTest.createStep("Navegando a la pagina de AutomationExercise", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Is on home page boolean.
     *
     * @return the boolean
     */
    public boolean isOnHomePage() {
        return driver.getCurrentUrl().contains("automationexercise.com") &&
                helpers.isElementVisible(signupLoginButton, 5);
    }

    /**
     * Verify home page loaded home page.
     *
     * @return the home page
     */
    public HomePage verifyHomePageLoaded() {
        if (!isOnHomePage()) {
            throw new RuntimeException("No estamos en la página principal");
        }
        BaseTest.createStep("Verificado que estamos en la página principal", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Click signup login signup login page.
     *
     * @return the signup login page
     */
    public SignupLoginPage clickSignupLogin() {
        helpers.clickElement(signupLoginButton, "botón Signup/Login");
        BaseTest.createStep("Accediendo a la sección de Signup / Login", true, true, IMMEDIATE);
        helpers.waitForPageToLoad(labelLoginAccount);
        return new SignupLoginPage(driver);
    }

    /**
     * Is product visible boolean.
     *
     * @param productName the product name
     * @return the boolean
     */
    public boolean isProductVisible(String productName) {
        By productLocator = By.xpath("//div[contains(@class,'productinfo')]//p[text()='" + productName + "']");
        return helpers.isElementVisible(productLocator, 5);
    }


    /**
     * Verify user is logged in page home page.
     *
     * @param expectedUsername the expected username
     * @return the home page
     */
    public HomePage verifyUserIsLoggedInPage(String expectedUsername) {
        helpers.waitForElementToAppear(loggedInMessage, 5);
        String loggedInText = helpers.getElementText(loggedInMessage);

        if (loggedInText.contains(expectedUsername)) {
            BaseTest.createStep("Usuario logueado correctamente como: " + expectedUsername, true, true, IMMEDIATE);
        } else {
            BaseTest.processBuffer(COMMIT_MERGED_FAILURE,
                    "Usuario esperado: " + expectedUsername + ", pero encontrado: " + loggedInText, true);
        }
        return this;
    }


    /**
     * Verify user is logged in home page.
     *
     * @return the home page
     */
    public HomePage verifyUserIsLoggedIn() {
        helpers.waitForElementToAppear(loggedInMessage, 3);
        BaseTest.createStep("Verificando que el usuario está logueado", true, true, IMMEDIATE);
        return this;
    }


    /**
     * Gets logged in username.
     *
     * @return the logged in username
     */
    public String getLoggedInUsername() {
        String loggedInText = helpers.getElementText(loggedInMessage);
        // Extraer solo el nombre de usuario de "Logged in as Username"
        if (loggedInText != null && loggedInText.contains("Logged in as ")) {
            return loggedInText.replace("Logged in as ", "").trim();
        }
        return null;
    }


    /**
     * Click logout home page.
     *
     * @return the home page
     */
    public HomePage clickLogout() {
        helpers.clickElement(logoutButton, "botón Logout");
        BaseTest.createStep("Haciendo clic en Logout", true, true, IMMEDIATE);
        return new HomePage(driver); // Retorna a HomePage sin login
    }


    /**
     * Click delete account home page.
     *
     * @return the home page
     */
    public DeleteAccountPage clickDeleteAccount() {
        helpers.clickElement(deleteAccountButton, "botón Delete Account");
        BaseTest.createStep("Haciendo clic en Delete Account", true, true, IMMEDIATE);
        return new DeleteAccountPage(driver);
    }


    /**
     * Verify logged in options are available home page.
     *
     * @return the home page
     */
    public HomePage verifyLoggedInOptionsAreAvailable() {
        helpers.waitForElementToAppear(logoutButton, 5);
        helpers.waitForElementToAppear(deleteAccountButton, 5);
        BaseTest.createStep("Verificando que las opciones de usuario logueado están disponibles", true, true, IMMEDIATE);
        return this;
    }


    /**
     * Verify user is not logged in home page.
     *
     * @return the home page
     */
    public HomePage verifyUserIsNotLoggedIn() {
        // Verificar que el mensaje "Logged in as" NO esté presente
        boolean isNotLoggedIn = helpers.verifyElementIsNotVisible(loggedInMessage, 5);

        if (isNotLoggedIn) {
            BaseTest.createStep("Verificando que el usuario NO está logueado", true, true, IMMEDIATE);
        } else {
            BaseTest.processBuffer(COMMIT_MERGED_FAILURE,
                    "ERROR: El usuario todavía aparece como logueado después de eliminar cuenta", true);
        }
        return this;
    }


    /**
     * Verify guest user options home page.
     *
     * @return the home page
     */
    public HomePage verifyGuestUserOptions() {
        helpers.waitForElementToAppear(signupLoginButton, 5);
        BaseTest.createStep("Verificando que aparecen las opciones de usuario invitado (Signup/Login)", true, true, IMMEDIATE);
        return this;
    }


    /**
     * Verify user logged out state home page.
     *
     * @return the home page
     */
    public HomePage verifyUserLoggedOutState() {
        verifyUserIsNotLoggedIn();
        verifyGuestUserOptions();
        BaseTest.createStep("Verificación completa: usuario deslogueado correctamente", true, true, IMMEDIATE);
        return this;
    }





}

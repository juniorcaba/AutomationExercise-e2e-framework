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


public class HomePage extends BasePage {

    private final By signupLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");
    private final By labelLoginAccount = By.xpath("//*[contains(text(),'Login to your account')]");
    private final By homePageButton = By.xpath("//a[normalize-space()='Home']");
    private final By productsPageButton = By.xpath("//a[@href='/products']");
    private final By contactUsPageButton = By.xpath("//a[normalize-space()='Contact us']");


    WebHelpers helpers = new WebHelpers(driver);


    public HomePage(WebDriver driver) {
        super(driver);
    }


    public HomePage navigateToHome() {
        String baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupLoginButton));
        BaseTest.createStep("Navegando a la pagina de AutomationExercise", true, true, IMMEDIATE);
        return this;
    }

    public boolean isOnHomePage() {
        return driver.getCurrentUrl().contains("automationexercise.com") &&
                helpers.isElementVisible(signupLoginButton, 5);
    }

    public HomePage verifyHomePageLoaded() {
        if (!isOnHomePage()) {
            throw new RuntimeException("No estamos en la página principal");
        }
        BaseTest.createStep("Verificado que estamos en la página principal", true, true, IMMEDIATE);
        return this;
    }

    public SignupLoginPage clickSignupLogin() {
        helpers.clickElement(signupLoginButton, "botón Signup/Login");
        BaseTest.createStep("Accediendo a la sección de Signup / Login", true, true, IMMEDIATE);
        helpers.waitForPageToLoad(labelLoginAccount);
        return new SignupLoginPage(driver);
    }

    public boolean isProductVisible(String productName) {
        By productLocator = By.xpath("//div[contains(@class,'productinfo')]//p[text()='" + productName + "']");
        return helpers.isElementVisible(productLocator, 5);
    }

}

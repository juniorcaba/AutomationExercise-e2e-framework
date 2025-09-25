package pages;

import org.openqa.selenium.WebDriver;
import basetest.BaseTest;
import org.openqa.selenium.By;
import utils.WebHelpers;
import pages.CreateAccountPage;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;

public class SignupLoginPage extends BasePage {

    private final By signupNameField = By.xpath("//input[@placeholder='Name']");
    private final By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");

    private final By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private final By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");

    private final WebHelpers helpers;

    public SignupLoginPage(WebDriver driver) {
        super(driver);
        this.helpers = new WebHelpers(driver);
    }

    public SignupLoginPage fillSignupName(String name) {
        try {
            helpers.fillTextField(signupNameField, name);
            BaseTest.createStep("Ingresando nombre: " + name, true, true, IMMEDIATE);
        } catch (Exception e) {
            helpers.handleFieldError("nombre", e);
        }
        return this;
    }

    public SignupLoginPage fillSignupEmail(String email) {
        try {
            helpers.fillTextField(signupEmailField, email);
            BaseTest.createStep("Ingresando email: " + email, true, true, IMMEDIATE);
        } catch (Exception e) {
            helpers.handleFieldError("email", e);
        }
        return this;
    }

    public CreateAccountPage clickSignupButton() {
        try {
            helpers.clickElement(signupButton);
            BaseTest.createStep("Haciendo clic en Signup", true, true, IMMEDIATE);
            return new CreateAccountPage(driver);
        } catch (Exception e) {
            helpers.handleClickError("botón Signup", e);
            return new CreateAccountPage(driver);
        }
    }



}
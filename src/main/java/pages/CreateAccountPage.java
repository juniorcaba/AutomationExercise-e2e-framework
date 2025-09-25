package pages;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.WebHelpers;

import pages.SignupLoginPage;
import pages.HomePage;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;
import static org.apache.commons.lang3.exception.ExceptionUtils.getMessage;

public class CreateAccountPage extends BasePage {

    private final By titleInput = By.xpath("//label[normalize-space()='Title']");
    private final By mrSelectButton = By.xpath("//input[@id='id_gender1']");
    private final By mrsSelectButton = By.xpath("//input[@id='id_gender2']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By newsletterCheckbox = By.xpath("//input[@id='newsletter']");
    private final By specialOffersCheckbox = By.xpath("//input[@id='optin']");


    private final WebHelpers helpers;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        this.helpers = new WebHelpers(driver);
    }


    public CreateAccountPage selectMrTitle() {
        helpers.clickElement(mrSelectButton);
        BaseTest.createStep("Seleccionando título: Mr.", true, true, IMMEDIATE);
        return this; // Permanece en la misma página
    }


    public CreateAccountPage selectMrsTitle() {
        helpers.clickElement(mrsSelectButton);
        BaseTest.createStep("Seleccionando título: Mrs.", true, true, IMMEDIATE);
        return this; // Permanece en la misma página
    }

    /**
     * Selecciona el título basado en parámetro
     * @param title El título a seleccionar ("Mr." o "Mrs.")
     * @return La misma instancia de la página para encadenamiento
     */
    public CreateAccountPage selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr.") || title.equalsIgnoreCase("Mr")) {
            helpers.clickElement(mrSelectButton);
            BaseTest.createStep("Seleccionando título: Mr.", true, true, IMMEDIATE);
        } else if (title.equalsIgnoreCase("Mrs.") || title.equalsIgnoreCase("Mrs")) {
            helpers.clickElement(mrsSelectButton);
            BaseTest.createStep("Seleccionando título: Mrs.", true, true, IMMEDIATE);
        } else {
            BaseTest.processBuffer(COMMIT_MERGED_FAILURE, "Título no válido: " + title + ". Use 'Mr.' o 'Mrs.'", true);
            throw new IllegalArgumentException("Título no válido: '" + title + "'. Use 'Mr.', 'Mr', 'Mrs.', o 'Mrs'");
        }
        return this;
    }

    public CreateAccountPage fillPassword(String password) {
        try {
            helpers.fillTextField(passwordField, password);
            BaseTest.createStep("Ingresando Password: " + password, true, true, IMMEDIATE);
        } catch (Exception e) {
            helpers.handleFieldError("password", e);
        }
        return this;
    }

    public CreateAccountPage selectNewsletterSubscription() {
        try {
            helpers.clickElement(newsletterCheckbox);
            BaseTest.createStep("Seleccionando suscripción al newsletter", true, true, IMMEDIATE);
        } catch (Exception e) {
            helpers.handleClickError("checkbox newsletter", e);
        }
        return this;
    }

    public CreateAccountPage selectSpecialOffers() {
        try {
            helpers.clickElement(specialOffersCheckbox);
            BaseTest.createStep("Seleccionando ofertas especiales", true, true, IMMEDIATE);
        } catch (Exception e) {
            helpers.handleClickError("checkbox ofertas especiales", e);
        }
        return this;
    }
}


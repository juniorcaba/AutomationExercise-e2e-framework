package pages;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebHelpers;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;

public class AccountCreatedPage extends BasePage {

    // Locators basados en las imágenes
    private final By accountCreatedTitle = By.xpath("//b[normalize-space()='Account Created!']");
    private final By congratulationsMessage = By.xpath("//text()[contains(.,'Congratulations! Your new account has been successfully created!')]/parent::*");
    private final By continueButton = By.xpath("//button[contains(text(),'Continue')] | //a[contains(text(),'Continue')]");
    private final By memberPrivilegesMessage = By.xpath("//text()[contains(.,'member privileges')]/parent::*");

    private final WebHelpers helpers;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
        this.helpers = new WebHelpers(driver);
    }

    /**
     * Verifica que la página de confirmación de cuenta creada esté cargada
     */
    public AccountCreatedPage verifyAccountCreatedPageIsDisplayed() {
        helpers.waitForPageToLoad(accountCreatedTitle, 5);
        BaseTest.createStep("Verificando que el titulo 'Account Created' está presente", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Verifica que aparezca el mensaje de congratulaciones
     */
    public AccountCreatedPage verifySuccessMessage() {
        helpers.waitForElementToAppear(congratulationsMessage, 5);
        BaseTest.createStep("Verificando mensaje de éxito: 'Congratulations! Your new account has been successfully created!'", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Obtiene el texto completo del mensaje de congratulaciones
     */
    public String getSuccessMessage() {
        String message = helpers.getElementText(congratulationsMessage);
        BaseTest.createStep("Obteniendo mensaje de éxito: " + message, true, true, IMMEDIATE);
        return message;
    }

    /**
     * Verifica que aparezca el mensaje sobre privilegios de miembro
     */
    public AccountCreatedPage verifyMemberPrivilegesMessage() {
        helpers.waitForElementToAppear(memberPrivilegesMessage, 5);
        BaseTest.createStep("Verificando mensaje sobre privilegios de miembro", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Hace clic en el botón Continue y navega a la siguiente página
     * @return Dependiendo del flujo, podría retornar HomePage, AccountPage, etc.
     */
    public HomePage clickContinueAfterAccountCreation() {
        helpers.clickElement(continueButton, "botón Continue después de crear cuenta");
        BaseTest.createStep("Haciendo clic en Continue después de crear cuenta exitosamente", true, true, IMMEDIATE);
        return new HomePage(driver);
    }

    /**
     * Flujo completo de verificación de la página
     */
    public AccountCreatedPage verifyAccountCreationSuccess() {
        verifyAccountCreatedPageIsDisplayed();
        verifySuccessMessage();
        verifyMemberPrivilegesMessage();
        BaseTest.createStep("Verificación completa de creación de cuenta exitosa", true, true, IMMEDIATE);
        return this;
    }
}
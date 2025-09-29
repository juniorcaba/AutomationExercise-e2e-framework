package pages.user;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.WebHelpers;

import static basetest.BaseTest.StepMode.*;

public class DeleteAccountPage extends BasePage {

    private final By accountDeleteTitle = By.xpath("//b[normalize-space()='Account Deleted!']]");
    private final By deleteMessage = By.xpath("//text()[contains(.,'Your account has been permanently deleted!')]/parent::*");
    private final By continueButton = By.xpath("//button[contains(text(),'Continue')] | //a[contains(text(),'Continue')]");
    private final By memberPrivilegesMessage = By.xpath("//text()[contains(.,'member privileges')]/parent::*");

    private final WebHelpers helpers;

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
        this.helpers = new WebHelpers(driver);
    }

    /**
     * Verifica que la página de confirmación de cuenta creada esté cargada
     */
    public DeleteAccountPage verifyAccountDeletePageIsDisplayed() {
        helpers.waitForPageToLoad(accountDeleteTitle, 10);
        BaseTest.createStep("Verificando que el titulo 'Account Deleted!' está presente", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Verifica que aparezca el mensaje de congratulaciones
     */
    public DeleteAccountPage verifySuccessMessage() {
        helpers.waitForElementToAppear(deleteMessage, 5);
        BaseTest.createStep("Verificando mensaje de Eliminacion de la cuenta: 'Your account has been permanently deleted!'", true, true, IMMEDIATE);
        return this;
    }

    /**
     * Obtiene el texto completo del mensaje de congratulaciones
     */
    public String getSuccessMessage() {
        String message = helpers.getElementText(deleteMessage);
        BaseTest.createStep("Obteniendo mensaje de éxito: " + message, true, true, IMMEDIATE);
        return message;
    }

    /**
     * Hace clic en el botón Continue y navega a la siguiente página
     * @return Dependiendo del flujo, podría retornar HomePage, AccountPage, etc.
     */
    public HomePage clickContinueAfterAccountDeletion() {
        helpers.clickElement(continueButton, "botón Continue después de eliminar cuenta");
        BaseTest.createStep("Haciendo clic en Continue después de eliminar cuenta", true, true, IMMEDIATE);
        return new HomePage(driver);
    }

    /**
     * Flujo completo de verificación de la página
     */
    public DeleteAccountPage verifyDeleteAccountSuccess() {
        verifyAccountDeletePageIsDisplayed();
        verifySuccessMessage();
        BaseTest.createStep("Verificación completa de creación de cuenta exitosa", true, true, IMMEDIATE);
        return this;
    }
}
package pages;

import basetest.BaseTest;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WebHelpers;
import pages.AccountCreatedPage;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;

public class CreateAccountPage extends BasePage {

    private final By titleInput = By.xpath("//label[normalize-space()='Title']");
    private final By addressSection = By.xpath("//b[normalize-space()='Address Information']");
    private final By mrSelectButton = By.xpath("//input[@id='id_gender1']");
    private final By mrsSelectButton = By.xpath("//input[@id='id_gender2']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By enterAccountInfo = By.xpath("//b[normalize-space()='Enter Account Information']");
    private final By newsletterCheckbox = By.xpath("//input[@id='newsletter']");
    private final By specialOffersCheckbox = By.xpath("//input[@id='optin']");
    private final By dayDropdown = By.cssSelector("select[data-qa='days']");
    private final By monthDropdown = By.cssSelector("select[data-qa='months']");
    private final By yearDropdown = By.cssSelector("select[data-qa='years']");
    private final By firstNameInput = By.xpath("//input[@id='first_name']");
    private final By lastNameInput = By.xpath("//input[@id='last_name']");
    private final By companyInput = By.xpath("//input[@id='company']");
    private final By address1Input = By.xpath("//input[@id='address1']");
    private final By address2Input = By.xpath("//input[@id='address2']");
    private final By countryInput = By.xpath("//select[@id='country']");
    private final By stateInput = By.xpath("//input[@id='state']");
    private final By cityInput = By.xpath("//input[@id='city']");
    private final By zipcodeInput = By.xpath("//input[@id='zipcode']");
    private final By mobileNumberInput = By.xpath("//input[@id='mobile_number']");
    private final By createAccountBtn = By.xpath("//*[contains(text(), 'Create Account')]");



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
        helpers.fillTextField(passwordField, password, "password");
        BaseTest.createStep("Ingresando Password: " + password, true, true, IMMEDIATE);
        return this;
    }


    public CreateAccountPage selectNewsletterSubscription(){
        helpers.clickElement(newsletterCheckbox, "Checkbox newsletter");
        BaseTest.createStep("Seleccionando suscripción al newsletter", true, true, IMMEDIATE);
        return this;
    }


    public CreateAccountPage selectSpecialOffers() {
        helpers.clickElement(specialOffersCheckbox, "checkbox ofertas especiales");
        BaseTest.createStep("Seleccionando ofertas especiales", true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage verifyCreateAccountInfoIsVisible() {
        helpers.waitForPageToLoad(enterAccountInfo);
        BaseTest.createStep("Verificando que la página Create Account está cargada", true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillDateOfBirth(String day, String month, String year) {
        helpers.fillDateOfBirth(dayDropdown, monthDropdown, yearDropdown, day, month, year, "fecha de nacimiento");
        BaseTest.createStep("Configurando fecha de nacimiento: " + day + "/" + month + "/" + year, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillFirstName(String firstName) {
        //helpers.scrollToElement(addressSection);
        helpers.fillTextField(firstNameInput, firstName, "firstName");
        BaseTest.createStep("Ingresando el nombre: " + firstName, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillLastName(String lastName) {
        helpers.fillTextField(lastNameInput, lastName, "firstName");
        BaseTest.createStep("Ingresando el apellido: " + lastName, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillCompany(String company) {
        helpers.scrollToElement(companyInput);
        helpers.fillTextField(companyInput, company, "company");
        BaseTest.createStep("Ingresando empresa: " + company, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillAddress1(String address1) {
        helpers.fillTextField(address1Input, address1, "firstName");
        BaseTest.createStep("Ingresando la direccion 1: " + address1, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillAddress2(String address2) {
        helpers.fillTextField(address2Input, address2, "firstName");
        BaseTest.createStep("Ingresando la direccion 2: " + address2, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillCountry(String country){
        helpers.selectDropdownByText(countryInput, country);
        BaseTest.createStep("Ingresando el pais: " +country, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillState(String state){
        helpers.fillTextField(stateInput, state);
        BaseTest.createStep("Ingresando el Estado: " +state, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillCity(String city){
        helpers.scrollToElement(cityInput);
        helpers.fillTextField(cityInput, city);
        BaseTest.createStep("Ingresando la Cuidad: " +city, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillZipCode(String zipcode){
        helpers.fillTextField(zipcodeInput, zipcode);
        BaseTest.createStep("Ingresando el codigo Postal: " +zipcode, true, true, IMMEDIATE);
        return this;
    }

    public CreateAccountPage fillMobilePhone(String mobilePhone){
        helpers.fillTextField(mobileNumberInput, mobilePhone);
        BaseTest.createStep("Ingresando el Numero de telefono movil: " +mobilePhone, true, true, IMMEDIATE);
        return this;
    }

    public AccountCreatedPage clickCreateAccountButton() {
        helpers.clickElement(createAccountBtn, "botón para crear cuenta");
        BaseTest.createStep("Haciendo clic en Create Account", true, true, IMMEDIATE);
        return new AccountCreatedPage(driver);
    }

    public CreateAccountPage test(){
        helpers.isElementVisible(createAccountBtn, 10);
        return this;
    }




}


    package pages.authentication;

    import org.openqa.selenium.WebDriver;
    import basetest.BaseTest;
    import org.openqa.selenium.By;
    import pages.BasePage;
    import pages.user.HomePage;
    import utils.WebHelpers;

    import static basetest.BaseTest.StepMode.*;

    public class SignupLoginPage extends BasePage {

        private final By signupNameField = By.xpath("//input[@placeholder='Name']");
        private final By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
        private final By signupButton = By.xpath("//button[@data-qa='signup-button']");
        private final By loginEmailField = By.xpath("//input[@data-qa='login-email']");
        private final By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
        private final By loginButton = By.xpath("//button[@data-qa='login-button']");
        private final By loginToYouAccountMessage = By.xpath("//h2[normalize-space()='Login to your account']");
        private final By newUserSignupMessage = By.xpath("//h2[normalize-space()='New User Signup!']");
        private final By loginErrorMessage = By.xpath("//*[contains(text(),'Your email or password is incorrect!')]");
        private final By signupErrorMessage = By.xpath("//p[normalize-space()='Email Address already exist!']");
        private final By enterAccountInfo = By.xpath("//b[normalize-space()='Enter Account Information']");
        private final By logoutButton = By.xpath("//a[normalize-space()='Logout']");


        private final WebHelpers helpers;

        /**
         * Instantiates a new Signup login page.
         *
         * @param driver the driver
         */
        public SignupLoginPage(WebDriver driver) {
            super(driver);
            this.helpers = new WebHelpers(driver);

        }

        /**
         * Verifylogin to you account is visible signup login page.
         *
         * @return the signup login page
         */
        public SignupLoginPage verifyloginToYouAccountIsVisible() {
            helpers.waitForPageToLoad(loginToYouAccountMessage);
            BaseTest.createStep("Verificando que el mensaje 'Login to your account' esta presente ", true, true, IMMEDIATE);
            return this;
        }

        /**
         * Verifyl new user signup is visible signup login page.
         *
         * @return the signup login page
         */
        public SignupLoginPage verifylNewUserSignupIsVisible() {
            helpers.waitForPageToLoad(newUserSignupMessage);
            BaseTest.createStep("Verificando que el mensaje 'New User Signup!' esta presente ", true, true, IMMEDIATE);
            return this;
        }

        /**
         * Fill signup name signup login page.
         *
         * @param name the name
         * @return the signup login page
         */
        public SignupLoginPage fillSignupName(String name) {
            helpers.fillTextField(signupNameField, name, "Name");
            BaseTest.createStep("Ingresando nombre: " + name, true, true, IMMEDIATE);
            return this;
        }

        /**
         * Fill signup email signup login page.
         *
         * @param email the email
         * @return the signup login page
         */
        public SignupLoginPage fillSignupEmail(String email) {
            helpers.fillTextField(signupEmailField, email, "Email");
            BaseTest.createStep("Ingresando email: " + email, true, true, IMMEDIATE);
            return this;
        }

        /**
         * Fill login email signup login page.
         *
         * @param email the email
         * @return the signup login page
         */
        public SignupLoginPage fillLoginEmail(String email) {
            helpers.fillTextField(loginEmailField, email, "Email");
            BaseTest.createStep("Ingresando email: " + email, true, true, IMMEDIATE);
            return this;
        }

        /**
         * Fill login pass signup login page.
         *
         * @param password the password
         * @return the signup login page
         */
        public SignupLoginPage fillLoginPass(String password) {
            helpers.fillTextField(loginPasswordField, password, "Email");
            BaseTest.createStep("Ingresando email: " + password, true, true, IMMEDIATE);
            return this;
        }

        /**
         * Click signup button signup login page.
         *
         * @return the signup login page
         */
        public SignupLoginPage clickSignupButton() {
            helpers.clickElement(signupButton, "botón Signup");
            BaseTest.createStep("Haciendo clic en Signup", true, true, IMMEDIATE);
            return this;
        }

        /**
         * Signup and verify success create account page.
         *
         * @return the create account page
         */
        public CreateAccountPage signupAndVerifySuccess() {
            //helpers.validateActionResult(signupErrorMessage, enterAccountInfo, 2, "Signup");
            helpers.validateActionResult(signupErrorMessage, "Signup");

            BaseTest.createStep("Signup exitoso", true, true, IMMEDIATE);
            return new CreateAccountPage(driver);
        }


        /**
         * Click login button signup login page.
         *
         * @return the signup login page
         */
        public SignupLoginPage clickLoginButton() {
            helpers.clickElement(loginButton, "botón Login");
            BaseTest.createStep("Haciendo clic en Login", true, true, IMMEDIATE);
            return this;
        }


        /**
         * Login and verify success home page.
         *
         * @return the home page
         */
        public HomePage loginAndVerifySuccess() {
            helpers.validateActionResult(loginErrorMessage, 2, "Login");
//            helpers.validateActionResult(loginErrorMessage, "Login");
            //helpers.isElementVisible(logoutButton, 2);
//            helpers.validateActionResult(loginErrorMessage, logoutButton,2,"Login");
            BaseTest.createStep("Login exitoso", true, true, IMMEDIATE);
            return new HomePage(driver);
        }



    }
package pages;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ConfigReader;
import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;


public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()=' Signup / Login']")
    private WebElement signupLoginLink;

    private final By signupLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");


    public HomePage(WebDriver driver) {
        super(driver);

        this.pageUrl = "https://automationexercise.com";
        this.validationLocator = signupLoginButton;
        this.pageName = "Homepage AutomationExercise";
    }

    public void navigateToHomePage() {
        String baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        BaseTest.createStep("Navegando a la pagina de AutomationExercise", true, true, IMMEDIATE);
    }

    public void navigateToHomePage02() {
        navigateToPage();
    }

}

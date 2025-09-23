package pages;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;
import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;


public class HomePage extends BasePage {

    @FindBy(xpath = "//a[text()=' Signup / Login']")
    private WebElement signupLoginLink;

    private final By signupLoginButton = By.xpath("//a[normalize-space()='Signup / Login']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        String baseUrl = ConfigReader.getBaseUrl();
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupLoginButton));
        BaseTest.createStep("Navegando a la pagina de AutomationExercise", true, true, IMMEDIATE);
    }

}

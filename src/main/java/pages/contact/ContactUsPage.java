package pages.contact;

import basetest.BaseTest;
import exceptions.ElementNotVisibleException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.WebHelpers;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;

public class ContactUsPage extends BasePage {

    private final By getInTouchMessage = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By getInTouchMessagess = By.xpath("//h2[normalize-space()='3434']");


    private final WebHelpers helpers;
    public ContactUsPage(WebDriver driver) {
        super(driver);
        this.helpers = new WebHelpers(driver);
    }

    public ContactUsPage verifylGetInTouchMessage() {
        if (!helpers.isElementPresent(getInTouchMessage, 10)) {
            BaseTest.processBuffer(COMMIT_MERGED_FAILURE,
                    "Mensaje 'Get In Touch' no está visible", true);
            throw new ElementNotVisibleException("Verificación falló: 'Get In Touch' no encontrado");
        }

        BaseTest.createStep("Get In Touch' esta presente", true, true, IMMEDIATE);
        return this;
    }


}

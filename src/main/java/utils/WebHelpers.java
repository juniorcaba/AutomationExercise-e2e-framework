package utils;

import basetest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static basetest.BaseTest.StepMode.*;
import static basetest.BaseTest.BufferAction.*;

/**
 * Clase de utilidades web genéricas para automatización con Selenium
 * Contiene métodos reutilizables independientes del dominio de negocio
 *
 * @author Tu nombre
 * @version 1.0
 */
public class WebHelpers {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public WebHelpers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
    }

    // ==================== INTERACCIÓN BÁSICA CON ELEMENTOS ====================

    /**
     * Llena un campo de texto con el valor especificado
     * @param locator Localizador del elemento
     * @param value Valor a ingresar
     */
    public void fillTextField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
        field.clear();
        field.sendKeys(value);
    }

    /**
     * Llena un campo de texto por atributo específico
     * @param attributeName Nombre del atributo (ej: "formcontrolname", "placeholder", "name")
     * @param attributeValue Valor del atributo
     * @param value Texto a ingresar
     */
    public void fillTextFieldByAttribute(String attributeName, String attributeValue, String value) {
        By locator = By.xpath("//input[@" + attributeName + "='" + attributeValue + "']");
        fillTextField(locator, value);
    }

    /**
     * Hace clic en un elemento de forma segura
     * @param locator Localizador del elemento
     */
    public void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Envía tecla TAB a un elemento específico
     * @param locator Localizador del elemento
     */
    public void sendTabKey(By locator) {
        WebElement element = driver.findElement(locator);
        actions.sendKeys(element, Keys.TAB).perform();
    }

    // ==================== VALIDACIONES ====================

    /**
     * Verifica si un campo está vacío
     * @param locator Localizador del elemento
     * @return true si está vacío, false si tiene contenido
     */
    public boolean isFieldEmpty(By locator) {
        WebElement element = driver.findElement(locator);
        String value = element.getAttribute("value");
        return value == null || value.trim().isEmpty();
    }

    /**
     * Verifica si un elemento está habilitado
     * @param locator Localizador del elemento
     * @return true si está habilitado, false si está deshabilitado
     */
    public boolean isElementEnabled(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isEnabled();
    }

    /**
     * Obtiene el texto de un elemento
     * @param locator Localizador del elemento
     * @return Texto del elemento o null si no se encuentra
     */
    public String getElementText(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getText().trim();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Obtiene el valor de un atributo de un elemento
     * @param locator Localizador del elemento
     * @param attributeName Nombre del atributo
     * @return Valor del atributo o null si no se encuentra
     */
    public String getElementAttribute(By locator, String attributeName) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attributeName);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    // ==================== DROPDOWNS Y SELECTS ====================

    /**
     * Selecciona una opción en un select HTML estándar por texto visible
     * @param locator Localizador del select
     * @param visibleText Texto visible de la opción
     */
    public void selectDropdownByText(By locator, String visibleText) {
        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(selectElement);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selecciona una opción en un select HTML estándar por valor
     * @param locator Localizador del select
     * @param value Valor de la opción
     */
    public void selectDropdownByValue(By locator, String value) {
        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    /**
     * Maneja dropdowns personalizados (como Angular Material, PrimeNG, etc.)
     * @param triggerLocator Localizador del elemento que abre el dropdown
     * @param optionsContainerLocator Localizador del contenedor de opciones
     * @param optionText Texto de la opción a seleccionar
     */
    public void selectCustomDropdown(By triggerLocator, By optionsContainerLocator, String optionText) {
        // Hacer clic en el trigger
        clickElement(triggerLocator);

        // Esperar que aparezcan las opciones
        wait.until(ExpectedConditions.presenceOfElementLocated(optionsContainerLocator));

        // Buscar y hacer clic en la opción deseada
        WebElement optionsContainer = driver.findElement(optionsContainerLocator);
        List<WebElement> options = optionsContainer.findElements(By.xpath(".//*"));

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(optionText)) {
                option.click();
                break;
            }
        }
    }

    // ==================== NAVEGACIÓN Y SCROLL ====================

    /**
     * Hace scroll hacia abajo en la página
     */
    public void scrollDown() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    /**
     * Hace scroll hacia arriba en la página
     */
    public void scrollUp() {
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     * Hace scroll hacia un elemento específico
     * @param locator Localizador del elemento
     */
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hace scroll usando JavaScript
     * @param pixels Cantidad de píxeles a hacer scroll (positivo = abajo, negativo = arriba)
     */
    public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixels + ");");
    }

    // ==================== ESPERAS Y SINCRONIZACIÓN ====================

    /**
     * Espera que un elemento esté presente y luego desaparezca
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera en segundos
     */
    public void waitForElementToAppearAndDisappear(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Hace clic cuando el elemento esté listo, manejando spinners
     * @param elementLocator Localizador del elemento a hacer clic
     * @param spinnerLocator Localizador del spinner (opcional, puede ser null)
     * @param timeoutSeconds Tiempo máximo de espera
     */
    public void clickWhenReady(By elementLocator, By spinnerLocator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Esperar que desaparezca el spinner si existe
        if (spinnerLocator != null) {
            try {
                customWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
            } catch (TimeoutException e) {
                System.out.println("Warning: Spinner did not disappear within expected time");
            }
        }

        // Hacer clic en el elemento
        WebElement element = customWait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Usar JavaScript como respaldo
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Espera que un spinner desaparezca
     * @param spinnerLocator Localizador del spinner
     * @param timeoutSeconds Tiempo máximo de espera
     */
    public void waitForSpinnerToDisappear(By spinnerLocator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            customWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
        } catch (TimeoutException e) {
            System.out.println("Warning: Spinner did not disappear within " + timeoutSeconds + " seconds");
        }
    }

    // ==================== VERIFICACIONES AVANZADAS ====================

    /**
     * Verifica si un elemento es visible con timeout personalizado
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera
     * @return true si es visible, false si no
     */
    public boolean isElementVisible(By locator, int timeoutSeconds) {
        for (int i = 0; i < timeoutSeconds * 2; i++) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    return true;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                // Continuar verificando
            }
        }
        return false;
    }

    /**
     * Espera que un elemento aparezca y luego desaparezca
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo total de espera
     * @return true si el elemento apareció y desapareció, false si no
     */
    public boolean waitForElementToAppearAndThenDisappear(By locator, int timeoutSeconds) {
        boolean appeared = false;
        int attempts = timeoutSeconds * 2;

        for (int i = 0; i < attempts; i++) {
            try {
                List<WebElement> elements = driver.findElements(locator);

                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    appeared = true;
                }

                if (appeared && (elements.isEmpty() || !elements.get(0).isDisplayed())) {
                    return true;
                }

                Thread.sleep(500);
            } catch (Exception e) {
                // Continuar verificando
            }
        }
        return false;
    }

    /**
     * Espera que un elemento aparezca
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera
     * @return true si aparece, false si no
     */
    public boolean waitForElementToAppear(By locator, int timeoutSeconds) {
        for (int i = 0; i < timeoutSeconds * 2; i++) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    return true;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                // Continuar verificando
            }
        }
        return false;
    }

    /**
     * Espera que un elemento desaparezca
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera
     * @return true si desaparece, false si no
     */
    public boolean waitForElementToDisappear(By locator, int timeoutSeconds) {
        for (int i = 0; i < timeoutSeconds * 2; i++) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                    return true;
                }
                Thread.sleep(500);
            } catch (Exception e) {
                // Continuar verificando
            }
        }
        return false;
    }

    // ==================== UTILIDADES JAVASCRIPT ====================

    /**
     * Ejecuta JavaScript en el navegador
     * @param script Script a ejecutar
     * @param args Argumentos para el script
     * @return Resultado de la ejecución
     */
    public Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    /**
     * Hace clic usando JavaScript (útil cuando el clic normal falla)
     * @param locator Localizador del elemento
     */
    public void clickWithJavaScript(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Resalta un elemento (útil para debugging)
     * @param locator Localizador del elemento
     */
    public void highlightElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border='3px solid red';", element);
    }

    // ==================== MANEJO DE VENTANAS Y FRAMES ====================

    /**
     * Cambia a una nueva ventana/pestaña
     * @return Handle de la ventana original para poder regresar
     */
    public String switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return originalWindow;
    }

    /**
     * Regresa a una ventana específica
     * @param windowHandle Handle de la ventana
     */
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /**
     * Cambia a un frame
     * @param frameLocator Localizador del frame
     */
    public void switchToFrame(By frameLocator) {
        WebElement frame = driver.findElement(frameLocator);
        driver.switchTo().frame(frame);
    }

    /**
     * Regresa al contenido principal (sale de todos los frames)
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void waitForPageToLoad(By pageIdentifierLocator, int timeoutSeconds) {
        waitForElementToAppear(pageIdentifierLocator, timeoutSeconds);
    }

    public void waitForPageToLoad(By pageIdentifierLocator) {
        waitForPageToLoad(pageIdentifierLocator, 10);
    }

// ==================== MANEJO DE ERRORES ====================

    public void handleFieldError(String fieldName, Exception e) {
        String errorMessage = "Error al llenar campo " + fieldName + ": " + e.getMessage();
        BaseTest.processBuffer(COMMIT_MERGED_FAILURE, errorMessage, true);
        throw new RuntimeException("Error en campo " + fieldName, e);
    }

    public void handleClickError(String elementName, Exception e) {
        String errorMessage = "Error al hacer clic en " + elementName + ": " + e.getMessage();
        BaseTest.processBuffer(COMMIT_MERGED_FAILURE, errorMessage, true);
        throw new RuntimeException("Elemento " + elementName + " no clickeable", e);
    }



}
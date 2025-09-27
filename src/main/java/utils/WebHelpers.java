package utils;

import basetest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import java.text.Normalizer;
import java.util.regex.Pattern;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    // ==================== INTERACCIÓN BÁSICA CON ELEMENTOS ====================

    /**
     * Llena un campo de texto con manejo de errores y logging automático
     * @param locator Localizador del elemento
     * @param value Valor a ingresar
     * @param fieldDescription Descripción del campo para logs y manejo de errores
     */
    public void fillTextField(By locator, String value, String fieldDescription) {
        try {
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
            field.clear();
            field.sendKeys(value);
        } catch (Exception e) {
            handleFieldError(fieldDescription, e);
        }
    }

    /**
     * Llena un campo de texto
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
     * Hace clic en un elemento de forma segura con manejo de errores y logging automático
     * @param locator Localizador del elemento
     * @param elementDescription Descripción del elemento para logs y manejo de errores
     */
    public void clickElement(By locator, String elementDescription) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            handleClickError(elementDescription, e);
        }
    }

    /**
     * Intenta hacer clic en un elemento con timeout corto
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo de espera personalizado
     * @param elementDescription Descripción del elemento
     * @return true si pudo hacer clic, false si el elemento no existe
     */
    public boolean tryClickElement(By locator, int timeoutSeconds, String elementDescription) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            WebElement element = shortWait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            return true; // Éxito
        } catch (TimeoutException e) {
            // No lanzar error - simplemente retornar false
            return false;
        }
    }

    /**
     * Verifica si un elemento es clickeable (sin hacer clic)
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo de espera
     * @return true si es clickeable, false si no
     */
    public boolean isElementClickable(By locator, int timeoutSeconds) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            shortWait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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
    public void selectDropdown(By locator, String visibleText) {
        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(selectElement);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selecciona una opción en un select HTML estándar por texto visible
     * @param locator Localizador del select
     * @param visibleText Texto visible de la opción
     */
    public void selectDropdownByText(By locator, String visibleText) {
        try {
            WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
            Select select = new Select(selectElement);
            List<WebElement> options = select.getOptions();

            String normalizedSearchText = normalizeText(visibleText);

            // Estrategia 1: Coincidencia exacta (comportamiento original)
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(visibleText)) {
                    select.selectByVisibleText(option.getText().trim());
                    return;
                }
            }

            // Estrategia 2: Coincidencia sin acentos
            for (WebElement option : options) {
                String optionText = option.getText().trim();
                if (normalizeText(optionText).equals(normalizedSearchText)) {
                    select.selectByVisibleText(optionText);
                    return;
                }
            }

            // Si no encuentra nada, lanzar error con opciones disponibles
            StringBuilder availableOptions = new StringBuilder();
            for (int i = 0; i < Math.min(options.size(), 5); i++) {
                availableOptions.append("'").append(options.get(i).getText().trim()).append("'");
                if (i < Math.min(options.size(), 5) - 1) availableOptions.append(", ");
            }

            throw new RuntimeException("No se encontró '" + visibleText + "' en el dropdown. " +
                    "Opciones disponibles: " + availableOptions.toString());

        } catch (Exception e) {
            handleFieldError("dropdown", e);
        }
    }

    private String normalizeText(String text) {
        if (text == null) return "";
        String normalized = Normalizer.normalize(text.toLowerCase().trim(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
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

    /**
     * Convierte número de mes a nombre de mes
     * @param monthNumber Número del mes (1-12 o 01-12)
     * @return Nombre del mes en inglés
     */
    private String convertMonthNumberToName(String monthNumber) {
        int month = Integer.parseInt(monthNumber);
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        if (month >= 1 && month <= 12) {
            return months[month - 1];
        }
        throw new IllegalArgumentException("Número de mes inválido: " + monthNumber);
    }

    /**
     * Completa los campos de fecha de nacimiento con conversión automática de mes
     * @param dayLocator Localizador del dropdown del día
     * @param monthLocator Localizador del dropdown del mes
     * @param yearLocator Localizador del dropdown del año
     * @param day Día (ej: "17")
     * @param month Mes como número (ej: "11") o nombre (ej: "November")
     * @param year Año (ej: "2010")
     * @param fieldDescription Descripción para logs
     */
    public void fillDateOfBirth(By dayLocator, By monthLocator, By yearLocator, String day, String month, String year, String fieldDescription) {
        try {
            // Convertir mes si es número
            String monthToUse = month;
            if (month.matches("\\d{1,2}")) { // Si es número
                monthToUse = convertMonthNumberToName(month);
            }

            selectDropdownByText(dayLocator, day);
            selectDropdownByText(monthLocator, monthToUse);
            selectDropdownByText(yearLocator, year);
        } catch (Exception e) {
            handleFieldError(fieldDescription, e);
        }
    }

    // ==================== NAVEGACIÓN Y SCROLL ====================

    /**
     * Hace scroll hacia abajo en la página usando PAGE_DOWN
     */
    public void scrollDown() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    /**
     * Hace scroll hacia arriba en la página usando PAGE_UP
     */
    public void scrollUp() {
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    /**
     * Hace scroll suave hacia abajo con JavaScript
     * @param pixels Cantidad de píxeles a hacer scroll
     */
    public void smoothScrollDown(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo({top: window.scrollY + " + pixels + ", behavior: 'smooth'});");
    }

    /**
     * Hace scroll hacia un elemento específico
     * @param locator Localizador del elemento
     */
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    /**
     * Hace scroll hacia un elemento específico sin esperar
     * @param locator Localizador del elemento
     */
    public void scrollToElementImmediate(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Hace scroll usando JavaScript con control total
     * @param pixels Cantidad de píxeles a hacer scroll (positivo = abajo, negativo = arriba)
     */
    public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixels + ");");
    }

    /**
     * Hace scroll al final de la página
     */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Hace scroll al inicio de la página
     */
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Hace scroll dentro de un elemento específico (útil para divs con scroll)
     * @param containerLocator Localizador del contenedor con scroll
     * @param pixels Píxeles a hacer scroll dentro del contenedor
     */
    public void scrollInsideElement(By containerLocator, int pixels) {
        WebElement container = driver.findElement(containerLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += " + pixels + ";", container);
    }

    /**
     * Verifica si un elemento está visible en el viewport
     * @param locator Localizador del elemento
     * @return true si está visible, false si no
     */
    public boolean isElementInViewport(By locator) {
        WebElement element = driver.findElement(locator);
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                element
        );
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        try {
            // Custom condition que espera aparición y luego desaparición
            return wait.until(webDriver -> {
                List<WebElement> elements = webDriver.findElements(locator);

                // Si no hay elementos o no están visibles, aún esperando que aparezca
                if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                    return false;
                }

                // El elemento apareció, ahora esperar un momento y verificar si desapareció
                try {
                    Thread.sleep(100); // Pequeña pausa para detectar desaparición
                    elements = webDriver.findElements(locator);
                    return elements.isEmpty() || !elements.get(0).isDisplayed();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            });
        } catch (TimeoutException e) {
            return false;
        }
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

//    public boolean waitForElementToAppear(By locator, int timeoutSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            return true;
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }

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

    /**
     * Verifica que un elemento NO esté presente usando WebDriverWait (mejor práctica)
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera
     * @return true si NO está presente, false si está presente
     */
    public boolean verifyElementIsNotPresent(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            // Usar ExpectedConditions predefinido
            customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica que un elemento NO esté visible usando WebDriverWait (mejor práctica)
     * @param locator Localizador del elemento
     * @param timeoutSeconds Tiempo máximo de espera
     * @return true si NO está visible, false si está visible
     */
    public boolean verifyElementIsNotVisible(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            // Esperar hasta que el elemento sea invisible
            customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true; // No está visible
        } catch (TimeoutException e) {
            return false; // Todavía está visible después del timeout
        }
    }

    /**
     * Valida el resultado de una acción esperando error o éxito
     * @param errorLocator Localizador del mensaje de error
     * @param successLocator Localizador del elemento que indica éxito (puede ser null)
     * @param timeoutSeconds Tiempo máximo de espera
     * @param actionDescription Descripción de la acción para logs
     * @return true si fue exitoso, lanza excepción si hay error
     */
    public boolean validateActionResult(By errorLocator, By successLocator, int timeoutSeconds, String actionDescription) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        try {
            if (successLocator != null) {
                // Esperar por error O éxito
                shortWait.until(ExpectedConditions.or(
                        ExpectedConditions.visibilityOfElementLocated(errorLocator),
                        ExpectedConditions.visibilityOfElementLocated(successLocator)
                ));
            } else {
                // Solo esperar por error
                try {
                    shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
                } catch (TimeoutException e) {
                    // Si no hay error en el tiempo especificado, asumir éxito
                    return true;
                }
            }
        } catch (TimeoutException e) {
            // Si no aparece nada, asumir éxito
            return true;
        }

        // Verificar si hay error
        List<WebElement> errors = driver.findElements(errorLocator);
        if (!errors.isEmpty() && errors.get(0).isDisplayed()) {
            String errorText = errors.get(0).getText();
            String fullErrorMessage = actionDescription + " falló: " + errorText;
            BaseTest.processBuffer(COMMIT_MERGED_FAILURE, fullErrorMessage, true);
            throw new RuntimeException(fullErrorMessage);
        }

        return true; // Éxito
    }

    /**
     * Versión simplificada solo para verificar errores
     * @param errorLocator Localizador del mensaje de error
     * @param timeoutSeconds Tiempo máximo de espera
     * @param actionDescription Descripción de la acción para logs
     * @return true si fue exitoso, lanza excepción si hay error
     */
    public boolean validateActionResult(By errorLocator, int timeoutSeconds, String actionDescription) {
        return validateActionResult(errorLocator, null, timeoutSeconds, actionDescription);
    }

    /**
     * Versión con timeout por defecto
     * @param errorLocator Localizador del mensaje de error
     * @param actionDescription Descripción de la acción para logs
     * @return true si fue exitoso, lanza excepción si hay error
     */
    public boolean validateActionResult(By errorLocator, String actionDescription) {
        return validateActionResult(errorLocator, null, 2, actionDescription);
    }

    /**
     * Obtiene el texto de error de forma segura
     * @param errorLocator Localizador del mensaje de error
     * @return Texto del error o mensaje por defecto si no se encuentra
     */
    public String getErrorMessageSafe(By errorLocator) {
        try {
            List<WebElement> errors = driver.findElements(errorLocator);
            if (!errors.isEmpty() && errors.get(0).isDisplayed()) {
                return errors.get(0).getText();
            }
            return "No se encontró mensaje de error";
        } catch (Exception e) {
            return "Error al obtener mensaje: " + e.getMessage();
        }
    }

    /**
     * Verifica si un elemento de error está visible inmediatamente
     * @param errorLocator Localizador del mensaje de error
     * @return true si el error está visible, false si no
     */
    public boolean isErrorVisible(By errorLocator) {
        try {
            List<WebElement> errors = driver.findElements(errorLocator);
            return !errors.isEmpty() && errors.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
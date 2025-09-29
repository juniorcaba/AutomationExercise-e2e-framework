package exceptions;

/**
 * Excepción lanzada cuando una página web completa no carga correctamente.
 *
 * Esta excepción indica que la página en su totalidad falló al cargar,
 * ya sea por timeout, elementos clave ausentes, o navegación a URL incorrecta.
 *
 * Cuándo usar:
 * - La página no carga dentro del tiempo esperado
 * - Múltiples elementos clave de la página están ausentes
 * - La URL no es la esperada después de una navegación
 * - Timeout general al cargar la página
 * - La página muestra error 404, 500, etc.
 *
 * Cuándo NO usar:
 * - Solo un elemento individual no está visible (usar ElementNotVisibleException)
 * - Error específico de signup (usar SignupFailedException)
 * - Error de autenticación (usar InvalidCredentialsException)
 *
 * Ejemplo:
 *   driver.get(BASE_URL);
 *   if (!helpers.waitForElementToAppear(homePageLogo, 10)) {
 *       throw new PageNotLoadedException("Home page no cargó", driver.getCurrentUrl());
 *   }
 *
 * @author Hector Caba
 * @since 1.0
 */
public class PageNotLoadedException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message mensaje describiendo el fallo de carga
     */
    public PageNotLoadedException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción incluyendo la URL actual para debugging.
     *
     * @param message mensaje describiendo el fallo de carga
     * @param currentUrl la URL actual del navegador cuando ocurrió el error
     */
    public PageNotLoadedException(String message, String currentUrl) {
        super(message + " - Current URL: " + currentUrl);
    }

    /**
     * Construye una nueva excepción con mensaje y causa.
     *
     * @param message mensaje describiendo el fallo de carga
     * @param cause la causa raíz de la excepción
     */
    public PageNotLoadedException(String message, Throwable cause) {
        super(message, cause);
    }
}
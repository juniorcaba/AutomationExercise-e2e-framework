package exceptions;

/**
 * Excepción lanzada cuando un elemento esperado no es visible en la página web.
 * Esta excepción se usa cuando un elemento individual específico no aparece
 * o no es visible dentro del tiempo de espera establecido.
 * Cuándo usar:
 * - Un botón esperado no es visible
 * - Un mensaje de confirmación no aparece
 * - Un link o elemento de navegación no está presente
 * - Verificaciones de elementos individuales en la página
 * Cuándo NO usar:
 * - Cuando la página completa no carga (usar PageNotLoadedException)
 * - Cuando un proceso de signup falla (usar SignupFailedException)
 * - Cuando las credenciales son inválidas (usar InvalidCredentialsException)
 * ¡Ejemplo:
 * if (! Helpers.isElementPresent(userMenuLocator, 5)) {
 * throw new ElementNotVisibleException("Menú de usuario no visible");
 * }
 *
 * @author Hector
 * @since 1.0
 */

public class ElementNotVisibleException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message mensaje detallando qué elemento no fue visible
     */
    public ElementNotVisibleException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción con mensaje y causa.
     *
     * @param message mensaje detallando qué elemento no fue visible
     * @param cause   la causa raíz de la excepción
     */
    public ElementNotVisibleException(String message, Throwable cause) {
        super(message, cause);
    }
}


package exceptions;

/**
 * Excepción lanzada cuando las credenciales de autenticación son inválidas.
 *
 * Esta excepción se usa específicamente para errores de login/autenticación
 * cuando el usuario o contraseña son incorrectos.
 *
 * Cuándo usar:
 * - Usuario y/o contraseña incorrectos
 * - Cuenta bloqueada o deshabilitada
 * - Sesión expirada y requiere re-autenticación
 * - Token de autenticación inválido
 * - Credenciales no reconocidas por el sistema
 *
 * Cuándo NO usar:
 * - Para errores de signup/registro (usar SignupFailedException)
 * - Para elementos no visibles (usar ElementNotVisibleException)
 * - Para páginas que no cargan (usar PageNotLoadedException)
 * - Para errores de validación de formularios (usar SignupFailedException)
 *
 * Ejemplo:
 *   fillLoginEmail(email);
 *   fillLoginPassword(password);
 *   clickLoginButton();
 *   if (helpers.waitForElementToAppear(loginErrorMessage, 3)) {
 *       throw new InvalidCredentialsException("Login falló: credenciales inválidas");
 *   }
 *
 * @author Tu nombre
 * @since 1.0
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message mensaje describiendo el fallo de autenticación
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción con mensaje y causa.
     *
     * @param message mensaje describiendo el fallo de autenticación
     * @param cause la causa raíz de la excepción
     */
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
package exceptions;

/**
 * Excepción lanzada cuando el proceso de registro (signup) de usuario falla.
 *
 * Esta excepción se usa específicamente para errores relacionados con el
 * proceso de creación de cuenta o registro de nuevos usuarios.
 *
 * Cuándo usar:
 * - Email ya existe en el sistema
 * - Validaciones del formulario de signup fallan
 * - Error del servidor durante el registro
 * - Contraseña no cumple requisitos
 * - Campos requeridos faltantes en signup
 * - Captcha o verificación falla
 *
 * Cuándo NO usar:
 * - Para errores de login (usar InvalidCredentialsException)
 * - Para elementos no visibles (usar ElementNotVisibleException)
 * - Para páginas que no cargan (usar PageNotLoadedException)
 *
 * Ejemplo:
 *   if (helpers.waitForElementToAppear(signupErrorMessage, 3)) {
 *       String errorText = helpers.getElementText(signupErrorMessage);
 *       throw new SignupFailedException("Signup falló: " + errorText);
 *   }
 *
 * @author Tu nombre
 * @since 1.0
 */
public class SignupFailedException extends RuntimeException {

    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message mensaje describiendo por qué falló el signup
     */
    public SignupFailedException(String message) {
        super(message);
    }

    /**
     * Construye una nueva excepción con mensaje y causa.
     *
     * @param message mensaje describiendo por qué falló el signup
     * @param cause la causa raíz de la excepción
     */
    public SignupFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
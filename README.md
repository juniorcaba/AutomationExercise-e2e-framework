# AutomationExercise E2E Framework

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green.svg)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

Framework de automatización end-to-end para [AutomationExercise.com](https://automationexercise.com/), desarrollado con Selenium WebDriver, Java y TestNG. Implementa el patrón Page Object Model con Fluent Interface para crear pruebas robustas y mantenibles.

## 🎯 Características principales

- **Page Object Model (POM)** con Fluent Interface
- **Manejo robusto de errores** con try-catch y validaciones
- **Reporting avanzado** con ExtentReports
- **WebDriverManager** para gestión automática de drivers
- **Helpers reutilizables** para operaciones comunes
- **Configuración flexible** mediante archivos properties
- **Soporte multi-browser** (Chrome, Firefox, Edge)
- **Integración con CI/CD**

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 21 | Lenguaje de programación |
| Selenium WebDriver | 4.15.0 | Automatización web |
| TestNG | 7.8.0 | Framework de testing |
| ExtentReports | 5.1.1 | Generación de reportes |
| WebDriverManager | 5.6.2 | Gestión de drivers |
| Maven | 3.6+ | Gestión de dependencias |

## 📁 Estructura del proyecto

```
src/
├── main/java/
│   ├── basetest/
│   │   └── BaseTest.java          # Configuración base para tests
│   ├── pages/
│   │   ├── BasePage.java          # Clase base para páginas
│   │   ├── HomePage.java          # Página principal
│   │   ├── SignupLoginPage.java   # Página de registro/login
│   │   └── CreateAccountPage.java # Página de creación de cuenta
│   └── utils/
│       └── WebHelpers.java        # Utilidades web reutilizables
└── test/java/
    └── tests/
        └── authentication/
            └── RegistrationTests.java # Tests de registro
```

## 🚀 Configuración inicial

### Prerrequisitos
- Java JDK 21 o superior
- Maven 3.6 o superior
- Git

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/juniorcaba/AutomationExercise-e2e-framework.git
   cd AutomationExercise-e2e-framework
   ```

2. **Instalar dependencias:**
   ```bash
   mvn clean install
   ```

3. **Verificar instalación:**
   ```bash
   mvn test -Dtest=RegistrationTests#testRegisterUser
   ```

## ▶️ Ejecución de pruebas

### Ejecutar todas las pruebas
```bash
mvn test
```

### Ejecutar pruebas específicas
```bash
# Ejecutar una clase de pruebas
mvn test -Dtest=RegistrationTests

# Ejecutar un método específico
mvn test -Dtest=RegistrationTests#testRegisterUser

# Ejecutar con browser específico
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Ejecutar en modo headless
```bash
mvn test -Dheadless=true
```

## 📊 Reportes

Los reportes se generan automáticamente después de cada ejecución:

- **Ubicación:** `test-output/ExtentReports/`
- **Formato:** HTML con screenshots
- **Contenido:** Pasos detallados, screenshots en fallos, logs de ejecución

### Ejemplo de reporte
```
✅ Test: Register User
├── ✅ Navegando a página principal
├── ✅ Verificando carga de página
├── ✅ Haciendo clic en Signup/Login
├── ✅ Ingresando nombre: Mateo
├── ✅ Ingresando email: emaildeprueba@test.com
├── ✅ Haciendo clic en Signup
└── ✅ Seleccionando título: Mr.
```

## 🧪 Ejemplos de uso

### Test básico de registro
```java
@Test(description = "Test Case 1: Register User")
public void testRegisterUser(){
    new HomePage(getDriver())
            .navigateToHome()
            .verifyHomePageLoaded()
            .clickSignupLogin()
            .fillSignupName("Mateo")
            .fillSignupEmail("emaildeprueba@test.com")
            .clickSignupButton()
            .selectTitle("Mr.")
            .fillPassword("password123");
}
```

### Test con validaciones
```java
@Test(description = "Test Case 2: Register User with Validation")
public void testRegisterUserWithValidation(){
    new HomePage(getDriver())
            .navigateToHome()
            .verifyHomePageLoaded()
            .clickSignupLogin()
            .fillSignupName("Ana García")
            .fillSignupEmail("ana@test.com")
            .clickSignupButton()
            .verifyCreateAccountPageLoaded()
            .selectTitleWithValidation("Mrs.")
            .verifyTitleSelected("Mrs.");
}
```

## 🏗️ Arquitectura del framework

### Patrón Page Object Model
Cada página web está representada por una clase Java que encapsula:
- Localizadores de elementos
- Métodos de interacción
- Validaciones específicas

### Fluent Interface
Permite encadenar métodos para crear tests legibles:
```java
page.action1().action2().validation1().action3();
```

### WebHelpers
Clase utilitaria que centraliza operaciones comunes:
- Llenar campos de texto
- Hacer clics seguros
- Esperas inteligentes
- Manejo de errores
- Scroll y navegación

### Manejo de errores robusto
- Try-catch en todas las interacciones críticas
- Mensajes descriptivos en fallos
- Screenshots automáticos en errores
- Logging detallado para debugging

## 📈 Casos de prueba cubiertos

### Módulo de Autenticación
- [x] Registro de usuario nuevo
- [x] Validación de campos obligatorios
- [x] Selección de opciones (título, newsletter)
- [ ] Login con credenciales válidas
- [ ] Validación de email duplicado

### Módulo de E-commerce
- [ ] Agregar productos al carrito
- [ ] Proceso de checkout
- [ ] Gestión de cuenta de usuario

## 🤝 Contribuir

1. **Fork** el repositorio
2. **Crea** una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit** tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. **Push** a la rama (`git push origin feature/nueva-funcionalidad`)
5. **Crea** un Pull Request

### Estándares de código
- Seguir convenciones de nomenclatura Java
- Documentar métodos públicos con JavaDoc
- Incluir manejo de errores apropiado
- Mantener el patrón Fluent Interface
- Agregar tests para nuevas funcionalidades

## 📝 Configuración adicional

### Propiedades del sistema
```properties
# Browser configuration
browser=chrome
headless=false
implicit.wait=10
explicit.wait=30

# Application URLs
base.url=https://automationexercise.com
api.url=https://automationexercise.com/api
```

### Variables de entorno
```bash
export BROWSER=chrome
export HEADLESS=false
export BASE_URL=https://automationexercise.com
```

## 🐛 Troubleshooting

### Problemas comunes

**Error: WebDriver no encontrado**
```bash
# Solución: WebDriverManager se encarga automáticamente
# Verificar conexión a internet
```

**Error: Elemento no encontrado**
```bash
# Verificar localizadores en las páginas
# Aumentar tiempos de espera si es necesario
```

**Error: Tests fallan aleatoriamente**
```bash
# Revisar waits implícitos y explícitos
# Verificar estabilidad de elementos en la página
```

## 📞 Soporte

- **Issues:** [GitHub Issues](https://github.com/juniorcaba/AutomationExercise-e2e-framework/issues)
- **Email:** [tu-email@ejemplo.com]
- **Documentación:** [Wiki del proyecto](https://github.com/juniorcaba/AutomationExercise-e2e-framework/wiki)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

**Desarrollado con ❤️ para la comunidad de QA Automation**

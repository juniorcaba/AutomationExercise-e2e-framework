# AutomationExercise E2E Framework

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green.svg)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

Framework de automatización para pruebas E2E del sitio [AutomationExercise.com](https://automationexercise.com), implementado con Selenium WebDriver, Java y TestNG.

## Arquitectura

El proyecto utiliza **Page Object Model** con **Fluent Interface** para crear pruebas mantenibles y escalables. Cada página tiene su propia clase con métodos que permiten encadenar acciones de forma natural.

```java
// Ejemplo de Fluent Interface
HomePage(getDriver())
                .navigateToHome()
                .verifyHomePageLoaded()
                .clickSignupLogin()
```

## Stack Tecnológico

- **Java 21** - Lenguaje base
- **Selenium WebDriver 4.15.0** - Automatización del navegador
- **TestNG 7.8.0** - Framework de pruebas y organización
- **ExtentReports 5.1.1** - Reportes visuales detallados
- **WebDriverManager 5.6.2** - Gestión automática de drivers
- **Maven** - Gestión de dependencias y construcción

## Funcionalidades Cubiertas

- ✅ Registro de nuevos usuarios
- ✅ Login/Logout de usuarios existentes
- ✅ Navegación por catálogo de productos
- ✅ Gestión del carrito de compras
- ✅ Proceso completo de checkout
- ✅ Validaciones de UI y funcionalidad

## Instalación y Configuración

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+
- Git

### Configuración inicial

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/automation-exercise-framework.git
   cd automation-exercise-framework
   ```

2. **Instalar dependencias**
   ```bash
   mvn clean install
   ```

3. **Configurar navegador** (opcional)
   
   Edita `src/main/resources/config.properties`:
   ```properties
   browser=chrome  # Opciones: chrome, firefox, edge
   base.url=https://automationexercise.com
   implicit.wait=10
   ```

## Ejecución

### Ejecutar todas las pruebas
```bash
mvn clean test
```

### Ejecutar pruebas específicas
```bash
# Por clase
mvn test -Dtest=LoginTest

# Por método
mvn test -Dtest=LoginTest#testValidLogin

# Por grupos de TestNG
mvn test -Dgroups=smoke
```

### Parámetros de ejecución
```bash
# Cambiar navegador
mvn test -Dbrowser=firefox

# Ejecución en modo headless
mvn test -Dheadless=true
```

## Estructura del Proyecto

```
src/
├── main/java/
│   ├── pages/          # Page Objects
│   ├── utils/          # Helpers y utilidades
│   └── config/         # Configuraciones
├── test/java/
│   ├── tests/          # Casos de prueba
│   └── base/           # Clases base para pruebas
└── resources/
    ├── config.properties
    └── testng.xml
```

## Características Destacadas

### Manejo Robusto de Errores
Cada acción incluye validaciones y manejo de excepciones para garantizar estabilidad.

### Reporting Detallado
Los reportes incluyen screenshots automáticos en caso de fallo y logging paso a paso.

### Configuración Flexible
Parámetros centralizados en archivos `.properties` para fácil mantenimiento.

### Integración CI/CD Ready
Compatible con Jenkins, GitHub Actions y otros sistemas de integración continua.

## Reportes

Los reportes se generan automáticamente en:
- `target/extent-reports/` - Reportes HTML detallados
- `target/screenshots/` - Screenshots de fallos

## Contribución

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## Contacto

**Héctor** - Automation Engineer

¿Tienes preguntas o sugerencias? No dudes en abrir un issue o contactarme directamente.

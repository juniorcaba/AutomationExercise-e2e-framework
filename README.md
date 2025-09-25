# AutomationExercise E2E Framework

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green.svg)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

Este repositorio contiene las pruebas automatizadas para el sitio [AutomationExercise.com](https://automationexercise.com). El objetivo es validar los flujos principales del sitio usando Selenium WebDriver con Java y TestNG.

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

## ¿Qué incluye?

- Pruebas de registro de usuario
- Login y logout
- Navegación por productos
- Agregar al carrito y proceso de compra
- Validaciones básicas de UI
- 
## ⚙️ Configuración

- El navegador por defecto es Chrome. Puedes cambiarlo en `config.properties`.
- Las URLs, credenciales y parámetros están centralizados en `ConfigReader`.

## ▶️ Cómo ejecutar

1. Clona el repositorio
2. Asegúrate de tener Java y Maven instalados
3. Ejecuta:  
   ```bash
   mvn clean test

 📌 Notas adicionales
- El framework usa Fluent Interface para encadenar acciones y mejorar la legibilidad.
- Cada Page Object incluye validaciones como verifyPageLoaded() para asegurar estabilidad.
- Los pasos se registran visualmente con BaseTest.createStep() para trazabilidad.


👨‍💻 Autor
Este proyecto fue creado por Héctor como parte de un enfoque técnico para automatización modular, trazable y escalable.
Se aceptan sugerencias, mejoras y pull requests.


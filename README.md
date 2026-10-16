# 🛒 WebSwagLabsAutomatorMaven — Serenity BDD

> Framework de automatización de pruebas **End-to-End (E2E)** para la aplicación web [Swag Labs](https://www.saucedemo.com/) usando **Serenity BDD**, **Cucumber** y **JUnit**, con **Maven** como gestor de construcción.

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.13.2-6DB33F)](https://serenity-bdd.github.io/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.x-23D96C?logo=cucumber&logoColor=white)](https://cucumber.io/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13.2-25A162)](https://junit.org/junit4/)
[![License](https://img.shields.io/badge/license-ISC-blue.svg)](#-licencia)

> 🚧 **Proyecto en desarrollo activo.** Se irán agregando nuevos escenarios y páginas a medida que avance la automatización.

---

## 📋 Tabla de contenidos

- [Descripción](#-descripción)
- [Escenarios automatizados](#-escenarios-automatizados)
- [Stack tecnológico](#-stack-tecnológico)
- [Arquitectura del framework](#-arquitectura-del-framework)
- [Requisitos previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Cómo ejecutar los tests](#-cómo-ejecutar-los-tests)
- [Reportes de Serenity](#-reportes-de-serenity)
- [Convenciones y buenas prácticas](#-convenciones-y-buenas-prácticas)
- [Roadmap](#-roadmap)
- [Autor](#-autor)
- [Licencia](#-licencia)

---

## 📖 Descripción

Este repositorio contiene un **framework de automatización web** construido sobre **Serenity BDD**, un marco de trabajo que combina **Cucumber** (para escribir escenarios en lenguaje natural/Gherkin) con **JUnit** (para la ejecución de los tests) y genera **reportes ricos en detalles** con capturas de pantalla, pasos ejecutados y tiempos.

El objetivo es validar los flujos críticos de la aplicación **Swag Labs** (login y carrito de compras) de forma reproducible, mantenible y con reportes de alta calidad.

**Características principales:**

- ✅ **BDD con Gherkin**: escenarios legibles por negocio y técnicos, en español.
- ✅ **Patrón Page Object Model (POM)**: separación entre páginas, steps y definiciones.
- ✅ **WebDriverManager**: descarga y gestión automática del driver de Chrome.
- ✅ **Reportes HTML de Serenity**: con screenshots, pasos y resultados detallados.
- ✅ **Configuración externalizada** en `serenity.properties`.
- ✅ **Runner centralizado** con tags de Cucumber para ejecución selectiva.

---

## 🧪 Escenarios automatizados

| Feature | Archivo | Escenarios | Tags |
|---|---|---|---|
| **CP01 — Login** | `1-login.feature` | 1. Credenciales válidas → módulo de productos<br>2. Credenciales inválidas → mensaje de error | `@Suite` `@Login` `@ValidCredentials` `@InvalidCredentials` |
| **CP02 — Carrito de compras** | `2-products.feature` | 1. Listar productos en el carrito<br>2. Carrito vacío → no procesar compra<br>3. Procesar compra → mensaje de éxito | `@Suite` `@ShoppingCart` `@ShoppingCartList` `@ShoppingCartEmpty` `@ShoppingCartProcess` |

**Ejemplo de escenario (Gherkin):**

```gherkin
@Suite @Login
Feature: CP01 - Validar inicio de sesion

  Background:
    Given el usuario navega al sitio web

  @ValidCredentials
  Scenario: 1 - Validar con credenciales correctas
    When ingresa credenciales validas
    Then la aplicacion deberia mostrar el modulo principal de productos

  @InvalidCredentials
  Scenario: 2 - Validar con credenciales incorrectas
    When ingresa credenciales invalidas
    Then la aplicacion deberia mostrar un mensaje de error
```

---

## 🛠 Stack tecnológico

| Herramienta | Versión | Rol |
|---|---|---|
| [Java](https://www.oracle.com/java/) | `17` | Lenguaje de programación |
| [Maven](https://maven.apache.org/) | `3.9+` | Gestor de dependencias y build |
| [Serenity BDD](https://serenity-bdd.github.io/) | `4.13.2` | Framework de automatización y reportes |
| [Cucumber](https://cucumber.io/) | `7.x` (incluido en Serenity) | Motor BDD y ejecución de escenarios |
| [JUnit](https://junit.org/junit4/) | `4.13.2` | Plataforma de ejecución de tests |
| [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) | `5.8.0` | Gestión automática de drivers |
| [SLF4J Simple](https://www.slf4j.org/) | `2.0.7` | Logging |

---

## 🏗 Arquitectura del framework

El framework sigue el patrón **Page Object Model (POM)** adaptado a Serenity BDD:

```
┌─────────────────────────────────────────────┐
│         Features (.feature)                  │  ← Gherkin: lenguaje de negocio
├─────────────────────────────────────────────┤
│      Step Definitions (definitions/)         │  ← Traducción Gherkin → Java
├─────────────────────────────────────────────┤
│          Steps (steps/)                      │  ← Tareas de alto nivel (Serenity)
├─────────────────────────────────────────────┤
│          Pages (pages/)                      │  ← Page Objects (mapeo de UI)
├─────────────────────────────────────────────┤
│     Utilities (utilities/website/)           │  ← Helpers, config, constantes
└─────────────────────────────────────────────┘
```

**Flujo de ejecución:**

1. El **Runner** (`Runner.java`) carga las features desde `src/test/resources/features`.
2. Cucumber busca los **step definitions** en `com.swag.co.definitions`.
3. Cada step ejecuta acciones sobre los **Page Objects** (`pages/`) o **Steps** (`steps/`).
4. Serenity intercepta las acciones de WebDriver y genera los reportes automáticamente.
5. Al finalizar, se genera el **reporte HTML** en `target/site/serenity/`.

---

## ✅ Requisitos previos

| Herramienta | Verificar con |
|---|---|
| Java 17 (JDK) | `java -version` |
| Maven 3.9+ | `mvn -version` |
| Google Chrome (última versión) | — |
| Git | `git --version` |

> 💡 **Nota:** El proyecto usa **WebDriverManager**, por lo que **no necesitas descargar manualmente el ChromeDriver**. Se gestiona automáticamente al ejecutar los tests.

---

## 📦 Instalación

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/Hsolorzano1987/WebSwagLabsAutomatorMaven.git
   cd WebSwagLabsAutomatorMaven
   ```

2. **Compilar el proyecto** (descarga dependencias)

   ```bash
   mvn clean compile
   ```

3. **Verificar que todo esté listo**

   ```bash
   mvn clean test -DskipTests=false
   ```

---

## ⚙️ Configuración

La configuración principal vive en `serenity.properties`:

| Propiedad | Valor | Descripción |
|---|---|---|
| `serenity.project.name` | `SWAG LABS` | Nombre del proyecto en reportes |
| `serenity.test.root` | `org.example` | Paquete raíz de tests |
| `webdriver.driver` | `chrome` | Navegador a usar |
| `webdriver.autodownload` | `true` | Descarga automática del driver |
| `serenity.browser.maximized` | `true` | Maximizar ventana al iniciar |
| `serenity.use.unique.browser` | `false` | Reutilizar instancia de navegador |
| `serenity.restart.browser.each.scenario` | `true` | Reiniciar navegador por escenario |

### Flags de Chrome configurados

El proyecto incluye flags para evitar mensajes molestos y comportamientos no deseados durante la automatización:

```
--no-sandbox
--disable-dev-shm-usage
--disable-infobars
--disable-extensions
--remote-allow-origins=*
--disable-save-password-bubble
--disable-notifications
--disable-popup-blocking
--disable-blink-features=AutomationControlled
```

También se desactivan:
- El administrador de contraseñas de Chrome.
- Las alertas de brechas de seguridad.

---

## 🗂 Estructura del proyecto

```
WebSwagLabsAutomatorMaven/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/swag/co/
│       │       ├── definitions/          # Step Definitions (Gherkin → Java)
│       │       ├── pages/                # Page Objects (mapeo de UI)
│       │       ├── steps/                # Tareas de alto nivel (Serenity Steps)
│       │       ├── utilities/website/    # Helpers y configuración web
│       │       └── Runner.java           # Clase runner de Cucumber + Serenity
│       └── resources/
│           └── features/                 # Archivos .feature (Gherkin)
│               ├── 1-login.feature
│               └── 2-products.feature
├── .gitignore
├── pom.xml
├── serenity.properties
└── README.md
```

| Carpeta / Archivo | Propósito |
|---|---|
| `definitions/` | Implementación Java de cada paso Gherkin |
| `pages/` | Clases que representan las páginas web (Page Object Model) |
| `steps/` | Tareas de alto nivel que agrupan acciones sobre páginas |
| `utilities/website/` | Funciones auxiliares, constantes, configuración |
| `Runner.java` | Clase que lanza la ejecución de Cucumber con Serenity |
| `features/` | Escenarios en Gherkin organizados por caso de prueba |

---

## ▶️ Cómo ejecutar los tests

### Ejecutar toda la suite

```bash
mvn clean verify
```

> 💡 `verify` ejecuta los tests **y** genera el reporte de Serenity automáticamente.

### Ejecutar solo los tests (sin reporte completo)

```bash
mvn clean test
```

### Ejecutar por tags de Cucumber

El `Runner.java` está configurado con `tags = "@Suite"` por defecto. Para ejecutar otros tags:

```bash
mvn clean verify -Dcucumber.filter.tags="@Login"
```

```bash
mvn clean verify -Dcucumber.filter.tags="@ShoppingCart"
```

```bash
mvn clean verify -Dcucumber.filter.tags="@ValidCredentials"
```

### Ejecutar un feature específico

```bash
mvn clean verify -Dcucumber.features="src/test/resources/features/1-login.feature"
```

### Ejecutar sin generar reporte (más rápido)

```bash
mvn clean test -Dserenity.report.skip=true
```

---

## 📊 Reportes de Serenity

Serenity genera **reportes HTML interactivos** con:

- ✅ Resultado por escenario (passed / failed / skipped).
- 📸 **Screenshots automáticos** de cada paso.
- 📝 Detalle paso a paso de lo que ejecutó el framework.
- ⏱ Tiempos por escenario y por paso.
- 🐛 Stack traces en caso de fallos.

### Cómo ver el reporte

Después de ejecutar `mvn clean verify`, abrí:

```
target/site/serenity/index.html
```

En Windows:

```powershell
start target/site/serenity/index.html
```

En Mac/Linux:

```bash
open target/site/serenity/index.html
```

> 📌 **Nota:** Los reportes de Serenity se generan en `target/site/serenity/` y están **ignorados por git** (la carpeta `target/` está en el `.gitignore`).

---

## 🧭 Convenciones y buenas prácticas

- 🏷 **Tags de Cucumber**: usar `@Suite` como tag raíz, y tags específicos por feature (`@Login`, `@ShoppingCart`) y por escenario (`@ValidCredentials`, `@InvalidCredentials`).
- 🧩 **Page Object Model**: cada página web tiene su clase en `pages/` con localizadores y métodos de acción.
- 📝 **Nombres de escenarios** descriptivos y en lenguaje de negocio (español).
- 🚫 **No commitear** `target/`, `*.iml`, `.idea/` (ya están en `.gitignore`).
- 🔒 **Nunca** subir credenciales reales al repositorio.
- ✅ **Ejecutar localmente** antes de hacer push (`mvn clean verify`).
- 📂 **Un feature por caso de prueba** (`1-login.feature`, `2-products.feature`).

---

## 🗺 Roadmap

> 🚧 Proyecto **en desarrollo activo**.

- [x] Configuración inicial del proyecto (Serenity BDD + Cucumber + JUnit + Maven).
- [x] Feature de Login (credenciales válidas e inválidas).
- [x] Feature de Carrito de compras (listar, vaciar, procesar).
- [x] Configuración de Chrome con flags de automatización.
- [x] Reportes HTML de Serenity.
- [ ] Agregar más casos de prueba (checkout, perfil, etc.).
- [ ] Integración con CI/CD (GitHub Actions).
- [ ] Data-driven testing con archivos externos (CSV/Excel).
- [ ] Soporte multi-navegador (Firefox, Edge).
- [ ] Reportes con Allure (alternativa a Serenity).

---

## 👤 Autor

**Hernán Solórzano** — [@Hsolorzano1987](https://github.com/Hsolorzano1987)

---

## 📄 Licencia

Este proyecto está bajo la licencia **ISC**. Consultá el archivo `LICENSE` (si aplica) para más detalles.

---

<p align="center">
  Hecho con ❤️ usando <a href="https://serenity-bdd.github.io/">Serenity BDD</a> + <a href="https://cucumber.io/">Cucumber</a> + <a href="https://junit.org/junit4/">JUnit 4</a>
</p>

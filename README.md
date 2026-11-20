# OrangeHRM Automation Testing Project

## Project Overview

This project is an automated testing framework developed for the OrangeHRM Demo application as part of a Software Testing Graduation Project.

The framework uses Java, Selenium WebDriver, TestNG, and Maven, following the Page Object Model (POM) design pattern.

It supports data-driven testing, parallel test execution, and Allure reporting.

## Technologies Used

- **Java** – Programming language
- **Selenium WebDriver** – Browser automation
- **TestNG** – Test execution and assertions
- **Maven** – Dependency management and build automation
- **Page Object Model (POM)** – Framework design pattern
- **Gson / JSON** – Test data management
- **Allure Report** – Test execution reporting
- **IntelliJ IDEA** – Development environment
- **Git & GitHub** – Version control

## Application Under Test

**OrangeHRM Demo**

https://opensource-demo.orangehrmlive.com/

## Automated Test Scenarios

The framework includes 11 automated test cases covering:

| Module | Test Scenarios |
|---|---|
| Login | Valid login, invalid login, empty fields validation |
| PIM | Employee search, non-existent employee, Add Employee page validation, required fields, employee creation and search |
| Admin | Add User page validation |
| Footer | External footer link validation |
| Sidebar | Navigation menu visibility |

**Total Automated Test Cases: 11**

## Framework Features

- Page Object Model (POM)
- TestNG assertions and test suites
- JSON-based test data
- TestNG DataProvider
- ThreadLocal WebDriver management
- Parallel test execution
- Explicit waits
- Allure HTML reporting
- Maven test execution

## Project Structure

```text
automation-testing-graduation-project/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       └── resources/
│           ├── testdata.json
│           ├── testng.xml
│           └── allure.properties
├── allure-report/
├── pom.xml
├── .gitignore
└── README.md
```

## How to Run the Tests

### Prerequisites

- Java JDK
- Maven
- Google Chrome
- IntelliJ IDEA or another Java IDE

### Steps

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Reload Maven dependencies.
4. Run the tests using Maven.

```bash
mvn clean test
```

Alternatively, run the `test` lifecycle goal from the IntelliJ Maven tool window.

## Allure Test Report

The project includes an Allure HTML report generated from the automated test execution.

**Latest recorded execution results:**

- Total Tests: 11
- Passed: 11
- Failed: 0
- Pass Rate: 100%

To regenerate the report, execute the following Maven goal after running the tests:

```bash
mvn io.qameta.allure:allure-maven:3.1.0:report
```

The report is generated at:

```text
target/site/allure-maven-plugin/index.html
```

A copy of the generated report is included in the `allure-report` directory.

For correct rendering, serve the HTML report through a local web server when necessary.

## Project Status

**Completed — 11 automated test cases successfully executed with a 100% pass rate.**

## Author

Amany

Software Testing Graduation Project

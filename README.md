# AutomationExercise Testing Project

![Maven Build](https://img.shields.io/badge/build-passing-brightgreen)
![Java](https://img.shields.io/badge/Java-25-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-orange)
![Allure](https://img.shields.io/badge/Allure-2.31.0-purple)

---

## Test Execution Results

<img width="1896" height="907" alt="image" src="https://github.com/user-attachments/assets/1c98fc9b-aad4-4bc8-a5e2-c1957027f267" />
<img width="1919" height="864" alt="image" src="https://github.com/user-attachments/assets/c81152da-3ae8-40df-8f00-22b4858c3807" />
<img width="1919" height="912" alt="image" src="https://github.com/user-attachments/assets/d290a693-3b97-40fe-95a2-96ac24a82e6c" />

*This screenshot shows the results of the latest test suite execution including passed, failed, and skipped tests.*

---

## Table of Contents

- [Project Overview](#project-overview)
- [Project Design](#project-design)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Quick Run Commands](#quick-run-commands)
- [Test Strategy](#test-strategy)
- [Allure Reports](#allure-reports)

---

## Project Overview
>
> The purpose of this project is to automate the testing of the **AutomationExercise** website. It includes:
>
> 1. **UI Testing:** Automating web interface interactions such as login, signup, dashboard navigation, and form validations.  
> 2. **API Testing:** Validating backend endpoints for user registration, login, and data consistency.  
> 3. **E2E Testing:** Combining API calls with UI validation to test complete user workflows.  
>
> The project design and test strategies were **referenced from best practices in test automation**, adapted to this framework.  
>
---

## Project Design
>
> This project follows best practices in test automation with the following design principles:  
>
> 1. **Page Object Model (POM):**
>    - Separates UI locators and actions from test logic.  
>    - Makes tests cleaner, reusable, and easier to maintain.  
>
> 3. **Fluent Interface:**  
>    - Methods are designed to allow chaining, making test scripts more readable and expressive.  
>    - Enables writing tests in a natural, step-by-step style.  
>
> 4. **Data-Driven Approach:**  
>     - Test data is stored externally in JSON files.  
>    - Allows running the same test with multiple data sets without changing the code.  
> 
> These design patterns make the framework **scalable, maintainable, and easy to extend** for new tests.  
> 
---

## Tech Stack
> 
> - **Java 25**  
> - **Selenium 4.38.0** – UI automation  
> - **Rest-Assured 6.0.0** – API automation  
> - **TestNG 7.11.0** – Test framework  
> - **Allure 2.31.0** – Test reporting  
> - **Gson 2.13.1 / JsonPath 2.10.0** – JSON handling  
> - **Apache Commons IO 2.21.0** – File handling
> 
---

## Project Structure
> 
> ```text
> .
> ├── src
> │   ├── main/java/pom            # Page Object Model & business logic
> │   │   ├── APIValidators        # Logic for validating API response schemas and bodies
> │   │   └── pages                # Web UI Page Objects (locators and actions)
> │   ├── main/java/utils          # Framework engines & helper classes
> │   │   ├── Framework            # Core drivers, element actions, waits
> │   │   └── HelperClasses        # Business-specific functions (API endpoints, user functions)
> │   ├── main/resources           # Configuration files (Allure, environment, etc.)
> │   └── test/java/pom.tests      # Test suites
> │       ├── APITesting           # Functional API test scripts
> │       ├── E2ETesting           # End-to-end tests (hybrid API + UI)
> │       └── UiTesting            # Pure frontend/UI functional tests
> │   └── test/resources           # Test data
> │       ├── APITestingData       # JSON files for API payloads
> │       ├── E2ETestingData       # Data for end-to-end scenarios
> │       └── UITestingData        # Data for UI-specific test cases
> ├── XMLFiles                     # TestNG suite runners
> ├── pom.xml                      # Maven dependencies and build configuration
> └── target
>     └── allure-report
>         └── index.html           # Generated Allure report
> 
> ```
> 
---

## Setup & Installation
> 
> Follow these steps to get the automation framework running on your local machine:
> 
> ### 1. Prerequisites
>> Ensure you have the following installed:
>> * **Java SDK** (version 11 or higher)
>> * **Maven** (version 3.6+)
>> * **Git**
>> * **Chrome** (or your preferred browser for UI testing)
> 
> ### 2. Clone the Repository
>> Open your terminal and run:
>> ```bash
>>   git clone <repository-url>
>>   cd automationexercise-testing
>> ```
> 
> ### 3. Install Maven dependencies
>> Open your terminal and run:
>> ```bash
>>   mvn clean install
>> ```
> 
> ### 4. Configure environment properties in src/main/resources/environment.properties if needed:
>> Open your terminal and run:
>> ```text
>>   browser=chrome
>>   baseURL=https://automationexercise.com
>> ```
> 
---

## Running Tests
> 
> ### Run All Test Cases
>> ```bash
>>   mvn clean test
>> ```
> This will execute:
>> - API tests
>> - E2E tests (UI with API setup)
>> - UI tests (no setup data)
>> - UI tests with UI setup
> 
---

## Quick Run Commands
> 
>   ### 1. Run Only API Tests:
>>   Open your terminal and run:
>>   ```bash
>>     mvn clean test -DsuiteXmlFile=XMLFiles/APITestCasesRunner.xml
>>   ```
>   
>   ### 2. Run Only E2E Tests:
>>   Open your terminal and run:
>>   ```bash
>>     mvn clean test -DsuiteXmlFile=XMLFiles/E2ETestCasesRunner.xml
>>   ```
>>   
>   ### 3. Run Only UI Tests:
>>   Open your terminal and run:
>>   ```bash
>>     mvn clean test -DsuiteXmlFile=XMLFiles/UITestCasesRunner.xml
>>   ```
>   
>   ### 4. Run UI Tests With UI Setup:
>>   Open your terminal and run:
>>   ```bash
>>     mvn clean test -DsuiteXmlFile=XMLFiles/UITestCasesWithUISetupRunner.xml
>>   ```
> 
---

## Test Strategy
> 
> **Execution Order:**  
>   - API → UI with API setup (E2E) → UI without setup → UI with UI setup
> 
> **Data Handling:**  
>   - API tests prepare data for E2E tests
>   - UI tests may use API-created data or set up their own data
> 
> **Validation Approach:**  
>   - UI: Verify elements, buttons, forms, pages
>   - API: Verify response codes, JSON structure, content
>   - E2E: Ensure workflows complete successfully
> 
> **Error Handling:**  
>    - Positive and negative scenarios validated
> 
---

## Allure Reports
> 
> **Location:** `target/allure-report/index.html`  
> **Generated automatically after TestNG suite execution**
> 

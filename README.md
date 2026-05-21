# Cucumber Playwright Java Project Documentation

## Project Overview
This project is a comprehensive **Behavior-Driven Development (BDD)** testing framework that combines **Cucumber** with **Playwright** for automated testing in Java. It provides a robust framework for testing web applications using Gherkin syntax and Playwright's powerful browser automation capabilities.

## Project Structure
```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           ├── steps          # Cucumber step definitions
│       │           └── hooks          # Cucumber hooks (setup/teardown)
│       └── resources
│           └── features               # Gherkin feature files
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

## Features
- **Cucumber BDD Framework**: Write tests in Gherkin syntax for better readability and collaboration
- **Playwright Automation**: Leverage Playwright for reliable cross-browser testing (Chromium, Firefox, WebKit)
- **JUnit 5 Integration**: Native support for JUnit 5 (Jupiter) assertions and lifecycle management
- **JSON Data Handling**: Jackson integration for handling JSON test data and API responses
- **Parallel Test Execution**: Run tests in parallel using Gradle and JUnit Platform

## Setup Instructions

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Gradle 7.0+ (or use the included `gradlew` wrapper)
- Git

### 1. Clone the Repository
```bash
git clone https://github.com/TruongTheLuan/cucumber-playwright-java.git
cd cucumber-playwright-java
```

### 2. Install Dependencies
The project uses Gradle as the build tool. Dependencies are defined in `build.gradle`:

```bash
./gradlew clean build
```

Or on Windows:
```bash
gradlew.bat clean build
```

### 3. Install Playwright Browsers
```bash
./gradlew playwright --args="install"
```

### 4. Run Tests
To execute all tests:
```bash
./gradlew test
```

## Usage Examples

### Running All Cucumber Tests
Execute all feature files:
```bash
./gradlew test
```

### Running Specific Feature Files
Run tests for a particular feature:
```bash
./gradlew test --args="--features=src/test/resources/features/login.feature"
```

### Running Tests by Tags
Execute tests with specific tags:
```bash
./gradlew test --args="--tags=@smoke"
```

### Running Tests in Headless Mode
By default, Playwright runs in headless mode. To run with browser UI visible:
```bash
./gradlew test -Dheaded=true
```

### Generating Test Reports
Cucumber generates reports automatically. View them after test execution:
```
build/reports/
```

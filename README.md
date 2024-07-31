
 # Selenium WebDriver SQL Injection Test

 This project demonstrates how to use Selenium WebDriver to interact with a sample web application login form and attempt a controlled SQL injection attack. The provided Java code navigates to the OWASP Juice Shop login page, enters a crafted SQL injection string into the username field, submits the form, and analyzes the response for error messages indicative of a failed SQL injection attempt.

 ## Project Setup

 ### Prerequisites

 1. **Java Development Kit (JDK):** Ensure you have JDK 8 or higher installed. You can download it from [Oracle's JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html).

 2. **Integrated Development Environment (IDE):** You can use any Java IDE of your choice. Here are a few options:
    - [Eclipse IDE](https://www.eclipse.org/downloads/)
    - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
    - [NetBeans IDE](https://netbeans.apache.org/download/index.html)

 3. **Apache Maven:** Use Maven for dependency management. Download it from [Apache Maven](https://maven.apache.org/download.cgi) and follow the installation instructions.

 4. **Selenium WebDriver:** You will need the Selenium Java library. Add the following dependency to your `pom.xml` file:

    ```xml
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.8.0</version> <!-- Check for the latest version -->
    </dependency>
    ```

 5. **ChromeDriver:** Download the appropriate version of ChromeDriver from [ChromeDriver Downloads](https://sites.google.com/chromium.org/driver/downloads) and place it in a directory included in your system's PATH or specify its location in the code.

 ### Eclipse IDE Setup

 1. **Install Eclipse IDE:**
    - Download and install Eclipse IDE for Java Developers from [Eclipse IDE Downloads](https://www.eclipse.org/downloads/).

 2. **Create a New Maven Project:**
    - Open Eclipse IDE.
    - Go to `File` > `New` > `Other...`.
    - Select `Maven` > `Maven Project` and click `Next`.
    - Choose the workspace location and click `Next`.
    - Select the `maven-archetype-quickstart` template and click `Next`.
    - Enter `Group Id` and `Artifact Id` (e.g., `com.example` and `selenium-sqli-test`).
    - Click `Finish`.

 3. **Add Selenium Dependency:**
    - Open the `pom.xml` file in your Maven project.
    - Add the Selenium dependency inside the `<dependencies>` tag:

      ```xml
      <dependency>
          <groupId>org.seleniumhq.selenium</groupId>
          <artifactId>selenium-java</artifactId>
          <version>4.8.0</version> <!-- Check for the latest version -->
      </dependency>
      ```

    - Save the `pom.xml` file. Eclipse will automatically download and add the Selenium WebDriver library to your project.

 4. **Add Your Java Code:**
    - Right-click on the `src/main/java` directory in the Project Explorer.
    - Select `New` > `Class`.
    - Name the class `SqlInjectionTest` and click `Finish`.
    - Copy and paste the provided Java code into the `SqlInjectionTest.java` file.

 5. **Configure ChromeDriver:**
    - Download ChromeDriver from [ChromeDriver Downloads](https://sites.google.com/chromium.org/driver/downloads).
    - Place the `chromedriver.exe` file in a directory included in your system's PATH or specify its location in the code by updating the path in the `System.setProperty` method.

 6. **Run the Test:**
    - Right-click on the `SqlInjectionTest.java` file.
    - Select `Run As` > `Java Application`.


 ### Project Structure

```bash
 selenium-sqli-test/
 │
 ├── src/
 │   ├── main/
 │   │   └── java/
 │   │       └── SqlInjectionTest.java
 │
 ├── pom.xml
 └── README.md
```

 ### Code Overview

 - **`SqlInjectionTest.java`**: Contains the Java code to launch the browser, perform the SQL injection test, and analyze the response.

 ### Running the Test

 **Clone the repository**:
    ```bash
    git clone https://github.com/Pramodh9653/selenium-sql-injection.git
    ```

 ### Assumptions

 - The web application at `https://juice-shop.herokuapp.com/#/login` is set up to handle SQL injection attempts in a controlled manner for testing purposes.
 - You have correctly configured your environment and dependencies as per the prerequisites.

 ### Handling Exceptions

 - Ensure that you handle any potential exceptions such as `NoSuchElementException` or `TimeoutException` gracefully in a real-world scenario.
 - Adjust the SQL injection string according to the specific application behavior for better accuracy.

 ### Contributor

 - **T. PRAMODH KUMAR**

 [Back to top](#selenium-webdriver-sql-injection-test)

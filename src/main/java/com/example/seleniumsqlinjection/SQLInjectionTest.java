package com.example.seleniumsqlinjection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class SQLInjectionTest {

    public static void main(String[] args) {
        // Manually set the path to the downloaded ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tammi\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Set ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");

        // Initialize ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);

        Scanner scanner = new Scanner(System.in);

        try {
            // Navigate to the OWASP Juice Shop login page
            String url = "https://juice-shop.herokuapp.com/#/login";
            driver.get(url);

            // Wait for the page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("#/login"));

            // Prompt the user to enter credentials manually
            System.out.println("Please enter your username and password on the login page.");
            System.out.println("After entering the details, press Enter here to continue...");
            scanner.nextLine();  // Wait for the user to press Enter

            // Locate the username and password input fields and get their values
            WebElement usernameField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));

            String enteredUsername = usernameField.getAttribute("value");
            String enteredPassword = passwordField.getAttribute("value");

            // Validate username and password
            if (!isValidUsername(enteredUsername)) {
                System.out.println("Invalid username. It must be a valid email and not contain SQL injection keywords.");
                return;
            }
            if (!isValidPassword(enteredPassword)) {
                System.out.println("Invalid password. It must be at least 8 characters long and contain an uppercase letter, a lowercase letter, a number, and a special character.");
                return;
            }

            System.out.println("Username entered: " + enteredUsername);
            System.out.println("Password entered: " + enteredPassword);

            // Clear the fields
            usernameField.clear();
            passwordField.clear();

            // Enter a crafted malicious username (SQL injection string)
            String maliciousUsername = "' OR '1'='1";
            usernameField.sendKeys(maliciousUsername);

            // Enter the original password
            passwordField.sendKeys(enteredPassword);

            // Locate and submit the login form
            WebElement loginButton = driver.findElement(By.id("loginButton"));
            loginButton.click();

            // Wait for the login response
            WebDriverWait responseWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean loginFailed = false;

            // Check for error message or failed login indicators
            try {
                WebElement errorMessage = responseWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
                System.out.println("SQL injection attempt failed with error: " + errorMessage.getText());
                loginFailed = true;
            } catch (Exception e) {
                // Handle case where "error-message" class is not found
            }

            if (!loginFailed) {
                try {
                    WebElement errorMessage = responseWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Invalid email or password.')]")));
                    System.out.println("SQL injection attempt failed with error: " + errorMessage.getText());
                    loginFailed = true;
                } catch (Exception e) {
                    // Handle case where specific error message is not found
                }
            }

            if (!loginFailed) {
                System.out.println("SQL injection attempt may have succeeded or no error message was displayed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
            scanner.close();
        }
    }

    // Validate the username (email format and no SQL injection keywords)
    private static boolean isValidUsername(String username) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        String[] sqlKeywords = {"'", "\"", ";", "--", "/*", "*/", "OR", "AND"};
        if (!username.matches(emailRegex)) {
            return false;
        }
        for (String keyword : sqlKeywords) {
            if (username.toUpperCase().contains(keyword)) {
                return false;
            }
        }
        return true;
    }

    // Validate the password (at least 8 characters, one uppercase, one lowercase, one number, one special character)
    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(passwordRegex);
    }
}

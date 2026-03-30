package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Thread-safe WebDriver factory using ThreadLocal.
 *
 * One driver instance is created per thread. Since testng.xml runs each
 * browser in its own thread (parallel="tests"), this gives exactly:
 *   Thread 1 → ChromeDriver
 *   Thread 2 → FirefoxDriver
 *   Thread 3 → EdgeDriver
 *
 * The browser name is read from:
 *   1. System property "browser" (set by testng.xml <parameter> via Surefire, or -Dbrowser=xxx on CLI)
 *   2. Default: "chrome"
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static void initDriver() {
        // testng.xml passes <parameter name="browser" value="chrome|firefox|edge"/>
        // Surefire picks it up as a system property automatically
        String browser = System.getProperty("browser", "firefox").trim().toLowerCase();

        WebDriver driver;
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver(new FirefoxOptions());
                break;
            case "edge":
                driver = new EdgeDriver(new EdgeOptions());
                break;
            case "chrome":
            default:
                driver = new ChromeDriver(new ChromeOptions());
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driverThread.set(driver);

        System.out.println("[Thread " + Thread.currentThread().getName()
                + "] Browser launched: " + browser.toUpperCase());
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver not initialised for thread ["
                    + Thread.currentThread().getName() + "]. "
                    + "Ensure @Before hook calls DriverManager.initDriver().");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}
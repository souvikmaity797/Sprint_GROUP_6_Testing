package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utils.DriverManager;
import utils.SessionManager;

/**
 * Cucumber lifecycle hooks – sequential scenarios, parallel browsers.
 *
 * Lifecycle per browser thread:
 *   @Before (first scenario)  → initDriver() + login
 *   @Before (later scenarios) → driver already alive, session active → skip both
 *   @After  (every scenario)  → screenshot on failure only; driver stays OPEN
 *   @AfterAll                 → quit driver once ALL scenarios on this thread are done
 *
 * Result: each browser opens ONCE and closes ONCE.
 *         All 7 scenarios run inside that single browser window.
 */
public class Hooks {

    // ── Open browser once per thread (lazy init) ──────────────────────────────

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        try {
            DriverManager.getDriver();          // already alive → reuse
        } catch (IllegalStateException e) {
            DriverManager.initDriver();         // first scenario on this thread
            SessionManager.reset();
        }
        System.out.println("[Thread " + Thread.currentThread().getName() + "] "
                + "Starting: " + scenario.getName());
    }

    // ── Screenshot on failure (driver still open at this point) ──────────────

    @After(order = 0)
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png",
                        "Failure screenshot: " + scenario.getName());
                System.out.println("[Thread " + Thread.currentThread().getName()
                        + "] FAILED: " + scenario.getName() + " - screenshot captured.");
            } catch (Exception e) {
                System.err.println("Could not capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("[Thread " + Thread.currentThread().getName()
                    + "] PASSED: " + scenario.getName());
        }
        // Driver intentionally NOT quit here — reused by next scenario
    }
    
 

    // ── Quit browser after ALL scenarios on this thread are done ─────────────

    @AfterAll
    public static void closeBrowser() {
        System.out.println("[Thread " + Thread.currentThread().getName()
                + "] All scenarios done - closing browser.");
        DriverManager.quitDriver();
        SessionManager.reset();
    }
}
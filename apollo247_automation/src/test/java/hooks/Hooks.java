package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import driver.Driver;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private WebDriver driver;

    @Before(order = 0) // runs first
    public void setUp() {
        // Initialize the driver if not already done
        driver = Driver.getDriver();
        // Optionally, maximize and set default timeouts here if not in Driver class
        driver.manage().window().maximize();
    }

    @After(order = 0) // runs last
    public void tearDown() {
        // Only quit driver at the very end of all scenarios if desired
        // If you quit here, session will be lost for next scenario
        // Driver.quitDriver();
    }
}



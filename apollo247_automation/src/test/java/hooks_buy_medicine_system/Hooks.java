package hooks_buy_medicine_system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

//    @Before
//    public void setUp() {
//
//        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("user-data-dir=C:/edge-profile-" + Thread.currentThread().getId());
//        tlDriver.set(new EdgeDriver(options));
//        getDriver().manage().window().maximize();
//    }
    
    @Before public void setUp() { 
    	tlDriver.set(new EdgeDriver()); 
    	getDriver().manage().window().maximize(); 
    }

    @After
    public void tearDown() {

        try {
            Thread.sleep(3000);
        } catch (Exception e) {}

        getDriver().quit();
        tlDriver.remove();
    }
}
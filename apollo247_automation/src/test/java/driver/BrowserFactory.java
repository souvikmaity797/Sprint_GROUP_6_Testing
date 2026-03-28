package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                FirefoxOptions options = new FirefoxOptions();
//                options.addPreference("permissions.default.geo", 1);
//                return new FirefoxDriver();
                WebDriverManager.firefoxdriver().setup();

                FirefoxProfile profile = new FirefoxProfile();

                // Auto allow geolocation
                profile.setPreference("permissions.default.geo", 1);

                // Enable geo but stop Firefox asking Mozilla service
                profile.setPreference("geo.enabled", true);
                profile.setPreference("geo.provider.network.url", "");

                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);

                return new FirefoxDriver(options);
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
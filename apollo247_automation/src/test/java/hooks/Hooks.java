package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import driver.Driver;

public class Hooks {

    @Before
    public void setUp() {
        Driver.init();   // ✅ initializes driver
    }

    @After
    public void tearDown() {
        Driver.quit();
    }
}

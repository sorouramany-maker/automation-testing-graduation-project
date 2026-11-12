
package orangehrm.com.base;

import orangehrm.com.utils.ConfigReader;
import orangehrm.com.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");

        WebDriver driver;

        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        DriverManager.setDriver(driver);

        driver.manage().window().maximize();

        driver.get(ConfigReader.getProperty("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        WebDriver driver = DriverManager.getDriver();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DriverManager.removeDriver();
        }
    }
}

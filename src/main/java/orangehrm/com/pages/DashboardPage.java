
package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dashboardHeader =
            By.xpath("//h6[normalize-space()='Dashboard']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public boolean isDashboardDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        dashboardHeader)).isDisplayed();
    }

    public boolean isSidebarMenuDisplayed(String menuName) {

        By menuItem = By.xpath(
                "//aside//span[normalize-space()='" + menuName + "']"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(menuItem)
        ).isDisplayed();
    }

}


package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By footerText =
            By.cssSelector(".oxd-layout-footer");

    private final By orangeHRMLink =
            By.linkText("OrangeHRM, Inc");

    public FooterPage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public void scrollToFooter() {
        WebElement footer = wait.until(
                ExpectedConditions.presenceOfElementLocated(footerText)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", footer
        );
    }

    public String getFooterText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(footerText)
        ).getText();
    }

    public void clickBrandingLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(orangeHRMLink)
        ).click();
    }

    public boolean isOrangeHRMWebsiteOpened() {

        wait.until(
                ExpectedConditions.numberOfWindowsToBe(2)
        );

        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);

            if (driver.getCurrentUrl().contains("orangehrm.com")) {
                return true;
            }
        }

        return false;
    }
}

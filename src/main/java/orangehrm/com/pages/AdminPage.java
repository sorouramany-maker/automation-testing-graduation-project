
package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By adminMenu =
            By.xpath("//span[normalize-space()='Admin']");

    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");

    private final By addUserHeader =
            By.xpath("//h6[normalize-space()='Add User']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public void openAdmin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(adminMenu)
        ).click();
    }

    public void clickAddUser() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();
    }

    public boolean isAddUserPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        addUserHeader)
        ).isDisplayed();
    }

    public boolean isFieldDisplayed(String fieldName) {
        By field = By.xpath(
                "//label[normalize-space()='" + fieldName + "']"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(field)
        ).isDisplayed();
    }
}

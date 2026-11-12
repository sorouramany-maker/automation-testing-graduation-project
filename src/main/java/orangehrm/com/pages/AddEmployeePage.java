
package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.name("firstName");
    private final By lastNameField = By.name("lastName");

    private final By saveButton =
            By.xpath("//button[@type='submit']");

    private final By firstNameRequiredMessage =
            By.xpath("//input[@name='firstName']" +
                    "/ancestor::div[contains(@class,'oxd-input-group')]" +
                    "//span[contains(@class,'oxd-input-field-error-message')]");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public void enterLastName(String lastName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameField)
        ).sendKeys(lastName);
    }

    public void clickSave() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }

    public String getFirstNameRequiredMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameRequiredMessage)
        ).getText();
    }

    public void enterFirstName(String firstName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).sendKeys(firstName);
    }

    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSave();
    }

    public boolean isPersonalDetailsPageDisplayed() {

        By personalDetailsHeader =
                By.xpath("//h6[normalize-space()='Personal Details']");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        personalDetailsHeader)
        ).isDisplayed();
    }

}


package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    private final By invalidCredentialsMessage =
            By.xpath("//*[contains(@class,'oxd-alert-content-text')]");

    private final By requiredMessages =
            By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getInvalidCredentialsMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        invalidCredentialsMessage)).getText();
    }

    public int getRequiredMessagesCount() {
        return wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        requiredMessages, 1)).size();
    }

    public boolean areBothFieldsRequired() {

        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        requiredMessages, 1)
        );

        java.util.List<WebElement> messages =
                driver.findElements(requiredMessages);

        return messages.size() == 2
                && messages.stream()
                .allMatch(message ->
                        message.getText().trim().equals("Required"));
    }

}

package orangehrm.com.pages;
import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class AddEmployeePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By firstNameField = By.name("firstName");
    private final By lastNameField = By.name("lastName");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By firstNameRequiredMessage = By.xpath(
                    "//input[@name='firstName']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//span[contains(@class,'oxd-input-field-error-message')]");
    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By validationMessages = By.cssSelector(".oxd-input-field-error-message");
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait")
        );
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(waitTime)
        );
    }
    public void enterFirstName(String firstName) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameField)
        );
        input.clear();
        input.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField)
        );
        input.clear();
        input.sendKeys(lastName);
    }
    public void clickSave() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
        System.out.println(
                "URL after Save: " + driver.getCurrentUrl()
        );
        List<WebElement> errors =
                driver.findElements(validationMessages);
        System.out.println(
                "Page validation messages: " +
                        errors.stream()
                                .map(WebElement::getText)
                                .toList()
        );
    }
    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSave();
    }
    public String getFirstNameRequiredMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameRequiredMessage)
        ).getText();
    }
    public boolean isPersonalDetailsPageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        personalDetailsHeader)
        ).isDisplayed();
    }
}

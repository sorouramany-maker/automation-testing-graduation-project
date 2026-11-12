package orangehrm.com.pages;

import orangehrm.com.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pimMenu =
            By.xpath("//span[text()='PIM']");

    private final By employeeNameField =
            By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");

    private final By searchButton =
            By.xpath("//button[@type='submit']");

    private final By resultsTable =
            By.cssSelector(".oxd-table-body");

    private final By addEmployeeMenu =
            By.xpath("//a[normalize-space()='Add Employee']");

    private final By firstNameField =
            By.name("firstName");

    private final By lastNameField =
            By.name("lastName");

    public PIMPage(WebDriver driver) {
        this.driver = driver;

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait"));

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(waitTime));
    }

    public void openPIM() {
        wait.until(
                ExpectedConditions.elementToBeClickable(pimMenu)
        ).click();
    }

    // TC04: Search for existing employee
    public void searchEmployee(String employeeName) {

        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        employeeNameField)
        );

        nameInput.clear();
        nameInput.sendKeys(employeeName);

        By employeeOption = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')]"
                        + "[contains(normalize-space(.), '"
                        + employeeName + "')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(employeeOption)
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }

    public boolean isEmployeeDisplayed(String employeeName) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultsTable)
        ).getText().contains(employeeName);
    }

    // TC05: Search for non-existing employee
    public void searchNonExistingEmployee(String employeeName) {

        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        employeeNameField)
        );

        nameInput.clear();
        nameInput.sendKeys(employeeName);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }

    public boolean isNoRecordsFoundDisplayed() {

        By noRecordsMessage =
                By.xpath("//*[normalize-space()='No Records Found']");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        noRecordsMessage)
        ).isDisplayed();
    }

    // TC06: Open Add Employee page
    public void openAddEmployeePage() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addEmployeeMenu)
        ).click();
    }

    public boolean isFirstNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameField)
        ).isDisplayed();
    }
}

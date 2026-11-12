package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.PIMPage;
import orangehrm.com.utils.Auth;
import orangehrm.com.utils.DriverManager;
import orangehrm.com.pages.AddEmployeePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PIMTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeTest() {
        Auth.loginAsAdmin();
    }

    @Test
    public void searchExistingEmployeeTest() {

        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());

        pimPage.openPIM();
        pimPage.searchEmployee("Joy Smith");

        Assert.assertTrue(
                pimPage.isEmployeeDisplayed("Joy"),
                "Employee was not found in search results"
        );
    }

    @Test
    public void searchNonExistingEmployeeTest() {

        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());

        pimPage.openPIM();
        pimPage.searchNonExistingEmployee(
                "ZZZNonExistingEmployee98765"
        );

        Assert.assertTrue(
                pimPage.isNoRecordsFoundDisplayed(),
                "No Records Found message was not displayed"
        );
    }

    @Test
    public void openAddEmployeePageTest() {

        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());

        pimPage.openPIM();
        pimPage.openAddEmployeePage();

        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl()
                        .contains("/pim/addEmployee"),
                "Add Employee page URL is incorrect"
        );

        Assert.assertTrue(
                pimPage.isFirstNameDisplayed(),
                "First Name field is not displayed"
        );

        Assert.assertTrue(
                pimPage.isLastNameDisplayed(),
                "Last Name field is not displayed"
        );
    }

    @Test
    public void addEmployeeWithEmptyFirstNameTest() {

        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());

        AddEmployeePage addEmployeePage =
                new AddEmployeePage(DriverManager.getDriver());

        // Step 1: Open PIM
        pimPage.openPIM();

        // Step 2: Open Add Employee page
        pimPage.openAddEmployeePage();

        // Step 3: Leave First Name empty
        // Enter Last Name only
        addEmployeePage.enterLastName("TestEmployee");

        // Step 4: Click Save
        addEmployeePage.clickSave();

        // Step 5: Verify Required validation
        Assert.assertEquals(
                addEmployeePage.getFirstNameRequiredMessage(),
                "Required",
                "Required validation is not displayed under First Name"
        );
    }

    @Test
    public void addEmployeeSuccessfullyTest() {

        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());

        AddEmployeePage addEmployeePage =
                new AddEmployeePage(DriverManager.getDriver());

        // Generate unique employee name
        String firstName = "Auto" + System.currentTimeMillis();
        String lastName = "Test";

        // Open Add Employee page
        pimPage.openPIM();
        pimPage.openAddEmployeePage();

        // Add employee
        addEmployeePage.addEmployee(firstName, lastName);

        // Verify Personal Details page
        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsPageDisplayed(),
                "Personal Details page was not displayed"
        );

        // Navigate back to Employee List
        pimPage.openPIM();

        // Search for the newly added employee
        pimPage.searchEmployee(firstName + " " + lastName);

        // Verify employee appears in results
        Assert.assertTrue(
                pimPage.isEmployeeDisplayed(firstName),
                "New employee was not found in Employee List"
        );
    }

}

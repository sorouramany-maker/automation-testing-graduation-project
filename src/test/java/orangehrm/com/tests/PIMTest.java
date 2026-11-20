package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.PIMPage;
import orangehrm.com.utils.Auth;
import orangehrm.com.utils.DriverManager;
import orangehrm.com.pages.AddEmployeePage;
import orangehrm.com.dataproviders.TestDataProvider;
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

        AddEmployeePage addEmployeePage =
                new AddEmployeePage(DriverManager.getDriver());

        // Generate unique employee name
        String firstName = "Auto" + java.util.UUID
                .randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);

        String lastName = "SearchTest";
        pimPage.openPIM();
        pimPage.openAddEmployeePage();
        addEmployeePage.addEmployee(firstName, lastName);
        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsPageDisplayed(),
                "Employee creation failed"
        );
        pimPage.openPIM();
        pimPage.searchEmployee(firstName + " " + lastName);
        Assert.assertTrue(
                pimPage.isEmployeeDisplayed(firstName),
                "Employee was not found: "
                        + firstName + " " + lastName
        );
    }
    @Test(
            dataProvider = "nonExistingEmployeeData",
            dataProviderClass = TestDataProvider.class
    )
    public void searchNonExistingEmployeeTest(String employeeName) {
        PIMPage pimPage =
                new PIMPage(DriverManager.getDriver());
        pimPage.openPIM();
        pimPage.searchNonExistingEmployee(employeeName);
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
        pimPage.openPIM();
        pimPage.openAddEmployeePage();
        addEmployeePage.enterLastName("TestEmployee");
        addEmployeePage.clickSave();
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
        String firstName = "Auto" + System.currentTimeMillis();
        String lastName = "Test";
        pimPage.openPIM();
        pimPage.openAddEmployeePage();
        addEmployeePage.addEmployee(firstName, lastName);
        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsPageDisplayed(),
                "Personal Details page was not displayed"
        );
        pimPage.openPIM();
        pimPage.searchEmployee(firstName + " " + lastName);
        Assert.assertTrue(
                pimPage.isEmployeeDisplayed(firstName),
                "New employee was not found in Employee List"
        );
    }
}

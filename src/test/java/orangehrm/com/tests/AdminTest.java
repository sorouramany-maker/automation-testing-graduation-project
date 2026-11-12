
package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.AdminPage;
import orangehrm.com.utils.Auth;
import orangehrm.com.utils.DriverManager;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeTest() {
        Auth.loginAsAdmin();
    }

    @Test
    public void verifyAddUserPageTest() {

        AdminPage adminPage =
                new AdminPage(DriverManager.getDriver());

        // Navigate to Admin
        adminPage.openAdmin();

        // Open Add User form
        adminPage.clickAddUser();

        // Verify Add User page
        Assert.assertTrue(
                adminPage.isAddUserPageDisplayed(),
                "Add User page was not displayed"
        );

        // Verify required fields
        String[] fields = {
                "User Role",
                "Employee Name",
                "Username",
                "Password"
        };

        for (String field : fields) {
            Assert.assertTrue(
                    adminPage.isFieldDisplayed(field),
                    field + " field is not displayed"
            );
        }
    }
}

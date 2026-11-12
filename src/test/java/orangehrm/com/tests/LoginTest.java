
package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.LoginPage;
import orangehrm.com.pages.DashboardPage;
import orangehrm.com.utils.DriverManager;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        DashboardPage dashboardPage =
                new DashboardPage(DriverManager.getDriver());

        // Step 1: Login with valid credentials
        loginPage.login("Admin", "admin123");

        // Step 2: Verify Dashboard URL
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl()
                        .contains("/dashboard/index"),
                "User was not redirected to Dashboard"
        );

        // Step 3: Verify Dashboard header
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard header is not displayed"
        );
    }

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        // Login with invalid credentials
        loginPage.login("WrongUser", "WrongPassword123");

        // Verify error message
        Assert.assertEquals(
                loginPage.getInvalidCredentialsMessage(),
                "Invalid credentials",
                "Incorrect error message displayed"
        );
    }

    @Test
    public void emptyFieldsLoginTest() {

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        // Click Login without entering credentials
        loginPage.clickLogin();

        // Verify both fields show Required validation

        Assert.assertTrue(
                loginPage.areBothFieldsRequired(),
                "Both username and password must show Required validation"
        );
            }

}

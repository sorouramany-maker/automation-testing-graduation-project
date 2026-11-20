
package orangehrm.com.tests;
import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.LoginPage;
import orangehrm.com.pages.DashboardPage;
import orangehrm.com.utils.DriverManager;
import orangehrm.com.dataproviders.TestDataProvider;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest {
    // TC01 - Valid Login
    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = TestDataProvider.class
    )
    public void validLoginTest(String username, String password) {
        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        DashboardPage dashboardPage =
                new DashboardPage(DriverManager.getDriver());

        loginPage.login(username, password);

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10)
        );
        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.urlContains("/dashboard/index")
                ),
                "User was not redirected to Dashboard"
        );
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard header is not displayed"
        );
    }
    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = TestDataProvider.class
    )
    public void invalidLoginTest(String username, String password) {

        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        loginPage.login(username, password);

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
        loginPage.clickLogin();
        Assert.assertTrue(
                loginPage.areBothFieldsRequired(),
                "Both username and password must show Required validation"
        );
    }
}

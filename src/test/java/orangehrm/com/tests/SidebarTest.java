
package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.DashboardPage;
import orangehrm.com.utils.Auth;
import orangehrm.com.utils.DriverManager;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SidebarTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeTest() {
        Auth.loginAsAdmin();
    }

    @Test
    public void verifySidebarMenuTest() {

        DashboardPage dashboardPage =
                new DashboardPage(DriverManager.getDriver());

        String[] expectedMenus = {
                "Admin",
                "PIM",
                "Leave",
                "Time",
                "Recruitment",
                "My Info",
                "Performance",
                "Dashboard",
                "Directory"
        };

        for (String menu : expectedMenus) {
            Assert.assertTrue(
                    dashboardPage.isSidebarMenuDisplayed(menu),
                    menu + " is not displayed in the sidebar"
            );
        }
    }
}

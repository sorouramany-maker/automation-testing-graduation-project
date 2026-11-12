

package orangehrm.com.utils;

import orangehrm.com.pages.LoginPage;

public class Auth {

    public static void loginAsAdmin() {
        LoginPage loginPage =
                new LoginPage(DriverManager.getDriver());

        loginPage.login("Admin", "admin123");
    }
}

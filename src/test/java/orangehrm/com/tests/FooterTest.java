
package orangehrm.com.tests;

import orangehrm.com.base.BaseTest;
import orangehrm.com.pages.FooterPage;
import orangehrm.com.utils.Auth;
import orangehrm.com.utils.DriverManager;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeTest() {
        Auth.loginAsAdmin();
    }

    @Test
    public void verifyFooterBrandingLinkTest() {

        FooterPage footerPage =
                new FooterPage(DriverManager.getDriver());

        // Scroll to footer
        footerPage.scrollToFooter();

        // Verify footer text
        Assert.assertTrue(
                footerPage.getFooterText().contains("OrangeHRM, Inc"),
                "OrangeHRM branding text is not displayed"
        );

        // Click branding link
        footerPage.clickBrandingLink();

        // Verify new tab URL
        Assert.assertTrue(
                footerPage.isOrangeHRMWebsiteOpened(),
                "OrangeHRM website was not opened in new tab"
        );
    }
}

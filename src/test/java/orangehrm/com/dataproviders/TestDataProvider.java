package orangehrm.com.dataproviders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestDataProvider {

    private static JsonObject readTestData() {

        InputStream input = TestDataProvider.class
                .getClassLoader()
                .getResourceAsStream("testdata.json");

        if (input == null) {
            throw new RuntimeException(
                    "testdata.json file not found"
            );
        }

        try (InputStreamReader reader =
                     new InputStreamReader(
                             input, StandardCharsets.UTF_8)) {

            return JsonParser.parseReader(reader)
                    .getAsJsonObject();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to read testdata.json", e
            );
        }
    }

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {

        JsonObject login = readTestData()
                .getAsJsonObject("validLogin");

        return new Object[][]{
                {
                        login.get("username").getAsString(),
                        login.get("password").getAsString()
                }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {

        JsonObject login = readTestData()
                .getAsJsonObject("invalidLogin");

        return new Object[][]{
                {
                        login.get("username").getAsString(),
                        login.get("password").getAsString()
                }
        };
    }

    @DataProvider(name = "existingEmployeeData")
    public static Object[][] existingEmployeeData() {

        String employeeName = readTestData()
                .getAsJsonObject("employees")
                .get("existingEmployee")
                .getAsString();

        return new Object[][]{
                {employeeName}
        };
    }

    @DataProvider(name = "nonExistingEmployeeData")
    public static Object[][] nonExistingEmployeeData() {

        String employeeName = readTestData()
                .getAsJsonObject("employees")
                .get("nonExistingEmployee")
                .getAsString();

        return new Object[][]{
                {employeeName}
        };
    }

    @DataProvider(name = "newEmployeeData")
    public static Object[][] newEmployeeData() {

        JsonObject employee = readTestData()
                .getAsJsonObject("employees")
                .getAsJsonObject("newEmployee");

        return new Object[][]{
                {
                        employee.get("firstName").getAsString(),
                        employee.get("lastName").getAsString()
                }
        };
    }
}

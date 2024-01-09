package testrunners;

import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginCredPage;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends PageSetup {
    LoginCredPage loginPage;
    @Test(priority = 1)
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage = new LoginCredPage(driver);
        String username = Utils.getUserFromJsonArray().get("username").toString();
        String password = Utils.getUserFromJsonArray().get("password").toString();
        loginPage.doLogin(username, password);
        String textActual = driver.findElement(By.xpath("//h6[text()=\"Dashboard\"]")).getText();
        String textExpected = "Dashboard";
        Assert.assertEquals(textActual, textExpected);
    }
}

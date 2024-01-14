package testrunners;

import config.PageSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginCredPage;
import pages.PIMModulePage;

public class SearchTestRunner extends PageSetup {

    PIMModulePage pimModule;
    LoginCredPage loginPage;
    @BeforeTest
    public void doLoginWithValidCreds() {
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("admin", "admin123");
    }
    @Test
    public void searchByID() {



    }
}

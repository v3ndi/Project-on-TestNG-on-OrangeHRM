import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class TestRunnerForLoginPage extends PageSetup{

    LoginCred loginPage;
    @Test(priority = 1)
    public void doLoginWithWrongUser(){
        loginPage = new LoginCred(driver);
        loginPage.doLogin("abmin","admin123");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected = "Invalid credentials";
        Assert.assertEquals(textActual, textExpected);
    }

    @Test(priority = 2)
    public void doLoginWithWrongPassword(){
        loginPage = new LoginCred(driver);
        loginPage.doLogin("admin","admin123wqert");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected = "Invalid credentials";
        Assert.assertEquals(textActual, textExpected);
    }

    @Test(priority = 3)
    public void doLoginWithValidCreds() {
        loginPage = new LoginCred(driver);
        loginPage.doLogin("admin", "admin123");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        String textActual = driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText();
        String textExpected = "Dashboard";
        Assert.assertEquals(textActual, textExpected);
    }
    @Test (priority = 4)
    public void logOut(){
        loginPage = new LoginCred(driver);
        loginPage.dologOut();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }
}

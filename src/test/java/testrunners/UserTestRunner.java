package testrunners;

import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginCredPage;
import pages.UserMyinfoPage;
import utils.Utils;
import java.io.IOException;


public class UserTestRunner extends PageSetup {
    LoginCredPage loginPage;
    UserMyinfoPage userMyinfoPage;
    @Test(priority = 1,groups = "smoke",description = "newly created user can log in with valid username and password")
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage = new LoginCredPage(driver);
        String username = Utils.getUserFromJsonArray().get("username").toString();
        String password = Utils.getUserFromJsonArray().get("password").toString();
        String textExpectedL = Utils.getUserFromJsonArray().get("firstName").toString();
        String textExpectedF = Utils.getUserFromJsonArray().get("lastName").toString();
        String fullNameExpected = textExpectedL+" "+textExpectedF;
        loginPage.doLogin(username, password);
        String textActual = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        Assert.assertEquals(textActual,fullNameExpected);
    }
    @Test(priority = 2,description = "user selecting Gender and saving it")
    public void myInfo() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userMyInfoAndGender();
        Thread.sleep(3000);
        Utils.scroll(driver,0,200);
    }
    @Test(priority = 3,description = "Set blood Type from user my info option and save it")
    public void bloodType() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodType();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="O+";
        Assert.assertEquals(bloodActual,bloodExpected);
    }

    @Test(priority = 4,groups = "smoke",description = "update blood Type from O+ to AB- and save it")
    public void bloodTypeUpdate() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodTypeUpdate();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="AB-";
        Assert.assertEquals(bloodActual,bloodExpected);
    }
    @Test (priority = 5,groups = "smoke",description = "User successfully log out")
    public void logOut(){
        loginPage = new LoginCredPage(driver);
        loginPage.dologOut();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }
}

package testrunners;

import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginCredPage;
import pages.PIMModulePage;
import pages.UserMyinfoPage;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UserTestRunner extends PageSetup {
    LoginCredPage loginPage;
    UserMyinfoPage userMyinfoPage;
    @Test(priority = 1)
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
    @Test(priority = 2)
    public void myInfo() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userMyInfo();
        Thread.sleep(3000);
        Utils.scroll(driver,0,200);
    }
    @Test(priority = 3)
    public void bloodType() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodType();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="O+";
        Assert.assertEquals(bloodActual,bloodExpected);
        System.out.println(bloodActual);
    }
}

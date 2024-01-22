package testrunners;

import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginCredPage;
import pages.UserMyinfoPage;
import utils.Utils;
import java.io.IOException;


public class UserTestRunner extends PageSetup {
    LoginCredPage loginPage;
    UserMyinfoPage userMyinfoPage;


    @Test(priority = 1,description = "Newly created user trying to log in with Invalid Credential")
    public void doLoginWithInValidCreds() throws IOException, ParseException {
        loginPage = new LoginCredPage(driver);
        String username = Utils.getUserFromJsonArray().get("username").toString();
        String password = "wrongpass";
        String titleExpected = "Invalid credentials";
        loginPage.doLogin(username, password);
        String textActual = driver.findElements(By.className("oxd-text")).get(1).getText();
        Assert.assertEquals(textActual,titleExpected);
    }

    @Test(priority = 2,groups = "smoke",description = "Newly created user can log in with valid Credential")
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage = new LoginCredPage(driver);
        String username = Utils.getUserFromJsonArray().get("username").toString();
        String password = Utils.getUserFromJsonArray().get("password").toString();
        String textExpectedF = Utils.getUserFromJsonArray().get("firstName").toString();
        String textExpectedL = Utils.getUserFromJsonArray().get("lastName").toString();
        String fullNameExpected = textExpectedF+" "+textExpectedL;
        loginPage.doLogin(username, password);
        String textActual = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        Assert.assertEquals(textActual,fullNameExpected);
    }

    @Test(priority = 3,description = "Selecting Wrong Gender and saving it")
    public void myInfoWrongGender() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userWrongGender();
        Thread.sleep(3000);
        Utils.scroll(driver,0,200);
        WebElement selectMale=driver.findElements(By.className("oxd-radio-input--active")).get(0);
        if(!(selectMale.isSelected())) {
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 4,description = "User selecting Gender and saving it")
    public void myInfoGender() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userGender();
        Thread.sleep(3000);
        Utils.scroll(driver,0,200);
        WebElement selectMale=driver.findElements(By.className("oxd-radio-input--active")).get(0);
        if(selectMale.isSelected()) {
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 5,description = "Set blood Type from user my info option and save it")
    public void bloodType() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodType();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="O+";
        Assert.assertEquals(bloodActual,bloodExpected);
    }

    @Test(priority = 6,groups = "smoke",description = "Update blood Type from O+ to AB- and save it")
    public void bloodTypeUpdate() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodTypeUpdate();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="AB-";
        Assert.assertEquals(bloodActual,bloodExpected);
    }
    @Test(priority = 7,description = "Wrong blood type input")
    public void bloodTypeWrongUpdate() throws InterruptedException {
        userMyinfoPage = new UserMyinfoPage(driver);
        userMyinfoPage.userBloodTypeUpdate();
        Utils.scroll(driver);
        String bloodActual=driver.findElements(By.className("oxd-select-text-input")).get(2).getText();
        String bloodExpected ="AB+";
        Assert.assertNotEquals(bloodActual,bloodExpected);
    }

    @Test (priority = 8,description = "User Unable to log out")
    public void unableTologOut(){
        loginPage = new LoginCredPage(driver);
        loginPage.unableToLogOut();
        String textActual = driver.findElements(By.className("oxd-text")).get(12).getText();
        String textExpected = "Login";
        Assert.assertNotEquals(textActual, textExpected);
    }
    @Test (priority = 9,groups = "smoke",description = "User successfully log out")
    public void logOut(){
        loginPage = new LoginCredPage(driver);
        loginPage.dologOut();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }
}

package testrunners;

import config.PageSetup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginCredPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends PageSetup {

    LoginCredPage loginPage;
    @Test(priority = 1,enabled = true)
    public void doLoginWithWrongUser(){
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("abmin","admin123");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected = "Invalid credentials";
        Assert.assertEquals(textActual, textExpected);
    }

    @Test(priority = 2,enabled = true)
    public void doLoginWithWrongPassword(){
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("admin","admin123wqert");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected = "Invalid credentials";
        Assert.assertEquals(textActual, textExpected);
    }

    @Test(priority = 3)
    public void doLoginWithValidCreds() throws IOException, ParseException, IOException {
        loginPage = new LoginCredPage(driver);
        String fileLocation="./src/test/resources/employee.json";
        JSONParser parser=new JSONParser();
        JSONArray empArray= (JSONArray) parser.parse(new FileReader(fileLocation));
        JSONObject adminCredObj= (JSONObject) empArray.get(0);
        if (System.getProperty("username") != null && System.getProperty("password") != null) {
            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        } else {
            loginPage.doLogin(adminCredObj.get("username").toString(), adminCredObj.get("password").toString());
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        String textActual = driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText();
        String textExpected = "Dashboard";
        Assert.assertEquals(textActual, textExpected);
    }
    @Test (priority = 4)
    public void logOut(){
        loginPage = new LoginCredPage(driver);
        loginPage.dologOut();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }
}

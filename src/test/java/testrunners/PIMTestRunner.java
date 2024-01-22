package testrunners;

import com.github.javafaker.Faker;
import config.EmpModel;
import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginCredPage;
import pages.PIMModulePage;
import utils.Utils;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
public class PIMTestRunner extends PageSetup {


    PIMModulePage pimModule;
    LoginCredPage loginPage;


    @BeforeTest(groups = "smoke",description = "log in as admin")
    public void doLoginWithValidCreds() {
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("admin", "admin123");
    }
    public String generateRandomPassword() {
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numericChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
        String allChars = upperCaseChars + lowerCaseChars + numericChars + specialChars + numericChars;
        return RandomStringUtils.random(12, allChars);

    }
    @Test(priority = 1,description="Can not create User without firstname and lastname")
    public void canNotCreateUser() throws InterruptedException {
        pimModule = new PIMModulePage(driver);
        pimModule.notCreatingUser();
        String actualRequiredField = "Required";
        String expectedRequiredField = driver.findElements(By.className("oxd-text")).get(15).getText();
        Assert.assertEquals(actualRequiredField,expectedRequiredField);
    }
    @Test(priority = 2,description="Admin creating User successfully")
    public void pimCreateUser() throws IOException, ParseException, InterruptedException {

        pimModule = new PIMModulePage(driver);

        Faker faker = new Faker();
        String firstName=faker.name().firstName();
        String lastName= faker.name().lastName();
        String username=faker.name().username();
        String password=generateRandomPassword();

        pimModule.filUp(firstName,lastName,username,password);
        String employeeId = pimModule.getEmployeeID();
        String titleTextActual=driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String titleTextExpected = "Personal Details";
        Assert.assertEquals(titleTextActual,titleTextExpected);
        Thread.sleep(3000);

        EmpModel empModel =new EmpModel();
        empModel.setFirstName(firstName);
        empModel.setLastName(lastName);
        empModel.setUsername(username);
        empModel.setPassword(password);
        empModel.setEmployeeId(employeeId);
        Utils.saveUsers(empModel);
    }
    @Test(priority = 3,groups = "smoke",description="After creating user, admin searching a user by employee ID")
    public void searchEmployeeById() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String employeeIdJson = Utils.getUserFromJsonArray().get("employeeId").toString();
        pimModule.searchEmployeeById(employeeIdJson);
        Utils.scroll(driver,0,80);
        Thread.sleep(1000);
        String titleTextExpected=driver.findElements(By.className("oxd-padding-cell")).get(10).getText();
        Assert.assertEquals(employeeIdJson,titleTextExpected);
        Thread.sleep(2000);
    }

    @Test(priority = 4,description="Searching a user by Wrong employee ID")
    public void searchEmployeeByWrongId() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String employeeIdJson = "16615";
        pimModule.searchEmployeeById(employeeIdJson);
        Utils.scroll(driver,0,80);
        Thread.sleep(1000);
        String actualTittle="No Records Found";
        String titleTextExpected=driver.findElements(By.className("oxd-text")).get(14).getText();
        Assert.assertEquals(actualTittle,titleTextExpected);
        Thread.sleep(2000);
    }

    @Test(priority = 5,description="After creating user, admin searching a user by username")
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String firstNameActualUser=Utils.getUserFromJsonArray().get("firstName").toString();
        pimModule.searchEmployeeByName(firstNameActualUser);
        Thread.sleep(2000);
        String nameTitleExpected=driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Assert.assertTrue(nameTitleExpected.startsWith(firstNameActualUser));
    }

    @Test(priority = 6,description="Admin searching a user by Wrong username")
    public void searchEmployeeByWrongName() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String firstWrongName="aedwfrsaewfrs";
        pimModule.searchEmployeeByName(firstWrongName);
        String actualTitle="Invalid";
        Thread.sleep(2000);
        String nameTitleExpected=driver.findElements(By.className("oxd-text")).get(14).getText();
        Assert.assertTrue(nameTitleExpected.startsWith(actualTitle));
    }

    @Test (priority = 7,groups = "smoke",description = "Admin successfully log out")
    public void logOut(){
        loginPage = new LoginCredPage(driver);
        loginPage.dologOut();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected = "Login";
        Assert.assertEquals(textActual, textExpected);
    }

}

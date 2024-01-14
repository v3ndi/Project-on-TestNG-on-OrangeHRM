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

public class PIMTestRunner extends PageSetup {


    PIMModulePage pimModule;
    LoginCredPage loginPage;


    @BeforeTest
    public void doLoginWithValidCreds() {
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("admin", "admin123");
    }

    @Test(priority = 1)
    public void pimCreateUser() throws IOException, ParseException, InterruptedException {

        pimModule = new PIMModulePage(driver);
        Faker faker = new Faker();


        String firstName=faker.name().firstName();
        String lastName= faker.name().lastName();
        String username=faker.name().username();
        String password="P@ssword123";

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
    @Test(priority = 2)
    public void searchEmployeeById() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String employeeIdJson = Utils.getUserFromJsonArray().get("employeeId").toString();
        pimModule.searchEmployeeById(employeeIdJson);
        String titleTextExpected=driver.findElements(By.className("oxd-table-cell")).get(1).getText();

        Thread.sleep(3000);
        Utils.scroll(driver,0,150);
        Assert.assertEquals(titleTextExpected, employeeIdJson);
    }
    @Test(priority = 3)
    public void searchEmployeeByName() throws IOException, ParseException, InterruptedException {
        pimModule = new PIMModulePage(driver);
        String firstNameActualUser=Utils.getUserFromJsonArray().get("firstName").toString();
        pimModule.searchEmployeeByName(firstNameActualUser);
        Thread.sleep(3000);
        String nameTitleExpected=driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Assert.assertTrue(nameTitleExpected.startsWith(firstNameActualUser));
    }


}

package testrunners;

import com.github.javafaker.Faker;
import config.EmpModel;
import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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

    @Test
    public void pimCreateUser() throws IOException, ParseException {

        pimModule = new PIMModulePage(driver);
        Faker faker = new Faker();


        String firstName=faker.name().firstName();
        String lastName= faker.name().lastName();
        String username=faker.name().username();
        String password="P@ssword123";

        pimModule.filUp(firstName,lastName,username,password);
        String employeeId = pimModule.getEmployeeID();
//        System.out.println("Retrieved Employee ID: " + employeeId);

        String titleTextActual=driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String titleTextExpected = "Personal Details";
        Assert.assertEquals(titleTextActual,titleTextExpected);



        EmpModel empModel =new EmpModel();
        empModel.setFirstName(firstName);
        empModel.setLastName(lastName);
        empModel.setUsername(username);
        empModel.setPassword(password);
        empModel.setEmployeeId(employeeId);
        Utils.saveUsers(empModel);
    }

}

package testrunners;

import com.github.javafaker.Faker;
import config.PageSetup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginCredPage;
import pages.PIMModulePage;
import utils.Utils;

import java.io.IOException;

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
        pimModule.filUp(firstName,lastName,"0327",username,password); // er jonno onno arekta perameter kivabe nibo?

        String titleTextActual=driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String titleTextExpected = "Personal Details";
        Assert.assertEquals(titleTextActual,titleTextExpected);


        Utils.saveUsers(firstName,lastName,username,password);
    }

}

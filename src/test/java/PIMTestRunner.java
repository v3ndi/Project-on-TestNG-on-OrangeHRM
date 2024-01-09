import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class PIMTestRunner extends PageSetup{
    PIMModule pimModule;
    LoginCred loginPage;

    @BeforeTest
    public void doLoginWithValidCreds() {
        loginPage = new LoginCred(driver);
        loginPage.doLogin("admin", "admin123");
    }

    @Test
    public void pimCreateUser(){
        pimModule = new PIMModule(driver);
        pimModule.filUp("tariqul","islam","Shourav","vendi","P@ssword123");
        String titleTextActual=driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]")).getText();
        String titleTextExpected = "Personal Details";
        Assert.assertEquals(titleTextActual,titleTextExpected);
    }
}

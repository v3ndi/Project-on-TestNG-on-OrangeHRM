package testrunners;

import config.PageSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginCredPage;

public class SearchTestRunner extends PageSetup {

    LoginCredPage loginPage;
    @BeforeTest
    public void doLoginWithValidCreds() {
        loginPage = new LoginCredPage(driver);
        loginPage.doLogin("admin", "admin123");
    }
    @Test
    public void searchByID() {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        WebElement textCalendar = driver.findElement(By.id("datePickerMonthYearInput"));
        textCalendar.click();
        textCalendar.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        textCalendar.sendKeys("02/10/2024");
        textCalendar.sendKeys(Keys.ENTER);
    }
}
